package io.devutils.validators;

import java.util.Collection;

/**
 * Specialized validator for Collection values.
 */
public class CollectionValidator<T extends Collection<?>> extends Validator<T> {
    
    private CollectionValidator(T value) {
        super(value);
    }

    public static <T extends Collection<?>> CollectionValidator<T> of(T value) {
        return new CollectionValidator<>(value);
    }

    /**
     * Validate collection is not empty.
     */
    public CollectionValidator<T> notEmpty() {
        return notEmpty("Collection must not be empty");
    }

    public CollectionValidator<T> notEmpty(String message) {
        must(c -> c != null && !c.isEmpty(), message);
        return this;
    }

    /**
     * Validate collection size is within range.
     */
    public CollectionValidator<T> size(int min, int max) {
        return size(min, max, String.format("Collection size must be between %d and %d", min, max));
    }

    public CollectionValidator<T> size(int min, int max, String message) {
        must(c -> c.size() >= min && c.size() <= max, message);
        return this;
    }

    /**
     * Validate collection has minimum size.
     */
    public CollectionValidator<T> minSize(int min) {
        return minSize(min, "Collection must have at least " + min + " elements");
    }

    public CollectionValidator<T> minSize(int min, String message) {
        must(c -> c.size() >= min, message);
        return this;
    }

    /**
     * Validate collection has maximum size.
     */
    public CollectionValidator<T> maxSize(int max) {
        return maxSize(max, "Collection must have at most " + max + " elements");
    }

    public CollectionValidator<T> maxSize(int max, String message) {
        must(c -> c.size() <= max, message);
        return this;
    }
}
