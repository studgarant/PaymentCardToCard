package com.cahek.ws;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LoggingRequestHandler implements SOAPHandler<SOAPMessageContext> {

    private static Logger log = Logger.getLogger(LoggingRequestHandler.class.getName());

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
   
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        //for response message only, true for outbound messages, false for inbound
        SOAPMessage soapMsg = context.getMessage();

        if (!isRequest) {
            try {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                soapMsg.writeTo(bout);
                String msg = bout.toString("UTF-8");
                log.info("SOAP incoming message: \n" + msg);

            } catch (SOAPException | IOException e) {
                System.err.println(e);
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
