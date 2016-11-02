package com.cahek.ws;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.math.BigDecimal;

/**
 * Commission
 */
@XmlRootElement(name = "commission")
@XmlAccessorType(XmlAccessType.FIELD)
public class Commission {

    /**
     * id
     */
    @XmlAttribute
    private int id;

    /**
     * Credit card brand
     */
    private CardBrand brand;

    /**
     * Credit card currency
     */
    private Currency currency;

    /**
     * Credit card commission
     */
    private BigDecimal value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CardBrand getBrand() {
        return brand;
    }

    public void setBrand(CardBrand brand) {
        this.brand = brand;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
