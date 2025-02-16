package com.paul.subham.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Longest common subsequence (Memoization)
 * 2. Longest common subsequence (Tabulation)
 * 3. Longest common subsequence (Tabulation - Space optimized)
 * 4. Print longest common subsequence (Tabulation)
 * 5. Longest common substring (Memoization)
 * 6. Longest common substring (Tabulation)
 * 7. Longest common substring (Tabulation - Space optimized)
 * 8. Longest Palindromic subsequence (Tabulation - Space optimized)
 * 9. Minimum insertions to make a string palindrome (Tabulation - Space optimized)
 * 10. Minimum insertions/deletions to convert a string(Tabulation - Space optimized)
 * 11. Shortest Common Supersequence (Tabulation)
 * 12. Distinct subsequences (Memoization)
 * 13. Distinct subsequences (Tabulation)
 * 14. Distinct subsequences (Tabulation - Space optimized)
 * 15. Edit distance (Memoization)
 * 16. Edit distance (Tabulation)
 * 17. Edit distance (Tabulation - space optimized)
 * 18. Wildcard matching (Memoization)
 * 19. Wildcard matching (Tabulation)
 * 20. Wildcard matching (Tabulation - Space optimized)
 * 21. Palindrome Partitioning - list of all partitions (Recursion)
 * 22. Palindrome Partitioning - list of all partitions (Memoization)
 * 23. Count palindromic subsequences (Memoization)
 * 24. Count palindromic subsequences (Tabulation)
 */
