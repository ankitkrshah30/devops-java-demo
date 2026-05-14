# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copy the project files
COPY pom.xml .
COPY src ./src
# Compile the Java application and skip tests to speed up the pipeline
RUN mvn clean package -DskipTests

# Stage 2: Package the application into a lightweight runtime container
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy the compiled .jar file from the build stage
COPY --from=build /app/target/spring-boot-thymeleaf-*.jar app.jar
# Expose the standard Spring Boot web port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]