#!/bin/sh
./mavin_build.sh
docker-compose --project-directory ./ --env-file ./config/main.env down
docker-compose -f docker-compose.yaml -f docker-compose.dev.yaml --project-directory ./ --env-file ./config/main.env up