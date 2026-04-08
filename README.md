# Student Service

A Spring Boot REST API for basic employee management (create, list, update, delete) using Spring Web, Spring Data JPA, and MySQL.

## Tech Stack

- Java 17
- Spring Boot 3.5.13
- Spring Web
- Spring Data JPA
- MySQL
- Lombok
- Maven

## Project Structure

```text
src/main/java/com/cloudtech
  StudentServiceApplication.java
  controller/EmployeeController.java
  entity/Employee.java
  model/
    EmployeeAddRequest.java
    EmployeeAddResponse.java
    EmployeeShowResponse.java
  repository/EmployeeRepository.java
  service/EmployeeService.java
```

## API Base Path

All employee APIs are under:

```text
/employee
```

## Endpoints

### 1) Get All Employees

- Method: `GET`
- URL: `/employee/show`
- Response: `200 OK`

Example response:

```json
[
  {
    "id": 1,
    "name": "Alice",
    "department": "Engineering",
    "salary": 75000.0
  },
  {
    "id": 2,
    "name": "Bob",
    "department": "HR",
    "salary": 50000.0
  }
]
```

### 2) Add Employee

- Method: `POST`
- URL: `/employee/add`
- Request body:

```json
{
  "name": "Alice",
  "department": "Engineering",
  "salary": 75000.0
}
```

- Response: `200 OK`

Example response:

```json
{
  "id": 1,
  "name": "Alice",
  "department": "Engineering",
  "salary": 75000.0
}
```

### 3) Update Employee

- Method: `PUT`
- URL: `/employee/update/{id}`
- Request body:

```json
{
  "name": "Alice Updated",
  "department": "Product",
  "salary": 80000.0
}
```

- Response: `200 OK`
- Error: `404 Not Found` if employee does not exist

### 4) Delete Employee

- Method: `DELETE`
- URL: `/employee/delete/{id}`
- Response: `200 OK`
- Error: `404 Not Found` if employee does not exist

## Prerequisites

- JDK 17+
- Maven 3.9+ (or use Maven Wrapper)
- MySQL 8+

## Database Configuration

Current datasource values in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/JPAEMS
spring.datasource.username=root
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Create the database before running:

```sql
CREATE DATABASE JPAEMS;
```

## Run the Application

Using Maven Wrapper (recommended):

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Using installed Maven:

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

## Build and Test

```bash
./mvnw clean test
./mvnw clean package
```

Windows PowerShell:

```powershell
.\mvnw.cmd clean test
.\mvnw.cmd clean package
```

## Quick cURL Examples

Add employee:

```bash
curl -X POST http://localhost:8080/employee/add \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","department":"Engineering","salary":75000}'
```

Get all employees:

```bash
curl http://localhost:8080/employee/show
```

Update employee:

```bash
curl -X PUT http://localhost:8080/employee/update/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Updated","department":"Product","salary":80000}'
```

Delete employee:

```bash
curl -X DELETE http://localhost:8080/employee/delete/1
```

## Notes

- The schema is managed with `spring.jpa.hibernate.ddl-auto=update`.
- Response DTOs currently mirror entity fields (`id`, `name`, `department`, `salary`).
- For production, move database credentials to environment variables or a secure secret manager.
