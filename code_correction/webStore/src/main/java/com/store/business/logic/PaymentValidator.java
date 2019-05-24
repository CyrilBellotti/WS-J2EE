/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.business.logic;

import javax.ejb.Local;

/**
 *
 * @author cesi
 */
@Local
public interface PaymentValidator {

    public Boolean process(String ccNumber, Double amount);
    
}
