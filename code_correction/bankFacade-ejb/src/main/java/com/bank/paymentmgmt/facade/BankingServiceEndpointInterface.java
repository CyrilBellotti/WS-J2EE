/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.paymentmgmt.facade;

import javax.jws.*;

/**
 *
 * @author cesi
 */
@WebService(name = "BankingEndpoint")
public interface BankingServiceEndpointInterface {
   @WebMethod(operationName = "paymentOperation")
   @WebResult(name = "acceptedPayment")       
   Boolean createPayment(@WebParam(name = "cardNumber")String ccNumber, @WebParam(name = "amountPaid") Double amount);
}
