package com.cahek.ws;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommissionListTest {

    @Test
    public void createCommissionList() throws Exception
    {
        List<Commission> list = new ArrayList<Commission>();

        Commission commission1 = new Commission();
        commission1.setId(1);
        commission1.setBrand(CardBrand.VISA);
        commission1.setCurrency(Currency.USD);
        commission1.setValue(BigDecimal.valueOf(1.5));

        Commission commission2 = new Commission();
        commission2.setId(2);
        commission2.setBrand(CardBrand.MAESTRO);
        commission2.setCurrency(Currency.EUR);
        commission2.setValue(BigDecimal.valueOf(3));

        list.add(commission1);
        list.add(commission1);

        CommissionList commissionList = new CommissionList();
        commissionList.setCommissionList(list);

        list = commissionList.getCommissionList();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertTrue(list.size() == 2);
    }

}