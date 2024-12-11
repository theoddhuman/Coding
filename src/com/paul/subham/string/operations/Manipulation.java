package com.paul.subham.string.operations;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 *
 * 1. Remove specified characters from a string
 * 2. Reverse individual words in a string (Using stack)
 * 3. Reverse individual words in a string (Using Java stream)
 * 4. Reverse a string using stack
 * 5. Reverse sequence of words in a string (Using stack)
 * 6. Reverse sequence of words in a string (Space optimized)
 * 7. Sort characters by frequency
 */
public class Manipulation {
    public static void main(String[] args) {
        //char[] res = removeSpecificChars("hellobaby".toCharArray(), new char[]{'l','o','a'});
        System.out.println(reverseSequenceWordEfficient("I    am Subham  "));
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
     * HELLO DADDY -> OLLEH YDDAD
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

    /**
     * Reverse sequence of words in a string (Using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static String reverseSequenceWord(String s) {
        Stack<String> stack = new Stack<>();
        String str = "";
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) != ' ') {
                str += s.charAt(i);
            } else {
                if(!str.isEmpty()) {
                    stack.push(str);
                }
                str = "";
            }
        }
        stack.push(str);
        str = "";
        while(!stack.isEmpty()) {
            str = str + stack.pop() + " ";
        }
        return str.trim();
    }

    /**
     * Reverse sequence of words in a string (Space optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static String reverseSequenceWordEfficient(String s) {
        Stack<String> stack = new Stack<>();
        String str = "";
        String result = "";
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) != ' ') {
                str += s.charAt(i);
            } else {
                if(!str.isEmpty()) {
                    result = " " + str + result;
                }
                str = "";
            }
        }
        if(!str.isEmpty()) {
            if(!result.isEmpty()) {
                result = str + result;
            } else {
                result = str;
            }
        }
        return result.trim();
    }

    /**
     * Sort characters by frequency
     *
     * Given a string s, sort it in decreasing order based on the frequency of the characters.
     * The frequency of a character is the number of times it appears in the string.
     * Return the sorted string. If there are multiple answers, return any of them.
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.count-a.count);
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }
        StringBuilder res = new StringBuilder();
        while(!pq.isEmpty()) {
            Pair current = pq.remove();
            for(int i=0; i<current.count; i++) {
                res.append(current.c);
            }
        }
        return res.toString();
    }
}

class Pair {
    char c;
    int count;
    Pair(char c, int count) {
        this.c = c;
        this.count = count;
    }
}
