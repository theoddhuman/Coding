package com.paul.subham.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author subham.paul
 * 
 * 1. Longest increasing subsequence (Recursion)
 * 2. Longest increasing subsequence (Memoization)
 * 3. Longest increasing subsequence (Tabulation)
 * 4. Longest increasing subsequence (Binary Search)
 */
public class Subsequence {
    public static void main(String[] args) {
        int[] a = {10,9,2,5,3,7,101,18};
        System.out.println(longestIncreasingSubsequenceBinarySearch(a));
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
}
