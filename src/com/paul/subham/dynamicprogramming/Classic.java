package com.paul.subham.dynamicprogramming;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 1. 0/1 Knapsack Problem (Recursion)
 * 2. 0/1 Knapsack Problem (Memoization)
 * 3. 0/1 Knapsack Problem (Tabulation)
 * 4. 0/1 Knapsack Problem (Tabulation - Space optimized)
 * 5. Edit distance (recursion)
 * 6. Edit distance (Memoization)
 * 7. Edit distance (Tabulation)
 * 8. Edit distance (Tabulation - space optimized)
 * 9. Edit distance (Tabulation - space optimized 2)
 * 10. Coin change (Recursion)
 * 11. Coin change (Memoization)
 * 12. Coin change (Tabulation)
 *
 *
 */
public class Classic {
    public static void main(String[] args) {
//        int[] weight = {20,24,36,40,42};
//        int[] value = {12,35,41,25,32};
//        System.out.println(knapsackTabulationSpaceOptimized(weight, value, 100, 5));
        int[] a = {2};
        System.out.println(minCoinTabulation(a, 3));
    }

    /**
     * 0/1 Knapsack Problem (Recursion)
     *
     * Given n items where each item has some weight and value associated with it and also given a bag with capacity W,
     * The task is to put the items into the bag such that the sum of values associated with them is the maximum possible.
     * weight = [20,24,36,40,42]
     * value = [12,35,41,25,32], w=100
     * maximum Possible value = 100 ( weight 24 + 36 + 40)
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

    /**
     * Edit distance (recursion)
     *
     * We are given two strings ‘S1’ and ‘S2’. We need to convert S1 to S2. The following three operations are allowed:
     * 1. Deletion of a character.
     * 2. Replacement of a character with another one.
     * 3. Insertion of a character.
     *
     * When the last characters of strings don’t match. Make three recursive calls as show below:
     *
     * Insert s2[n-1] at last of s1 : editDist(m, n-1)
     * Replace s1[m-1] with s2[n-1] : editDist(m-1, n-1)
     * Remove s1[m-1] : editDist(m-1, n)
     *
     * TC: O(3^n)
     * SC: O(n)
     */
    public static int editDistance(String s1, String s2) {
        return editDistance(s1, s2, s1.length(), s2.length());
    }

    private static int editDistance(String s1, String s2, int m, int n) {
        if(m == 0) {
            return n;
        }
        if(n == 0) {
            return m;
        }
        if (s1.charAt(m-1) == s2.charAt(n-1)) {
            return editDistance(s1, s2, m-1, n-1);
        }
        return 1 + Math.min(editDistance(s1, s2, m-1, n), Math.min(editDistance(s1, s2, m-1, n-1), editDistance(s1, s2, m, n-1)));
    }

    /**
     * Edit distance (Memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    private static int editDistanceMemoization(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i=0; i<=s1.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return editDistance(s1, s2, s1.length(), s2. length(), dp);
    }

    private static int editDistance(String s1, String s2, int m, int n, int[][] dp) {
        if(m == 0) {
            return n;
        }
        if(n == 0) {
            return m;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }
        if (s1.charAt(m-1) == s2.charAt(n-1)) {
            return dp[m][n] = editDistance(s1, s2, m-1, n-1);
        }
        return dp[m][n] = 1 + Math.min(editDistance(s1, s2, m-1, n, dp), Math.min(editDistance(s1, s2, m-1, n-1, dp), editDistance(s1, s2, m, n-1, dp)));
    }

    /**
     * Edit distance (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int editDistanceTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m; i++) {
            dp[i][0] = i;
        }
        for(int i=0; i<=n; i++) {
            dp[0][i] = i;
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Edit distance (Tabulation - space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public static int editDistanceTabulationSpaceOptimized(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] curr = new int[n+1];
        int[] pre = new int[n+1];
        for(int i=0; i<=n; i++) {
            pre[i] = i;
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    curr[j] = pre[j-1];
                } else {
                    curr[j] = 1 + Math.min(curr[j-1], Math.min(pre[j], pre[j-1]));
                }
            }
            int[] temp = curr;
            curr = pre;
            pre = temp;
        }
        return pre[n];
    }

    /**
     * Edit distance (Tabulation - space optimized 2)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public static int editDistanceTabulationSpaceOptimized2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] curr = new int[n+1];
        for(int i=0; i<=n; i++) {
            curr[i] = i;
        }
        int pre;
        int temp;
        for(int i=1; i<=m; i++) {
            pre = curr[0];
            curr[0] = i;
            for(int j=1; j<=n; j++) {
                temp = curr[j];
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    curr[j] = pre;
                } else {
                    curr[j] = 1 + Math.min(curr[j-1], Math.min(pre, curr[j]));
                }
                pre = temp;
            }
        }
        return curr[n];
    }

    /**
     * Coin change (Recursion)
     *
     * Given an array coins[] of size N and a target value sum, where coins[i] represents the coins of different denominations.
     * You have an infinite supply of each of coins. The task is to find minimum number of coins required to make the given value sum.
     * If it’s not possible to make a change, print -1
     *
     * TC: O(n^k)
     * SC: O(n)
     */
    public static int minCountRecursion(int[] a, int sum) {
        return minCoinUtil(a, sum);
    }

    private static int minCoinUtil(int[] a, int sum) {
        if(sum == 0) {
            return 0;
        }
        int count = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            if(a[i] <= sum) {
                int c = minCoinUtil(a, sum-a[i]);
                if(c != Integer.MAX_VALUE) {
                    count = Math.min(count, 1 + c);
                }
            }
        }
        return count;
    }

    /**
     * Coin change (Memoization)
     *
     * TC: O(nk)
     * SC: O(n)
     */
    public static int minCoinMemoization(int[] a, int sum) {
        int[] dp = new int[sum+1];
        Arrays.fill(dp, -1);
        int coinCount = minCoinUtil(a, sum, dp);
        return  coinCount == Integer.MAX_VALUE? -1: coinCount;
    }

    private static int minCoinUtil(int[] a, int sum, int[] dp) {
        if(sum == 0) {
            return 0;
        }
        if(dp[sum] != -1) {
            return dp[sum];
        }
        int count = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            if(a[i] <= sum) {
                int c = minCoinUtil(a, sum-a[i]);
                if(c != Integer.MAX_VALUE) {
                    count = Math.min(count, 1 + c);
                }
            }
        }
        return dp[sum] = count;
    }

    /**
     * Coin change (Tabulation)
     *
     * TC: O(nk)
     * SC: O(n)
     */
    private static int minCoinTabulation(int[] a, int sum) {
        int[] dp = new int[sum+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=sum; i++) {
            for(int j=0; j<a.length; j++) {
                if(a[j] <= i) {
                    int coinCount = dp[i-a[j]];
                    if(coinCount != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], 1+coinCount);
                    }
                }
            }
        }
        if(dp[sum] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[sum];
    }



}
