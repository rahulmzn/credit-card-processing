package com.bank.credit.card.api.constraints.validation;

import com.bank.credit.card.api.constraints.CardNumber;
import com.bank.credit.card.api.util.Luhn10Util;

import javax.validation.ConstraintValidator;
import java.util.List;

public class CardNumberValidator extends CardCheckBase implements ConstraintValidator<CardNumber, CharSequence> {

    @Override
    public void initialize(CardNumber constraintAnnotation) {
        super.initialize(
                constraintAnnotation.startIndex(),
                constraintAnnotation.endIndex(),
                constraintAnnotation.checkDigitIndex()
        );
    }

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

