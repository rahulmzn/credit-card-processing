package com.bank.credit.card.api.constraints.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CardNumberValidatorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @InjectMocks
    CardNumberValidator   validator;

    @Test
    public void shouldValidateSuccessfully() {
        List<Integer> chars = Arrays.asList(4,7,4,8,4,6,0,0,3,3,7,7,2,0,1);
        assertTrue(validator.isCheckDigitValid(chars, '8'));
    }

    @Test
    public void shouldNotValidateSuccessfully() {
        List<Integer> chars = Arrays.asList(4,7,4,8,4,6,0,0,3,3,7,7,2,0);
        assertFalse(validator.isCheckDigitValid(chars, '8'));
    }

    @Test
    public void shouldExtractDigitAsInteger() {
        assertEquals(1, validator.extractDigit('1'));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowErrorIfNotValidDigit() {
        validator.extractDigit('s');
    }

    @Test
    public void shouldValidateNumberAgainstChecksum() {
        CharSequence number =  "4748460033772018";
        validator.initialize(0,Integer.MAX_VALUE,-1);
        assertTrue(validator.isValid(number, null));
    }
}
