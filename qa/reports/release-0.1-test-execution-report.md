# 📊 Test Execution Report  
## Chess Stats API – Release 0.1

**Version:** 1.0  
**Related Test Plan:** Release 0.1  
**Related Test Cases:** Release 0.1 Test Cases  
**Environment:** Windows 11, Docker, PostgreSQL 16, Java 21  
**Execution Type:** Manual  
**Executed By:** Joshua Pearson  
**Execution Date:** 22.02.26  

---

# 1️⃣ Execution Summary

| Test Case | Status |
|-----------|--------|
| TC-01     | PASS   |
| TC-02     | PASS   |
| TC-03     | PASS   |
| TC-04     | PASS   |
| TC-05     | PASS   |
| TC-06     | PASS   |
| TC-07     | PASS   |
| TC-08     | PASS   |
| TC-09     | PASS   |

All in-scope test cases executed successfully. No defects identified during Release 0.1 validation.

---

# 2️⃣ Detailed Test Results & Evidence

---

## TC-01 — Application Startup

**Status:** ✅ PASS  

**Evidence (Startup Log Snippet):**
```
Tomcat started on port 8080 (http) with context path '/'  
```

**Notes:**  
Application started without errors or stack traces.

---

## TC-02 — Flyway Migration Execution (Initial Run)

**Status:** ✅ PASS  

**Evidence (Startup Log Snippet):**
```
Current version of schema "public": << Empty Schema >>
Migrating schema "public" to version "1 - init"
Successfully applied 1 migration to schema "public", now at version v1
```

**Notes:**  
Migration executed successfully on clean database.

---

## TC-03 — Flyway Validation on Restart

**Status:** ✅ PASS  

**Evidence (Startup Log Snippet):**
```
Successfully validated 1 migration
Current version of schema "public": 1
Schema "public" is up to date. No migration necessary.
```

**Notes:**  
Migration validated correctly. No reapplication occurred.

---

## TC-04 — Database Table Creation

**Status:** ✅ PASS  

**Evidence (SQL Output):**
```
\dt

 Schema |         Name          | Type  | Owner 
--------+-----------------------+-------+-------
 public | flyway_schema_history | table | chess
 public | games                 | table | chess
 public | moves                 | table | chess
 public | players               | table | chess
```

**Notes:**  
All expected tables present.

---

## TC-05 — Schema Structure Validation

**Status:** ✅ PASS  

**Evidence (SQL Output Excerpt):**
```
\d players

Column      | Type
------------|------------------------
id          | uuid
username    | character varying(30)
created_at  | timestamp with time zone
Indexes:
    "players_pkey" PRIMARY KEY, btree (id)
    "ux_players_username" UNIQUE, btree (username)
```

```
\d games

Column           | Type
-----------------|------------------------
id               | uuid
white_player_id  | uuid
black_player_id  | uuid
played_at        | timestamp with time zone
game_result      | character varying(10)
created_at       | timestamp with time zone
```

```
\d moves

Column       | Type
-------------|------------------------
id           | uuid
game_id      | uuid
move_number  | integer
move_color   | character varying
san          | character varying
created_at   | timestamp with time zone
```

**Notes:**  
Column names and data types match migration definition.

---

## TC-06 — Games Constraint Enforcement

**Status:** ✅ PASS  

**Evidence (Constraint Violation):**
```
ERROR:  new row for relation "games" violates check constraint "ck_games_players_different"
```

**Notes:**  
Database correctly rejected invalid insert.

---

## TC-07 — Moves Constraint Enforcement

**Status:** ✅ PASS  

**Evidence (Constraint Violation):**
```
ERROR:  new row for relation "moves" violates check constraint "ck_moves_move_number"
```

```
ERROR:  new row for relation "moves" violates check constraint "ck_moves_move_color"
```

**Notes:**  
Invalid inserts correctly rejected.

---

## TC-08 — Index Verification

**Status:** ✅ PASS  

**Evidence (SQL Output):**
```
tablename | indexname
----------+------------------------------
players   | players_pkey
players   | ux_players_username
games     | games_pkey
games     | ix_games_white_played_at
games     | ix_games_black_played_at
games     | ix_games_played_at
games     | ix_games_result
moves     | moves_pkey
moves     | ux_moves_game_move_color
moves     | ix_moves_game_move_number
moves     | ix_moves_san
```

**Notes:**  
All expected indexes present.

---

## TC-09 — Flyway History Verification

**Status:** ✅ PASS  

**Evidence (SQL Output):**
```
version | success
--------|---------
1       | t
```

**Notes:**  
Migration recorded successfully in Flyway history table.

---

# 3️⃣ Defect Summary

No defects identified during execution.

---

# 4️⃣ Release Assessment

Release 0.1 infrastructure and schema validation completed successfully.  

Database integrity, migration control, and startup stability confirmed.  

Release approved for progression to Release 0.2.  

---

**QA Sign-Off:**  
Joshua Pearson  
22.02.26