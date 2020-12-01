# Microservice_Asigment  

  

This is our microservice for car ratings as described in task 1. It works together with the other car projects that Dora provided. 


### Deployment on Kubernetes
We are using Kubernetes via Docker Desktop. 
Our own micro service as well as Dora's Car projects are dockerized with a simple dockerfile:
```YML
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
And deployed on kubernetes. 

### Observations
We could not get Eureka to work together with Kubernetes, so we ended up not using it. 
Kubernetes deployments can be exposed as a service, and with that we balance load (instead of using Ribbon as was provided)




### MongoDB
MongoDB is deployed on a digitalocean. When the service's is started up in kubernetes or on localhost you can hit the endpoints with ip and these endpoints:

* the ratings of a particular car
 URI: {ip}/car/1

- the ratings they selves have given earlier
 URI: {ip}/user/1
