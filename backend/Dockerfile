# Start from the default Docker image.
FROM openjdk:17-jdk-slim

# Install necessary packages and Chrome
RUN apt-get update \
    && apt-get install -y wget curl unzip software-properties-common \
    && wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && dpkg -i google-chrome-stable_current_amd64.deb; apt-get -fy install

#Copy the application jar file and resources
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/stocks.json /src/main/resources/stocks.json
COPY ./src/main/resources/chromedriver_linux /src/main/resources/chromedriver_linux

#Set the entrypoint
ENTRYPOINT ["java", "-jar", "/app.jar"]
