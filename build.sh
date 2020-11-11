#!/bin/sh
mvn clean package
docker-compose --project-directory ./ --env-file ./config/main.env build