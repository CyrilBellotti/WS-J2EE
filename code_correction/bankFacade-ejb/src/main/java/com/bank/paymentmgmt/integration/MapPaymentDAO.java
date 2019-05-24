/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.paymentmgmt.integration;

import com.bank.paymentmgmt.domain.Payment;
import com.bank.paymentmgmt.domain.PaymentStatus;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author cesi
 */
@ApplicationScoped
public class MapPaymentDAO implements PaymentDAO {
    
    //compteur initialisé à 1
    private AtomicLong count = new AtomicLong(1);
    private Map<Long,Payment> payments = new ConcurrentHashMap<>();
    
    //stockage d'un ordre de paiement dans une Map
    @Override
    public Payment add(Payment payment){
        payment.setId(count.getAndIncrement());//on génère l'id
        payment.setStatus(PaymentStatus.VALIDATED);
        //on stocke l'ordre de paiement dans la Map - la clé est l'id.
        payments.put(payment.getId(), payment);
        return payment;
    }
    
    //suppression d'un ordre de paiement
    @Override
    public Payment delete (Long id){
        Payment deletedPayment = payments.remove(id);//on supprime de l'entrée correspondant à l'id passé
        if(deletedPayment == null){
            return null;
        }
        deletedPayment.setStatus(PaymentStatus.CANCELLED);
        return deletedPayment;
    }
    
    //recherche d'un paiement en fonction de son id 
    @Override
    public Payment find(Long id) {
        return payments.get(id);//récupération dans la  Map de l'objet Payment associé à la clé 
    }
    
    
    //obtention d'une liste contenant les paiements créés non supprimés.
    @Override
    public List<Payment> getAllStoredPayments(){
        List<Payment> paymentList = new ArrayList<>(payments.values());
        
        //Boucle pour tracer la liste - pourra être supprimée par la suite
        for(Payment p : paymentList){
            System.out.println(p);
        }
        
        return paymentList;
    }

}
