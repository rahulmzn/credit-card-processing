package com.bank.credit.card.api.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void testEqualsAndHashCode() {
       assertEquals ( new Card("Name","478", "100",10.0,Brand.VISA ), new Card("Name","478", "100",10.0,Brand.VISA ));
       assertNotEquals( new Card("Name","478", "100",10.0,Brand.VISA ), new Card("Name","567", "100",10.0,Brand.VISA ) );
    }

    @Test
    public void testToString() {
       Card card =  new Card("Name","478", "100",10.0,Brand.VISA );
       assertTrue(card.toString().startsWith("Card"));
    }
}
