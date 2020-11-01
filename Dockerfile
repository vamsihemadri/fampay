FROM gradle:5.2 as builder
COPY --chown=gradle:gradle  .  /home/gradle/src
ARG PROPERTIES_PATH=src/main/resources/docker
RUN echo $PROPERTIES_PATH
COPY $PROPERTIES_PATH/application.properties /home/gradle/src/application.properties
COPY $PROPERTIES_PATH/flyway.properties /home/gradle/src/flyway.properties
COPY src/main/resources/logback-stg.xml /home/gradle/src/docker-logback.xml
WORKDIR /home/gradle/src
RUN gradle build -x test -i -Dflyway.configFiles=/home/gradle/src/flyway.properties flywayRepair flywayMigrate -i

FROM openjdk:11-jre-slim
RUN mkdir /etc/zoom/
WORKDIR /code/
COPY --from=builder  /home/gradle/src/build/libs/zoom-datastore.jar /code/
COPY --from=builder /home/gradle/src/docker-logback.xml /code/docker-logback.xml
COPY --from=builder  /home/gradle/src/application.properties /etc/zoom/application.properties
ARG LOGIN_PROFILE=staging
RUN echo $LOGIN_PROFILE
ENV JAVA_OPTS="-Xms512m -Xmx512m -Dspring.config.location=/etc/zoom/ -Dlogin.profiles.active=$LOGIN_PROFILE"
RUN echo $JAVA_OPTS
ENTRYPOINT exec java -jar $JAVA_OPTS zoom-datastore.jar