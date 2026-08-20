# Spring Boot REST API

A RESTful API built with Spring Boot 4.1, featuring JWT authentication, role-based authorization, and modular architecture.

## Tech Stack

- **Java 21**, **Spring Boot 4.1.0**, **Maven**
- **Database:** PostgreSQL (production), H2 (testing)
- **Security:** Spring Security + JWT (JJWT 0.12.6)
- **Other:** Lombok, Spring Data JPA, Bean Validation

## Modules

| Module      | Description                                          |
|-------------|------------------------------------------------------|
| **User**    | User registration and CRUD operations                |
| **Auth**    | JWT-based login and authentication                   |
| **Product** | Product catalog management                           |
| **Order**   | Order placement and status tracking                  |
| **Address** | User address management (HOME / OFFICE / OTHER)      |

## API Endpoints

### Auth — `/api/v1/auth`

| Method | Path     | Description              | Access |
|--------|----------|--------------------------|--------|
| POST   | `/login` | Login and receive JWT    | Public |

### User — `/api/v1/users`

| Method | Path   | Description    | Access        |
|--------|--------|----------------|---------------|
| POST   | `/`    | Create user    | Public        |
| GET    | `/{id}`| Get user by ID | Authenticated |
| GET    | `/`    | List all users | Authenticated |
| PUT    | `/{id}`| Update user    | Authenticated |
| DELETE | `/{id}`| Delete user    | Authenticated |

### Product — `/api/v1/products`

| Method | Path   | Description       | Access        |
|--------|--------|-------------------|---------------|
| POST   | `/`    | Create product    | Authenticated |
| GET    | `/{id}`| Get product by ID | Authenticated |
| GET    | `/`    | List all products | Authenticated |
| PUT    | `/{id}`| Update product    | Authenticated |
| DELETE | `/{id}`| Delete product    | Authenticated |

### Order — `/api/v1/orders`

| Method | Path           | Description         | Access        |
|--------|----------------|---------------------|---------------|
| POST   | `/`            | Create order        | Authenticated |
| GET    | `/{id}`        | Get order by ID     | Authenticated |
| GET    | `/my-orders`   | Get my orders       | Authenticated |
| PATCH  | `/{id}/status` | Update order status | Admin         |

**Order Statuses:** `PLACED` → `CONFIRMED` → `SHIPPED` → `DELIVERED` → `CANCELLED`

### Address — `/api/v1/address`

| Method | Path             | Description              | Access        |
|--------|------------------|--------------------------|---------------|
| POST   | `/`              | Create address           | Authenticated |
| GET    | `/`              | List all addresses       | Authenticated |
| GET    | `/{id}`          | Get address by ID        | Authenticated |
| GET    | `/user/{userId}` | Get addresses by user ID | Authenticated |
| PUT    | `/{id}`          | Update address           | Authenticated |
| DELETE | `/{id}`          | Delete address           | Authenticated |

## Security

- Stateless JWT authentication (Bearer token in `Authorization` header)
- BCrypt password encoding
- Two roles: `USER` and `ADMIN`
- Public endpoints: user registration and login
- All other endpoints require authentication
- Method-level authorization with `@PreAuthorize`

## Database Schema

```
users ──< addresses
users ──< orders ──< order_items >── products
```

- All entities extend `BaseEntity` with auto-managed `createdAt` / `updatedAt` fields (JPA Auditing)

## Getting Started

### Prerequisites

- Java 21
- PostgreSQL
- Maven

### Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd springboot-rest-api
   ```

2. Configure the database in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/springboot_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Set JWT properties:
   ```properties
   jwt.secret=your-256-bit-secret-key
   jwt.expiration=3600000
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Run tests:
   ```bash
   ./mvnw test
   ```

## Error Handling

Consistent error responses via `@RestControllerAdvice`:

| Exception                    | HTTP Status |
|------------------------------|-------------|
| `ResourceNotFoundException`  | 404         |
| `DuplicateResourceException` | 409         |
| `InvalidCredentialsException`| 401         |
| `BadCredentialsException`    | 401         |
| Validation errors            | 400         |
