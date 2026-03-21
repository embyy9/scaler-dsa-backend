# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Purpose

This is a personal learning repository for the Scaler.com Backend Engineering Program, covering DSA, Databases/SQL, and Low-Level Design.

## Building and Running

There is no build tool (no Maven, Gradle, or Makefile). Compile and run files manually:

```bash
# Compile a single file (from module root)
javac src/com/scaler/dsa/<topic>/<FileName>.java

# Run a class (the main() method serves as the test driver)
java -cp src com.scaler.dsa.<topic>.<ClassName>

# Example
javac dsa-searching-linkedlists/src/com/scaler/dsa/searching/BinarySearch.java
java -cp dsa-searching-linkedlists/src com.scaler.dsa.searching.BinarySearch
```

There is no test framework — each class has an embedded `main()` method with sample cases.

## Architecture

Each folder is self-contained with its own `src/` tree:

```
dsa-searching-linkedlists/src/com/scaler/dsa/   ← searching, linkedlist
dsa-stacks-queues-trees/src/com/scaler/dsa/     ← stacks, queues, trees
dsa-graphs-dp-greedy/src/com/scaler/dsa/        ← graphs, dp, greedy, backtracking
databases-sql/queries/                           ← raw .sql practice files
low-level-design/                                ← LLD patterns (planned)
```

Folders do not share source or depend on each other. All Java code uses only the standard library (`java.util.*`).

## Code Conventions

- Each topic class (e.g., `BinarySearch.java`, `LinkedListProblems.java`) contains related problem solutions as static methods.
- `Node.java` / `TreeNode.java` are shared data structure definitions within their folder.
- Time and space complexity are annotated in comments above each method.
- The `main()` method in each class demonstrates usage and acts as a manual test.