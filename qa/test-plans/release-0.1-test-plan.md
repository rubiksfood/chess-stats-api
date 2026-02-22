# 📘 Test Plan  
## Chess Stats API – Release 0.1

**Version:** 1.0  
**Release Under Test:** 0.1  
**Author:** Joshua Pearson  
**Date:** 22.02.26  
**Environment:** Local (Windows 11, Docker, PostgreSQL, Java 21)  
**QA:** Joshua Pearson  

---

# 1️⃣ Introduction

This document defines the test strategy and scope for validating Release 0.1 of the Chess Stats API.

Release 0.1 focuses exclusively on:

- Application startup stability
- Docker-based PostgreSQL configuration
- Flyway migration execution
- Database schema creation
- Enforcement of database-level constraints
- Index creation
- Basic application accessibility (health endpoint if implemented)

No business logic or API feature validation is included in this release.

---

# 2️⃣ Test Objectives

The objectives of testing Release 0.1 are:

- Verify the application starts successfully without runtime errors.
- Confirm Flyway migration V1 executes or validates correctly.
- Validate database schema matches defined migration.
- Confirm integrity constraints are correctly enforced.
- Confirm required indexes are present.
- Ensure no regression on restart.

---

# 3️⃣ Scope

## 3.1 In Scope

- Application startup validation
- Flyway migration execution and validation
- Database schema structure
- Constraint enforcement
- Index presence
- Flyway schema history verification

## 3.2 Out of Scope

- REST endpoint functional testing (beyond health check)
- Business logic validation
- Statistics calculations
- Performance testing
- Security testing
- CI/CD validation
- Automated test validation

---

# 4️⃣ Test Approach

Testing will be conducted manually using:

- Docker CLI
- Maven Wrapper
- PostgreSQL psql client (via container)
- Direct SQL inspection
- Application log review

Testing follows a risk-based approach, prioritizing:

1. Infrastructure stability
2. Schema correctness
3. Data integrity enforcement

Evidence will be captured through logs and SQL query outputs.

---

# 5️⃣ Test Environment

| Component   | Version |
|-------------|----------|
| OS          | Windows 11 |
| Java        | 21 |
| Spring Boot | 4.0.3 |
| PostgreSQL  | 16 (Docker) |
| Flyway      | Managed by Spring Boot |
| Build Tool  | Maven Wrapper |

Database is initialised using:

```
docker compose up -d
```

Application started using:

```
.\mvnw.cmd spring-boot:run
```

---

# 6️⃣ Entry Criteria

Testing may begin when:

- Code is committed for Release 0.1
- Docker container is running
- Application builds successfully
- Flyway migration file exists

---

# 7️⃣ Exit Criteria

Testing is complete when:

- All in-scope test conditions executed
- No critical or high-severity defects remain open
- Schema matches migration definition
- Flyway migration validated on restart

---

# 8️⃣ Test Conditions (High-Level)

The following test conditions must be validated:

### TC-01 – Application Startup
Application starts without runtime exceptions.

### TC-02 – Flyway Migration Execution
Migration V1 applies successfully on first run.

### TC-03 – Flyway Validation on Restart
Migration validates without reapplication or checksum errors.

### TC-04 – Table Creation
Tables `players`, `games`, `moves`, and `flyway_schema_history` exist.

### TC-05 – Schema Structure Validation
Columns exist with correct names and data types.

### TC-06 – Games Constraint Enforcement
CHECK constraint prevents same player as white and black.

### TC-07 – Moves Constraint Enforcement
CHECK constraints enforce valid move_number and move_color; uniqueness constraint enforced.

### TC-08 – Index Verification
Defined indexes exist to support expected query patterns.

### TC-09 – Flyway History Verification
Migration recorded with success status.  

**Note**: TC IDs in this plan are aligned with Release 0.1 test cases and execution report.  

---

# 9️⃣ Risks and Mitigations

| Risk | Impact | Likelihood | Mitigation |
|------|--------|------------|------------|
| Migration edited after application | High | Medium | Never modify applied migration; create new version |
| Missing constraint | High | Medium | Negative SQL testing |
| Missing index | Medium | Medium | Query pg_indexes |

---

# 🔟 Test Deliverables

The following artefacts will be produced:

- Test Execution Report (Release 0.1)
- Defect Log (if applicable)
- SQL validation evidence
- Application startup log evidence

All artefacts stored under:

```
/qa/
```

---

# 1️⃣1️⃣ Assumptions

- Local PostgreSQL container accessible
- No external dependencies required
- Schema controlled exclusively by Flyway
- No concurrent users

---

# 1️⃣2️⃣ Approval

Release 0.1 may progress to Release 0.2 when:

- Infrastructure is stable
- Schema integrity is verified
- No blocking defects remain

---