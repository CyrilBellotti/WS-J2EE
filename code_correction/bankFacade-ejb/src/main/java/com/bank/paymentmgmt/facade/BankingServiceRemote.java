/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.paymentmgmt.facade;

import com.bank.paymentmgmt.domain.Payment;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author cesi
 */
@Remote
public interface BankingServiceRemote extends BankingServiceEndpointInterface {
    
    List<Payment> lookupAllStoredPayments();
    
    Payment lookupStoredPayment(Long id);
    
    Payment deleteStoredPayment(Long id);
}
