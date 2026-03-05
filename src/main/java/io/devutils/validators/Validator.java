package io.devutils.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Fluent validation builder for any type.
 * Provides chainable validation rules with custom error messages.
 *
 * @param <T> the type being validated
 */
public class Validator<T> {
    private final T value;
    private final List<String> errors = new ArrayList<>();
    private String fieldName;

    protected Validator(T value) {
        this.value = value;
    }

    /**
     * Start validation for a value.
     */
    public static <T> Validator<T> of(T value) {
        return new Validator<>(value);
    }

    /**
     * Set the field name for better error messages.
     */
    public Validator<T> field(String name) {
        this.fieldName = name;
        return this;
    }

    /**
     * Validate that value is not null.
     */
    public Validator<T> notNull() {
        return notNull(fieldName + " must not be null");
    }

    public Validator<T> notNull(String message) {
        if (value == null) {
            errors.add(message);
        }
        return this;
    }

    /**
     * Apply custom validation rule.
     */
    public Validator<T> must(Predicate<T> condition, String message) {
        if (value != null && !condition.test(value)) {
            errors.add(message);
        }
        return this;
    }

    /**
     * Apply validation only if condition is true.
     */
    public Validator<T> when(boolean condition, Predicate<T> validation, String message) {
        if (condition) {
            must(validation, message);
        }
        return this;
    }

    /**
     * Check if validation passed.
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * Get all validation errors.
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    /**
     * Throw exception if validation failed.
     */
    public T orThrow() {
        return orThrow(ValidationException::new);
    }

    public T orThrow(Supplier<? extends RuntimeException> exceptionSupplier) {
        if (!isValid()) {
            throw exceptionSupplier.get();
        }
        return value;
    }

    /**
     * Get the validated value if valid, otherwise return default.
     */
    public T orDefault(T defaultValue) {
        return isValid() ? value : defaultValue;
    }
}
