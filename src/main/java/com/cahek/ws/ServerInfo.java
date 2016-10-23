package com.cahek.ws;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebResult;

@WebService
@HandlerChain(file = "handler.xml")
public class ServerInfo {

    @WebMethod
    @WebResult(name = "Commission")
    public String CardToCardPayment(@WebParam(name = "TransactionInfo") Transaction transaction) {

        transaction.calculateCommission();
        new SaveTransaction(transaction);

        return transaction.getCommission() + " " + transaction.getCurrency() + " ("
                + transaction.getCommissionPercent() + "%)";
    }
}
