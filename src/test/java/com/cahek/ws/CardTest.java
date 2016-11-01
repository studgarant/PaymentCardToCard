package com.cahek.ws;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Crate new card number '5555 4444 3333 2222'
 * owner 'Petr Ivanov'
 * valid date MM/YY '12/20'
 */
public class CardTest {
    @Test
    public void createCard() throws Exception {

        Card card = new Card();
        card.setCardNumber("5555444433332222");
        card.setOwnerName("Petr Ivanov");
        card.setValid("12/20");

        assertThat(card.getCardNumber(), is("5555444433332222"));
        assertThat(card.getCardNumber(), is("Petr Ivanov"));
        assertThat(card.getCardNumber(), is("12/20"));
    }
}