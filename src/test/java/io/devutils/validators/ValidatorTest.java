package io.devutils.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void testNotNull_withValidValue_passes() {
        String result = Validator.of("test")
            .notNull()
            .orThrow();
        
        assertEquals("test", result);
    }

    @Test
    void testNotNull_withNullValue_fails() {
        Validator<String> validator = Validator.of((String) null)
            .field("value")
            .notNull();
        
        assertFalse(validator.isValid());
        assertEquals(1, validator.getErrors().size());
    }

    @Test
    void testMust_withValidCondition_passes() {
        Integer result = Validator.of(10)
            .must(n -> n > 5, "Must be greater than 5")
            .orThrow();
        
        assertEquals(10, result);
    }

    @Test
    void testMust_withInvalidCondition_fails() {
        Validator<Integer> validator = Validator.of(3)
            .must(n -> n > 5, "Must be greater than 5");
        
        assertFalse(validator.isValid());
        assertTrue(validator.getErrors().get(0).contains("greater than 5"));
    }

    @Test
    void testOrDefault_withInvalidValue_returnsDefault() {
        String result = Validator.of((String) null)
            .notNull()
            .orDefault("default");
        
        assertEquals("default", result);
    }

    @Test
    void testMultipleValidations_collectsAllErrors() {
        Validator<String> validator = Validator.of((String) null)
            .field("username")
            .notNull()
            .must(s -> s.length() > 3, "Too short");
        
        assertEquals(1, validator.getErrors().size());
    }
}
