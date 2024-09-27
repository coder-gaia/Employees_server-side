# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
# Make sure to adjust the path to your JAR file if necessary
COPY target/employee-server-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on (change if necessary)
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
