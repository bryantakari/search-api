FROM maven:3.8.6-amazoncorretto-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn dependency:go-offline -B



RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/geonames-1.0.jar geonames.jar

EXPOSE 8080
CMD ["java", "-Xms512m", "-Xmx2048m","-jar","geonames.jar"]