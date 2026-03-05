# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-03-04

### Added
- Initial release of Fluent Validators
- `Validator<T>` - Generic validator for any type
- `StringValidator` - Specialized string validation
  - `notBlank()` - Validate non-empty strings
  - `email()` - Email format validation
  - `url()` - URL format validation
  - `matches(regex)` - Regex pattern matching
  - `length(min, max)` - Length constraints
- `NumberValidator<T>` - Specialized numeric validation
  - `positive()` - Positive number validation
  - `negative()` - Negative number validation
  - `between(min, max)` - Range validation
  - `greaterThan(value)` - Greater than validation
  - `lessThan(value)` - Less than validation
- `CollectionValidator<T>` - Specialized collection validation
  - `notEmpty()` - Non-empty collection validation
  - `size(min, max)` - Size constraints
  - `minSize(min)` - Minimum size validation
  - `maxSize(max)` - Maximum size validation
- Core validation features
  - `notNull()` - Null check validation
  - `must(predicate, message)` - Custom validation rules
  - `when(condition, predicate, message)` - Conditional validation
  - `field(name)` - Field name for error messages
  - `isValid()` - Check validation status
  - `getErrors()` - Collect all error messages
  - `orThrow()` - Throw ValidationException on failure
  - `orThrow(supplier)` - Throw custom exception on failure
  - `orDefault(value)` - Return default value on failure
- Comprehensive test coverage (25+ tests)
- Zero external dependencies
- Java 11+ support

### Documentation
- Comprehensive README with examples
- API reference documentation
- Real-world usage examples
- Contributing guidelines
- Publishing guide for Maven Central

[1.0.0]: https://github.com/[your-username]/fluent-validators/releases/tag/v1.0.0
