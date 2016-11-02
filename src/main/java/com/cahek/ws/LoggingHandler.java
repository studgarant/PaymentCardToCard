package com.cahek.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Interception SOAP handlers
 */
public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    final static Logger logger = Logger.getLogger(LoggingHandler.class);

    /**
     * Logging response only
     *
     * @param context {@link SOAPMessageContext}
     * @return true
     */
    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        SOAPMessage soapMsg = context.getMessage();

        if (!isRequest) {
            try {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                soapMsg.writeTo(bout);
                String msg = bout.toString("UTF-8");
                logger.info("SOAP incoming message: " + msg);

            } catch (SOAPException | IOException e) {
                logger.error("SOAP incoming message: " + e.toString());
            }
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {

        return true;
    }

    @Override
    public void close(MessageContext context) {

    }

    @Override
    public Set<QName> getHeaders() {

        return null;
    }
}
