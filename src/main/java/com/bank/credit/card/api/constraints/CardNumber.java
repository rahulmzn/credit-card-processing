package com.bank.credit.card.api.constraints;

import com.bank.credit.card.api.constraints.validation.CardNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CardNumberValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(CardNumber.List.class)
public @interface CardNumber {

    String message() default "invalid credit card number";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * @return the start index (inclusive) for calculating the checksum.
     */
    int startIndex() default 0;

    /**
     * @return the end index (inclusive) for calculating the checksum.
     */
    int endIndex() default Integer.MAX_VALUE;

    /**
     * @return The index of the check digit in the input. Per default it is assumed that the check digit is the last
     * digit of the specified range. If set, the digit at the specified index is used. If set
     * the following must hold true:
     * {@code checkDigitIndex > 0 && (checkDigitIndex < startIndex || checkDigitIndex >= endIndex}.
     */
    int checkDigitIndex() default -1;

    /**
     * Defines several {@code @CreditCardValidation} annotations on the same element.
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        CardNumber[] value();
    }
}
