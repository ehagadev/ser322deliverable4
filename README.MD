# SER 322 Deliverable 4

This is a simple Spring Boot application developed for the SER 322 course deliverable 4.

## Overview

The project is a Spring Boot application that demonstrates the integration of Thymeleaf for UI rendering, H2 (an SQL db) database for data storage, and basic controller endpoints.

## Prerequisites

Before running the application, make sure you have the following installed:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or later)
- [Maven](https://maven.apache.org/download.cgi)

## Video Walk-Through Link

[YouTube_link](www.youtube.com)

## Getting Started

1. Clone the repository:

```
   git clone git@github.com:ehagadev/ser322deliverable4.git
```

## Navigate to the project directory:
- cd ser322deliverable4
- Run the application Mac: ./mvnw spring-boot:run
- Run the application Windows: mvnw spring-boot:run
  Access the application:
- Open your web browser and go to http://localhost:8080.
- To log into the H2 console, Open your web browser and go to http://localhost:8080/h2-console.
- - check application.properties (within resources dir) for specific configuration information

### Project Structure
- src/main/java/com/ser322deliverable4: Java source code
- src/main/resources: Configuration files, templates, and static resources

### Technologies Used
- Spring Boot
- Thymeleaf
- H2 Database

## System Architecture
- (UI/View) Forms in the front to accept query parameters, upon submission we hit an endpoint in a controller method where those input parameters are passed
- (UI/View) Controller consumes input parameters (either an object or primitive data types) and calls a service method based on that specific query and passed in parameters
- Service Method handles business logic, i.e.,  calls repo layer where actual query is defined with the proper SQL statement and annotated to a java method (see user repo for example). Service method passes response from the repo layer back up to the controller
- Define actual query statement on a java method (done in the repository interface)
- (UI/View) Controller takes service layer response and displays/renders template for user
