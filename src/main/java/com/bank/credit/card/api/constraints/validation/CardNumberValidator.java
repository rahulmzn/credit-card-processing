package com.bank.credit.card.api.constraints.validation;

import com.bank.credit.card.api.constraints.CardNumber;
import com.bank.credit.card.api.util.Luhn10Util;

import javax.validation.ConstraintValidator;
import java.util.List;

/**
 * Validate given card number against LUHN Algorithm
 * See for ref {@link Luhn10Util#calculateLuhnMod10Check(List) LUHN Algorithm custom implementation }
 */
public class CardNumberValidator extends CardCheckBase implements ConstraintValidator<CardNumber, CharSequence> {

    @Override
    public void initialize(CardNumber constraintAnnotation) {
        super.initialize(
                constraintAnnotation.startIndex(),
                constraintAnnotation.endIndex(),
                constraintAnnotation.checkDigitIndex()
        );
    }

    /**
     * This function should be used for checking if all given digits are valid
     *
     * @param digits: Digits which needs to get validated
     * @param checkDigit the check digit
     *
     * @return {@code true} if the luhn 10 check result matches the check digit, {@code false} otherwise
     */
    @Override
    public boolean isCheckDigitValid(List<Integer> digits, char checkDigit) {
        int modResult = Luhn10Util.calculateLuhnMod10Check(digits);

        if (!Character.isDigit(checkDigit)) {
            return false;
        }

        int checkValue = extractDigit(checkDigit);
        return checkValue == modResult;
    }
}

