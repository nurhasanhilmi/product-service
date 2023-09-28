FROM maven:3.9.4-eclipse-temurin-17-alpine as BUILDER

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn install -DskipTests

FROM eclipse-temurin:17.0.8.1_1-jre-alpine

COPY --from=BUILDER /app/target/*.jar /app/app.jar

EXPOSE 8080

WORKDIR /app
ENTRYPOINT ["java", "-jar", "app.jar"]
