# Publishing to Maven Central

## Quick Start Checklist

Before publishing, ensure you have:
- [ ] Sonatype JIRA account
- [ ] Claimed your groupId (io.devutils)
- [ ] GPG keys set up
- [ ] Maven settings.xml configured
- [ ] Updated pom.xml with your information
- [ ] All tests passing
- [ ] Documentation complete

## Detailed Steps

### 1. Create Sonatype Account

1. Go to https://issues.sonatype.org
2. Create a JIRA account
3. Create a new issue to claim your groupId:
   - Project: Community Support - Open Source Project Repository Hosting (OSSRH)
   - Issue Type: New Project
   - Group Id: io.devutils
   - Project URL: https://github.com/[your-username]/fluent-validators
   - SCM URL: https://github.com/[your-username]/fluent-validators.git

4. Wait for approval (usually within 2 business days)

### 2. Set Up GPG Keys

Generate GPG key pair:
```bash
gpg --gen-key
```

List your keys:
```bash
gpg --list-keys
```

Publish your public key:
```bash
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID
```

Export your key for backup:
```bash
gpg --export-secret-keys YOUR_KEY_ID > private-key.asc
```

### 3. Configure Maven Settings

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

### 4. Update pom.xml

Replace all placeholders in pom.xml:
- `[your-username]` → Your GitHub username
- `[your-id]` → Your developer ID
- `[Your Name]` → Your full name
- `[your-email]` → Your email address

### 5. Build and Test

Run full build with tests:
```bash
mvn clean verify
```

This will:
- Compile the code
- Run all tests
- Generate sources JAR
- Generate Javadoc JAR
- Sign all artifacts with GPG

### 6. Deploy to Maven Central

Deploy to staging repository:
```bash
mvn clean deploy
```

Or deploy without running tests again:
```bash
mvn deploy -DskipTests
```

### 7. Release via Sonatype Nexus

1. Login to https://s01.oss.sonatype.org
2. Click "Staging Repositories" in left menu
3. Find your repository (io.devutils-XXXX)
4. Select it and click "Close"
5. Wait for validation to complete
6. Click "Release"
7. Your artifact will sync to Maven Central within 2-4 hours

### 8. Verify Publication

After 2-4 hours, check:
- Maven Central: https://search.maven.org/artifact/io.devutils/fluent-validators
- MVN Repository: https://mvnrepository.com/artifact/io.devutils/fluent-validators

## Releasing New Versions

### Update Version

Update version in pom.xml:
```xml
<version>1.1.0</version>
```

### Create Git Tag

```bash
git tag -a v1.1.0 -m "Release version 1.1.0"
git push origin v1.1.0
```

### Deploy

```bash
mvn clean deploy
```

### Update Documentation

- Update CHANGELOG.md
- Update README.md with new version
- Create GitHub release with release notes

## Troubleshooting

### GPG Signing Fails

If GPG signing fails:
```bash
export GPG_TTY=$(tty)
```

Or add to pom.xml:
```xml
<configuration>
    <gpgArguments>
        <arg>--pinentry-mode</arg>
        <arg>loopback</arg>
    </gpgArguments>
</configuration>
```

### Validation Errors

Common validation errors:
- Missing Javadoc JAR → Ensure maven-javadoc-plugin is configured
- Missing sources JAR → Ensure maven-source-plugin is configured
- Missing POM information → Check all required POM fields
- Unsigned artifacts → Ensure GPG plugin is configured

### Deployment Fails

If deployment fails:
- Check your credentials in settings.xml
- Verify your Sonatype account is approved
- Ensure you have permission for the groupId
- Check network connectivity

## Promotion Tips

### After Publishing

1. **Update README badges** with actual Maven Central link
2. **Announce on social media**
   - Twitter/X with #Java #OpenSource
   - LinkedIn
   - Reddit (r/java, r/programming)
3. **Write a blog post** about the library
4. **Submit to awesome lists**
   - awesome-java
   - awesome-maven
5. **Create demo projects** showing usage
6. **Respond to issues** quickly
7. **Keep documentation updated**

### SEO Optimization

- Use good keywords in description
- Create comprehensive documentation
- Add code examples
- Link from other projects
- Get listed in package directories

### Community Building

- Be responsive to issues and PRs
- Welcome contributions
- Maintain a changelog
- Follow semantic versioning
- Write clear commit messages

## Resources

- [Sonatype OSSRH Guide](https://central.sonatype.org/publish/publish-guide/)
- [Maven Central Requirements](https://central.sonatype.org/publish/requirements/)
- [GPG Guide](https://central.sonatype.org/publish/requirements/gpg/)
- [Maven Deploy Plugin](https://maven.apache.org/plugins/maven-deploy-plugin/)

