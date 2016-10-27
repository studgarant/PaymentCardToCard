package com.cahek.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  Transaction information
 */
@XmlType(propOrder = {"cardFrom", "cardTo", "currency", "amount", "commission"})
public class Transaction {

    /** Payment from this credit card*/
    private Card cardFrom;

    /** Payment to this credit card*/
    private Card cardTo;

    /** Transaction amount*/
    private BigDecimal amount;

    /** Transaction currency*/
    private Currency currency;

    /** Transaction commission*/
    private BigDecimal commission;

    /**
     * Get card brand from first number of credit card
     * @param cardNumber credit card number
     * @return credit card brand
     */
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

    /**
     * Calculate commission
     */
    public void calculateCommission() {

        setCommission(new BigDecimal(new GetCommissionPercent().ComissionGetFromDB(getCardBrand(
                cardFrom.getCardNumber()), currency).toString()).setScale(2, RoundingMode.UP));
    }

    /**
     * Get commissions percent
     * @return  commissions percent
     */
    public BigDecimal getCommissionPercent() {

        BigDecimal returnValue = new BigDecimal(getCommission().toString());
        returnValue = returnValue.multiply(new BigDecimal(100));
        returnValue = returnValue.divide(new BigDecimal(getAmount().toString())).setScale(2, RoundingMode.UP);

        return returnValue;
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
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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
    public BigDecimal getCommission() {

        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }
}
