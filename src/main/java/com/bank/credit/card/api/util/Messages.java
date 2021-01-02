package com.bank.credit.card.api.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Messages {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ErrorCodes{
       public static String INVALID_CREDIT_CARD_NUMBER = "invalid credit card number";
    }


}
