# Fampay Assignment

This project is in response to the fampay assignemnt.

### Setup

This project is a `gradle` based spring boot application. It uses [JDBI](http://jdbi.org/) for database access and [PostgreSQL](https://www.postgresql.org/) as the database.

**Quick Setup:** The project is on docker and can be run with below commands:

* Build docker image: `docker build --rm -t zoom-datastore . [--build-arg key=value]`. The following build-args are supported:
    * `PROPERTIES_PATH`: path of directory to read *application.properties* and *flyway.properties*. The path must be in the build context. Use tmp directory to copy file(s) which are not in context. Default: src/main/resources
    
    
* Run docker container: `docker run -p 8080:8080 zoom-datastore` 

**Gradle Wrapper** The project uses gradle wrapper:

* To update gradle wrapper version, update the version in `wrapper` section in `build.gradle` and run `gradle wrapper`

**Plugins:** The project uses the below plugins:

* [Spring Boot plugin](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/)
* [Spotless plugin](https://github.com/diffplug/spotless/tree/master/plugin-gradle): For code formatting (check and apply)
    * Check: `./gradlew spotlessCheck`
    * Format code: `./gradlew spotlessApply`
* [Flyway plugin](https://flywaydb.org/documentation/gradle/): For db migrations
    * Setup DB: `./gradlew flywayMigrate -i -Dflyway.url="jdbc:postgresql://{HOST}:{PORT}/{DB_NAME}?currentSchema={SCHEMA_NAME}" -Dflyway.user={USER} -Dflyway.password={PASSWORD}`


**Custom tasks:** The following custom tasks have been added to maintain code quality:

* `gitExecutableHooks`: It is run before `processResources` and installs pre-push git hook. The pre-push hook validates code formatting apart from verifying that it is a clean build.


