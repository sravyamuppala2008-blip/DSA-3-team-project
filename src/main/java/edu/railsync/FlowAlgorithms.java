package edu.railsync;

import java.util.*;

/** Graph flow, bipartite matching and vertex-cover demonstrations. */
public final class FlowAlgorithms {
    private FlowAlgorithms() {}

    private static int[][] copy(int[][] a) {
        int[][] b = new int[a.length][];
        for (int i = 0; i < a.length; i++) b[i] = a[i].clone();
        return b;
    }

    public static int edmondsKarp(int[][] capacity, int source, int sink) {
        int[][] residual = copy(capacity); int n = capacity.length, flow = 0;
        while (true) {
            int[] parent = new int[n]; Arrays.fill(parent, -1); parent[source] = source;
            Queue<Integer> q = new ArrayDeque<>(); q.add(source);
            while (!q.isEmpty() && parent[sink] == -1) {
                int u = q.remove();
                for (int v = 0; v < n; v++) if (parent[v] == -1 && residual[u][v] > 0) {
                    parent[v] = u; q.add(v);
                }
            }
            if (parent[sink] == -1) break;
            int add = Integer.MAX_VALUE;
            for (int v = sink; v != source; v = parent[v]) add = Math.min(add, residual[parent[v]][v]);
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v]; residual[u][v] -= add; residual[v][u] += add;
            }
            flow += add;
        }
        return flow;
    }

    public static int fordFulkerson(int[][] capacity, int source, int sink) {
        // DFS augmenting paths; integer capacities ensure termination.
        int[][] residual = copy(capacity); int n = capacity.length, total = 0;
        while (true) {
            boolean[] seen = new boolean[n]; int add = dfs(residual, source, sink, Integer.MAX_VALUE, seen);
            if (add == 0) return total;
            total += add;
        }
    }
    private static int dfs(int[][] r, int u, int sink, int amount, boolean[] seen) {
        if (u == sink) return amount;
        seen[u] = true;
        for (int v = 0; v < r.length; v++) if (!seen[v] && r[u][v] > 0) {
            int pushed = dfs(r, v, sink, Math.min(amount, r[u][v]), seen);
            if (pushed > 0) { r[u][v] -= pushed; r[v][u] += pushed; return pushed; }
        }
        return 0;
    }

    /** Dinic's maximum flow for directed graphs with non-negative integer capacities. */
    public static int dinic(int[][] capacity, int source, int sink) {
        int n = capacity.length; int[][] residual = copy(capacity); int total = 0;
        while (true) {
            int[] level = new int[n]; Arrays.fill(level, -1); level[source] = 0;
            Queue<Integer> q = new ArrayDeque<>(); q.add(source);
            while (!q.isEmpty()) {
                int u = q.remove();
                for (int v = 0; v < n; v++) if (level[v] < 0 && residual[u][v] > 0) {
                    level[v] = level[u] + 1; q.add(v);
                }
            }
            if (level[sink] < 0) break;
            int[] next = new int[n]; int pushed;
            while ((pushed = dinicDfs(residual, level, next, source, sink, Integer.MAX_VALUE)) > 0) total += pushed;
        }
        return total;
    }
    private static int dinicDfs(int[][] r, int[] level, int[] next, int u, int sink, int amount) {
        if (u == sink) return amount;
        for (; next[u] < r.length; next[u]++) {
            int v = next[u];
            if (r[u][v] > 0 && level[v] == level[u] + 1) {
                int pushed = dinicDfs(r, level, next, v, sink, Math.min(amount, r[u][v]));
                if (pushed > 0) { r[u][v] -= pushed; r[v][u] += pushed; return pushed; }
            }
        }
        return 0;
    }

    /** Maximum bipartite matching; left vertices connect to eligible right vertices. */
    public static int maximumBipartiteMatching(boolean[][] edges) {
        int left = edges.length, right = left == 0 ? 0 : edges[0].length;
        int[] matchedLeft = new int[right]; Arrays.fill(matchedLeft, -1); int count = 0;
        for (int u = 0; u < left; u++)
            if (matchDfs(u, edges, matchedLeft, new boolean[right])) count++;
        return count;
    }
    private static boolean matchDfs(int u, boolean[][] e, int[] match, boolean[] seen) {
        for (int v = 0; v < match.length; v++) if (e[u][v] && !seen[v]) {
            seen[v] = true;
            if (match[v] == -1 || matchDfs(match[v], e, match, seen)) { match[v] = u; return true; }
        }
        return false;
    }

    /** Returns a 2-approximate vertex cover for an undirected graph edge list. */
    public static Set<Integer> vertexCoverApproximation(int[][] edges) {
        Set<Integer> cover = new LinkedHashSet<>(); Set<Integer> coveredEdges = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            if (coveredEdges.contains(i)) continue;
            int u = edges[i][0], v = edges[i][1]; cover.add(u); cover.add(v);
            for (int j = 0; j < edges.length; j++)
                if (edges[j][0] == u || edges[j][1] == u || edges[j][0] == v || edges[j][1] == v)
                    coveredEdges.add(j);
        }
        return cover;
    }

    public static void demo() {
        int[][] cap = {
            {0, 8, 5, 0, 0, 0}, {0, 0, 0, 5, 3, 0}, {0, 0, 0, 0, 4, 0},
            {0, 0, 0, 0, 0, 8}, {0, 0, 0, 0, 0, 7}, {0, 0, 0, 0, 0, 0}
        };
        System.out.println("Passenger-network max flow (Ford-Fulkerson): " + fordFulkerson(cap, 0, 5));
        System.out.println("Passenger-network max flow (Edmonds-Karp): " + edmondsKarp(cap, 0, 5));
        System.out.println("Passenger-network max flow (Dinic): " + dinic(cap, 0, 5));
        boolean[][] eligible = {{true, true, false}, {true, false, true}, {false, true, true}};
        System.out.println("Train-to-platform maximum matching: " + maximumBipartiteMatching(eligible));
        int[][] conflicts = {{0,1},{1,2},{2,3},{0,3}};
        System.out.println("Approximate conflict vertex cover: " + vertexCoverApproximation(conflicts));
    }
}
