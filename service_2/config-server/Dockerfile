#define base docker image
FROM openjdk:11
ARG JAR_FILE=target/config-server-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} config.jar
ENTRYPOINT ["java", "-jar", "config.jar"]