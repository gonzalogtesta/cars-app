# README #

SysOne - Cars administration app


App deployed under: http://apps-sysone-app.apps.us-east-2.online-starter.openshift.com/

Using MongoDB Atlas instance for DB persistance.

SpringBoot runs under 8080 locally, but it's configured to run under port 80 in OpenShift.


## Technologies ##

 - Maven
 - SpringBoot
 - MongoDB
 - Docker

## Setup ##

Project setup with maven:

> mvn install

App requires Docker in order build and deploy the application in openshift.

Image build with:

> mvn install dockerfile:build

Image build with mongo configuration:

> mvn -Dmongo.config="-Dspring.data.mongodb.uri=mongodb+srv://<user>:<pass>@<server>/<db>" install dockerfile:build

Upload to docker hub:

> docker push

Deploy to openshift:

> oc new-app --docker-image="goez/apps:latest"

### Testing ###

Run application tests:

> mvn test

Insomnia Workspace under folder:

> *src/test/insomnia*