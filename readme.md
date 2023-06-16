# Welcome to Mediscreen
### A diabetes prediction app

## :rocket: Getting Started

<div display="inline-block">
    <img src="https://img.shields.io/badge/Java-11-green"/>
    <img src="https://img.shields.io/badge/Spring%20Boot-2.7.1-brightgreen"/>
    <img src="https://img.shields.io/badge/Maven-3.9.0-red"/>
    <img src="https://img.shields.io/badge/MySQL-8.0.32-blue"/>
    <img src="https://img.shields.io/badge/MongoDB-4.2.24-lightgreen"/>
</div>

## :hammer_and_wrench: Configuration

For this project, you will need :

* Java 11
* Mysql - Create mediscreen database
* MongoDB
* Docker Desktop

On your IDE, configure each projects with your parameters
In application.properties file, located in : src/main/resources

* server.port

For user microservice with Mysql:
* spring.datasource.url
* spring.datasource.username
* spring.datasource.password


For notes microservice with MongoDB: 

* spring.data.mongodb.port
* spring.data.mongodb.database

Merge user and notes project configuration for configure report microservice

* When you start the application for a first time, in user microservice, switch spring.jpa.hibernate.ddl-auto to create ( switch at "upgrade" for the next time )

* Check for each project the dockerfile and you have to change the docker-compose.yml 
## :black_nib: First Step
For each microservice : 

Go to one microservice and build a jar with : ````mvn clean install````

After that, build docker image  with : ``` docker build -t ms-user .```

For the next step, back to Mediscreen folder and start ````docker-compose up````

## :books: Documentation

After ````docker-compose```` is launched, you have 4 microservices : 

* 9000 : front
* 9001 : user
* 9002 : notes
* 9003 : report

For each microservice, the API documentation is available to : ```http://localhost:<port>/swagger-ui/```

## :fire: Let's go

* Now, you can register as a doctor. You need a login step
* After the login, you can create a new patient
* The notes can put in the patient profile
* With notes en patient information's, you can generate a report of risk for diabetes
>  Note  
>  This project is an exercise of OpenClassRooms Java training