#!/bin/bash
echo "====================="
echo "====GMAP BUILD======="
echo "====================="
echo "Initiating maven Build!"
mvn clean install
echo "Building & running container!"
docker-compose up --build
echo "====================="
echo "=======ALL SET======="
echo "====================="