# Project 1 Dockerfile: Personal Ledger
# Uses Temurin JDK 17 base image and runs the packaged Spring Boot jar.

FROM eclipse-temurin:17-jdk-jammy

# Build copies the fat JAR into the image. We expect the Maven package step
# to create target/*.jar before building this Docker image.
ARG JAR_FILE=target/*.jar
WORKDIR /app

# Copy the jar produced by `mvn package` into the image and name it app.jar
COPY ${JAR_FILE} app.jar

# Expose the port configured in application.yml (8081)
EXPOSE 8081

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
