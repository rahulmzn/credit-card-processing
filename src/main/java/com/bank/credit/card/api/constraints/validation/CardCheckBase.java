package com.bank.credit.card.api.constraints.validation;


import org.slf4j.Logger;

import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class CardCheckBase {

    private static final Logger LOG = getLogger(CardCheckBase.class);

    private static final Pattern REGX_EXP = Pattern.compile("[^0-9]");

    private static final int DEC_RADIX = 10;

    /**
     * The start index for the checksum calculation
     */
    private int startIndex;

    /**
     * The end index for the checksum calculation
     */
    private int endIndex;

    /**
     * The index of the checksum digit
     */
    private int checkDigitIndex;

    public abstract boolean isCheckDigitValid(List<Integer> digits, char checkDigit);

    protected void initialize(int startIndex, int endIndex, int checkDigitIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.checkDigitIndex = checkDigitIndex;
        this.validateOptions();
    }

    public boolean isValid(final CharSequence value, final ConstraintValidatorContext context) {
        LOG.debug("Class {} isValid called", CardCheckBase.class.getSimpleName());

        if (Objects.isNull(value)||(value.length() < 13 || value.length() > 19) ) {
            return false;
        }

        String valueAsString = value.toString();
        String digitsAsString;
        char checkDigit;
        try {
            digitsAsString = extractVerificationString(valueAsString);
            checkDigit = extractCheckDigit(valueAsString);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        List<Integer> digits;
        try {
            digits = extractDigits(digitsAsString);
        } catch (NumberFormatException e) {
            return false;
        }

            return this.isCheckDigitValid(digits, checkDigit);
    }

    /**
     * Returns the numeric {@code int} value of a {@code char}
     *
     * @param value the input {@code char} to be parsed
     * @return the numeric {@code int} value represented by the character.
     * @throws NumberFormatException in case character is not a digit
     */
    protected int extractDigit(char value) throws NumberFormatException {
        if (Character.isDigit(value)) {
            return Character.digit(value, DEC_RADIX);
        } else {
            throw new NumberFormatException(String.format("'%c' is not a digit.", value));
        }
    }

    /**
     * Parses the {@link String} value as a {@link List} of {@link Integer} objects
     *
     * @param value the input string to be parsed
     * @return List of {@code Integer} objects.
     * @throws NumberFormatException in case any of the characters is not a digit
     */
    private List<Integer> extractDigits(final String value) throws NumberFormatException {
        List<Integer> digits = new ArrayList<>(value.length());
        char[] chars = value.toCharArray();
        for (char c : chars) {
            digits.add(extractDigit(c));
        }
        return digits;
    }

    private void validateOptions() {
        if (this.startIndex < 0) {
            throw new IllegalArgumentException(String.format("Start index cannot be negative: %d.", this.startIndex));
        }

        if (this.endIndex < 0) {
            throw new IllegalArgumentException(String.format("End index cannot be negative: %d.", this.endIndex));
        }

        if (this.startIndex > this.endIndex) {
            throw new IllegalArgumentException(String.format("Invalid Range: %1$d > %2$d.", this.startIndex, this.endIndex));
        }

        if (this.checkDigitIndex > 0 && this.startIndex <= this.checkDigitIndex && this.endIndex > this.checkDigitIndex) {
            throw new IllegalArgumentException(String.format("Invalid Range: %1$d > %2$d.", this.startIndex, this.endIndex));
        }
    }

    private boolean validateInput(String value) {
        return REGX_EXP.matcher(value).matches();
    }

    private String extractVerificationString(String value) throws IndexOutOfBoundsException {
        // the string contains the check digit, just return the digits to verify
        if (endIndex == Integer.MAX_VALUE) {
            return value.substring(0, value.length() - 1);
        } else if (checkDigitIndex == -1) {
            return value.substring(startIndex, endIndex);
        } else {
            return value.substring(startIndex, endIndex + 1);
        }
    }

    private char extractCheckDigit(String value) throws IndexOutOfBoundsException {
        // take last character of string to be validated unless the index is given explicitly
        if (checkDigitIndex == -1) {
            if (endIndex == Integer.MAX_VALUE) {
                return value.charAt(value.length() - 1);
            } else {
                return value.charAt(endIndex);
            }
        } else {
            return value.charAt(checkDigitIndex);
        }
    }

}
