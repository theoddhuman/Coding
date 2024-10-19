package com.paul.subham.dynamicprogramming;

/**
 *
 * 1. Subset sum equal to k (Memoization)
 * 2. Subset sum equal to k (Tabulation)
 * 3. Subset sum equal to k (Tabulation - Space Optimized)
 */
public class Subsequence {
    public static void main(String[] args) {

    }

    /**
     * Subset sum equal to k (Memoization)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subSetSumK(int[] a, int k) {
        int n = a.length;
        Boolean[][] dp = new Boolean[n+1][k+1];
        return subSetSumK(a, n, k, dp);
    }

    private static boolean subSetSumK(int[] a, int i, int target, Boolean[][] dp) {
        if(target == 0) {
            return true;
        }
        if(i==0) {
            return false;
        }
        if(dp[i][target] != null) {
            return dp[i][target];
        }
        if(a[i-1] > target) {
            return dp[i][target] = subSetSumK(a, i-1, target, dp);
        }
        return dp[i][target] = subSetSumK(a, i-1, target-a[i-1], dp) || subSetSumK(a, i-1, target, dp);
    }

    /**
     * Subset sum equal to k (Tabulation)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subsetSumToKTab(int[] a, int k){
        int n = a.length;
        boolean[][] dp = new boolean[n+1][k+1];
        for(int i=0; i<=n; i++) {
            dp[i][0] = true;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                if(a[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-a[i-1]];
                }
            }
        }
        return dp[n][k];
    }

    /**
     * Subset sum equal to k (Tabulation - Space Optimized)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subsetSumToK(int[] a, int k){
        int n = a.length;
        boolean[] pre = new boolean[k+1];
        boolean[] cur = new boolean[k+1];
        pre[0] = cur[0] = true;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                if(a[i-1] > j) {
                    cur[j] = pre[j];
                } else {
                    cur[j] = pre[j] || pre[j-a[i-1]];
                }
            }
            System.arraycopy(cur, 0, pre, 0, k + 1);
        }
        return pre[k];
    }
}
