# SER 322 Deliverable 4

This is a simple Spring Boot application developed for the SER 322 course deliverable 4.

## Overview

The project is a Spring Boot application that demonstrates the integration of Thymeleaf for UI rendering, H2 (an SQL db) database for data storage, and basic controller endpoints.

## Prerequisites

Before running the application, make sure you have the following installed:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or later)
- [Maven](https://maven.apache.org/download.cgi)

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
