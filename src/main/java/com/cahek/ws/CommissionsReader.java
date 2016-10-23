package com.cahek.ws;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class CommissionsReader {

    private String filePath;

    public CommissionsReader(String filePath) {
        this.filePath = filePath;
    }

    public CommissionList ReadFromXml() {
        CommissionList commissions = new CommissionList();

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(CommissionList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            commissions = (CommissionList) jaxbUnmarshaller.unmarshal(new File(filePath));

        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            return commissions;
        }
    }
}
