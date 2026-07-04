# Use the official Eclipse Temurin Java 25 runtime
FROM eclipse-temurin:25-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled executable JAR file from your target folder into the container
COPY target/college_event_web-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application is running on
EXPOSE 8084

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]