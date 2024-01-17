FROM openjdk:17-jdk-slim

WORKDIR /app

COPY /target/item-service*.jar .

RUN mv ./item-service*.jar  ./item-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "item-service.jar"]