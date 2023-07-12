FROM openjdk:17-jdk-alpine

COPY build/libs/arth-0.0.1-SNAPSHOT.jar arch.jar

ENTRYPOINT ["java","-jar","arch.jar"]