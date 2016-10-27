package com.cahek.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Credit card information
 */
@XmlType(propOrder = {"cardNumber", "ownerName", "valid"})
public class Card {

    /** Credit card number*/
    private String cardNumber;

    /** Credit card owner*/
    private String ownerName;

    /** Credit card month/year date MM/YY */
    private String valid;

    @XmlElement(required = true)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @XmlElement(required = true)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @XmlElement(required = true)
    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
