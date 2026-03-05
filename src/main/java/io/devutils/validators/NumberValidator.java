package io.devutils.validators;

/**
 * Specialized validator for Number values with common numeric validations.
 */
public class NumberValidator<T extends Number & Comparable<T>> extends Validator<T> {
    
    private NumberValidator(T value) {
        super(value);
    }

    public static <T extends Number & Comparable<T>> NumberValidator<T> of(T value) {
        return new NumberValidator<>(value);
    }
    
    @Override
    public NumberValidator<T> field(String name) {
        super.field(name);
        return this;
    }
    
    @Override
    public NumberValidator<T> notNull() {
        super.notNull();
        return this;
    }
    
    @Override
    public NumberValidator<T> notNull(String message) {
        super.notNull(message);
        return this;
    }
    
    @Override
    public NumberValidator<T> must(java.util.function.Predicate<T> condition, String message) {
        super.must(condition, message);
        return this;
    }

    /**
     * Validate number is positive (&gt; 0).
     */
    public NumberValidator<T> positive() {
        return positive("Number must be positive");
    }

    public NumberValidator<T> positive(String message) {
        must(n -> n.doubleValue() > 0, message);
        return this;
    }

    /**
     * Validate number is negative (&lt; 0).
     */
    public NumberValidator<T> negative() {
        return negative("Number must be negative");
    }

    public NumberValidator<T> negative(String message) {
        must(n -> n.doubleValue() < 0, message);
        return this;
    }

    /**
     * Validate number is within range (inclusive).
     */
    public NumberValidator<T> between(T min, T max) {
        return between(min, max, String.format("Number must be between %s and %s", min, max));
    }

    public NumberValidator<T> between(T min, T max, String message) {
        must(n -> n.compareTo(min) >= 0 && n.compareTo(max) <= 0, message);
        return this;
    }

    /**
     * Validate number is greater than value.
     */
    public NumberValidator<T> greaterThan(T value) {
        return greaterThan(value, "Number must be greater than " + value);
    }

    public NumberValidator<T> greaterThan(T value, String message) {
        must(n -> n.compareTo(value) > 0, message);
        return this;
    }

    /**
     * Validate number is less than value.
     */
    public NumberValidator<T> lessThan(T value) {
        return lessThan(value, "Number must be less than " + value);
    }

    public NumberValidator<T> lessThan(T value, String message) {
        must(n -> n.compareTo(value) < 0, message);
        return this;
    }
}
