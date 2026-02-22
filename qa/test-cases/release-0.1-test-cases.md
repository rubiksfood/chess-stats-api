# 📋 Test Cases  
## Chess Stats API – Release 0.1

**Version:** 1.0  
**Related Test Plan:** Release 0.1  
**Environment:** Windows 11, Docker, PostgreSQL 16, Java 21  
**Execution Type:** Manual  

---

# TC-01 — Application Startup

**Objective:** Verify application starts successfully.

**Preconditions:**
- Docker Desktop running
- No other service using port 8080

**Steps:**
1. Start database:
   ```
   docker compose up -d
   ```
2. Start application:
   ```
   .\mvnw.cmd spring-boot:run
   ```

**Expected Result:**
- Application starts without errors
- Tomcat binds to port 8080
- No stack traces in logs

**Pass Criteria:** Startup completes successfully.

---

# TC-02 — Flyway Migration Execution (Initial Run)

**Objective:** Confirm migration V1 executes correctly.

**Preconditions:**
- Clean database (no existing tables)

**Steps:**
1. Start application (as in TC-01).
2. Observe logs.

**Expected Result:**
- Flyway reports migration V1 applied successfully.
- No checksum or validation errors.

**Pass Criteria:** Migration applied successfully.

---

# TC-03 — Flyway Validation on Restart

**Objective:** Confirm migration validates correctly on restart.

**Preconditions:**
- Database already migrated

**Steps:**
1. Stop application.
2. Restart application.

**Expected Result:**
- Flyway validates migration.
- Migration is NOT re-applied.
- No validation errors.

**Pass Criteria:** Application starts without migration errors.

---

# TC-04 — Database Table Creation

**Objective:** Confirm required tables exist.

**Steps:**
1. Connect to database:
   ```
   docker exec -it chess_stats_db psql -U chess -d chess_stats
   ```
2. List tables:
   ```
   \dt
   ```

**Expected Result:**
- players
- games
- moves
- flyway_schema_history

**Pass Criteria:** All required tables present.

---

# TC-05 — Schema Structure Validation

**Objective:** Validate column names and data types.

**Steps:**
1. Describe tables:
   ```
   \d players
   \d games
   \d moves
   ```

**Expected Result:**
- All expected columns present
- Correct data types (uuid, timestamptz, varchar, int)
- Column naming matches migration

**Pass Criteria:** No missing or incorrectly named columns.

---

# TC-06 — Games Constraint Enforcement

**Objective:** Verify CHECK constraint on different players.

**Preconditions:**
- At least one valid player exists in the `players` table
OR
- Create test player if none exists

**Steps:**
1. Attempt to insert game with same player as white and black.
2. If no player exists, insert a test player.

**Expected Result:**
- Insert rejected
- CHECK constraint violation message displayed

**Pass Criteria:** Database rejects invalid insert.

---

# TC-07 — Moves Constraint Enforcement

**Objective:** Verify move constraints.

**Steps:**
1. Attempt insert with move_number = 0.
2. Attempt insert with invalid move_color value.

**Expected Result:**
- Insert rejected
- Appropriate constraint violation message displayed

**Pass Criteria:** Database rejects invalid inserts.

---

# TC-08 — Index Verification

**Objective:** Confirm required indexes exist.

**Steps:**
1. Execute:
   ```
   SELECT tablename, indexname
   FROM pg_indexes
   WHERE tablename IN ('players','games','moves');
   ```

**Expected Result:**
- Unique index on players.username
- Indexes on games (player/date)
- Index on moves.san

**Pass Criteria:** All expected indexes present.

---

# TC-09 — Flyway History Verification

**Objective:** Confirm migration recorded correctly.

**Steps:**
1. Execute:
   ```
   SELECT version, success
   FROM flyway_schema_history;
   ```

**Expected Result:**
- Version 1 present
- success = true

**Pass Criteria:** Migration recorded successfully.

---

# Execution Summary (To Be Completed After Testing)

| Test Case | Status (Pass/Fail) | Notes |
|-----------|--------------------|-------|
| TC-01     | Pass               | |
| TC-02     | Pass               | |
| TC-03     | Pass               | |
| TC-04     | Pass               | |
| TC-05     | Pass               | |
| TC-06     | Pass               | |
| TC-07     | Pass               | |
| TC-08     | Pass               | |
| TC-09     | Pass               | |

**Executed By:** Joshua Pearson  
**Date:** 22.02.26  

---

# Notes

- All failures must be logged in `/qa/defects/`.