#! /bin/bash
mvn clean package -DskipTests=true
rm -f ~/Desktop/dist/server.jar
mv ./target/*.jar ~/Desktop/dist/server.jar
scp ~/Desktop/dist/server.jar root@47.93.56.128:/root/eyao