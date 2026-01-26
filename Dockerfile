FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy Gradle wrapper and config for caching
COPY gradle gradle
COPY gradlew .
COPY build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew dependencies --no-daemon

# Copy source and build
COPY src src
RUN ./gradlew clean bootJar --no-daemon

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Use Render's PORT env variable
ENV PORT=8080
EXPOSE 8080

# Start Spring Boot app using JSON array syntax (fixes /bin/sh error)
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
