/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.paymentmgmt.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cesi
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment implements Serializable{
    
    @XmlAttribute
    private Long id;
    
    @XmlElement
    private PaymentStatus status;
    
    @XmlElement
    private String ccNumber;
    
    @XmlElement
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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
    
    
    
    @Override
    public String toString() {
       return "business.domain.Payment[id="+id+" ccNumber=" + ccNumber + " Amount="+ amount+"]";
    }    
}

