package io.devutils.examples;

import io.devutils.validators.StringValidator;
import io.devutils.validators.NumberValidator;
import io.devutils.validators.Validator;

/**
 * Real-world example: User Registration Form Validation
 */
public class UserRegistrationExample {

    public static void main(String[] args) {
        System.out.println("=== Fluent Validators Demo ===\n");
        
        // Example 1: Valid user registration
        System.out.println("Example 1: Valid User Registration");
        try {
            registerUser("john.doe@example.com", "SecurePass123", 25);
            System.out.println("✓ User registered successfully!\n");
        } catch (Exception e) {
            System.out.println("✗ Registration failed: " + e.getMessage() + "\n");
        }
        
        // Example 2: Invalid email
        System.out.println("Example 2: Invalid Email");
        try {
            registerUser("not-an-email", "SecurePass123", 25);
            System.out.println("✓ User registered successfully!\n");
        } catch (Exception e) {
            System.out.println("✗ Registration failed: " + e.getMessage() + "\n");
        }
        
        // Example 3: Weak password
        System.out.println("Example 3: Weak Password");
        try {
            registerUser("jane@example.com", "weak", 30);
            System.out.println("✓ User registered successfully!\n");
        } catch (Exception e) {
            System.out.println("✗ Registration failed: " + e.getMessage() + "\n");
        }
        
        // Example 4: Underage user
        System.out.println("Example 4: Underage User");
        try {
            registerUser("kid@example.com", "SecurePass123", 15);
            System.out.println("✓ User registered successfully!\n");
        } catch (Exception e) {
            System.out.println("✗ Registration failed: " + e.getMessage() + "\n");
        }
        
        // Example 5: Collecting multiple errors
        System.out.println("Example 5: Collecting Multiple Errors");
        collectAllErrors("", "123", 200);
    }
    
    /**
     * Validates and registers a user (throws on first error)
     */
    public static void registerUser(String email, String password, Integer age) {
        // Validate email - throws ValidationException if invalid
        String validEmail = StringValidator.of(email)
            .field("email")
            .notBlank()
            .email()
            .orThrow();
        
        // Validate password - throws ValidationException if invalid
        String validPassword = StringValidator.of(password)
            .field("password")
            .notBlank()
            .length(8, 128)
            .matches(".*[A-Z].*", "Password must contain at least one uppercase letter")
            .matches(".*[0-9].*", "Password must contain at least one number")
            .orThrow();
        
        // Validate age - throws ValidationException if invalid
        Integer validAge = NumberValidator.of(age)
            .field("age")
            .notNull()
            .positive()
            .between(18, 120)
            .orThrow();
        
        System.out.println("  Email: " + validEmail);
        System.out.println("  Password: " + "*".repeat(validPassword.length()));
        System.out.println("  Age: " + validAge);
    }
    
    /**
     * Collects all validation errors instead of failing on first error
     */
    public static void collectAllErrors(String email, String password, Integer age) {
        // Validate email
        Validator<String> emailValidator = StringValidator.of(email)
            .field("email")
            .notBlank()
            .email();
        
        // Validate password
        Validator<String> passwordValidator = StringValidator.of(password)
            .field("password")
            .notBlank()
            .length(8, 128)
            .matches(".*[A-Z].*", "Password must contain uppercase letter")
            .matches(".*[0-9].*", "Password must contain number");
        
        // Validate age
        Validator<Integer> ageValidator = NumberValidator.of(age)
            .field("age")
            .notNull()
            .positive()
            .between(18, 120);
        
        // Collect all errors
        boolean hasErrors = false;
        
        if (!emailValidator.isValid()) {
            System.out.println("  Email errors:");
            for (String error : emailValidator.getErrors()) {
                System.out.println("    - " + error);
            }
            hasErrors = true;
        }
        
        if (!passwordValidator.isValid()) {
            System.out.println("  Password errors:");
            for (String error : passwordValidator.getErrors()) {
                System.out.println("    - " + error);
            }
            hasErrors = true;
        }
        
        if (!ageValidator.isValid()) {
            System.out.println("  Age errors:");
            for (String error : ageValidator.getErrors()) {
                System.out.println("    - " + error);
            }
            hasErrors = true;
        }
        
        if (!hasErrors) {
            System.out.println("  ✓ All validations passed!");
        }
        System.out.println();
    }
}
