# Use lightweight Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the JAR
RUN ./mvnw clean package -DskipTests

# Expose port (matches app.yml)
EXPOSE 8089

# Run the JAR
ENTRYPOINT ["java", "-jar", "target/email-service-0.0.1-SNAPSHOT.jar"]
