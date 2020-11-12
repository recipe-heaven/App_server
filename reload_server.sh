#!/bin/sh
./maven_build.sh
docker-compose --env-file ./config_files/main.env up --build -V -d server