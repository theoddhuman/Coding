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
 * 8. Longest substring with at most k distinct characters
 * 9. Longest substring with at most k distinct characters (Sliding Window)
 * 10. Longest substring with at most k distinct characters (Sliding Window - optimized)
 * 11. Count substring with at most k distinct characters
 * 12. Count substring with exactly k distinct characters
 * 13. Minimum window substring
 * 14. Minimum window substring (Sliding window)
 * 15. Minimum window subsequence
 */
public class SubString {
    public static void main(String[] args) {
        String s = "aaabbccd";
        System.out.println(longestSubstringAtMostKCharSWOpt(s, 2));
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
     * SC: O(1)
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
    public static int numberOfSubstringsSW(String s) {
        int count = 0;
        int n = s.length();
        int[] lastSeen = {-1,-1,-1};
        for(int i=0; i<n; i++) {
            lastSeen[s.charAt(i)-'a'] = i;
            count += Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2])) + 1;
        }
        return count;
    }

    /**
     * Longest substring with at most k distinct characters
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int longestSubstringAtMostKChar(String s, int k) {
        int n = s.length();
        int max = 0;
        for(int i=0; i<n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            for(int j=i; j<n; j++) {
                char c = s.charAt(j);
                map.put(c, map.getOrDefault(c, 0)+1);
                if(map.size() <= k) {
                    max = Math.max(max, j-i+1);
                } else {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * Longest substring with at most k distinct characters (Sliding Window)
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public static int longestSubstringAtMostKCharSW(String s, int k) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int start = 0;
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
            if(map.size() <= k) {
                max = Math.max(max, i-start+1);
            }
            while(map.size() > k) {
                char startc = s.charAt(start);
                map.put(startc, map.get(startc)-1);
                if(map.get(startc) == 0) {
                    map.remove(startc);
                }
                start++;
            }
        }
        return max;
    }

    /**
     * Longest substring with at most k distinct characters (Sliding Window - optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int longestSubstringAtMostKCharSWOpt(String s, int k) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int start = 0;
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
            if(map.size() <= k) {
                max = Math.max(max, i-start+1);
            }
            if(map.size() > k) {
                char startc = s.charAt(start);
                map.put(startc, map.get(startc)-1);
                if(map.get(startc) == 0) {
                    map.remove(startc);
                }
                start++;
            }
        }
        return max;
    }

    /**
     * Count substring with at most k distinct characters
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public static int countSubstringAtMostKCharSWOpt(String s, int k) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        int start = 0;
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
            while(map.size() > k) {
                char startc = s.charAt(start);
                map.put(startc, map.get(startc)-1);
                if(map.get(startc) == 0) {
                    map.remove(startc);
                }
                start++;
            }
            count += (i-start+1);
        }
        return count;
    }

    /**
     * Count substring with exactly k distinct characters
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int countSubstrK(String s, int k) {
        return countSubstringAtMostKCharSWOpt(s, k) - countSubstringAtMostKCharSWOpt(s,k-1);
    }


    /**
     * Minimum window substring
     *
     * Given two strings s and t of lengths m and n respectively, return the minimum window substring
     * of s such that every character in t (including duplicates) is included in the window.
     * If there is no such substring, return the empty string "".
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = -1;
        for(int i=0; i<s.length(); i++) {
            int[] hash = new int[256];
            for(int j=0; j<t.length(); j++) {
                hash[t.charAt(j)]++;
            }
            int count = 0;
            for(int j=i; j<s.length(); j++) {
                char c = s.charAt(j);
                if(hash[c] > 0) {
                    count++;
                }
                hash[c]--;
                if(count == t.length()) {
                    if(j-i+1 < min) {
                        min = j-i+1;
                        start = i;
                        end = j;
                        break;
                    }
                }
            }
        }
        return s.substring(start,end+1);
    }

    /**
     * Minimum window substring (Sliding window)
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public String minWindowSW(String s, String t) {
        int[] hash = new int[256];
        for (int j = 0; j < t.length(); j++) {
            hash[t.charAt(j)]++;
        }
        int count = 0;
        int min = Integer.MAX_VALUE;
        int start = 0;
        int l = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hash[c] > 0) {
                count++;
            }
            hash[c]--;
            while (count == t.length()) {
                if (i - l + 1 < min) {
                    min = i - l + 1;
                    start = l;
                }
                hash[s.charAt(l)]++;
                if(hash[s.charAt(l)] > 0) {
                    count--;
                }
                l++;
            }
        }
        return min == Integer.MAX_VALUE? "" : s.substring(start, start+min);
    }

    /**
     * Minimum window subsequence
     *
     * Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.
     * If there is no such window in s1 that covers all characters in s2, return the empty string "".
     * If there are multiple such minimum-length windows, return the one with the left-most starting index.
     *
     * TC: O(mn)
     * SC: O(mn)
     */
    public String minWindowSS(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m; i++) {
            Arrays.fill(dp[i], 1000000000);
        }
        dp[0][0]=0;
        int min = m + 1;
        int end = 0;
        for(int i=1; i<=m; i++) {
            dp[i][0] = 0;
            for(int j=1; j<=n; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + dp[i-1][j];
                }
            }
            if(dp[i][n] < min) {
                min = dp[i][n];
                end = i;
            }
        }
        return min > m  ? "" : s.substring(end-min, end);
    }
}
