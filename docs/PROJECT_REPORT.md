# RailSync — Project Report

## 1. Abstract

RailSync is a Java console prototype that connects key Data Structures and Algorithms topics to an intelligent railway-operations scenario. It demonstrates searching train and station records, indexing operational text, correcting passenger/station names, optimizing small planning problems, analysing flow capacity, assigning services to eligible platforms, and processing continuous event samples.

The purpose is to show how algorithm choice affects search speed, data organization, optimization, and capacity analysis. The prototype is deliberately self-contained and uses sample records; it is not integrated with railway infrastructure or live railway data.

## 2. Problem statement

Railway-management applications work with train identifiers, station names, schedules, passenger/ticket records, service alerts, maintenance documents, and changing operational events. Searching large records, finding repeated document text, correcting typing mistakes, selecting feasible schedules, and locating capacity bottlenecks are different computational problems and need different algorithmic approaches.

## 3. Objectives

- Demonstrate multiple DSA modules through a single menu-driven Java application.
- Use readable sample railway records so outputs are easy to explain.
- Compare algorithmic approaches for text search and flow analysis.
- Demonstrate dynamic programming for small optimization examples.
- Explain NP-completeness and approximation using scheduling constraints.
- Demonstrate randomized sampling and parallel-style data aggregation.

## 4. System design

**Input:** built-in railway sample records, search patterns, small graphs, and event arrays.  
**Processing:** selected algorithms run on the input data.  
**Output:** match positions, index structures, edit distances, route/flow values, matching results, or aggregated statistics.

The current version is a local algorithm demonstrator. A production system would require validated live data feeds, authentication, secure storage, fault-tolerant services, monitoring, and domain review.

## 5. Algorithm modules

### Module 1 — String algorithms
- **KMP:** uses a prefix table to avoid repeating comparisons when matching a pattern.
- **Z-function:** computes the longest prefix match starting at each string position.
- **Rabin–Karp:** uses rolling hashes to screen candidate matches.
- **Aho–Corasick:** builds a trie with failure links to find several keywords in one text scan.

**Railway use:** train numbers, station names, ticket codes, and service-alert keywords.

### Module 2 — Suffix structures
- **Suffix array:** stores suffix start positions in sorted order.
- **Kasai LCP:** calculates longest common prefixes of adjacent sorted suffixes.
- **Suffix automaton:** represents substrings compactly and supports substring membership queries.

**Railway use:** indexing long service descriptions and identifying repeated wording. This educational implementation uses a simple suffix sort; it does not implement the linear-time SA-IS construction.

### Module 3 — Dynamic programming
- **Levenshtein distance:** estimates the number of insertions, deletions, and substitutions needed to correct a name.
- **Bitmask DP:** finds a minimum-cost assignment for a small set of tasks.
- **Matrix-chain multiplication:** finds a low-cost parenthesization of matrix products.
- **Optimal BST:** estimates a search tree arrangement from access frequencies.

**Railway use:** data correction and small resource-planning examples.

### Module 4 — Flow and matching
- **Ford–Fulkerson / Edmonds–Karp:** calculate maximum flow; Edmonds–Karp uses BFS to find augmenting paths.
- **Dinic:** uses level graphs and blocking flows.
- **Bipartite matching:** assigns compatible trains to available platforms in a small example.
- **Vertex-cover 2-approximation:** greedily chooses endpoints of uncovered edges.

**Railway use:** model simplified passenger transfer capacity and compatibility between trains and platforms. Actual station operations have many more constraints than this graph model.

### Module 5 — NP-completeness and approximation
SAT/3-SAT can represent Boolean scheduling constraints. Reductions between 3-SAT, CLIQUE, INDEPENDENT-SET, and VERTEX-COVER illustrate relationships among decision problems. The application explains the modelling chain and demonstrates a vertex-cover 2-approximation. It does not claim to solve arbitrary large railway schedules optimally.

### Module 6 — Randomized and parallel algorithms
- Randomized QuickSort sorts records using randomized pivots.
- Reservoir sampling selects a fixed-size sample from a stream without storing the entire stream.
- Miller–Rabin performs probabilistic primality testing for integer-processing examples.
- Blelloch-style scan is represented by a parallel-prefix-sum demonstration.
- Parallel reduction aggregates event counts using Java parallel streams.

## 6. Testing checklist

- Launch the application and verify all six menu modules open.
- Confirm KMP finds a known sample alert.
- Confirm the suffix array returns sorted suffix positions.
- Confirm edit distance between `CENTRAL` and `CENTRE` is displayed.
- Confirm flow algorithms return a maximum-flow value for the sample network.
- Confirm reservoir sampling returns no more than the requested sample size.
- Exit using menu option `0`.

## 7. Limitations and future enhancements

- Current data is local and illustrative, not live.
- GUI, database integration, authentication, and external APIs are not included.
- The suffix array uses a straightforward sorting implementation for clarity.
- Small DP and graph examples are intended for classroom-scale inputs.
- Future work could add a JavaFX dashboard, PostgreSQL persistence, validated CSV import, live-data adapters, and automated tests.

## 8. Conclusion

RailSync provides a unified and explainable demonstration of string processing, suffix indexing, dynamic programming, graph flow, NP-completeness concepts, and randomized/parallel processing in a railway-management context. Its main value is educational: it shows how selecting a suitable algorithm can improve the way operational information is searched, organized, and analysed.
