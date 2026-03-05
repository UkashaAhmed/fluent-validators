# Getting Started with Fluent Validators

This guide will help you publish your Fluent Validators library to Maven Central and start building your user base.

## ✅ What's Ready

Your library is complete and ready to publish:

- ✅ Core validation classes (Validator, StringValidator, NumberValidator, CollectionValidator)
- ✅ 25 passing unit tests
- ✅ Comprehensive documentation (README, API docs, examples)
- ✅ Maven POM configured for Maven Central
- ✅ Zero dependencies
- ✅ Java 11+ compatible
- ✅ MIT License

## 📋 Pre-Publishing Checklist

### 1. Update pom.xml

Replace these placeholders in `pom.xml`:
- `[your-username]` → Your GitHub username
- `[your-id]` → Your developer ID (usually your GitHub username)
- `[Your Name]` → Your full name
- `[your-email]` → Your email address

### 2. Create GitHub Repository

```bash
# Initialize git (if not already done)
git init
git add .
git commit -m "Initial commit: Fluent Validators v1.0.0"

# Create repository on GitHub, then:
git remote add origin https://github.com/[your-username]/fluent-validators.git
git branch -M main
git push -u origin main

# Create first release tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

### 3. Set Up Sonatype Account

1. Go to https://issues.sonatype.org
2. Create a JIRA account
3. Create a new issue:
   - Project: Community Support - Open Source Project Repository Hosting (OSSRH)
   - Issue Type: New Project
   - Group Id: `io.devutils`
   - Project URL: `https://github.com/[your-username]/fluent-validators`
   - SCM URL: `https://github.com/[your-username]/fluent-validators.git`
4. Wait for approval (usually 1-2 business days)

### 4. Set Up GPG Keys

```bash
# Generate GPG key
gpg --gen-key

# List keys
gpg --list-keys

# Publish public key (replace YOUR_KEY_ID)
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID

# Also publish to other keyservers
gpg --keyserver keys.openpgp.org --send-keys YOUR_KEY_ID
```

### 5. Configure Maven Settings

Create or edit `~/.m2/settings.xml`:

```xml
<settings>
    <servers>
        <server>
            <id>ossrh</id>
            <username>your-jira-username</username>
            <password>your-jira-password</password>
        </server>
    </servers>
    
    <profiles>
        <profile>
            <id>ossrh</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <gpg.executable>gpg</gpg.executable>
                <gpg.passphrase>your-gpg-passphrase</gpg.passphrase>
            </properties>
        </profile>
    </profiles>
</settings>
```

## 🚀 Publishing to Maven Central

### Build and Verify

```bash
# Run all tests
mvn clean test

# Build with all artifacts (skip GPG for testing)
mvn clean verify -Dgpg.skip=true
```

### Deploy to Maven Central

```bash
# Deploy to staging repository
mvn clean deploy

# If you get GPG errors, try:
export GPG_TTY=$(tty)
mvn clean deploy
```

### Release via Sonatype Nexus

1. Login to https://s01.oss.sonatype.org
2. Click "Staging Repositories" in left menu
3. Find your repository (search for "io.devutils")
4. Select it and click "Close"
5. Wait for validation (usually 5-10 minutes)
6. Click "Release"
7. Your artifact will sync to Maven Central in 2-4 hours

### Verify Publication

After 2-4 hours, check:
- https://search.maven.org/artifact/io.devutils/fluent-validators
- https://mvnrepository.com/artifact/io.devutils/fluent-validators

## 📣 Promoting Your Library

### 1. Update README Badges

Once published, the Maven Central badge will work automatically.

### 2. Social Media Announcement

Post on:
- Twitter/X: "Just published Fluent Validators - a lightweight Java validation library with zero dependencies! 🚀 #Java #OpenSource"
- LinkedIn: Share with your network
- Reddit: Post to r/java and r/programming

### 3. Write a Blog Post

Topics to cover:
- Why you created it
- How it solves validation problems
- Code examples
- Comparison with other libraries

### 4. Submit to Lists

- [awesome-java](https://github.com/akullpp/awesome-java)
- [awesome-maven](https://github.com/takari/maven-wrapper)
- Java Weekly newsletters

### 5. Create Demo Projects

Build example projects showing:
- Spring Boot REST API validation
- Jakarta EE validation
- Standalone Java application

### 6. Engage with Community

- Respond to GitHub issues quickly
- Welcome contributions
- Help users with questions
- Keep documentation updated

## 📊 Tracking Success

### Maven Central Stats

Check download statistics:
- https://search.maven.org/artifact/io.devutils/fluent-validators

### GitHub Metrics

Monitor:
- Stars
- Forks
- Issues
- Pull requests
- Traffic (in Insights)

### Resume Impact

Highlight:
- "Published open-source Java library to Maven Central"
- "X downloads per month"
- "X GitHub stars"
- "Active community with X contributors"

## 🎯 Next Steps

### Version 1.1.0 Ideas

Consider adding:
- Date/time validators
- File validators
- Network validators (IP, MAC address)
- Credit card validators
- More string validators (phone, postal code)
- Async validation support
- Spring Boot auto-configuration

### Community Building

- Set up GitHub Discussions
- Create a Discord/Slack channel
- Write tutorials and guides
- Create video demos
- Speak at meetups/conferences

## 💡 Tips for Success

1. **Quality First**: Keep tests passing, fix bugs quickly
2. **Documentation**: Keep README and examples up-to-date
3. **Responsiveness**: Reply to issues within 24-48 hours
4. **Consistency**: Follow semantic versioning
5. **Promotion**: Share updates regularly
6. **Patience**: Building a user base takes time

## 📞 Need Help?

- Maven Central Guide: https://central.sonatype.org/publish/
- GPG Guide: https://central.sonatype.org/publish/requirements/gpg/
- GitHub Issues: For technical questions

---

Good luck with your library! 🎉
