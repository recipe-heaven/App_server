#!/bin/sh
mvn clean package
docker-compose --project-directory ./ --env-file ./config/main.env down
docker-compose -f docker-compose.yaml -f docker-compose.dev.yaml --project-directory ./ --env-file ./config/main.env up