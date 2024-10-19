package com.paul.subham.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author subham.paul
 *
 * 2. Longest increasing subsequence (Memoization)
 * 3. Longest increasing subsequence (Tabulation)
 * 4. Longest increasing subsequence (Binary Search)
 * 5. Longest common subsequence (Recursion)
 * 6. Longest common subsequence (memoization)
 * 7. Longest common subsequence (Tabulation)
 * 8. Longest common subsequence (Tabulation - space optimized)
 * 9. Maximum sum increasing subsequence (Recursion)
 * 10. Maximum sum increasing subsequence (Memoization)
 * 11. Maximum sum increasing subsequence (Tabulation)
 */
public class LIS {
    public static void main(String[] args) {
        int[] a = { 3, 34, 4, 12, 5, 2 };
        //int[] a = {1,1,1,1};
        String s1 = "abcde";
        String s2 = "ace";
    }



    /**
     * Longest increasing subsequence (Recursion)
     * 10,9,2,5,3,7,101,18 -> 2,3,7,101 -> size 4
     *
     * TC: O(Exponential)
     * SC: O(n)
     *
     */
    private static int longest=1;
    public static int longestIncreasingSubsequenceRecursion(int[] a) {
        for(int i=0; i<a.length; i++) {
            count(a, Integer.MIN_VALUE, i, 0);
        }
        return longest;
    }
    private static void count(int[] a, int last, int current, int count) {
        if(a[current] > last) {
            count += 1;
            longest = Math.max(count, longest);
            last = a[current];
        }
        for(int i=current+1; i<a.length; i++) {
            count(a, last, i, count);
        }
    }

    /**
     * Longest increasing subsequence (Memoization)
     *
     * TC: O(n^2)
     * SC: O(n^2)
     *
     */
    public static int lengthOfLIS(int[] a) {
        int longest = 1;
        int[] dp = new int[a.length];
        Arrays.fill(dp,-1);
        for(int i=0; i<a.length; i++) {
            longest = Math.max(longest,count(a, i, dp));
        }
        return longest;
    }

    private static int count(int[] a, int current, int dp[]) {
        if(dp[current] != -1) {
            return dp[current];
        }
        int longest = 1;
        for(int i=current+1; i<a.length; i++) {
            if(a[current] < a[i]) {
                longest = Math.max(longest, 1 + count(a, i, dp));
            }
        }
        return dp[current] = longest;
    }

