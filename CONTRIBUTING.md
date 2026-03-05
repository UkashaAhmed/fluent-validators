# Contributing to Fluent Validators

Thank you for your interest in contributing to Fluent Validators! We welcome contributions from the community.

## How to Contribute

### Reporting Bugs

If you find a bug, please create an issue on GitHub with:
- A clear, descriptive title
- Steps to reproduce the issue
- Expected behavior
- Actual behavior
- Java version and environment details

### Suggesting Features

We love new ideas! Please create an issue with:
- A clear description of the feature
- Use cases and examples
- Why this feature would be useful

### Pull Requests

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature-name`)
3. Make your changes
4. Add tests for your changes
5. Ensure all tests pass (`mvn test`)
6. Commit your changes (`git commit -m 'Add some feature'`)
7. Push to your branch (`git push origin feature/your-feature-name`)
8. Open a Pull Request

### Code Style

- Follow standard Java conventions
- Use meaningful variable and method names
- Add Javadoc comments for public APIs
- Keep methods focused and concise
- Write tests for new functionality

### Testing

- All new features must include unit tests
- Ensure existing tests still pass
- Aim for high test coverage
- Run tests with: `mvn test`

### Commit Messages

- Use clear, descriptive commit messages
- Start with a verb (Add, Fix, Update, Remove, etc.)
- Keep the first line under 50 characters
- Add detailed description if needed

Example:
```
Add email validation to StringValidator

- Implements RFC 5322 compliant email validation
- Adds comprehensive test coverage
- Updates documentation with examples
```

## Development Setup

1. Clone the repository
```bash
git clone https://github.com/[your-username]/fluent-validators.git
cd fluent-validators
```

2. Build the project
```bash
mvn clean install
```

3. Run tests
```bash
mvn test
```

4. Generate Javadocs
```bash
mvn javadoc:javadoc
```

## Questions?

Feel free to open an issue for any questions or discussions!

## Code of Conduct

Be respectful, inclusive, and professional in all interactions.
