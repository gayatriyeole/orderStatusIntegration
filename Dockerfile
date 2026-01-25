# ---------- Build stage ----------

FROM gradle:8.5-jdk17 AS build

WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon



# ---------- Runtime stage ----------

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar



ENV PORT=8080

EXPOSE 8080



ENTRYPOINT ["java","-XX:MaxRAMPercentage=75.0","-jar","app.jar”]

