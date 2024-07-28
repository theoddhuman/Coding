package com.paul.subham.string.operations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 *
 * 1. Remove specified characters from a string
 * 2. Reverse individual words in a string (Using stack)
 * 3. Reverse individual words in a string (Using Java stream)
 * 4. Reverse a string using stack
 */
public class Manipulation {
    public static void main(String[] args) {
        //char[] res = removeSpecificChars("hellobaby".toCharArray(), new char[]{'l','o','a'});
        System.out.println(reverseWordA("I am Subham"));
    }

    /**
     * Remove specified characters from a string
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static char[] removeSpecificChars(char[] str, char[] removeChars) {
        Map<Character, Boolean> removalMap = new HashMap<>();
        for (char removeChar : removeChars) {
            removalMap.put(removeChar, true);
        }
        int j = 0;
        for(int i=0; i<str.length; i++) {
            if(!removalMap.containsKey(str[i])) {
                str[j++] = str[i];
            }
        }
        char[] res = new char[j];
        System.arraycopy(str, 0, res, 0, j);
        return res;
    }

    /**
     * Reverse individual words in a string (Using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseWord(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) != ' ') {
                stack.push(s.charAt(i));
            } else {
                while(!stack.isEmpty()) {
                    System.out.print(stack.pop());
                }
                System.out.print(" ");
            }
        }
        while(!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }

    /**
     * Reverse individual words in a string (Using Java stream)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String reverseWordA(String s) {
        return Arrays.stream(s.split(" "))
                .map(str -> new StringBuilder(str).reverse())
                .collect(Collectors.joining(" "));
    }

    /**
     * Reverse a string using stack
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static StringBuffer reverse(StringBuffer s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            stack.push(s.charAt(i));
        }
        for(int i=0; i<s.length(); i++) {
            s.setCharAt(i, stack.pop());
        }
        return s;
    }
}
