# ♟ Chess Stats API

![Status](https://img.shields.io/badge/status-active_development-blue)
![Release](https://img.shields.io/badge/release-v0.1-green)
![QA](https://img.shields.io/badge/QA-validated-success)

Java Spring Boot + PostgreSQL REST API for chess game storage and statistics analysis.

This project is structured to simulate a realistic enterprise backend workflow with controlled releases, Flyway-managed schema migrations, and structured QA documentation.

**Release 0.1 is complete and validated.**

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

## 🧱 Architecture (Current State – v0.1)

Current implementation includes:

- Spring Boot application bootstrap
- Embedded Apache Tomcat (port 8080)
- Dockerized PostgreSQL 16
- Flyway V1 migration
- Health endpoint
- Database schema validated via manual QA

Planned layered architecture (from v0.2 onward):

- Controller layer – REST endpoints  
- Service layer – Business logic  
- Domain layer – Entities and invariants  
- Repository layer – Spring Data JPA  
- Database layer – PostgreSQL + Flyway  

---

## 🗄️ Database Schema (Release 0.1)

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

## 🚀 Tech Stack

- Java 21
- Spring Boot 4.x
- Maven (via Wrapper)
- PostgreSQL 16 (Docker)
- Flyway
- Docker Compose

Planned (v0.2+):
- Spring Data JPA
- JUnit 5
- Testcontainers
- GitHub Actions CI

---

## ▶ Running the Application

### 1️⃣ Start Database

```
docker compose up -d
```

To fully reset database:

```
docker compose down -v
```

---

### 2️⃣ Run Application (Windows)

```
.\mvnw.cmd spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

Health endpoint:

```
http://localhost:8080/health
```

---

## 🧪 QA & Validation (Release 0.1)

Release 0.1 includes structured QA artefacts under `/qa`:

- Test Plan
- Test Cases
- Test Execution Report
- Defect tracking structure

Validation performed:

- Application startup verification
- Flyway migration execution (clean DB)
- Flyway validation on restart
- Schema inspection via psql
- Constraint enforcement testing
- Index verification
- Flyway history validation

All evidence is embedded within execution reports.

Release 0.1 successfully signed off.

---

## 🏷 Release History

### v0.1 – Infrastructure & Schema Baseline (Completed)
- Spring Boot scaffold
- Dockerized PostgreSQL
- Flyway V1 migration
- Constraints and indexes defined
- Health endpoint
- Structured QA validation completed

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
- SQL-level integrity testing
- Migration discipline
- Professional Git history
- Clear separation of dev and QA artefacts

It simulates a realistic backend development lifecycle aligned with junior QA / Test Engineer roles.

---

## 📄 License

MIT License - open for learning, modification, and contribution.

Created for portfolio and educational use.

---

## 📬 Author

Joshua Pearson  
Backend + QA Portfolio Project