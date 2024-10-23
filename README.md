# Plant Friend Spring Boot :seedling:
A Spring Boot backend implemented in Java, connected to a PostgreSQL database.

## Configuration
To connect the application to a postgresql DB some values need to be set in `application.properties`
- `spring.datasource.url`: The postgresql database url
- `spring.datasource.username`: DB user
- `spring.datasource.password`: DB password (encrypted)
- `jasypt.encryptor.password`: Encryption password referring to the `JASYPT_ENCRYPTOR_PASSWORD` env var.

The DB password is in the following format `ENC(*************)`, because it is encrypted for security 
using the [jasypt-maven-plugin](https://github.com/ulisesbocchio/jasypt-spring-boot)
and decrypted on application startup using the `jasypt-spring-boot-starter` dependency. 

To encrypt the DB password use the `jasypt-maven-plugin` like so:
```
mvn jasypt:encrypt-value -Djasypt.encryptor.password="theEncryptionPassword" -Djasypt.plugin.value="theValueYouWantToEncrypt"
```

To run the application, the encryption password should be provided in the `JASYPT_ENCRYPTOR_PASSWORD` env var. 
In [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/) this can be done on the Run/Debug configuration window:

![Run Configuration - env vars](/assets/run_configuration.png)