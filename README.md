# ♟ Chess Stats API

![Status](https://img.shields.io/badge/status-active_development-blue)
![Release](https://img.shields.io/badge/release-v0.1-green)
![QA](https://img.shields.io/badge/QA-validated-success)

Java Spring Boot + PostgreSQL REST API for chess game storage and statistics analysis.

This project is structured to simulate a realistic enterprise backend workflow with controlled releases, Flyway-managed schema migrations, and structured QA documentation.

**Release 0.2 is implemented (awaiting QA validation).**

---

## 🎯 Project Purpose

This project is built as a production-style backend system to:

- Establish a clean, version-controlled database schema
- Enforce strong SQL-level data integrity
- Demonstrate migration discipline with Flyway
- Simulate structured QA workflow and release validation
- Provide an interview-ready backend + QA portfolio project

Primary focus: **enterprise structure + QA discipline before feature expansion.**

---

## 🧱 Architecture (Current State – v0.2)

Current implementation includes:

- Spring Boot application bootstrap
- Embedded Apache Tomcat (port 8080)
- Dockerized PostgreSQL 16
- Flyway-managed schema (V1 baseline)
- Players API (Controller + Service + Repository)
- Global exception handling (`@controllerAdvice`)
- OpenAPI / Swagger documentation
- Health endpoint

Layered architecture now implemented:

- Controller layer – REST endpoints  
- Service layer – Business logic  
- Domain layer – Entities
- Repository layer – Spring Data JPA  
- Database layer – PostgreSQL + Flyway  

---

## 🗄️ Database Schema

### Entities

### Player
- id (UUID, PK)
- username (unique)
- created_at

### Game
- id (UUID, PK)
- white_player_id (FK → players)
- black_player_id (FK → players)
- played_at
- game_result
- created_at
- CHECK: white_player_id ≠ black_player_id

### Move
- id (UUID, PK)
- game_id (FK → games)
- move_number
- move_color
- san
- created_at
- CHECK: valid move_number
- CHECK: valid move_color
- UNIQUE: (game_id, move_number, move_color)

### Indexing

Indexes defined for:
- Username uniqueness
- Game filtering by player/date/result
- Move lookup and frequency queries

Schema is fully managed via Flyway migration `V1__init.sql`.

---

## 🔌 API (Release 0.2)

### Players

#### POST /players
Create a new player

- Validates username (3–30 chars, alphanumeric + underscore)
- Returns 201 Created with Location header

Request example:
```
{
  "username": "rubiksfood"
}
```

Response example:
```
{
  "id": "2f2b03d7-4cbf-4b36-a6c5-cf2c2e95d0b7",
  "username": "rubiksfood",
  "createdAt": "2026-03-05T10:15:30Z"
}
```

---

#### GET /players/{id}
Retrieve player by ID

- Returns 200 OK
- Returns 404 Not Found if player does not exist

Response example:
```
{
  "id": "2f2b03d7-4cbf-4b36-a6c5-cf2c2e95d0b7",
  "username": "rubiksfood",
  "createdAt": "2026-03-05T10:15:30Z"
}
```

---

## ⚠ Error Handling

All errors follow a structured response format:

```
{
  "timestamp": "2026-02-22T15:46:38Z",
  "status": 400,
  "error": "VALIDATION_ERROR",
  "message": "username must not be blank",
  "path": "/players"
}
```

### Common Errors

- 400 VALIDATION_ERROR → Invalid input or malformed request
- 404 NOT_FOUND → Resource does not exist
- 409 CONFLICT → Duplicate username

---

## 📘 API Documentation

Swagger UI available at:

`http://localhost:8080/swagger-ui.html`

OpenAPI spec:

`http://localhost:8080/v3/api-docs`

Use Swagger UI to:
- Explore endpoints
- Execute requests
- Validate responses against documentation

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 4.x
- Maven (via Wrapper)
- PostgreSQL 16 (Docker)
- Flyway
- Docker Compose
- Spring Data JPA
- OpenAPI (SpringDoc)

Planned (v0.3+):
- JUnit 5
- Testcontainers
- GitHub Actions CI

---

## ▶ Running the Application

### 1️⃣ Start Database

`docker compose up -d`

To fully reset database:

`docker compose down -v`

---

### 2️⃣ Run Application

Windows:

`.\mvnw.cmd spring-boot:run`

Application runs at:

`http://localhost:8080`

Health endpoint:

`http://localhost:8080/health`

---

## 🧪 QA Status (Release 0.2)

**QA validation not yet executed.**

Planned validation scope:

- Player creation (happy path + validation)
- Duplicate username handling (409)
- Retrieval by ID (200 / 404)
- Error response structure verification
- Swagger documentation consistency vs actual behaviour
- Flyway migration consistency

QA artefacts will be added under /qa after execution.

---

## 🏷 Release History

### v0.2 – Players API & API Foundation (Implemented, Pending QA)
- Players API (POST, GET)
- DTO validation (Bean Validation)
- Global error handling (@ControllerAdvice)
- OpenAPI / Swagger integration
- Layered architecture introduced

---

### v0.1 – Infrastructure & Schema Baseline (Completed)
- Spring Boot scaffold
- Dockerized PostgreSQL
- Flyway V1 migration
- Constraints and indexes defined
- Health endpoint
- Structured QA validation completed

---

## 🛣 Roadmap

### v0.3 – Player Statistics
- Stats endpoint
- Strategy pattern for calculation
- Builder pattern for response DTOs

### v0.4 – Move Frequency & Filtering
- Move frequency endpoint
- Filtering and pagination

---


---

## 🛣 Roadmap

### v0.2 – Core Domain Implementation
- Player entity (JPA)
- Game creation endpoint
- Factory pattern for game invariants
- Integration testing baseline

### v0.3 – Player Statistics
- Stats endpoint
- Strategy pattern for calculation
- Builder pattern for response DTOs

### v0.4 – Move Frequency & Filtering
- Move frequency endpoint
- Potential Specification pattern
- Filtering and pagination

---

## 🧠 QA Portfolio Angle

This project is intentionally structured to demonstrate:

- Controlled release management
- Risk-based validation
- API + database testing
- Migration discipline
- Structured defect reporting
- Professional Git history
- Clear separation of dev and QA artefacts

Designed to reflect real-world QA + backend collaboration.

---

## 📄 License

MIT License - open for learning, modification, and contribution.

Created for portfolio and educational use.

---

## 📬 Author

Joshua Pearson  
Backend + QA Portfolio Project