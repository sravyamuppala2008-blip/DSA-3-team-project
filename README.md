# RailSync — Intelligent Railway Operations Platform

**A Java-based Data Structures and Algorithms project** that demonstrates how algorithmic techniques can support railway information retrieval, document indexing, schedule/resource optimization, passenger-flow analysis, and continuous operational-event processing.

## Team

- **2500032147** — Muppala Gnana Sravya
- **2500090253** — Basireddy Vedha Reddy
- **2500030711** — KURUVA SHIVA KEERTHANA
- **Project Guide:** G. Krishna Chaithanya

## What the application demonstrates

This is an educational prototype. It uses sample railway records and small illustrative networks; it is **not connected to a real railway system**.

| Module | Demonstration | DSA concepts |
|---|---|---|
| 1. String search | Search train/station/alert text | KMP, Z-function, Rabin–Karp, Aho–Corasick |
| 2. Document indexing | Sort suffixes and find repeated text | Suffix array, Kasai LCP, suffix automaton |
| 3. Optimization | Correct misspellings and optimize small combinations | Levenshtein distance, bitmask DP, matrix-chain DP, optimal BST |
| 4. Network flow | Find passenger-flow capacity and train/platform matches | Ford–Fulkerson, Edmonds–Karp, Dinic, bipartite matching, vertex-cover approximation |
| 5. Complexity concepts | Explain schedule constraints and approximation | SAT/3-SAT modelling, reduction chain, 2-approximation |
| 6. Randomized/parallel | Rank records, sample events, aggregate counts | randomized quicksort, reservoir sampling, Miller–Rabin, scan, parallel reduce |

## Requirements

- Java **17 or later**
- Eclipse IDE for Java Developers (recommended)
- No external libraries are required.

## Run in Eclipse

1. Download and extract `RailSync_Project.zip` to a normal folder, such as `Documents/RailSync_Project`.
2. Open Eclipse.
3. Select **File → Import… → General → Existing Projects into Workspace → Next**.
4. Select **Select root directory**, click **Browse…**, and choose the extracted `RailSync_Project` folder.
5. Tick `RailSync_Project`, then click **Finish**.
6. Expand `src/main/java → edu.railsync`.
7. Right-click `RailSyncApp.java` → **Run As → Java Application**.
8. Select a menu number in the Console and press Enter. Choose `0` to exit.

If Eclipse reports a JRE problem: right-click the project → **Properties → Java Build Path → Libraries** and select an installed JDK/JRE 17 or later. If necessary, use **Project → Clean** and run again.

## Run without Eclipse (optional)

From the project root, open a terminal:

```bash
javac -d out src/main/java/edu/railsync/*.java
java -cp out edu.railsync.RailSyncApp
```

## Suggested faculty demonstration

1. Start with **1 — Railway data and string search** to show how a train number or service alert is found.
2. Open **2 — Document indexing** to show suffix arrays and repeated substrings.
3. Show **3 — Dynamic programming** for passenger-data correction and resource optimization.
4. Use **4 — Network flow** to demonstrate bottleneck capacity and platform matching.
5. Explain **5 — NP-completeness** as a model of why large scheduling constraints can be computationally difficult.
6. Finish with **6 — Randomized and parallel algorithms** to show event-stream processing and aggregation.

## Project structure

```text
RailSync_Project/
├── src/main/java/edu/railsync/
│   ├── RailSyncApp.java
│   ├── RailwayData.java
│   ├── StringAlgorithms.java
│   ├── SuffixStructures.java
│   ├── DynamicProgramming.java
│   ├── FlowAlgorithms.java
│   ├── ComplexityAlgorithms.java
│   └── RandomizedParallelAlgorithms.java
├── docs/
│   └── PROJECT_REPORT.md
├── pom.xml
├── .project
├── .classpath
└── README.md
```

## Important academic note

The menu is a working demonstration of selected algorithms. The project report explains their railway use cases and complexity. Results use invented sample data for classroom demonstration and should not be interpreted as live railway status or operational advice.