    /**
     * Longest increasing subsequence (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n)
     *
     */
    public static int longestIncreasingSubsequenceTabulation(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(a[i] > a[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int longest = 1;
        for(int i=0; i<n; i++) {
            longest =Math.max(longest, dp[i]);
        }
        return longest;
    }

    /**
     * Longest increasing subsequence (Binary Search)
     *
     * TC: O(nlogn)
     * SC: O(n)
     *
     */
    public static int longestIncreasingSubsequenceBinarySearch(int[] a) {
        int n = a.length;
        List<Integer> ans = new ArrayList<>();
        ans.add(a[0]);
        for(int i=1; i<n; i++) {
            if(a[i] > ans.get(ans.size()-1)) {
                ans.add(a[i]);
            } else {
                int low = Collections.binarySearch(ans, a[i]);
                if(low < 0) {
                    low = 0;
                }
                ans.set(low, a[i]);
            }
        }
        return ans.size();
    }


    /**
     * Longest common subsequence (Recursion)
     *
     * "abcde", "ace" -> "ace" -> 3
     *
     * TC: O(2^(min(m,n)))
     * SC: O(min(m,n))
     *
     */
    public static int longestCommonSubSequence(String s1, String s2) {
        return longestCommonSubsequenceUtil(s1, s2, s1.length()-1, s2.length()-1);
    }

    private static int longestCommonSubsequenceUtil(String s1, String s2, int l1, int l2) {
        if(l1 < 0 || l2 < 0) {
            return 0;
        }
        if(s1.charAt(l1) == s2.charAt(l2)) {
            return 1 + longestCommonSubsequenceUtil(s1, s2, l1-1, l2-1);
        }
        return Math.max(longestCommonSubsequenceUtil(s1, s2, l1-1, l2),
                longestCommonSubsequenceUtil(s1, s2, l1, l2-1));
    }

    /**
     * Longest common subsequence (Memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     *
     */
    public static int longestCommonSubSequenceMemoization(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] dp = new int[l1][l2];
        for(int i=0; i<l1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return lcsMemoization(s1, s2, l1-1, l2-1, dp);
    }

    private static int lcsMemoization(String s1, String s2, int m, int n, int[][] dp) {
        if(m < 0 || n < 0) {
            return 0;
        }
        if(dp[m][n] != -1) {
            return dp[m][n];
        }
        if(s1.charAt(m) == s2.charAt(n)) {
            return dp[m][n] = 1 + longestCommonSubsequenceUtil(s1, s2, m-1, n-1);
        }
        return dp[m][n] = Math.max(longestCommonSubsequenceUtil(s1, s2, m-1, n),
                longestCommonSubsequenceUtil(s1, s2, m, n-1));
    }

    /**
     * Longest common subsequence (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     *
     */
    public static int longestCommonSubsequenceTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m][n];
        if(s1.charAt(0) == s2.charAt(0)) {
            dp[0][0] = 1;
        }
        for(int i=1; i<m; i++) {
            if(s1.charAt(i) == s2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int i=1; i<n; i++) {
            if(s1.charAt(0) == s2.charAt(i)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = dp[0][i-1];
            }
        }
        for (int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                if(s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * Longest common subsequence (Tabulation - space optimized)
     *
     * TC: O(mn)
     * SC: O(mn)
     *
     */
    public static int lcsTabulation(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[2][n+1];
        int rowIndex = 0;
        for (int i=1; i<=m; i++) {
            rowIndex = 1&i;
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[rowIndex][j] = 1 + dp[1-rowIndex][j-1];
                } else {
                    dp[rowIndex][j] = Math.max(dp[rowIndex][j-1], dp[1-rowIndex][j]);
                }
            }
        }
        return dp[rowIndex][n];
    }

    /**
     * Maximum sum increasing subsequence (Recursion)
     *
     * Given an array of n positive integers.
     * Find the sum of the maximum sum subsequence of the given array such that the integers
     * in the subsequence are sorted in strictly increasing order
     *
     * {1,100,2,3,1,100} -> {1,2,3,100} -> 106
     *
     * TC: O(2^n)
     * SC: O(n)
     */
    public static int maxSumIncreasingSubsequence(int[] a) {
        return maxSumIncreasingSubsequence(a, 0, Integer.MIN_VALUE);
    }

    private static int maxSumIncreasingSubsequence(int[] a, int i, int prev) {
        if(i == a.length) {
            return 0;
        }
        int take = Integer.MIN_VALUE;
        if(a[i] >= prev) {
            take = a[i] + maxSumIncreasingSubsequence(a, i+1, a[i]);
        }
        int nonTake = maxSumIncreasingSubsequence(a, i+1, prev);
        return Math.max(take, nonTake);
    }

    /**
     * Maximum sum increasing subsequence (Memoization)
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int maxIncreasingSumSubsequence(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n+1];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return maxSumIncreasingSubsequence(a, 0, -1, dp);
    }

    private static int maxSumIncreasingSubsequence(int[] a, int i, int prevIndex, int[][] dp) {
        if(i == a.length) {
            return 0;
        }
        if(dp[i][prevIndex+1] != -1) {
            return dp[i][prevIndex+1];
        }
        int take = Integer.MIN_VALUE;
        if(prevIndex == -1 || a[i] >= a[prevIndex]) {
            take = a[i] + maxSumIncreasingSubsequence(a, i+1, i, dp);
        }
        int nonTake = maxSumIncreasingSubsequence(a, i+1, prevIndex, dp);
        return dp[i][prevIndex+1] = Math.max(take, nonTake);
    }

    /**
     * Maximum sum increasing subsequence (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int maxSumIncreasingSubsequenceTabulation(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            dp[i] = a[i];
        }
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if (a[i] > a[j]) {
                    dp[i] = Math.max(a[i] + dp[j], dp[i]);
                }
            }
        }
        int max = dp[0];
        for(int i=1; i<n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
