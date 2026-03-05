package io.devutils.validators;

/**
 * Specialized validator for String values with common string validations.
 */
public class StringValidator extends Validator<String> {
    
    private StringValidator(String value) {
        super(value);
    }

    public static StringValidator of(String value) {
        return new StringValidator(value);
    }
    
    @Override
    public StringValidator field(String name) {
        super.field(name);
        return this;
    }
    
    @Override
    public StringValidator notNull() {
        super.notNull();
        return this;
    }
    
    @Override
    public StringValidator notNull(String message) {
        super.notNull(message);
        return this;
    }
    
    @Override
    public StringValidator must(java.util.function.Predicate<String> condition, String message) {
        super.must(condition, message);
        return this;
    }

    /**
     * Validate string is not empty or whitespace.
     */
    public StringValidator notBlank() {
        return notBlank("String must not be blank");
    }

    public StringValidator notBlank(String message) {
        must(s -> s != null && !s.trim().isEmpty(), message);
        return this;
    }

    /**
     * Validate string matches regex pattern.
     */
    public StringValidator matches(String regex) {
        return matches(regex, "String must match pattern: " + regex);
    }

    public StringValidator matches(String regex, String message) {
        must(s -> s.matches(regex), message);
        return this;
    }

    /**
     * Validate string length is within range.
     */
    public StringValidator length(int min, int max) {
        return length(min, max, String.format("String length must be between %d and %d", min, max));
    }

    public StringValidator length(int min, int max, String message) {
        must(s -> s.length() >= min && s.length() <= max, message);
        return this;
    }

    /**
     * Validate string is a valid email format.
     */
    public StringValidator email() {
        return matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", "Invalid email format");
    }

    /**
     * Validate string is a valid URL format.
     */
    public StringValidator url() {
        return matches("^https?://.*", "Invalid URL format");
    }
}
