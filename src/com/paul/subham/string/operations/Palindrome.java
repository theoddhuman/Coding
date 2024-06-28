package com.paul.subham.string.operations;

import java.util.HashSet;

/**
 * @author subham.paul
 * 
 * 1. Check if characters of a string can be rearranged to form a palindrome
 * 2. Check if characters of a string can be rearranged to form a palindrome (Hashing)
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindromePossibleHashing("aabccddee"));
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
}
