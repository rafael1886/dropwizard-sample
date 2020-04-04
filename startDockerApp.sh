#!/bin/bash

docker-compose down

d=$(docker images wizard-rest-app -q)
if [ -n "$d" ]; then
  echo "removing image wizard-rest-app $d ..."
  docker image rm wizard-rest-app
fi

gradle clean build runShadow
docker-compose up

# Run containers in the background
#docker-compose up -d