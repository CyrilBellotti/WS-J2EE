/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.model;

/**
 *
 * @author cesi
 */
public class PaymentOrder {
    
    private final String location;
    private final Double amount;
    private final Long orderNumber;

    public PaymentOrder(String location, Double amount, Long orderNumber) {
        this.location = location;
        this.amount = amount;
        this.orderNumber = orderNumber;
    }
    
    

    public String getLocation() {
        return location;
    }

    
    public Double getAmount() {
        return amount;
    }

    
    public Long getOrderNumber() {
        return orderNumber;
    }
        
}
