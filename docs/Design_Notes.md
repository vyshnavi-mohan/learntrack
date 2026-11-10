
# Design Notes – LearnTrack

## 1. Project Overview

LearnTrack is a console-based Student and Course Management System developed using Core Java.

The application follows a simple layered architecture to separate data representation, business logic, and user interaction.

## 2. Architecture

The application is divided into the following layers:

### Entity Layer

Package: `com.airtribe.learntrack.entity`

Contains classes representing the application's data:

- Person
- Student
- Course
- Enrollment

### Service Layer

Package: `com.airtribe.learntrack.service`

Contains business logic and operations:

- StudentService
- CourseService
- EnrollmentService

### UI Layer

Package: `com.airtribe.learntrack.ui`

- Main: Provides the console menu and accepts user input.

### Exception Layer

Package: `com.airtribe.learntrack.exception`

Contains custom exceptions:

- EntityNotFoundException
- InvalidInputException

### Utility Layer

Package: `com.airtribe.learntrack.util`

Contains reusable utility classes:

- IdGenerator
- InputValidator

## 3. Object-Oriented Programming Concepts

### Encapsulation

Entity fields are declared private and accessed through public getters and setters.

### Inheritance

Student extends Person and inherits common properties such as ID, name, and email.

### Polymorphism

Student overrides the `getDisplayName()` method inherited from Person.

### Abstraction

Business operations are handled by service classes, keeping the UI separate from the underlying business logic.

## 4. Data Management

The application uses ArrayList to store students, courses, and enrollments.

Data is maintained in memory and is lost when the application terminates.

## 5. Class Relationships

- Student inherits from Person.
- StudentService manages Student objects.
- CourseService manages Course objects.
- EnrollmentService manages Enrollment objects.
- EnrollmentService uses StudentService and CourseService to validate enrollment requests.
- Enrollment stores student and course IDs to associate a student with a course.

## 6. Design Decisions

- Used ArrayList for simple in-memory data management.
- Used static methods in IdGenerator to generate unique IDs during an application run.
- Used custom runtime exceptions for invalid input and missing entities.
- Used LocalDate to store enrollment dates.
- Used deactivation instead of permanent deletion for students and courses.
- Prevented duplicate active enrollments for the same student and course.
- Separated UI, service, entity, exception, and utility responsibilities to improve maintainability.

## 7. Future Improvements

Possible future enhancements include:

- Database integration for persistent storage.
- User authentication and role-based access.
- Search and filtering enhancements.
- A graphical user interface or web application.
- Automated unit testing.
