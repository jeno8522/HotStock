# Start from the default Docker image.
FROM openjdk:17-jdk-slim

# Install necessary packages and Chrome
RUN apt-get -y update

RUN apt -y install wget

RUN apt -y install unzip

RUN apt -y install curl

RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb

RUN apt -y install ./google-chrome-stable_current_amd64.deb

RUN wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/` curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`/chromedriver_linux64.zip

RUN unzip /tmp/chromedriver.zip chromedriver -d /usr/bin

#Copy the application jar file and resources
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/stocks.json /src/main/resources/stocks.json
#COPY ./src/main/resources/chromedriver_linux /usr/bin/chromedriver
RUN chmod +x /usr/bin/chromedriver


#Set the entrypoint
ENTRYPOINT ["java", "-jar", "/app.jar"]
