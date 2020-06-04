FROM openjdk:8-jdk-alpine

WORKDIR /service
COPY ./target/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
