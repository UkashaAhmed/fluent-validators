package io.devutils.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberValidatorTest {

    @Test
    void testPositive_withPositiveNumber_passes() {
        Integer result = NumberValidator.of(10)
            .positive()
            .orThrow();
        
        assertEquals(10, result);
    }

    @Test
    void testPositive_withNegativeNumber_fails() {
        Validator<Integer> validator = NumberValidator.of(-5)
            .positive();
        
        assertFalse(validator.isValid());
    }

    @Test
    void testBetween_withValidRange_passes() {
        Integer result = NumberValidator.of(50)
            .between(1, 100)
            .orThrow();
        
        assertEquals(50, result);
    }

    @Test
    void testBetween_withInvalidRange_fails() {
        Validator<Integer> validator = NumberValidator.of(150)
            .between(1, 100);
        
        assertFalse(validator.isValid());
    }

    @Test
    void testGreaterThan_withValidValue_passes() {
        Double result = NumberValidator.of(10.5)
            .greaterThan(5.0)
            .orThrow();
        
        assertEquals(10.5, result);
    }

    @Test
    void testLessThan_withValidValue_passes() {
        Integer result = NumberValidator.of(5)
            .lessThan(10)
            .orThrow();
        
        assertEquals(5, result);
    }
}
