package edu.railsync;

import java.util.*;

/** Dynamic-programming examples for railway data correction and small planning tasks. */
public final class DynamicProgramming {
    private DynamicProgramming() {}

    public static int levenshtein(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= a.length(); i++)
            for (int j = 1; j <= b.length(); j++)
                dp[i][j] = Math.min(Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1),
                    dp[i-1][j-1] + (a.charAt(i-1) == b.charAt(j-1) ? 0 : 1));
        return dp[a.length()][b.length()];
    }

    /** Minimum assignment cost for n tasks and n resources, n should be small. */
    public static int minAssignmentCost(int[][] cost) {
        int n = cost.length, states = 1 << n;
        int[] dp = new int[states]; Arrays.fill(dp, Integer.MAX_VALUE / 4); dp[0] = 0;
        for (int mask = 0; mask < states; mask++) {
            int task = Integer.bitCount(mask);
            if (task >= n) continue;
            for (int resource = 0; resource < n; resource++) if ((mask & (1 << resource)) == 0) {
                int next = mask | (1 << resource);
                dp[next] = Math.min(dp[next], dp[mask] + cost[task][resource]);
            }
        }
        return dp[states - 1];
    }

    public static long matrixChainMinimumCost(int[] dimensions) {
        int n = dimensions.length - 1;
        if (n <= 1) return 0;
        long[][] dp = new long[n][n];
        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1; dp[i][j] = Long.MAX_VALUE;
                for (int k = i; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]
                        + (long) dimensions[i] * dimensions[k+1] * dimensions[j+1]);
            }
        return dp[0][n-1];
    }

    /** Expected search cost for an optimal BST with successful-search frequencies only. */
    public static int optimalBstCost(int[] frequency) {
        int n = frequency.length;
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; len++)
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1, sum = 0;
                for (int k = i; k <= j; k++) sum += frequency[k];
                dp[i][j] = Integer.MAX_VALUE / 4;
                for (int r = i; r <= j; r++) {
                    int left = r > i ? dp[i][r-1] : 0;
                    int right = r < j ? dp[r+1][j] : 0;
                    dp[i][j] = Math.min(dp[i][j], left + right + sum);
                }
            }
        return n == 0 ? 0 : dp[0][n-1];
    }

    public static void demo() {
        System.out.println("Edit distance CENTRAL → CENTRE: " + levenshtein("CENTRAL", "CENTRE"));
        int[][] costs = {{9, 2, 7}, {6, 4, 3}, {5, 8, 1}};
        System.out.println("Minimum small assignment cost: " + minAssignmentCost(costs));
        System.out.println("Matrix-chain minimum multiplications: " + matrixChainMinimumCost(new int[]{10, 30, 5, 60}));
        System.out.println("Optimal BST weighted search cost: " + optimalBstCost(new int[]{3, 1, 4, 2}));
        System.out.println("Bitmask DP is suitable here because the example has only a few resources.");
    }
}
