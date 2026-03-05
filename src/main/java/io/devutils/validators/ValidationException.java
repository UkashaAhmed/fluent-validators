package io.devutils.validators;

/**
 * Exception thrown when validation fails.
 */
public class ValidationException extends RuntimeException {
    public ValidationException() {
        super("Validation failed");
    }

    public ValidationException(String message) {
        super(message);
    }
}
