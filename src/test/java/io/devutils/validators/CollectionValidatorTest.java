package io.devutils.validators;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CollectionValidatorTest {

    @Test
    void testNotEmpty_withNonEmptyList_passes() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> result = CollectionValidator.of(list)
            .notEmpty()
            .orThrow();
        
        assertEquals(3, result.size());
    }

    @Test
    void testNotEmpty_withEmptyList_fails() {
        List<String> list = Collections.emptyList();
        Validator<List<String>> validator = CollectionValidator.of(list)
            .notEmpty();
        
        assertFalse(validator.isValid());
    }

    @Test
    void testSize_withValidSize_passes() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> result = CollectionValidator.of(list)
            .size(1, 5)
            .orThrow();
        
        assertEquals(3, result.size());
    }

    @Test
    void testSize_withInvalidSize_fails() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Validator<List<Integer>> validator = CollectionValidator.of(list)
            .size(1, 5);
        
        assertFalse(validator.isValid());
    }

    @Test
    void testMinSize_withValidSize_passes() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> result = CollectionValidator.of(list)
            .minSize(2)
            .orThrow();
        
        assertEquals(3, result.size());
    }

    @Test
    void testMaxSize_withValidSize_passes() {
        List<String> list = Arrays.asList("a", "b");
        List<String> result = CollectionValidator.of(list)
            .maxSize(5)
            .orThrow();
        
        assertEquals(2, result.size());
    }
}
