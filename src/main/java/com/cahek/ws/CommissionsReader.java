package com.cahek.ws;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;

/**
 * Read commissions from xml file from path
 */
public class CommissionsReader {

    private String filePath;

    final static Logger logger = Logger.getLogger(SaveTransaction.class);

    /**
     * Set path xml file
     * @param filePath path xml file
     */
    public CommissionsReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read commissions from xml file to commissions list with help JAXB
     * @return commissions list
     */
    public CommissionList ReadFromXml() {
        CommissionList commissions = new CommissionList();

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(CommissionList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            commissions = (CommissionList) jaxbUnmarshaller.unmarshal(new File(filePath));

        } catch (JAXBException e) {
            logger.error("Read commissions from XML file: " + e.toString());
        }

            return commissions;
    }
}
