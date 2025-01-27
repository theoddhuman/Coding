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
 * 8. Remove outermost parenthesis
 * 9. Check if one string is rotation of another
 * 10. Check if one string is rotation of another (Concatenation Check)
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

    /**
     * Remove outermost parenthesis
     *
     * Input: s = "(()())(())(()(()))"
     * Output: "()()()()(())"
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static String removeOuterParentheses(String s) {
        String res = "";
        int balance = 0;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                if(balance > 0) {
                    res += s.charAt(i);
                }
                balance++;
            } else {
                balance--;
                if(balance > 0) {
                    res += s.charAt(i);
                }
            }
        }
        return res;
    }

    /**
     * Check if one string is rotation of another
     * <p>
     * TC: O(n^2)
     * SC: O(n)
     */
    public static boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        for(int i=0; i<s.length(); i++) {
            sChars = rotateOnce(sChars);
            if(new String(sChars).equals(goal)) {
                return true;
            }
        }
        return false;
    }

    private static char[] rotateOnce(char[] a) {
        char first = a[0];
        System.arraycopy(a, 1, a, 0, a.length-1);
        a[a.length-1] = first;
        return a;
    }

    /**
     * Check if one string is rotation of another (Concatenation Check)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean rotateStringConcatenationCheck(String s, String goal) {
        if(s.length() != goal.length()) {
            return false;
        }
        String doubleString = s+s;
        return doubleString.contains(goal);
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
