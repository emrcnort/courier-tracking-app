FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY storeDtos.json /app/storeDtos.json

COPY target/courier-tracking-app-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
