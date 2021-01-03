package com.bank.credit.card.api.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class BrandTest {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testDetection() {
        assertEquals(Brand.VISA, Brand.detect("4000056655665556"));
        assertEquals(Brand.VISA, Brand.detect("4242424242424242"));

        assertEquals(Brand.MASTERCARD, Brand.detect("5105105105105100"));
        assertEquals(Brand.MASTERCARD, Brand.detect("5200828282828210"));
        assertEquals(Brand.MASTERCARD, Brand.detect("5555555555554444"));

        assertEquals(Brand.AMERICAN_EXPRESS, Brand.detect("371449635398431"));
        assertEquals(Brand.AMERICAN_EXPRESS, Brand.detect("378282246310005"));

        assertEquals(Brand.DISCOVER, Brand.detect("6011000990139424"));
        assertEquals(Brand.DISCOVER, Brand.detect("6011111111111117"));

        assertEquals(Brand.DINERS_CLUB, Brand.detect("30569309025904"));
        assertEquals(Brand.DINERS_CLUB, Brand.detect("38520000023237"));

        assertEquals(Brand.JCB, Brand.detect("3530111333300000"));
        assertEquals(Brand.JCB, Brand.detect("3566002020360505"));

        assertEquals(Brand.UNKNOWN, Brand.detect("0000000000000000"));
    }


    @Test
    public void checkNoErrorWhenValidCardNumber() {
        Card car = new Card("Rahul Kumar","4748460033772018","1000",0.0,Brand.VISA);

        Set<ConstraintViolation<Card>> constraintViolations =
                validator.validate( car );

        assertEquals( 0, constraintViolations.size() );
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void checkErrorWhenInvalidCardNumber() {
        Card car = new Card("Rahul Kumar","4386280033772018","1000",0.0,Brand.VISA);

        Set<ConstraintViolation<Card>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "invalid credit card number",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void checkErrorWhenCardNumberIsMoreThan19Char() {
        Card car = new Card("Rahul Kumar","43862800111111133772018","1000",0.0,Brand.VISA);

        Set<ConstraintViolation<Card>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "invalid credit card number",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void checkErrorWhenCardNumberIsLessThan13Char() {
        Card car = new Card("Rahul Kumar","43862813772018","1000",0.0,Brand.VISA);

        Set<ConstraintViolation<Card>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "invalid credit card number",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void checkErrorWhenCardNumberContainsAlphabets() {
        Card car = new Card("Rahul Kumar","43862811rr133772018","1000",0.0,Brand.VISA);

        Set<ConstraintViolation<Card>> constraintViolations =
                validator.validate( car );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "invalid credit card number",
                constraintViolations.iterator().next().getMessage()
        );
    }


}
