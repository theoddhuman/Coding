package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 1. Count all possible paths from top left to bottom right of a matrix
 * 2. Count all possible paths from top left to bottom right of a matrix (Dynamic programming - memoization)
 * 3. Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation)
 * 4. Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation - space optimized)
 * 5. Count all possible paths from top left to bottom right of a matrix (Combinations)
 * 6. Count all possible paths from top left to bottom right of a matrix of 0's and 1's (0 pass through)
 * 7. Count all possible paths from top left to bottom right of a matrix of 0's and 1's (1 pass through)
 * 8. Count all possible paths from top left to bottom right of a matrix of 0's and 1's (1 pass through)(Dynamic Programming - Memoization)
 * 9. Count all possible paths from top left to bottom right of a matrix of 0's and 1's (1 pass through)(dynamic Programming - Tabulation)
 */
public class Path {
    public static void main(String[] args) {
        int[][] a = {{1,0,1},
                     {1,1,1},
                     {1,1,1}};
        System.out.println(countPath1Tabulation(a));
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix
     *
     * TC: O(2^n)
     * SC: O(mn)
     */
    public static int pathCount(int m, int n) {
        if(m==1 || n== 1) {
            return 1;
        }
        return pathCount(m-1, n) + pathCount(m, n-1);
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Dynamic programming - memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int pathCountMemoization(int m, int n){
        int[][] dp = new int[m+1][n+1];
        return pathCount(m, n, dp);
    }

    private static int pathCount(int m, int n, int[][] dp) {
        if(m==1 || n==1) {
            return dp[m][n] = 1;
        }
        if(dp[m][n] != 0) {
            return dp[m][n];
        }
        return dp[m][n] = pathCount(m-1, n, dp) + pathCount(m, n-1, dp);
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    private static int pathCountTabulation(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            dp[i][0] = 1;
        }
        for(int i=0; i<n; i++) {
            dp[0][i] = 1;
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Dynamic programming - tabulation - space optimized)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    private static int pathCountSpaceOptimized(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i=0; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix (Combinations)
     *
     * can go to right n-1 ways
     * can go to down m-1 ways
     *
     * total possible ways
     * = ((m-1)+(n-1))! / ((m-1)! * (n-1)!)
     * = (m+n-2)!/ ((m-1)! * (n-1)!) = (m+n-2 C n-1)
     *
     *   1 * 2 * 3 * 4 * ....(n-1) * n * (n+1) * (n+2) * ...... * (m+n-2)
     * = -----------------------------------------------------------------
     *   1 * 2 * .... * (n-1) * (m-1)!
     *
     *    n * (n+1) * (n+2) * ...... * (m+n-2)
     * = ---------------------------------------
     *    1 * 2 * 3 * ... * (m-1)
     *
     * TC: O(m)
     * SC: O(1)
     */
    private static int pathCountCombinations(int m, int n) {
        int pathCount = 1;
        for(int i=1; i<=m-1; i++) {
            pathCount = pathCount * (i+n-1) / i;
        }
        return pathCount;
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix of 0's and 1's (0 pass through)
     *
     * TC: O(2^n)
     * SC: O(mn)
     */
    public static int pathCount0(int[][] a) {
        return pathCount0(a, a.length, a[0].length, 0, 0);
    }

    private static int pathCount0(int[][] a, int m, int n, int i, int j) {
        if(i == m-1 && j == n-1) {
            return 1 - a[i][j];
        }
        if(a[i][j] == 1) {
            return 0;
        }
        if(i==m-1) {
            return pathCount0(a, m, n, i, j+1);
        }
        if(j==n-1) {
            return pathCount0(a, m, n, i+1, j);
        }
        return pathCount0(a, m, n, i, j+1) + pathCount0(a, m, n, i+1, j);
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix of 0's and 1's (1 pass through)
     *
     * TC: O(2^n)
     * SC: O(mn)
     */
    public static int pathCount1(int[][] a) {
        return pathCount1(a, a.length, a[0].length, 0, 0);
    }

    private static int pathCount1(int[][] a, int m, int n, int i, int j) {
        if(i == m-1 && j == n-1) {
            return a[i][j];
        }
        if(a[i][j] == 0) {
            return 0;
        }
        if(i==m-1) {
            return pathCount1(a, m, n, i, j+1);
        }
        if(j==n-1) {
            return pathCount1(a, m, n, i+1, j);
        }
        return pathCount1(a, m, n, i, j+1) + pathCount1(a, m, n, i+1, j);
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix of 0's and 1's (1 pass through)(dynamic Programming - Memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int pathCount1Memoization(int[][] a) {
        int[][] dp = new int[a.length][a[0].length];
        for(int i=0; i<a.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return pathCount1(a, a.length, a[0].length, 0, 0, dp);
    }

    private static int pathCount1(int[][] a, int m, int n, int i, int j, int[][] dp) {
        if(i == m-1 && j == n-1) {
            return dp[i][j] = a[i][j];
        }
        if(a[i][j] == 0) {
            return dp[i][j] = 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        if(i==m-1) {
            return dp[i][j] = pathCount1(a, m, n, i, j+1, dp);
        }
        if(j==n-1) {
            return  dp[i][j] = pathCount1(a, m, n, i+1, j, dp);
        }
        return dp[i][j] = pathCount1(a, m, n, i, j+1) + pathCount1(a, m, n, i+1, j, dp);
    }

    /**
     * Count all possible paths from top left to bottom right of a matrix of 0's and 1's (1 pass through)(dynamic Programming - Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int countPath1Tabulation(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = a[0][0];
        for(int i=1; i<m; i++) {
            if(dp[i-1][0] == 1 && a[i][0] == 1) {
                dp[i][0] = 1;
            }
        }
        for(int i=1; i<n; i++) {
            if(dp[0][i-1] == 1 && a[0][i] ==1) {
                dp[0][i] = 1;
            }
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(a[i][j] == 1) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
