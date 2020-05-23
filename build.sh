#!/bin/bash
echo "Initiating maven Build!"
mvn clean install
echo "Building & running container!"
docker-compose up --build