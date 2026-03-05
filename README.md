# Fluent Validators

[![Maven Central](https://img.shields.io/maven-central/v/io.devutils/fluent-validators.svg)](https://search.maven.org/artifact/io.devutils/fluent-validators)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-11%2B-blue.svg)](https://www.oracle.com/java/)

A lightweight, zero-dependency Java validation library with a fluent API for clean and readable validation code.

## 🚀 Why Fluent Validators?

Stop writing messy validation code with nested if-statements. Write clean, readable validations that express intent clearly.

**Before:**
```java
if (email == null || email.trim().isEmpty()) {
    throw new ValidationException("Email is required");
}
if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
    throw new ValidationException("Invalid email format");
}
if (email.length() > 255) {
    throw new ValidationException("Email too long");
}
```

**After:**
```java
String email = StringValidator.of(email)
    .notBlank()
    .email()
    .length(1, 255)
    .orThrow();
```

### Key Features

✅ **Zero dependencies** - No bloat, just pure Java  
✅ **Fluent API** - Chainable, readable validation rules  
✅ **Type-safe** - Compile-time safety with generics  
✅ **Lightweight** - Minimal footprint (~10KB)  
✅ **Java 11+** - Modern Java features  
✅ **Well-tested** - Comprehensive test coverage  

## 📦 Installation

### Maven

Add to your `pom.xml`:

```xml
<dependency>
    <groupId>io.devutils</groupId>
    <artifactId>fluent-validators</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```gradle
implementation 'io.devutils:fluent-validators:1.0.0'
```

## 📖 Quick Start

### String Validation

```java
import io.devutils.validators.StringValidator;

// Email validation
String email = StringValidator.of(userInput)
    .field("email")
    .notBlank()
    .email()
    .orThrow();

// Password validation
String password = StringValidator.of(pwd)
    .field("password")
    .notBlank()
    .length(8, 128)
    .matches(".*[A-Z].*", "Must contain uppercase letter")
    .matches(".*[0-9].*", "Must contain number")
    .orThrow();

// URL validation
String website = StringValidator.of(url)
    .notBlank()
    .url()
    .orThrow();
```

### Number Validation

```java
import io.devutils.validators.NumberValidator;

// Age validation
Integer age = NumberValidator.of(userAge)
    .field("age")
    .notNull()
    .positive()
    .between(18, 120)
    .orThrow();

// Price validation
Double price = NumberValidator.of(productPrice)
    .field("price")
    .notNull()
    .greaterThan(0.0)
    .lessThan(10000.0)
    .orThrow();
```

### Collection Validation

```java
import io.devutils.validators.CollectionValidator;

// List validation
List<String> tags = CollectionValidator.of(tagList)
    .field("tags")
    .notEmpty()
    .size(1, 10)
    .orThrow();
```

### Custom Validation

```java
import io.devutils.validators.Validator;

// Custom business rules
User user = Validator.of(userData)
    .field("user")
    .notNull()
    .must(u -> u.getAge() >= 18, "Must be 18 or older")
    .must(u -> u.hasAcceptedTerms(), "Must accept terms")
    .orThrow();
```

### Conditional Validation

```java
// Validate only when condition is true
Validator.of(user)
    .notNull()
    .when(user.isAdmin(), 
          u -> u.hasPermission("admin"), 
          "Admin users must have admin permission")
    .orThrow();
```

### Collecting All Errors

Instead of failing on the first error, collect all validation errors:

```java
Validator<String> emailValidator = StringValidator.of(email)
    .notBlank()
    .email()
    .length(5, 255);

Validator<String> passwordValidator = StringValidator.of(password)
    .notBlank()
    .length(8, 128)
    .matches(".*[A-Z].*", "Must contain uppercase")
    .matches(".*[0-9].*", "Must contain number");

// Check all validations
if (!emailValidator.isValid() || !passwordValidator.isValid()) {
    List<String> allErrors = new ArrayList<>();
    allErrors.addAll(emailValidator.getErrors());
    allErrors.addAll(passwordValidator.getErrors());
    
    // Return all errors to user
    return ResponseEntity.badRequest().body(allErrors);
}
```

### Using Default Values

```java
// Return default value if validation fails
String username = StringValidator.of(input)
    .notBlank()
    .length(3, 20)
    .orDefault("anonymous");
```

### Custom Exceptions

```java
// Throw custom exception
User user = Validator.of(userData)
    .notNull()
    .must(u -> u.getAge() >= 18, "Must be 18 or older")
    .orThrow(() -> new IllegalArgumentException("Invalid user data"));
```

## 🎯 Real-World Examples

### REST API Validation

```java
@PostMapping("/users")
public ResponseEntity<User> createUser(@RequestBody UserRequest request) {
    // Validate all fields
    String email = StringValidator.of(request.getEmail())
        .field("email")
        .notBlank()
        .email()
        .length(5, 255)
        .orThrow();
    
    String username = StringValidator.of(request.getUsername())
        .field("username")
        .notBlank()
        .length(3, 20)
        .matches("^[a-zA-Z0-9_]+$", "Only letters, numbers, and underscores")
        .orThrow();
    
    Integer age = NumberValidator.of(request.getAge())
        .field("age")
        .notNull()
        .between(18, 120)
        .orThrow();
    
    User user = userService.create(email, username, age);
    return ResponseEntity.ok(user);
}
```

### Form Validation with Error Collection

```java
public Map<String, List<String>> validateRegistrationForm(RegistrationForm form) {
    Map<String, List<String>> errors = new HashMap<>();
    
    Validator<String> emailValidator = StringValidator.of(form.getEmail())
        .notBlank()
        .email();
    
    Validator<String> passwordValidator = StringValidator.of(form.getPassword())
        .notBlank()
        .length(8, 128)
        .matches(".*[A-Z].*", "Must contain uppercase letter")
        .matches(".*[a-z].*", "Must contain lowercase letter")
        .matches(".*[0-9].*", "Must contain number");
    
    Validator<Integer> ageValidator = NumberValidator.of(form.getAge())
        .notNull()
        .between(18, 120);
    
    if (!emailValidator.isValid()) {
        errors.put("email", emailValidator.getErrors());
    }
    if (!passwordValidator.isValid()) {
        errors.put("password", passwordValidator.getErrors());
    }
    if (!ageValidator.isValid()) {
        errors.put("age", ageValidator.getErrors());
    }
    
    return errors;
}
```

### Builder Pattern Validation

```java
public class UserBuilder {
    private String email;
    private String username;
    private Integer age;
    
    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }
    
    public UserBuilder age(Integer age) {
        this.age = age;
        return this;
    }
    
    public User build() {
        // Validate before building
        String validEmail = StringValidator.of(email)
            .field("email")
            .notBlank()
            .email()
            .orThrow();
        
        String validUsername = StringValidator.of(username)
            .field("username")
            .notBlank()
            .length(3, 20)
            .orThrow();
        
        Integer validAge = NumberValidator.of(age)
            .field("age")
            .notNull()
            .positive()
            .orThrow();
        
        return new User(validEmail, validUsername, validAge);
    }
}
```

## 📚 API Reference

### Validator<T>

Generic validator for any type.

| Method | Description |
|--------|-------------|
| `of(T value)` | Create validator for value |
| `field(String name)` | Set field name for error messages |
| `notNull()` | Validate value is not null |
| `must(Predicate<T>, String)` | Custom validation rule |
| `when(boolean, Predicate<T>, String)` | Conditional validation |
| `isValid()` | Check if all validations passed |
| `getErrors()` | Get list of error messages |
| `orThrow()` | Get value or throw ValidationException |
| `orThrow(Supplier<Exception>)` | Get value or throw custom exception |
| `orDefault(T)` | Get value or return default |

### StringValidator

Specialized validator for strings.

| Method | Description |
|--------|-------------|
| `notBlank()` | Not null, empty, or whitespace |
| `email()` | Valid email format |
| `url()` | Valid URL format (http/https) |
| `matches(String regex)` | Match regex pattern |
| `length(int min, int max)` | Length between min and max |

### NumberValidator<T>

Specialized validator for numbers (Integer, Long, Double, etc.).

| Method | Description |
|--------|-------------|
| `positive()` | Greater than zero |
| `negative()` | Less than zero |
| `between(T min, T max)` | Between min and max (inclusive) |
| `greaterThan(T value)` | Greater than value |
| `lessThan(T value)` | Less than value |

### CollectionValidator<T>

Specialized validator for collections (List, Set, etc.).

| Method | Description |
|--------|-------------|
| `notEmpty()` | Not null and not empty |
| `size(int min, int max)` | Size between min and max |
| `minSize(int min)` | Minimum size |
| `maxSize(int max)` | Maximum size |

## 🎨 Use Cases

- ✅ REST API request validation
- ✅ Form data validation
- ✅ Configuration validation
- ✅ Domain model validation
- ✅ Builder pattern validation
- ✅ Input sanitization
- ✅ Business rule enforcement

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🌟 Show Your Support

If you find this library useful, please consider:
- ⭐ Starring the repository
- 🐛 Reporting bugs
- 💡 Suggesting new features
- 📖 Improving documentation
- 🔀 Contributing code

## 📞 Contact

- GitHub Issues: [Report a bug or request a feature](https://github.com/UkashaAhmed/fluent-validators/issues)
- GitHub Discussions: [Ask questions and discuss](https://github.com/UkashaAhmed/fluent-validators/discussions)

---

Made with ❤️ for the Java community