public class StringDP {
    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("abcdgh", "acdghr"));
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

    /**
     * Longest common substring (Memoization)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int longestCommonSubstringMem(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m;i++){
            Arrays.fill(dp[i],-1);
        }
        lcSubstring(s1,s2,m,n,dp);
        return max;
    }
    private static int max = 0;
    private static int lcSubstring(String s1, String s2, int i, int j, int[][] dp) {
        if(i==0 || j==0) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if(s1.charAt(i-1) == s2.charAt(j-1)) {
            ans = 1 + lcSubstring(s1, s2, i-1, j-1, dp);
            max = Math.max(ans, max);
        }
        //these two recursive calls are mandatory, otherwise we may miss a few substring matching if we return from above if condition,
        // ("yxxzzxxxx", "yzyzxxyxxz")
        lcSubstring(s1, s2, i-1, j,dp);
        lcSubstring(s1, s2, i, j-1,dp);
        return dp[i][j] = ans;
    }

    /**
     * Longest common substring (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int longestCommonSubstringTab(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(int i=1; i<=m;i++){
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max;
    }

    /**
     * Longest common substring (Tabulation - Space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public static int longestCommonSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] pre = new int[n+1];
        int[] cur = new int[n+1];
        int max = 0;
        for(int i=1; i<=m;i++){
            for(int j=1; j<=n; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    cur[j] = 1 + pre[j-1];
                    max = Math.max(cur[j], max);
                } else {
                    cur[j] = 0;
                }
            }
            System.arraycopy(cur, 0, pre, 0, n+1);
        }
        return max;
    }

    /**
     * Longest Palindromic subsequence (Tabulation - Space optimized)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int longestPalindromeSubsequence(String s) {
        String revS = new StringBuffer(s).reverse().toString();
        return longestCommonSubsequence(s, revS);
    }

    /**
     * Minimum insertions to make a string palindrome (Tabulation - Space optimized)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int minInsertions(String s) {
        return s.length() - longestPalindromeSubsequence(s);
    }

    /**
     * Minimum insertions/deletions to convert a string(Tabulation - Space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public static int minDistance(String word1, String word2) {
        int k = longestCommonSubsequence(word1, word2);
        return (word1.length() - k) + (word2.length() - k);
    }

    /**
     * Shortest Common Supersequence (Tabulation)
     *
     * A supersequence is defined as the string which contains both the strings S1 and S2 as subsequences.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static String shortestCommonSuperSequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<=m; i++) {
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
        StringBuilder sb = new StringBuilder();
        while(i>0 && j>0) {
            if(s1.charAt(i-1) == s2.charAt(j-1)) {
                sb.insert(0, s1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j] > dp[i][j-1]) {
                sb.insert(0, s1.charAt(i-1));
                i--;
            } else {
                sb.insert(0, s2.charAt(j-1));
                j--;
            }
        }
        while(i>0) {
            sb.insert(0, s1.charAt(i-1));
            i--;
        }
        while(j>0) {
            sb.insert(0, s2.charAt(j-1));
            j--;
        }
        return sb.toString();
    }

    /**
     * Distinct subsequences (Memoization)
     *
     * Given two strings s and t, return the number of distinct subsequences of s which equals t
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m;i++) {
            Arrays.fill(dp[i],-1);
        }
        return ways(s,t,m,n,dp);
    }

    private static int ways(String s, String t, int i, int j, int[][] dp) {
        if(j==0) {
            return 1;
        }
        if(i==0) {
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(s.charAt(i-1) == t.charAt(j-1)) {
            return dp[i][j] = ways(s,t,i-1,j-1,dp) + ways(s,t,i-1,j,dp);
        }
        return dp[i][j] = ways(s,t,i-1,j,dp);
    }

    /**
     * Distinct subsequences (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int numDistinctTab(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m;i++) {
            dp[i][0] = 1;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Distinct subsequences (Tabulation - Space optimized)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static int numDistinctTabSpaceOpt(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[] pre = new int[n+1];
        int[] cur = new int[n+1];
        pre[0] = cur[0] = 1;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    cur[j] = pre[j] + pre[j-1];
                } else {
                    cur[j] = pre[j];
                }
            }
            System.arraycopy(cur,0,pre,0,n+1);
        }
        return pre[n];
    }

    /**
     * Edit distance (Memoization)
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
     * TC: O(mn)
     * SC: O(n)
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
            return dp[m][n] = editDistance(s1, s2, m-1, n-1, dp);
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
            System.arraycopy(curr,0,pre,0,n+1);
        }
        return pre[n];
    }

    /**
     * Wildcard matching (Memoization)
     *
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        Boolean[][] dp = new Boolean[m+1][n+1];
        return isMatch(s,p,m,n,dp);
    }

    private static boolean isMatch(String s, String p, int i, int j, Boolean[][] dp) {
        if(i==0 && j==0) {
            return true;
        }
        if(j==0) {
            return false;
        }
        if(i==0) {
            return isAllStars(p, j);
        }
        if(dp[i][j] != null) {
            return dp[i][j];
        }
        if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
            return dp[i][j] = isMatch(s,p,i-1,j-1,dp);
        }
        if(p.charAt(j-1) == '*') {
            return dp[i][j] = isMatch(s,p,i,j-1,dp) || isMatch(s,p,i-1,j,dp);
        }
        return dp[i][j] = false;
    }

    private static boolean isAllStars(String s, int end) {
        for(int i=0; i<end; i++) {
            if(s.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    /**
     * Wildcard matching (Tabulation)
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public static boolean isMatchTab(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i=1; i<=n; i++) {
            dp[0][i] = isAllStars(p,i);
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Wildcard matching (Tabulation - Space optimized)
     *
     * TC: O(mn)
     * SC: O(n)
     */
    public static boolean isMatchTabSpaceOpt(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[] pre = new boolean[n+1];
        boolean[] cur = new boolean[n+1];
        pre[0] = true;
        for(int i=1; i<=n; i++) {
            pre[i] = isAllStars(p,i);
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1) == '?') {
                    cur[j] = pre[j-1];
                } else if(p.charAt(j-1) == '*') {
                    cur[j] = pre[j] || cur[j-1];
                } else {
                    cur[j] = false;
                }
            }
            System.arraycopy(cur,0,pre,0,n+1);
        }
        return pre[n];
    }

    /**
     * Palindrome Partitioning - list of all partitions (Recursion)
     *
     * You are given a string s, partition it in such a way that every substring is a palindrome.
     * Return all such palindromic partitions of s.
     *
     * TC: O(2^n)(k)(n/2) = O(nkn^2) n/2 -> palindrome checking, 2^n -> total no of possible strings, k-> avg count of sub strings in a list
     * SC: O(n)
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partition(s,0,new ArrayList<>(),res);
        return res;
    }

    private static void partition(String s, int i, List<String> list, List<List<String>> res) {
        if(i==s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int k=i; k<s.length(); k++) {
            String sub = s.substring(i,k+1);
            if(isPalindrome(sub)) {
                list.add(sub);
                partition(s,k+1,list,res);
                list.remove(list.size()-1);
            }
        }
    }

    private static boolean isPalindrome(String s ) {
        int n = s.length();
        for(int i=0; i<n/2;i++) {
            if(s.charAt(i) != s.charAt(n-1-i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Palindrome Partitioning - list of all partitions (Memoization)
     *
     * TC: O(n^2)(k)(n/2) = O(n^3)
     * SC: O(n^2)
     */
    public static List<List<String>> partitionMem(String s) {
        List<List<String>> res = new ArrayList<>();
        Boolean[][] dp = new Boolean[s.length()][s.length()];
        partition(s,0,new ArrayList<>(),res,dp);
        return res;
    }

    private static void partition(String s, int i, List<String> list, List<List<String>> res, Boolean[][] dp) {
        if(i==s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int k=i; k<s.length(); k++) {
            if(isPalindrome(s, i, k, dp)) {
                list.add(s.substring(i,k+1));
                partition(s,k+1,list,res,dp);
                list.remove(list.size()-1);
            }
        }
    }

    private static boolean isPalindrome(String s, int i, int j, Boolean[][] dp) {
        if(dp[i][j] != null) {
            return dp[i][j];
        }
        int n = s.length();
        for(int k=i; k<=(i+j)/2;k++) {
            if(s.charAt(k) != s.charAt((i+j-k))) {
                return dp[i][j] = false;
            }
        }
        return dp[i][j] = true;
    }

    /**
     * Count palindromic subsequences (Memoization)
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int countPS(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i],-1);
        }
        return countPSUtil(0, n-1, s, dp);
    }

    private static int countPSUtil(int i, int j, String s, int[][] dp) {
        if(i > j) {
            return 0;
        }
        if(i == j) {
            return 1;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        if(s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = 1 + countPSUtil(i+1, j, s, dp) + countPSUtil(i, j-1, s, dp);
        }
        return dp[i][j] = countPSUtil(i+1, j, s, dp) + countPSUtil(i, j-1, s, dp) - countPSUtil(i+1, j-1, s, dp);
    }

    /**
     * Count palindromic subsequences (Tabulation)
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int countPSTab(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=0; i<n; i++) {
            dp[i][i] = 1;
        }
        for(int l=2; l<=n; l++) {
            for(int i=0; i<=n-l; i++) {
                int j = l+i-1;
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 1 + dp[i][j-1] + dp[i+1][j];
                } else {
                    dp[i][j] = dp[i][j-1] + dp[i+1][j] - dp[i+1][j-1];
                }
            }
        }
        return dp[0][n-1];
    }
}
