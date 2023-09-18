#1

EXIST_BLUE=$(docker-compose -p mydowson-blue -f docker-compose.blue.yaml ps | grep Up)

if [ -z "$EXIST_BLUE" ]; then
        docker-compose -p mydowson-blue -f /var/lib/jenkins/workspace/A206/docker-compose.blue.yaml up -d
        BEFORE_COLOR="green"
        AFTER_COLOR="blue"
        BEFORE_PORT=8081
        AFTER_PORT=8080
else
        docker-compose -p mydowson-green -f /var/lib/jenkins/workspace/A206/docker-compose.green.yaml up -d
        BEFORE_COLOR="blue"
        AFTER_COLOR="green"
        BEFORE_PORT=8080
        AFTER_PORT=8081
fi

echo "${AFTER_COLOR} server up (port:${AFTER_PORT})"

#2
for cnt in {1..10}
do
        echo "서버 응답 확인중(${cnt}/10)";
        UP=$(curl -s http://localhost:${AFTER_PORT}/api/server/check)
        if [ -z "${UP}" ]
                then
                        sleep 5
                        continue
                else
                        break
        fi
done

if [ $cnt -eq 10 ]
        then
                echo "서버가 정상적으로 구동되지 않았습니다."
                exit 1
fi


# 3
sudo sed -i "s/${BEFORE_PORT}/${AFTER_PORT}/" /etc/nginx/conf.d/service-url.inc
sudo nginx -s reload
echo "Deploy Completed!!"


# 4
echo "$BEFORE_COLOR server down(port:${BEFORE_PORT})"
docker-compose -p mydowson-${BEFORE_COLOR} -f docker-compose.${BEFORE_COLOR}.yaml down