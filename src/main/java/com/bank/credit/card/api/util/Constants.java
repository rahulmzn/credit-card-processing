package com.bank.credit.card.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Common constants.
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    /**
     * Constants to define Card POJO properties
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PojoDescription{
        public static final String ID = "Credit card unique ID.";
        public static final String NAME = "Name of credit card Holder";
        public static final String BALANCE = "Available credit card Balance";
        public static final String LIMIT = "Total limit of credit card";
        public static final String CARD_NUMBER = "Credit card Number";
        public static final String BRAND = "Brand of credit card ex. VISA";
        public static final String CURRENCY = "Currency of card";
    }

}
