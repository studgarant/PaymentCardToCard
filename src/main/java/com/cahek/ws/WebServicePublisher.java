package com.cahek.ws;

import java.io.IOException;
import java.util.logging.LogManager;
import javax.xml.ws.Endpoint;

public class WebServicePublisher {

    public static void main(String[] args) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            LogManager.getLogManager().readConfiguration(classLoader.getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Couldnot setup logger configuration: " + e.toString());
        }

        CommissionList commissions = new CommissionsReader("src/main/resources/tax.xml").ReadFromXml();

        new SaveCommissionList(commissions.getCommissionList());

        Endpoint.publish("http://localhost:8889/ws/server", new ServerInfo());

        System.out.println("Service is published");
    }
}
