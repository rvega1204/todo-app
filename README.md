# Todos Application

A simple Todo application built with Java 21, Maven, Spring Boot, Spring Security, JPA, H2 Database, JSP, and Validation.

## Technologies Used

- **Java 21** - Core programming language
- **Maven** - Build and dependency management
- **Spring Boot** - Application framework
- **Spring Security** - Authentication and authorization
- **JPA (Java Persistence API)** - ORM for database interaction
- **H2 Database** - In-memory database for development and testing
- **JSP (JavaServer Pages)** - View layer for rendering pages
- **Validation** - Input validation and data integrity

## Getting Started

### Prerequisites

Ensure you have the following installed:
- Java 21
- Maven

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/rvega1204/todo-app.git
   cd todos-app
   ```
2. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```
3. The application will be available at:
   ```
   http://localhost:8080
   ```

## API Endpoints

The application exposes the following RESTful endpoints:

### List all Todos
**GET** `/list-todos`
- Returns a list of all todos.

### Add a Todo
**POST** `/add-todo`
- Creates a new todo item.

### Update a Todo
**PUT** `/update-todo/{id}`
- Updates an existing todo by its ID.

### Delete a Todo
**DELETE** `/delete-todo/{id}`
- Deletes a todo item by its ID.

## Security

- The application uses **Spring Security** for authentication and authorization.
- Users must authenticate to access certain endpoints.

## Database

- The application uses **H2 in-memory database**.
- Access the H2 Console at:
  ```
  http://localhost:8080/h2-console
  ```
- Default credentials:
    - **JDBC URL**: `jdbc:h2:mem:testdb`
    - **Username**: `sa`
    - **Password**: (leave blank)

## License

This project is licensed under the MIT License. Have fun changing the code, it's free!.

---

Enjoy building your Todos App! 🚀

