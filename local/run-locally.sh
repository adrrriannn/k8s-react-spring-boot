#!/bin/bash

mvn -f ../server/dto/pom.xml clean install

mvn -f ../server/indexing/pom.xml clean install docker:build

mvn -f ../server/notification/pom.xml clean install docker:build

mvn -f ../server/order/pom.xml clean install docker:build

mvn -f ../server/product/pom.xml clean install docker:build

docker-compose up

