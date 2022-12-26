#define base docker image
FROM openjdk:11
ARG JAR_FILE=target/eureka-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]