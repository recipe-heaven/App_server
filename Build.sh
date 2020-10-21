#!/bin/sh
if [ $(docker ps -a -f name=recipe_heaven | grep -w recipe_heaven | wc -l) -eq 1 ]; then
  docker rm -f recipe_heaven
fi
mvn clean package
docker build -t no.twct.recipeheaven/appserver .
docker-compose up