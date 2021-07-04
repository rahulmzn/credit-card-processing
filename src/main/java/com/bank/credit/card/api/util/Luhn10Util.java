package com.bank.credit.card.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Luhn10Util {

    private static final Logger LOG = LoggerFactory.getLogger(Luhn10Util.class);
    private static final int DECIMAL = 10;

    /**
     * This function will validate the given list of integer values against LUHN ALGORITHM and calculate the by module 10 and return result.
     * <p>
     *      Here is how it works for sample visa card number: 4003600000000014
     * </p>
     * <p>
     *      1: Get product for each digit of card number except last digit,
     *      Hence multiply the digits from the back, starting from the second-to-last. Skip one number,
     *      double the next until you’re now on the first number.
     * </p>
     * <p>
     *      4003600000000014  => 1 x 2 + 0 x 2 + 0 x 2 + 0 x 2 + 0 x 2 + 6 x 2 + 0 x 2 + 4 x 2
     * </p>
     * <p>
     *      2: Now, we have product digits for all ,
     * </p>
     * <p>
     *      Detailed product calculation as per LUHN Algorithm
     * </p>
     * <p>
     *     Now  digit of 1 × 2 is 2. However,  the product digit of 6 × 2 will be 1 + 2 (derived from its product '12') because 12 is higher than 10. Add all the derived product digits together.
     * </p>
     * <p>
     *     Hence => 2 + 0 + 0 + 0 + 0 + 1 + 2 + 0 + 8 = 13
     * </p>
     * <p>
     *      3. The sum derived should be added to the sum of the digits that weren’t doubled.
     * </p>
     *
     * <p>
     *      13 + 4 + 0 + 0 + 0 + 0 + 0 + 3 + 0 => 20
     * </p>
     * <p>
     *      4. At Last return mod of result..
     * </p>
     *
     * @param digits : Integer card number as list of digits
     * @return : Mod as result from card number calculation.
     */
    public static int calculateLuhnMod10Check(final List<Integer> digits) {
        LOG.debug("Started calculating Luhn10 checksum in class {}", Luhn10Util.class.getSimpleName());
        int sum = 0;
        boolean even = true;
        for (int index = digits.size() - 1; index >= 0; index--) {
            int digit = digits.get(index);

            // multiply digit
            if (even) {
                digit <<= 1;
            }
            if (digit > 9) {
                digit -= 9;
            }
            sum += digit;
            even = !even;
        }
        return (10 - (sum % 10)) % 10;
    }

}
