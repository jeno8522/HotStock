# Start from the default Docker image.
FROM openjdk:17-jdk-slim

#Copy the application jar file and resources
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/stocks.json /src/main/resources/stocks.json

#Set the entrypoint
ENTRYPOINT ["java", "-jar", "/app.jar"]
