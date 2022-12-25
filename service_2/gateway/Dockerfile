#define base docker image
FROM openjdk:11
ARG JAR_FILE=target/gateway-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]