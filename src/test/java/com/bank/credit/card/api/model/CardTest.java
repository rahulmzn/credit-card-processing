package com.bank.credit.card.api.model;

import com.bank.credit.card.api.constants.Constants;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {


    @Test
    public void testEqualsAndHashCode() {
       assertEquals ( new Card("Name","478", "100",10.0,Brand.VISA, Constants.CURRENCY), new Card("Name","478", "100",10.0,Brand.VISA,Constants.CURRENCY ));
       assertNotEquals( new Card("Name","478", "100",10.0,Brand.VISA,Constants.CURRENCY), new Card("Name","567", "100",10.0,Brand.VISA,Constants.CURRENCY ) );
    }

    @Test
    public void testToString() {
       Card card =  new Card("Name","478", "100",10.0,Brand.VISA ,Constants.CURRENCY);
       assertTrue(card.toString().startsWith("Card"));
    }
}
