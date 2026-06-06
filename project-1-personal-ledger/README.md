# Project 1 — Personal Ledger

Quick start (local):

```bash
cd project-1-personal-ledger
mvn -DskipTests package
java -jar target/personal-ledger-0.0.1-SNAPSHOT.jar
```

Docker:

```bash
docker build -t personal-ledger:local .
docker run --rm -p 8081:8081 personal-ledger:local
```

API endpoints:
- `POST /api/transactions` — create
- `GET /api/transactions` — list
- `GET /api/transactions/{id}` — get by id

Configuration: database in `src/main/resources/application.yml`. Update JDBC settings before running.
