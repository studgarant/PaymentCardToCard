package com.cahek.ws;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class CommissionTest {

    @Test
    public void crateCommission() throws Exception{

        Commission commission= new Commission();

        commission.setId(7);
        commission.setBrand(CardBrand.MAESTRO);
        commission.setCurrency(Currency.EUR);
        commission.setValue(BigDecimal.valueOf(3.7));

        assertThat(commission.getId(), is(7));
        assertThat(commission.getBrand(), is(CardBrand.MAESTRO));
        assertThat(commission.getCurrency(), is(Currency.EUR));
        assertThat(commission.getValue(), is(BigDecimal.valueOf(3.7)));
    }
}