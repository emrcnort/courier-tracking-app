FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY stores.json /app/stores.json

COPY target/courier-tracking-app-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
