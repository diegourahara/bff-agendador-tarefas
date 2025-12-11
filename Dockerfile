FROM maven:3.9-eclipse-temurin-17-alpine AS Build

WORKDIR /app
COPY . .
RUN mvn clean install -DskipTest

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/app.jar"]

