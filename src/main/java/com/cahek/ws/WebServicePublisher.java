package com.cahek.ws;

import org.apache.log4j.Logger;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;;

/**
 * Publish web service in url
 */
public class WebServicePublisher {

    final static Logger logger = Logger.getLogger(SaveTransaction.class);

    public static void main(String[] args) {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("server.properties");
        Properties props = new Properties();

        try {
            props.load(input);
        } catch (IOException e) {
            logger.error("Open server properties file: " + e.toString());
        }

        /** Reading commissions from xml */
        CommissionList commissions = new CommissionsReader(props.getProperty("SERVER_COMMISSIONS_XML_PATH")).ReadFromXml();

        /** Check and create table in DB */
        new CheckTable();

        /** Save commissions to DB */
        new SaveCommissionList(commissions.getCommissionList());

        Endpoint.publish(props.getProperty("SERVER_PUBLISH_URL"), new ServerInfo());

    }
}