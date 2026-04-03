package com.paul.subham.companies.google;

import java.util.PriorityQueue;

/**
 * 1. Minimum Number of Increments on Sub arrays to Form a Target Array (Monotonic stack)
 * 2.a. Reorganize String (Using priority queue)
 * 2.b. Reorganize String (Counting and Odd/Even)
 */
public class Questions {

    public static void main(String[] args) {

    }

    /**
     * Minimum Number of Increments on Sub arrays to Form a Target Array (Monotonic stack)
     *
     * You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros.
     *
     * In one operation you can choose any subarray from initial and increment each value by one.
     *
     * Return the minimum number of operations to form a target array from initial.
     *
     * The test cases are generated so that the answer fits in a 32-bit integer.
     *
     * Example 1:
     * Input: target = [1,2,3,2,1]
     * Output: 3
     * Explanation: We need at least 3 operations to form the target array from the initial array.
     * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
     * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
     * [1,2,2,2,1] increment 1 at index 2.
     * [1,2,3,2,1] target array is formed.
     */
    public static int minNumberOperations(int[] a) {
        int count = a[0];
        for(int i=1; i<a.length; i++) {
            count+= Math.max(a[i]-a[i-1], 0);
        }
        return count;
    }

    /**
     * Reorganize String (Using priority queue)
     *
     * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
     * Return any possible rearrangement of s or return "" if not possible.
     *
     * Input: s = "aab"
     * Output: "aba"
     *
     * Input: s = "aaab"
     * Output: ""
     *
     * TC: O(nlog26)
     * SC: O(26)
     */
    public static String reorganizeString(String s) {
        int[] count = new int[26];
        for(int i=0; i<s.length(); i++) {
            count[s.charAt(i)-'a']++;
        }
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>((a, b) -> count[b-'a']-count[a-'a']);
        for(char i='a'; i<='z'; i++) {
            if(count[i-'a'] >0) {
                priorityQueue.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!priorityQueue.isEmpty()) {
            char first = priorityQueue.remove();
            if(sb.length()==0 || sb.charAt(sb.length()-1) != first) {
                sb.append(first);
                if(--count[first-'a']>0) {
                    priorityQueue.add(first);
                }
            } else {
                if(priorityQueue.isEmpty()) {
                    return "";
                }
                char second = priorityQueue.remove();
                sb.append(second);
                if(--count[second-'a'] > 0) {
                    priorityQueue.add(second);
                }
                priorityQueue.add(first);
            }
        }
        return sb.toString();
    }

    /**
     * Reorganize String (Counting and Odd/Even)
     *
     * TC: O(n)
     * SC: O(26)
     */
    public static String reorganizeStringOddEven(String s) {
        int[] count = new int[26];
        for(int i=0; i<s.length(); i++) {
            count[s.charAt(i)-'a']++;
        }
        int max = 0;
        int letter = 0;
        for(int i=0; i<26; i++) {
            if(count[i] > max) {
                max = count[i];
                letter = i;
            }
        }
        if(max > (s.length()+1)/2) {
            return "";
        }
        char[] ans = new char[s.length()];
        int index = 0;
        while(count[letter] > 0) {
            ans[index] = (char)(letter + 'a');
            index += 2;
            count[letter]--;
        }
        for(int i=0; i<26; i++) {
            while(count[i] > 0) {
                if(index >= s.length()) {
                    index = 1;
                }
                ans[index] = (char)(i+'a');
                index += 2;
                count[i]--;
            }
        }
        return new String(ans);
    }
}
