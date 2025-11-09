# Dockerfile
FROM openjdk:21

WORKDIR /app

COPY . .

# Gradle Wrapper ausführbar machen
RUN chmod +x gradlew

# Build durchführen
RUN ./gradlew build --no-daemon

# Startbefehl
CMD ["java", "-jar", "build/libs/quix-0.0.1-SNAPSHOT.jar"]
