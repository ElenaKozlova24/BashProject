#!/bin/bash

if [ "$#" -ne 3 ]; then
    echo "Использование: $0 <URL стенда> <имя браузера> <версия браузера>"
    exit 1
fi

URL_STAND=$1
BROWSER_NAME=$2
BROWSER_VERSION=$3
SELENIUM_HUB_URL="http://localhost:4444/wd/hub"

mvn test -DurlStand=$URL_STAND -DbrowserName=$BROWSER_NAME -DbrowserVersion=$BROWSER_VERSION -Dselenium.hub.url=$SELENIUM_HUB_URL

if [ $? -eq 0 ]; then
    echo "Тесты выполнены успешно"
else
    echo "Тесты завершились с ошибками"
fi


