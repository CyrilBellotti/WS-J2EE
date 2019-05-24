/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.model;

import com.store.business.logic.PaymentValidator;
import com.store.business.logic.Rest;
import com.store.business.logic.Soap;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author cesi
 */
@Named(value = "paymentModel")
@RequestScoped
public class PaymentBean {

    @Inject @Soap
    private  PaymentValidator paymentValidator;
    
    @Inject @Rest
    private PaymentValidator restPaymentValidator;

    private String ccNumber;
    private Double amount;
    
    public String doPaymentWithSoap(){
        System.out.println("Le paiement commence");  
        boolean isValid =   paymentValidator.process(ccNumber, amount);   
        if(isValid==true){
           return "valid";
        }else{
           return "invalid";
        }        
    }
    
        public String doPaymentWithRest(){
        System.out.println("Le paiement commence");   
        boolean isValid =   restPaymentValidator.process(ccNumber, amount);
        if(isValid==true){
            return "valid";
        }else{
            return "invalid";
        }   
    }



    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    
    
}
