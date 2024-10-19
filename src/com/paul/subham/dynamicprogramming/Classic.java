package com.paul.subham.dynamicprogramming;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 5. Edit distance (recursion)
 * 6. Edit distance (Memoization)
 * 7. Edit distance (Tabulation)
 * 8. Edit distance (Tabulation - space optimized)
 * 9. Edit distance (Tabulation - space optimized 2)
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


}
