package com.bank.credit.card.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Luhn10Util {

    private static final Logger LOG = LoggerFactory.getLogger(Luhn10Util.class);
    private static final int DEC_RADIX = 10;

    public static int calculateLuhnMod10Check(final List<Integer> digits) {
        LOG.debug("Started calculating Luhn10 checksum in class {}", Luhn10Util.class.getSimpleName() );
        int sum = 0;
        boolean even = true;
        for ( int index = digits.size() - 1; index >= 0; index-- ) {
            int digit = digits.get( index );

            if ( even ) {
                digit <<= 1;
            }
            if ( digit > 9 ) {
                digit -= 9;
            }
            sum += digit;
            even = !even;
        }
        return ( 10 - ( sum % 10 ) ) % 10;
    }

    public static int extractDigit(char value) throws NumberFormatException {
        if ( Character.isDigit( value ) ) {
            return Character.digit( value, DEC_RADIX );
        }
        else {
            throw new  IllegalArgumentException("Error occurred while extracting digits");
        }
    }

}
