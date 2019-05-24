/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.paymentmgmt.integration;

import com.bank.paymentmgmt.domain.Payment;
import java.util.List;

/**
 *
 * @author cesi
 */
public interface PaymentDAO {

    //stockage d'un ordre de paiement dans une Map
    Payment add(Payment payment);

    //suppression d'un ordre de paiement
    Payment delete(Long id);
    
    //recherche d'un paiement en fonction de son id
    Payment find(Long id);

    //obtention d'une liste contenant les paiements créés non supprimés
    List<Payment> getAllStoredPayments();
    
}
