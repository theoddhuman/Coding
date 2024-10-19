package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * 1. Longest common subsequence (Memoization)
 * 2. Longest common subsequence (Tabulation)
 * 3. Longest common subsequence (Tabulation - Space optimized)
 * 4. Print longest common subsequence (Tabulation)
 */
public class StringDP {
    public static void main(String[] args) {
        System.out.println(printLongestCommonSubsequence("abcde", "acd"));
    }

    /**
     * Longest common subsequence (Memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int longestCommonSubsequenceMem(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m;i++){
            Arrays.fill(dp[i],-1);
        }
        return lcs(s1,s2,m,n,dp);
    }

    private static int lcs(String s1, String s2, int i, int j, int[][] dp) {
        if(i==0 || j==0) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        if(s1.charAt(i-1) == s2.charAt(j-1)) {
            return dp[i][j] = 1 + lcs(s1, s2, i-1, j-1, dp);
        }
        return dp[i][j] = Math.max(lcs(s1, s2, i-1, j,dp), lcs(s1, s2, i, j-1,dp));
    }

    /**
     * Longest common subsequence (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int longestCommonSubsequenceTab(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<=m;i++){
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Longest common subsequence (Tabulation - Space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public static int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] pre = new int[n+1];
        int[] cur = new int[n+1];
        for(int i=1; i<=m;i++){
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    cur[j] = 1 + pre[j-1];
                } else {
                    cur[j] = Math.max(pre[j], cur[j-1]);
                }
            }
            System.arraycopy(cur,0,pre,0,n+1);
        }
        return pre[n];
    }

    /**
     * Print longest common subsequence (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static String printLongestCommonSubsequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<=m;i++){
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int i = m;
        int j = n;
        StringBuilder s = new StringBuilder();
        while(i>0 && j>0) {
           if(s1.charAt(i-1) == s2.charAt(j-1)) {
               s.insert(0, s1.charAt(i - 1));
               i--;
               j--;
           } else if(dp[i-1][j] > dp[i][j-1]) {
               i--;
           } else {
               j--;
           }
        }
        return s.toString();
    }
}
