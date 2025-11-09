# Dockerfile
FROM openjdk:21-jdk

WORKDIR /app

COPY . .

RUN ./gradlew build

CMD ["java", "-jar", "build/libs/quix-0.0.1-SNAPSHOT.jar"]
