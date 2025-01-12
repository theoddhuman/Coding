package com.paul.subham.string.operations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author subham.paul
 *
 * 1. Check if two strings are anagram (Sorting)
 * 2. Check if two strings are anagram (Counting characters)
 * 3. Check if two strings are anagram (Using one array for count)
 * 4. Check if two strings are isomorphic
 */
public class Structure {
    public static void main(String[] args) {

    }

    /**
     * Check if two strings are anagram (Sorting)
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static boolean areAnagram(char[] s1, char[] s2) {
        int n1 = s1.length;
        int n2 = s2.length;
        if (n1 != n2) {
            return false;
        }
        Arrays.sort(s1);
        Arrays.sort(s2);
        for (int i = 0; i < n1; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if two strings are anagram (Counting characters)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean areAnagramCount(char[] s1, char[] s2) {
        if(s1.length != s2.length) {
            return false;
        }
        int[] count1 = new int[256];
        int[] count2 = new int[256];
        for(int i=0; i<s1.length; i++) {
            count1[s1[i]]++;
            count2[s2[i]]++;
        }
        for(int i=0; i<256; i++) {
            if(count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if two strings are anagram (Using one array for count)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean areAnagramSingleCount(char[] s1, char[] s2) {
        if (s1.length != s2.length) {
            return false;
        }
        int[] count = new int[256];
        for (int i = 0; i < s1.length; i++) {
            count[s1[i]]++;
            count[s2[i]]--;
        }
        for (int i = 0; i < 256; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if two strings are isomorphic
     *
     * "egg" and "add" are isomorphic
     *
     * TC: O(n)
     * SC: O(n)
     */
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for(int i=0; i<s.length(); i++) {
            sMap.putIfAbsent(s.charAt(i), i);
            tMap.putIfAbsent(t.charAt(i), i);
            if(!Objects.equals(sMap.get(s.charAt(i)), tMap.get(t.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
