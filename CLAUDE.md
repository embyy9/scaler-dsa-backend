# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Purpose

This is a personal learning repository for the Scaler.com Backend Engineering Program, covering DSA (modules 5–7), Databases/SQL (module 8), and Low-Level Design (module 9).

## Building and Running

There is no build tool (no Maven, Gradle, or Makefile). Compile and run files manually:

```bash
# Compile a single file (from module root)
javac src/com/scaler/dsa/<topic>/<FileName>.java

# Run a class (the main() method serves as the test driver)
java -cp src com.scaler.dsa.<topic>.<ClassName>

# Example
javac module-05-advanced-dsa2/src/com/scaler/dsa/searching/BinarySearch.java
java -cp module-05-advanced-dsa2/src com.scaler.dsa.searching.BinarySearch
```

There is no test framework — each class has an embedded `main()` method with sample cases.

## Architecture

Each module is self-contained with its own `src/` tree:

```
module-05-advanced-dsa2/src/com/scaler/dsa/   ← searching, linkedlist
module-06-advanced-dsa3/src/com/scaler/dsa/   ← stacks, queues, trees
module-07-advanced-dsa4/src/com/scaler/dsa/advanced/  ← graphs, dp, greedy, backtracking
module-08-databases-sql/queries/              ← raw .sql practice files
module-09-lld-development1/                  ← LLD patterns (planned)
```

Modules do not share source or depend on each other. All Java code uses only the standard library (`java.util.*`).

## Code Conventions

- Each topic class (e.g., `BinarySearch.java`, `LinkedListProblems.java`) contains related problem solutions as static methods.
- `Node.java` / `TreeNode.java` are shared data structure definitions within their module.
- Time and space complexity are annotated in comments above each method.
- The `main()` method in each class demonstrates usage and acts as a manual test.