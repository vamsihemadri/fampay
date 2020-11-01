# Zoom Datastore

This project is the backend for zoom master data. Some of these include:

* Micro-market
* Client Address

### Setup

This project is a `gradle` based spring boot application. It uses [JDBI](http://jdbi.org/) for database access and [PostgreSQL](https://www.postgresql.org/) as the database.

**Quick Setup:** The project is on docker and can be run with below commands:

* Build docker image: `docker build --rm -t zoom-datastore . [--build-arg key=value]`. The following build-args are supported:
    * `PROPERTIES_PATH`: path of directory to read *application.properties* and *flyway.properties*. The path must be in the build context. Use tmp directory to copy file(s) which are not in context. Default: src/main/resources
    * `LOGIN_PROFILE`: The login environment to connect, Default: staging
    
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

**Dependencies:** The project uses some special dependencies:

* [Swagger UI](https://springfox.github.io/springfox/docs/current/#springfox-swagger-ui): When the code is deployed, it provides a UI which lists all APIs with their signatures. The UI is available at `{HOST}/swagger-ui.html`. It is disabled in production environment. 
* [Testcontainers](https://www.testcontainers.org/): Testcontainers is used to run JUnits by launching dependencies as containers. We launch a container for postgresql with postgis which is used in JUnit tests. The flyway plugin migrates the db to the latest version, to make it ready for JUnits.

**Custom tasks:** The following custom tasks have been added to maintain code quality:

* `gitExecutableHooks`: It is run before `processResources` and installs pre-push git hook. The pre-push hook validates code formatting and JUnits.

**Building Jar (With Java 11):** To build a JAR file with JAVA 11:

* Install Java 11 using `brew cask install java11`
* Create an Environment Variable `JAVA_11_HOME` with value as path to JDK's home folder. For mac, add this to your ~/.bash_profile `export JAVA_11_HOME=$(/usr/libexec/java_home -v11)`
* Run this command to build `./gradlew clean build -Dorg.gradle.java.home=$JAVA_11_HOME`

### Contribution guidelines

* All code must have corresponding JUnits and the code coverage must be above 90%.
* The pull-requests must be raised against *develop* branch
* All pull-requests must have an attached sonar report screenshot
* Code reviewers: Palash Goel, Chirag Bansal, Abhay Jindal, Ashu Gupta

### Who do I talk to?

* [Palash Goel](https://bitbucket.org/palashgoel7)
* [Chirag Bansal](https://bitbucket.org/chirag_rivigo)
* [Abhay Jindal](https://bitbucket.org/abhay_rivigo)
* [Ashu Gupta](https://bitbucket.org/ashu_rivigo)
* For feature wise code owners, please refer to `CODEOWNERS.md`.
