#!/bin/bash

mvn -f ../server/dto/pom.xml clean install -Dmaven.test.skip=true

mvn -f ../server/indexing/pom.xml clean package -Dmaven.test.skip=true docker:build

mvn -f ../server/notification/pom.xml clean package -Dmaven.test.skip=true docker:build

mvn -f ../server/order/pom.xml clean package -Dmaven.test.skip=true docker:build

mvn -f ../server/product/pom.xml clean package -Dmaven.test.skip=true docker:build

sh build-web-nginx.sh
#
#docker-compose up -d
