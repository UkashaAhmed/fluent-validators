package io.devutils.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {

    @Test
    void testNotBlank_withValidString_passes() {
        String result = StringValidator.of("hello")
            .notBlank()
            .orThrow();
        
        assertEquals("hello", result);
    }

    @Test
    void testNotBlank_withBlankString_fails() {
        Validator<String> validator = StringValidator.of("   ")
            .notBlank();
        
        assertFalse(validator.isValid());
    }

    @Test
    void testEmail_withValidEmail_passes() {
        String email = StringValidator.of("user@example.com")
            .email()
            .orThrow();
        
        assertEquals("user@example.com", email);
    }

    @Test
    void testEmail_withInvalidEmail_fails() {
        Validator<String> validator = StringValidator.of("invalid-email")
            .email();
        
        assertFalse(validator.isValid());
    }

    @Test
    void testLength_withValidLength_passes() {
        String result = StringValidator.of("hello")
            .length(3, 10)
            .orThrow();
        
        assertEquals("hello", result);
    }

    @Test
    void testLength_withInvalidLength_fails() {
        Validator<String> validator = StringValidator.of("hi")
            .length(3, 10);
        
        assertFalse(validator.isValid());
    }

    @Test
    void testMatches_withValidPattern_passes() {
        String result = StringValidator.of("abc123")
            .matches("[a-z0-9]+")
            .orThrow();
        
        assertEquals("abc123", result);
    }
}
