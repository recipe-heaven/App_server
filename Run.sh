#!/bin/sh
docker container rm -f recipe_heaven 
docker run --network=recipe_heaven_backend -p 9080:9080 -p 9443:9443 --user 1000 --name recipe_heaven -v $(pwd)/target:/config/dropins -v $(pwd)/server:/opt/ol/wlp/output/defaultServer/ no.twct.recipeheaven/appserver 