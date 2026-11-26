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
- Nothing has been built here yet; the project's language and stack are still to be decided.

## Conventions

- Don't commit secrets: `.env`, keys, and `.claude/settings.local.json` are already in `.gitignore`.
- Ask before pushing or creating anything on GitHub.
- Write commit messages in English, in the imperative mood (e.g. `Add .gitignore`).
