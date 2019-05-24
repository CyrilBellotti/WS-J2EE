/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.paymentmgmt.logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author cesi
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/paymentQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PaymentProcessor implements MessageListener {
    
    public PaymentProcessor() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            //on extrait le paiment du corps du message. - getBody est une méthode JMS 2.0
            String paymentMessage = message.getBody(String.class);
            for(int i = 0; i<=40;i++){
                System.out.println("[traitement long d'integration dans le processus bancaire de]");
                System.out.println(paymentMessage);
            }
            System.out.println("l'ordre de paiement "+paymentMessage+" va être retiré de la queue");
        } catch (JMSException ex) {
            Logger.getLogger(PaymentProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
