
package edu.railsync;

import java.util.Scanner;

public final class RailSyncApp {

    private RailSyncApp() {}

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            printBanner();

            while (true) {

                printMenu();
                System.out.print("Enter option: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {

                    case "1":
                        runStringModule();
                        break;

                    case "2":
                        runSuffixModule();
                        break;

                    case "3":
                        runDynamicProgrammingModule();
                        break;

                    case "4":
                        runNetworkModule();
                        break;

                    case "5":
                        runSchedulingModule();
                        break;

                    case "6":
                        runDataProcessingModule();
                        break;

                    case "7":
                        runAllModules();
                        break;

                    case "0":
                        System.out.println(
                            "\nThank you for exploring RailSync!"
                        );
                        System.out.println("Project closed.");
                        return;

                    default:
                        System.out.println(
                            "Invalid option. Please enter 0 to 7."
                        );
                }

                System.out.println(
                    "\nDemonstration completed."
                );

                System.out.println(
                    "Press Enter to return to the main menu..."
                );

                scanner.nextLine();
            }
        }
    }

    private static void printBanner() {

        System.out.println("\n");
        System.out.println(
            "=========================================================="
        );
        System.out.println(
            "       RAILSYNC - INTELLIGENT RAILWAY OPERATIONS"
        );
        System.out.println(
            "=========================================================="
        );

        System.out.println(
            "A Java-based Data Structures and Algorithms Project"
        );
    }

    private static void printMenu() {

        System.out.println("\n");
        System.out.println(
            "==================== MAIN MENU ===================="
        );

        System.out.println(
            "1. Search Train Information and Alerts"
        );

        System.out.println(
            "2. Railway Document Indexing and Text Search"
        );

        System.out.println(
            "3. Name Correction and Small Optimizations"
        );

        System.out.println(
            "4. Network Capacity and Platform Matching"
        );

        System.out.println(
            "5. Scheduling Constraints and Conflict Analysis"
        );

        System.out.println(
            "6. Railway Event Data Processing"
        );

        System.out.println(
            "7. Run Complete RailSync Demonstration"
        );

        System.out.println(
            "0. Exit"
        );

        System.out.println(
            "==================================================="
        );
    }

    private static void runStringModule() {

        System.out.println("\n");
        System.out.println(
            "========== MODULE 1: TRAIN AND ALERT SEARCH =========="
        );

        System.out.println(
            "PROBLEM: Find train numbers and important words in text."
        );

        System.out.println(
            "SOLUTION: Apply KMP, Rabin-Karp, Z-function and Aho-Corasick."
        );

        System.out.println("\nSample railway records:");

        RailwayData.TRAINS.forEach(
            train -> System.out.println(" - " + train)
        );

        System.out.println("\nAlgorithm results:");

        StringAlgorithms.demo();

        System.out.println(
            "\nUSE: Demonstrates fast searching in railway information."
        );
    }

    private static void runSuffixModule() {

        System.out.println("\n");
        System.out.println(
            "========== MODULE 2: DOCUMENT INDEXING =========="
        );

        System.out.println(
            "PROBLEM: Find information in long railway documents."
        );

        System.out.println(
            "SOLUTION: Build a suffix array and calculate LCP values."
        );

        SuffixStructures.demo();

        System.out.println(
            "\nUSE: Demonstrates text indexing and substring search."
        );
    }

    private static void runDynamicProgrammingModule() {

        System.out.println("\n");
        System.out.println(
            "========== MODULE 3: OPTIMIZATION =========="
        );

        System.out.println(
            "PROBLEM: Correct text and minimize the cost of small tasks."
        );

        System.out.println(
            "SOLUTION: Use edit distance and dynamic programming."
        );

        DynamicProgramming.demo();

        System.out.println(
            "\nUSE: Demonstrates name correction and optimization."
        );
    }

    private static void runNetworkModule() {

        System.out.println("\n");
        System.out.println(
            "========== MODULE 4: NETWORK AND PLATFORM ANALYSIS =========="
        );

        System.out.println(
            "PROBLEM: Analyze network capacity and resource assignments."
        );

        System.out.println(
            "SOLUTION: Use maximum flow and bipartite matching."
        );

        FlowAlgorithms.demo();

        System.out.println(
            "\nUSE: Demonstrates network capacity and platform matching."
        );
    }

    private static void runSchedulingModule() {

        System.out.println("\n");
        System.out.println(
            "========== MODULE 5: SCHEDULING CONSTRAINTS =========="
        );

        System.out.println(
            "PROBLEM: Represent scheduling rules and conflicts."
        );

        System.out.println(
            "SOLUTION: Demonstrate SAT, graph reductions and approximation."
        );

        ComplexityAlgorithms.demo();

        System.out.println(
            "\nUSE: Demonstrates modelling difficult scheduling problems."
        );
    }

    private static void runDataProcessingModule() {

        System.out.println("\n");
        System.out.println(
            "========== MODULE 6: EVENT DATA PROCESSING =========="
        );

        System.out.println(
            "PROBLEM: Sort, sample and summarize event data."
        );

        System.out.println(
            "SOLUTION: Use randomized and parallel algorithms."
        );

        RandomizedParallelAlgorithms.demo();

        System.out.println(
            "\nUSE: Demonstrates event sorting, sampling and aggregation."
        );
    }

    private static void runAllModules() {

        System.out.println("\n");
        System.out.println(
            "=========================================================="
        );
        System.out.println(
            "          RAILSYNC - COMPLETE PROJECT DEMO"
        );
        System.out.println(
            "=========================================================="
        );

        System.out.println(
            "\nThis demonstration runs all six DSA modules."
        );

        System.out.println(
            "All results are calculated using sample data."
        );

        runStringModule();
        runSuffixModule();
        runDynamicProgrammingModule();
        runNetworkModule();
        runSchedulingModule();
        runDataProcessingModule();

        System.out.println("\n");
        System.out.println(
            "=========================================================="
        );
        System.out.println(
            "        ALL SIX MODULES COMPLETED SUCCESSFULLY"
        );
        System.out.println(
            "=========================================================="
        );

        System.out.println(
            "RailSync demonstrates searching, indexing, optimization,"
        );

        System.out.println(
            "network analysis, scheduling models and data processing."
        );

        System.out.println(
            "This is an educational DSA demonstration, not a live"
        );

        System.out.println(
            "railway booking, tracking or scheduling system."
        );
    }
}
