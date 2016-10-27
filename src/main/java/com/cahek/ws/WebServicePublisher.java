package com.cahek.ws;

import org.apache.log4j.Logger;
import javax.xml.ws.Endpoint;

/**
 *  Publish web service in url
 */
public class WebServicePublisher {

      public static void main(String[] args) {

        /** Reading commissions from xml */
        CommissionList commissions = new CommissionsReader("src/main/resources/tax.xml").ReadFromXml();

        /** Save commissions to DB */
        new SaveCommissionList(commissions.getCommissionList());

        Endpoint.publish("http://localhost:8889/ws/server", new ServerInfo());
    }
}
