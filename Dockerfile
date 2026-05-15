# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copy the project files
COPY pom.xml .
COPY src ./src
# Compile the Java app, then IMMEDIATELY delete the extra/backup .jar files
RUN mvn clean package -DskipTests && rm -f target/*-plain.jar target/original-*.jar

# Stage 2: Package the application into a lightweight runtime container
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Because we deleted the backups, this wildcard safely copies the one remaining file
COPY --from=build /app/target/*.jar app.jar
# Expose the standard Spring Boot web port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]