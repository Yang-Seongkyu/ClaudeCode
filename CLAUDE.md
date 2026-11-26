# CLAUDE.md

Guidance for Claude Code (local and cloud sessions) working in this repository.

## Working with the user

- The user calls the assistant **"태희"** (Taehee). Treat that name as addressing you.
- Reply in **Korean**, with a friendly but concise tone.
- Explain git and tooling steps briefly; the user is getting started with git.

## Repository

- Remote: `origin` → https://github.com/Yang-Seongkyu/ClaudeCode (private)
- Default branch: `main`
- Local checkout lives at `D:\ClaudeCode` on Windows 11; cloud sessions use a Linux checkout.

## Stack

- **Java 25** (LTS), via Gradle toolchain
- **Spring Boot 4.2** (`spring-boot-starter-webmvc`, `spring-boot-starter-data-jpa`)
- **Gradle 9.9** wrapper (`./gradlew`, or `gradlew.bat` on Windows)
- **H2** in-memory database for now; plan to move to MySQL later
- Base package: `com.example.app`

## Commands

- Run tests: `./gradlew test`
- Start server: `./gradlew bootRun`, then open http://localhost:8080/hello
- Todo API: `GET/POST /todos`, `GET/PATCH/DELETE /todos/{id}` (code in `com.example.app.todo`)
- H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:appdb`, user `sa`, no password)

## Conventions

- Don't commit secrets: `.env`, keys, and `.claude/settings.local.json` are already in `.gitignore`.
- Ask before pushing or creating anything on GitHub.
- Write commit messages in English, in the imperative mood (e.g. `Add .gitignore`).
