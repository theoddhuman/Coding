package com.paul.subham.string.operations;

import java.util.HashSet;

/**
 * @author subham.paul
 *
 * 1. Check if characters of a string can be rearranged to form a palindrome
 * 2. Check if characters of a string can be rearranged to form a palindrome (Hashing)
 * 3. Longest palindromic substring
 * 4. Longest palindromic substring (Dynamic Programming)
 * 5. Longest palindromic substring (Using expansion from center)
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindromicSubstringSpaceOptimized("forgeeksskeegfor"));
    }


    /**
     * Check if characters of a string can be rearranged to form a palindrome
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isPalindromePossible(String s) {
        int[] a = new int[256];
        for(int i=0; i<s.length(); i++) {
            a[s.charAt(i)] ++;
        }
        int count = 0;
        for(int i=0; i<256; i++) {
            if(a[i] % 2 == 1) {
                count ++;
            }
            if(count > 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if characters of a string can be rearranged to form a palindrome (Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isPalindromePossibleHashing(String s) {
        HashSet<Character> set = new HashSet<>();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }

    /**
     * Longest palindromic substring
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public static int longestPalindromicSubstring(String s) {
        int maxLength = 1;
        int start = 0;
        for(int i=0; i<s.length(); i++) {
            for(int j=0; j<s.length(); j++) {
                boolean flag = true;
                for(int k = 0; k<(j-i+1)/2; k++) {
                    if(s.charAt(i+k) != s.charAt(j-k)) {
                        flag = false;
                        break;
                    }
                }
                if(flag && j-i+1 > maxLength) {
                    start = i;
                    maxLength = j-i+1;
                }
            }
        }
        System.out.println(s.substring(start, start+maxLength));
        return maxLength;
    }

    /**
     * Longest palindromic substring (Dynamic Programming)
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static int longestPalindromicSubstringDP(String s) {
        int n = s.length();
        // dp[i][j], i, start of substring and j, end of substring
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLength = 1;
        for(int i=0; i<n; i++) {
            dp[i][i] = true;
        }
        for(int i=0; i<n-1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }
        //k is length of substrings
        for(int k=3; k<=n; k++) {
            for(int i=0; i<n-k+1; i++) {
                int j = i+k-1;
                if(dp[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = Math.max(maxLength, k);
                }
            }
        }
        System.out.println(s.substring(start, start+maxLength));
        return maxLength;
    }

    /**
     * Longest palindromic substring (Using expansion from center)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    private static int longestPalindromicSubstringSpaceOptimized(String s) {
        int n = s.length();
        int maxLength = 1;
        int start = 0;
        int low, high;
        for(int i=1; i<n; i++) {
            //check for even strings
            low = i-1;
            high = i;
            while(low >=0 && high<n && s.charAt(low) == s.charAt(high)) {
                if(high-low+1 > maxLength) {
                    maxLength = high-low+1;
                    start = low;
                }
                low--;
                high++;
            }
            //check for odd strings
            low = i-1;
            high = i+1;
            while(low >=0 && high<n && s.charAt(low) == s.charAt(high)) {
                if(high-low+1 > maxLength) {
                    maxLength = high-low+1;
                    start = low;
                }
                low--;
                high++;
            }
        }
        System.out.println(s.substring(start, start+maxLength));
        return maxLength;
    }
}
