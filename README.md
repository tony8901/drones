# Drones
Musala project

## Introduction
There is a major new technology that is destined to be a disruptive force in the field of transportation: the drone. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure. Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

This project provides service through a REST API that allows clients to communicate with the drones (like a dispatch manager).

> Specific communication with the drone is outside the scope of this project.

## Technologies
* [Spring Framework](https://spring.io/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [H2 Database](http://h2database.com/html/main.html)

## Previous requirements
* [Java](https://www.java.com/en/)
* [Maven](https://maven.apache.org/)

## Clone the project
```
git clone https://github.com/tony8901/drones.git
```


## Compilation
To build the project, open a terminal at the root of the project and run the following command:
```
mvn clean install
```

## Execution
Open a terminal at the root of the project and run the following command:
```
java -jar target/drones-0.0.1-SNAPSHOT.jar
```
The application will start and be available at `http://localhost:8080`.

> The test data for Postman is in the drones/src/main/resources/postman/Musala Drones.postman_collection.json file.

## Swagger
To view the swagger API specifications go to: [http://localhost:8080/swagger-ui/index.html/](http://localhost:8080/swagger-ui/index.html/)

## Features
### StateDrone
* [GET Method]  All StateDrone - http://localhost:8080/api/admin/states
* [POST Method] Save a StateDrone - http://localhost:8080/api/admin/states
* [POST Method] Find a StateDrone by id - http://localhost:8080/api/admin/states/id/{id}
* [POST Method] Find a StateDrone by name - http://localhost:8080/api/admin/states/name/{name}
* [DELETE Method] Delete a StateDrone by id - http://localhost:8080/api/admin/states/delete/{id}

### ModelDrone
* [GET Method]  All ModelDrone - http://localhost:8080/api/admin/models
* [POST Method] Save a ModelDrone - http://localhost:8080/api/admin/models
* [POST Method] Find a ModelDrone by id - http://localhost:8080/api/admin/models/id/{id}
* [POST Method] Find a ModelDrone by name - http://localhost:8080/api/admin/models/name/{name}
* [DELETE Method] Delete a ModelDrone by id - http://localhost:8080/api/admin/models/delete/{id}

### Medications
* [GET Method]  All Medications - http://localhost:8080/api/admin/medications
* [POST Method] Save a Medication - http://localhost:8080/api/admin/medications
* [POST Method] Find a Medication by id - http://localhost:8080/api/admin/medications/id/{id}
* [POST Method] Find a Medication by name - http://localhost:8080/api/admin/medications/name/{name}
* [DELETE Method] Delete a Medication by id - http://localhost:8080/api/admin/medications/delete/{id}

### Drones
* [GET Method]  All Drones - http://localhost:8080/api/admin/drones
* [POST Method] Save a Drone - http://localhost:8080/api/admin/drones
* [POST Method] Find a Drone by id - http://localhost:8080/api/admin/drones/id/{id}
* [DELETE Method] Delete a Drone by id - http://localhost:8080/api/admin/drones/delete/{id}

### Management
* [POST Method] Charge a drone with a list of medications - http://localhost:8080/api/management/drones/{droneId}/charge?id={medicationId}&id={medicationId}
* [POST Method] Check the load of a drone by its id - http://localhost:8080/api/management/drones/{droneId}/loaded
* [GET Method] Checking available drones for loading - http://localhost:8080/api/management/drones/available
* [GET Method] Check drone battery level by its id - http://localhost:8080/api/management/drones/{droneId}/battery
