FROM maven:3.9.6-amazoncorretto-21 AS build
LABEL authors="puntawatsubhamani"

WORKDIR /app

COPY pom.xml .
COPY . /app

RUN mvn package -DskipTests

CMD ["java", "-jar", "./target/app.jar"]