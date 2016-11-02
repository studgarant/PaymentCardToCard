package com.cahek.ws;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 * Web Service work, get/send data to client
 */
@WebService
@HandlerChain(file = "handler.xml")
public class ServerInfo {

    /**
     * Get transaction information and return commission amount
     *
     * @param transaction Get message from client with transaction information
     * @return offer with commission amount and currency
     */
    @WebMethod
    @WebResult(name = "Commission")
    public String CardToCardPayment(@WebParam(name = "TransactionInfo") Transaction transaction) {

        transaction.calculateCommission();
        new SaveTransaction(transaction);

        return transaction.getCommission() + " " + transaction.getCurrency() + " ("
                + transaction.getCommissionPercent() + "%)";
    }
}
