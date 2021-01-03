package com.bank.credit.card.api.constraints.validation;

import com.bank.credit.card.api.constraints.CardNumber;
import com.bank.credit.card.api.util.Luhn10Util;

import javax.validation.ConstraintValidator;
import java.util.List;

/**
 * Luhn algorithm checksum validator
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
     * Validate check digit using Luhn 10 algorithm
     *
     * @param digits The digits over which to calculate the checksum
     * @param checkDigit the check digit
     *
     * @return {@code true} if the luhn check result matches the check digit, {@code false} otherwise
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

