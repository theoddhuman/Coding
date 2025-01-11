package com.paul.subham.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Length of the longest substring without repeating characters
 * 2. Length of the longest substring without repeating characters (Sliding window)
 * 3. Length of the longest substring without repeating characters (Binary search)
 * 4. Length of the longest substring without repeating characters (Storing last index)
 * 5. Length of the longest substring without repeating characters (Storing last index - using hashing)
 * 6. Number of substrings containing all three characters
 * 7. Number of substrings containing all three characters (Sliding window)
 */
public class SubString {
    public static void main(String[] args) {

    }

    /**
     * Length of the longest substring without repeating characters
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public static int longestUniqueSubstring(String s) {
        int n = s.length();
        int maxLength = 1;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(isDistinct(s, i, j)) {
                    maxLength = Math.max(maxLength, j-i+1);
                }
            }
        }
        return maxLength;
    }

    private static boolean isDistinct(String s, int start, int end) {
        boolean[] visited = new boolean[26];
        for(int i=start; i<=end; i++) {
            int c = s.charAt(i) - 'a';
            if(visited[c]) {
                return false;
            }
            visited[c] = true;
        }
        return true;
    }

    /**
     * Length of the longest substring without repeating characters (Sliding window)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int longestUniqueSubstringSlidingWindow(String s) {
        int n = s.length();
        int maxLength = 1;
        for(int i=0; i<n; i++) {
            boolean[] visited = new boolean[26];
            for(int j= i; j<n; j++) {
                int character = s.charAt(j) - 'a';
                if(visited[character]) {
                    break;
                }
                maxLength = Math.max(maxLength, j-i+1);
                visited[character] = true;
            }
        }
        return maxLength;
    }

    /**
     * Length of the longest substring without repeating characters (Binary search)
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static int longestUniqueSubstringBinarySearch(String s) {
        int low = 1;
        int high = s.length();
        int ans = 1;
        while(low <= high) {
            int mid = (low+high)/2;
            if(isValid(s, mid)) {
                ans = mid;
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return ans;
    }

    private static boolean isValid(String s, int mid) {
        int[] count = new int[26];
        int distinct = 0;
        for(int i=0; i<s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            if(count[s.charAt(i) - 'a'] == 1) {
                distinct++;
            }
            //sliding by substring of size mid
            if(i>=mid) {
                count[s.charAt(i-mid) - 'a']--;
                if(count[s.charAt(i-mid) - 'a'] == 0) {
                    distinct--;
                }
            }
            if(i>=mid-1 && distinct == mid) {
                return true;
            }
        }
        return false;
    }

    /**
     * Length of the longest substring without repeating characters (Storing last index)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int longestUniqueSubstringLastIndex(String s) {
        int result = 1;
        int startWindow = 0;
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);
        for(int i=0; i<s.length(); i++) {
            // If the character appeared in the current substring,
            // start substring from the next character of last appearance of the character
            startWindow = Math.max(startWindow, lastIndex[s.charAt(i) - 'a']+1);
            result = Math.max(result, i-startWindow+1);
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        return result;
    }

    /**
     * Length of the longest substring without repeating characters (Storing last index - using hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int longestUniqueSubstringHashing(String s) {
        int result = 1;
        int startWindow = 0;
        Map<Character, Integer> seen = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            // If the character appeared in the current substring,
            // start substring from the next character of last appearance of the character
            if(seen.containsKey(s.charAt(i))) {
                startWindow = Math.max(startWindow, seen.get(s.charAt(i))+1);
            }
            result = Math.max(result, i-startWindow+1);
            seen.put(s.charAt(i), i);
        }
        return result;
    }

    /**
     * Number of substrings containing all three characters
     *
     * Given a string s consisting only of characters a, b and c.
     * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public int numberOfSubstrings(String s) {
        int count = 0;
        int n = s.length();
        for(int i=0; i<n; i++) {
            int[] hash = new int[3];
            for(int j=i; j<n; j++) {
                hash[s.charAt(j)-'a'] = 1;
                if(hash[0] + hash[1] + hash[2] == 3) {
                    count = count + n-j;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * Number of substrings containing all three characters (Sliding window)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int numberOfSubstringsSW(String s) {
        int count = 0;
        int n = s.length();
        int[] lastSeen = {-1,-1,-1};
        for(int i=0; i<n; i++) {
            lastSeen[s.charAt(i)-'a'] = i;
            count += Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2])) + 1;
        }
        return count;
    }
}