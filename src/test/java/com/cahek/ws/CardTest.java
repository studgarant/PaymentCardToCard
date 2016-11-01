package com.cahek.ws;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CardTest {
    @Test
    public void createCard() throws Exception {

        Card card = new Card();
        card.setCardNumber("5555444433332222");
        card.setOwnerName("Petr Ivanov");
        card.setValid("12/20");

        assertThat(card.getCardNumber(), is("5555444433332222"));
        assertThat(card.getOwnerName(), is("Petr Ivanov"));
        assertThat(card.getValid(), is("12/20"));
    }
}