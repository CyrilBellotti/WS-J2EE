/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.paymentmgmt.facade;

import com.bank.paymentmgmt.domain.Payment;
import com.bank.paymentmgmt.integration.PaymentDAO;
import java.io.StringWriter;
import java.util.List;
import java.util.logging.*;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jws.WebService;
import javax.xml.bind.*;

/**
 *
 * @author cesi
 */
@Stateless
@WebService(
endpointInterface = "com.bank.paymentmgmt.facade.BankingServiceEndpointInterface",
portName = "BankingPort", 
serviceName = "BankingService"
 )
public class BankingServiceBean implements BankingServiceEndpointInterface, BankingServiceRemote {
    
    @Inject
    private PaymentDAO paymentDAO;
    
    @Inject //paquetage javax.inject
    private JMSContext context; //paquetage javax.jms
    
    @Resource(lookup = "jms/paymentQueue") //paquetage javax.annotation
    private Queue paymentQueue; //paquetage javax.jms


    @Override
    public Boolean createPayment(String ccNumber, Double amount) {
       if(ccNumber.length()== 10 ){   
            System.out.println("Montant payé : "+amount +" €");
            Payment p = new Payment();
            p.setCcNumber(ccNumber);
            p.setAmount(amount);
            p = paymentDAO.add(p);
            //juste pour tester
            //paymentDAO.getAllStoredPayments();
            sendPayment(p);//envoi du paiement sous forme de message JMS formaté en XML
            return true;
        } else {
	     return false;
	}
    }
    
    //méthodes déclarées dans BankingServiceRemote
    
    @Override
    public List<Payment> lookupAllStoredPayments() {
        return paymentDAO.getAllStoredPayments();
    }

    @Override
    public Payment lookupStoredPayment(Long id) {
        return paymentDAO.find(id);
    }

    @Override
    public Payment deleteStoredPayment(Long id) {
        Payment p = paymentDAO.delete(id);
        if(p!=null){
            sendPayment(p);
        }
        return p;
    }
    
    private void sendPayment(Payment payment){
        //utilisation de l'API JAX-B de gestion de flux pour marshaller (transformer) l'objet Payment en chaine XML
        JAXBContext jaxbContext;
        try {
            //obtention d'une instance JAXBContext associée au type Payment annoté avec JAX-B
            jaxbContext = JAXBContext.newInstance(Payment.class);
            //création d'un Marshaller pour transfomer l'objet Java en flux XML
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            StringWriter writer = new StringWriter();
            
            //transformation de l'objet en flux XML stocké dans un writer
            jaxbMarshaller.marshal(payment, writer);
            String xmlMessage = writer.toString();
            //affichage du XML dans la console de sortie
            System.out.println(xmlMessage);
            //encapsulation du paiement au format XML dans un objet javax.jms.TextMessage
            TextMessage msg = context.createTextMessage(xmlMessage);
            
            //envoi du message dans la queue paymentQueue
            context.createProducer().send(paymentQueue, msg);

        } catch (JAXBException ex) {
            Logger.getLogger(BankingServiceBean.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
       
}
