package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 1. 0/1 Knapsack Problem (Recursion)
 * 2. 0/1 Knapsack Problem (Memoization)
 * 3. 0/1 Knapsack Problem (Tabulation)
 * 4. 0/1 Knapsack Problem (Tabulation - Space optimized)
 *
 */
public class Classic {
    public static void main(String[] args) {
        int[] weight = {20,24,36,40,42};
        int[] value = {12,35,41,25,32};
        System.out.println(knapsackTabulationSpaceOptimized(weight, value, 100, 5));
    }

    /**
     * 0/1 Knapsack Problem (Recursion)
     *
     * Given n items where each item has some weight and value associated with it and also given a bag with capacity W,
     * The task is to put the items into the bag such that the sum of values associated with them is the maximum possible.
     * weight = [20,24,36,40,42]
     * value = [12,35,41,25,32], w=100
     * maximum Possible value = 101 ( weight 24 + 36 + 40)
     *
     * TC: O(2^n)
     * SC: O(n)
     *
     */
    public static int knapsack(int[] weight, int[] value, int w, int n) {
        return knapsackUtil(weight, value, w, n-1);
    }

    private static int knapsackUtil(int[] weight, int[] value, int w, int i) {
        if(i==-1 || w==0) {
            return 0;
        }
        if(weight[i] > w) {
            return knapsackUtil(weight, value, w, i-1);
        }
        return Math.max(knapsackUtil(weight, value, w, i-1),
                value[i] + knapsackUtil(weight, value, w-weight[i], i-1));
    }

    /**
     * 0/1 Knapsack Problem (Memoization)
     *
     * TC: O(nw)
     * SC: O(nw)
     *
     */
    public static int knapsackMemoization(int[] weight, int[] value, int w, int n) {
        int[][] dp = new int[n][w+1];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return knapsackUtil(weight, value, w, n-1, dp);
    }

    private static int knapsackUtil(int[] weight, int[] value, int w, int i, int[][] dp) {
        if(i==-1 || w==0) {
            return 0;
        }
        if(dp[i][w] != -1) {
            return dp[i][w];
        }
        if(weight[i] > w) {
            return dp[i][w] = knapsackUtil(weight, value, w, i-1);
        }
        return dp[i][w] = Math.max(knapsackUtil(weight, value, w, i-1, dp),
                value[i] + knapsackUtil(weight, value, w-weight[i], i-1, dp));
    }

    /**
     * 0/1 Knapsack Problem (Tabulation)
     *
     * TC: O(nw)
     * SC: O(nw)
     *
     */
    public static int knapsackTabulation(int[] weight, int[] value, int w, int n) {
        int[][] dp = new int[n+1][w+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=w; j++) {
                if(i==0 || j==0) {
                    dp[i][j] = 0;
                } else if(j >= weight[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], value[i-1] + dp[i-1][j-weight[i-1]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][w];
    }

    /**
     * 0/1 Knapsack Problem (Tabulation - Space optimized)
     * 
     * For calculating the current row of the dp[] array we require only previous row, 
     * but if we start traversing the rows from right to left then it can be done with a single row only
     *
     * TC: O(nw)
     * SC: O(w)
     *
     */
    public static int knapsackTabulationSpaceOptimized(int[] weight, int[] value, int w, int n) {
        int[] dp = new int[w+1];
        for(int i=0; i<n; i++) {
            for(int j=w; j>=0; j--) {
                if(weight[i] <= j) {
                    dp[j] = Math.max(dp[j], value[i] + dp[j-weight[i]]);
                }
            }
        }
        return dp[w];
    }
}
