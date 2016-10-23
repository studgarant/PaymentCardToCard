package com.cahek.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.math.RoundingMode;

//@XmlType(name = "transactionInfo", propOrder = {"cardFrom", "cardTo", "currency", "amount"})
@XmlType(propOrder = {"cardFrom", "cardTo", "currency", "amount", "commission"})
public class Transaction {

    private Card cardFrom;
    private Card cardTo;
    private float amount;
    private Currency currency;
    private float commission;

    public CardBrand getCardBrand(String cardNumber) {

        CardBrand cardBrand;

        switch (cardNumber.charAt(0)) {
            case '4':
                cardBrand = CardBrand.VISA;
                break;
            case '5':
                cardBrand = CardBrand.MASTERCARD;
                break;
            default:
                cardBrand = CardBrand.MAESTRO;
                break;
        }

        return cardBrand;
    }

    public void calculateCommission() {
        setCommission(new BigDecimal(new GetCommissionPercent().ComissionGetFromDB(getCardBrand(cardFrom.getCardNumber()), currency) * amount / 100).setScale(2, RoundingMode.UP).floatValue());
    }

    public float getCommissionPercent() {
        return new BigDecimal(getCommission() / getAmount() * 100).setScale(2, RoundingMode.UP).floatValue();
    }

    @XmlElement(required = true)
    public Card getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    @XmlElement(required = true)
    public Card getCardTo() {
        return cardTo;
    }

    public void setCardTo(Card cardTo) {
        this.cardTo = cardTo;
    }

    @XmlElement(required = true)
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @XmlElement(required = true)
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlElement(required = false)
    public float getCommission() {

        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }
}
