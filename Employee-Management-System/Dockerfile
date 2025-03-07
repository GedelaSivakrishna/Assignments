# Fetch openJDK from Docker Hub
FROM openjdk:17-slim

# Copy the jar file to app.jar
COPY target/Employee-Management-System.jar app.jar

# Command to run the Jar file
ENTRYPOINT ["java", "-jar", "app.jar"]