# 기본 도커 이미지로부터 시작합니다.
FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY ./src/main/resources/stocks.json /src/main/resources/stocks.json
RUN apt-get update
ENTRYPOINT ["java", "-jar", "/app.jar"]