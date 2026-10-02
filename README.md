# Spring Boot Student CRUD Demo

A simple **Spring Boot CRUD application** for managing student records using REST APIs, Spring Data JPA, and MySQL.

This project is being developed incrementally to learn and implement real-world Spring Boot concepts step by step.

## 🚀 Current Features

* Create a student
* Get all students
* Get student by ID
* Update student
* Delete student
* MySQL database integration
* Spring Data JPA
* Basic soft-delete support
* Unique mobile number validation at the database level
* Duplicate mobile number check before creating a student

## 🛠️ Tech Stack

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Hibernate**
* **MySQL**
* **Maven**
* **Postman**
* **Git & GitHub**

## 📁 Project Structure

```text
src/main/java/com/itihas/crudSpringBootDemo/
│
├── controller/
│   └── StudentController.java
│
├── service/
│   └── StudentService.java
│
├── repository/
│   └── StudentRepository.java
│
├── entity/
│   └── Student.java
│
├── exception/
│   └── ...
│
└── CrudSpringBootDemoApplication.java
```

## 🗄️ Database Configuration

The application uses MySQL as the database.

Configure the database connection in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Create the database before starting the application:

```sql
CREATE DATABASE studentdb;
```

## 📌 API Endpoints

| Method | Endpoint              | Description       |
| ------ | --------------------- | ----------------- |
| POST   | `/api/student/create` | Create a student  |
| GET    | `/api/student`        | Get all students  |
| GET    | `/api/student/{id}`   | Get student by ID |
| PUT    | `/api/student/{id}`   | Update a student  |
| DELETE | `/api/student/{id}`   | Delete a student  |

> Endpoint paths may evolve as the project is improved.

## 🧪 Testing

The APIs can currently be tested using **Postman**.

Example create request:

```json
{
    "name": "John",
    "age": 25,
    "email": "john@gmail.com",
    "roll_no": 101,
    "subject": "Java",
    "mobileNumber": "9876543210"
}
```

## 🔄 Development Roadmap

This project will gradually be enhanced with production-style Spring Boot concepts.

### Planned Improvements

* [ ] Bean validation
* [ ] Global exception handling
* [ ] Standard API response structure
* [ ] DTO layer
* [ ] Pagination and sorting
* [ ] Search and filtering
* [ ] Logging
* [ ] Swagger/OpenAPI documentation
* [ ] JUnit and Mockito testing
* [ ] Integration testing
* [ ] Database migration with Flyway
* [ ] Spring Security
* [ ] JWT authentication
* [ ] Role-based authorization
* [ ] Docker
* [ ] CI/CD with GitHub Actions

The goal is to evolve this project from a basic CRUD application into a **production-style Spring Boot application** while learning each concept incrementally.

## 📚 Learning Approach

Each major feature will be developed separately and tracked through Git commits and branches.

Example:

```text
Basic CRUD
    ↓
Validation
    ↓
Exception Handling
    ↓
DTOs
    ↓
Pagination
    ↓
Testing
    ↓
Security
    ↓
Docker
    ↓
CI/CD
```

## 👨‍💻 Author

**Itihas Verma**

GitHub: [itihask56](https://github.com/itihask56)
