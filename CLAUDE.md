# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project state

Early-stage Spring Boot 4.1.2 / Java 21 scaffold (group `com.wuthering`, Gradle, PostgreSQL). The only source is `server/src/main/java/com/wuthering/server/ServerApplication.java` plus generated test stubs; there is no domain code yet. Not a git repository.

## Build layout quirks (verify before relying on them)

- `settings.gradle` includes only `server`. Add `include 'verify-lib'` / `include 'mock-receiver'` once those directories exist (earlier versions listed them, but they were never created).
- Both the root `build.gradle` and `server/build.gradle` apply the Spring Boot plugin and declare near-identical dependencies, and each has its own `gradlew`. The root has no `src/`; all code lives in `server/`. The root build additionally has `springdoc-openapi-starter-webmvc-ui` (3.2.0); `server/` has `spring-boot-devtools` instead.
- Running Gradle from inside `server/` (its own wrapper) is the safest option; from the root, use `:server:` task paths. The root project applies the Boot plugin with no sources, so root-level `build`/`bootJar` may fail.

## Commands

Run from `server/` (use `gradlew.bat` on Windows PowerShell, `./gradlew` in Bash):

- Build: `./gradlew build`
- Run app: `./gradlew bootRun`
- All tests: `./gradlew test`
- Single test class: `./gradlew test --tests com.wuthering.server.ServerApplicationTests`
- Single method: `./gradlew test --tests 'com.wuthering.server.ServerApplicationTests.methodName'`

## Infrastructure

- `spring-boot-docker-compose` (developmentOnly) auto-starts `compose.yaml` (Postgres, user `myuser` / db `mydatabase`, random host port) on `bootRun`, so Docker must be running. `application.yaml` contains no datasource config; connection details come from the compose service.
- Tests use Testcontainers via `TestcontainersConfiguration` (`@ServiceConnection` PostgreSQLContainer, `postgres:latest`); `TestServerApplication` runs the app against it. Docker is required for tests too.
- Schema is managed by Flyway (`spring-boot-starter-flyway` + `flyway-database-postgresql`); migrations belong in `src/main/resources/db/migration`. None exist yet.
- Lombok is enabled for main and test code (annotation processors configured).
