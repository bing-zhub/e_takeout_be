#! /bin/bash
mvn clean package -DskipTests=true
mv ./target/*.jar ~/Desktop/dist/server.jar
