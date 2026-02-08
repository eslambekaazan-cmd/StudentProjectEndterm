Glow Up Tracker — Spring Boot REST API

A. Project Overview
This project is a Spring Boot RESTful API for a Glow Up Tracker system. The system allows managing self-care activities such as wellness and productivity tasks. The project is based on previous assignments that included JDBC, SOLID principles, and advanced OOP, and was transformed into a RESTful API using Spring Boot.

B. REST API Documentation

Endpoint list
/api/activities
/api/activities/{id}

HTTP methods
GET /api/activities – get all activities
GET /api/activities/{id} – get activity by id
POST /api/activities – create new activity
PUT /api/activities/{id} – update activity
DELETE /api/activities/{id} – delete activity

Sample JSON requests and responses

POST request example
name: Morning yoga
routineId: 1
activityType: WELLNESS
minutes: 15
intensity: medium

POST response example
id: 10
name: Morning yoga
category: WELLNESS
valid: true

GET response example
id: 1, name: Stretching, category: WELLNESS
id: 7, name: Plan day, category: PRODUCTIVITY

Postman screenshots
Screenshots demonstrating GET, POST, and DELETE requests are provided in the docs/screenshots folder.

C. Design Patterns Section

Singleton Pattern
The Singleton pattern is used for database connection configuration to ensure that only one instance of the database connection exists during application runtime.

Factory Pattern
The Factory pattern is used to create different types of activities such as WellnessActivity and ProductivityActivity based on the activity type.

Builder Pattern
The Builder pattern is used to construct complex objects such as Routine with optional parameters.

D. Component Principles Section

REP (Reuse/Release Equivalence Principle)
Repository and service layers are organized as reusable modules.

CCP (Common Closure Principle)
Classes that are likely to change together are grouped within the same packages.

CRP (Common Reuse Principle)
Modules depend only on interfaces that they actually use.

E. SOLID & OOP Summary

Single Responsibility Principle is applied so that each class has only one responsibility.
Open/Closed Principle is applied so that new activity types can be added without modifying existing code.
Dependency Inversion Principle is applied so that services depend on repository interfaces rather than concrete implementations.
Abstraction, inheritance, and polymorphism are used throughout the project.

F. Database Schema

The system uses a relational database with the following main tables: activities, routines, and routine_types. Activities are connected to routines using foreign keys.

G. System Architecture Diagram

The application follows a layered architecture: Controller layer communicates with Service layer, which communicates with Repository layer, which interacts with the Database. An architecture or UML diagram can be added if required.

H. Instructions to Run the Spring Boot Application

Open the project in IntelliJ IDEA.
Configure database connection settings in the DatabaseConnection class.
Run the Application.java file.
Access the API using the URL [http://localhost:8080/api/activities](http://localhost:8080/api/activities).

I. Reflection Section

This project helped me understand how to transform a layered Java application into a RESTful API. I learned how to apply design patterns in practice, work with Spring Boot, and test REST endpoints using Postman.

