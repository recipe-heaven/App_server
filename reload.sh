#! /bin/bash

#mvn -f Docker_manager/ clean compile package shade:shade && mvn -f ServerApi/ clean compile package &&
docker-compose -f docker-compose.new.yaml --project-directory ./ --env-file ./config_files/main.env up   --build --detach -V war_builder