# node18을 쓰고 build-stage로 명명합니다.
FROM node:18 as build-stage
# 작업 공간을 /app으로 정합니다.
WORKDIR /app
# package.json과 package-lock.json을 복사하여 종속성 설치를 진행합니다.
COPY package*.json ./
# npm install을 실행합니다.
RUN npm install
# npm sharp를 설치합니다.
RUN npm i sharp
# 소스코드를 복사합니다.
COPY . .
# npm run build를 실행합니다.
RUN npm run build
# 앱을 실행합니다.
CMD ["npm", "start"]