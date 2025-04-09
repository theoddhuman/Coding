package com.paul.subham.dynamicprogramming;

import java.util.*;

/**
 * @author subham.paul
 *
 * 1. Longest increasing subsequence (Memoization - take/not-take approach)
 * 2. Longest increasing subsequence (Memoization)
 * 3. Longest increasing subsequence (Tabulation)
 * 4. Longest increasing subsequence (Binary Search)
 * 5. Print longest increasing subsequence (Tabulation)
 * 6. Print longest divisible subset (Tabulation)
 * 7. Longest Subset of String Chain (Memoization)
 * 8. Longest Subset of String Chain (Tabulation)
 * 9. Longest Bitonic Subsequence (Tabulation)
 * 10. Number of longest increasing subsequences (Tabulation)
 * 11 Maximum sum increasing subsequence (Recursion)
 * 12. Maximum sum increasing subsequence (Memoization)
 * 13. Maximum sum increasing subsequence (Tabulation)
 *
 */
public class LIS {
    public static void main(String[] args) {
        //int[] a = { 3, 34, 4, 12, 5, 2 };
        int[] a = {10,9,2,5,3,7,101,18};
        String s1 = "abcde";
        String s2 = "ace";
        System.out.println(longestIncreasingSubsequenceBinarySearch(a));
    }

    /**
     * Longest increasing subsequence (Memoization - take/not-take approach)
     * 10,9,2,5,3,7,101,18 -> 2,3,7,101 -> size 4
     *
     * TC: O(n^2)
     * SC: O(n^2)
     *
     */
    public static int lengthOfLISMem(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++) {
            Arrays.fill(dp[i],-1);
        }
        return lengthOfLIS(a,0,-1,dp);
    }

    private static int lengthOfLIS(int[] a, int i, int pre, int[][] dp) {
        if(i==a.length) {
            return 0;
        }
        if(dp[i][pre+1] != -1) {
            return dp[i][pre+1];
        }
        if(pre == -1 || a[pre] < a[i]) {
            return dp[i][pre+1] = Math.max(1 + lengthOfLIS(a, i+1, i, dp), lengthOfLIS(a, i+1, pre, dp));
        }
        return dp[i][pre+1] = lengthOfLIS(a, i+1, pre, dp);
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
     * Print longest increasing subsequence (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n)
     *
     */
    public static ArrayList<Integer> longestIncreasingSubsequence(int n, int[] a) {
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        for(int i=0; i<n; i++) {
            hash[i]=i;
        }
        int index = 0;
        int max = 1;
        for(int i=1;i<n;i++) {
            for(int j=0; j<i; j++) {
                if(a[i] > a[j]) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        hash[i] = j;
                    }
                }
            }
            if(dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        while(hash[index] != index) {
            list.add(a[index]);
            index = hash[index];
        }
        list.add(a[index]);
        Collections.reverse(list);
        return list;
    }

    /**
     * Print longest divisible subset (Tabulation)
     *
     * A divisible subset is the one in which if we pick two elements i and j from the subset,
     * then either arr[i]%arr[j] == 0 or arr[j] % arr[i] == 0.
     *
     * TC: O(n^2)
     * SC: O(n)
     *
     */
    public List<Integer> largestDivisibleSubset(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);
        Arrays.sort(a);
        for(int i=0; i<n; i++) {
            hash[i] = i;
        }

        int index = 0;
        int max = 1;
        for(int i=1;i<n;i++) {
            for(int j=0; j<i; j++) {
                if(a[i] % a[j] == 0) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j]+1;
                        hash[i] = j;
                    }
                }
            }
            if(dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (hash[index] != index) {
            list.add(a[index]);
            index = hash[index];
        }
        list.add(a[index]);
        Collections.reverse(list);
        return list;
    }

    /**
     * Longest Subset of String Chain (Memoization)
     *
     * Given an array of words where each word consists of lowercase English letters.
     *
     * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA
     * without changing the order of the other characters to make it equal to wordB.
     *
     * Return the length of the longest possible word chain with words chosen from the given list of words
     *
     * As we need subset we can sort the array based on size, order doesn't matter for subsets.
     *
     * TC: O(n^2)
     * SC: O(n^2)
     *
     */
    public static int longestStrChain(String[] words) {
        int n = words.length;
        Arrays.sort(words, (s1,s2) -> s1.length()-s2.length());
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        int longest = 1;
        for(int i=0; i<n;i++) {
            longest = Math.max(longest, lsc(words,i,dp));
        }
        return longest;
    }

    public static int lsc(String[] words, int i, int[] dp) {
        if(dp[i] != -1) {
            return dp[i];
        }
        int longest = 1;
        for(int k=i+1; k<words.length; k++) {
            if(compare(words[i], words[k])) {
                longest = Math.max(longest, 1+lsc(words, k, dp));
            }
        }
        return dp[i] = longest;
    }

    private static boolean compare(String s1, String s2) {
        if(s1.length() + 1 != s2.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        while(j < s2.length()) {
            if(i < s1.length() && s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return (i == s1.length()) && (j == s2.length());
    }

    /**
     * Longest Subset of String Chain (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n)
     *
     */
    public int longestStrChainTab(String[] words) {
        int n = words.length;
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int longest = 1;
        for(int i=1; i<n;i++) {
            for(int j=0; j<i;j++) {
                if(compare(words[j],words[i])){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            longest = Math.max(longest, dp[i]);
        }
        return longest;
    }

    /**
     * Longest Bitonic Subsequence (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n)
     *
     */
    public static int LongestBitonicSequence(int n, int[] a) {
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        for(int i=1;i<n;i++) {
            for(int j=0; j<i; j++) {
                if(a[i] > a[j]) {
                    dp1[i] = Math.max(dp1[i], dp1[j]+1);
                }
            }
        }
        for(int i=n-2;i>=0;i--) {
            for(int j=n-1; j>i; j--) {
                if(a[i] > a[j]) {
                    dp2[i] = Math.max(dp2[i], dp2[j]+1);
                }
            }
        }
        int longest = 0;
        for(int i=0; i<n; i++) {
            //this condition is required if we don't consider increasing or decreasing subsequence as bitonic
            if(dp1[i] > 1 && dp2[i] > 1) {
                longest = Math.max(longest, dp1[i]+dp2[i]-1);
            }
        }
        return longest;
    }

    /**
     * Number of longest increasing subsequences (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n)
     *
     */
    public int findNumberOfLIS(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        int[] ct = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(ct,1);
        int max = 1;
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(a[i] > a[j]) {
                    if(dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j]+1;
                        ct[i] = ct[j];
                    } else if (dp[j]+1 == dp[i]) {
                        ct[i] += ct[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        int count = 0;
        for(int i=0;i<n;i++){
            if(dp[i] == max) {
                count += ct[i];
            }
        }
        return count;
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
