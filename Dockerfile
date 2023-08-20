# Build: docker build -t spring-boot-docker .
FROM maven:3-openjdk-17 AS build-image
WORKDIR /to-build-app
COPY . .
RUN mvn dependency:go-offline
RUN ./mvn clean package -DskipTests
COPY target/*.jar /to-build-app/app.jar

# Path: Dockerfile
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build-image /to-build-app/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]