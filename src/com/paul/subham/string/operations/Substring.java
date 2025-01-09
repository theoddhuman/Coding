package com.paul.subham.string.operations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Length of the longest valid substring (Using stack - two loop)
 * 2. Length of the longest valid substring (Using stack - single loop)
 * 3. Length of the longest valid substring (Using array)
 * 4. Length of the longest valid substring (Space optimization)
 */
public class Substring {
    public static void main(String[] args) {
        System.out.println(longestValidSubstring(")()((())())("));
        //System.out.println(longestValidSubstringSingleLoop("((("));
    }



    /**
     * Length of the longest valid substring (Using stack - two loop)
     *
     * ()()((()))
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int longestValidSubstringTwoLoop(String s) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    //base for the next valid substring
                    stack.push(i);
                }
            }
        }
        int last = s.length();
        int now;
        int result = 0;
        while(!stack.isEmpty()) {
            now = stack.pop();
            result = Math.max(result, last-now-1);
            last = now;
        }
        return result;
    }

    /**
     * Length of the longest valid substring (Using stack - single loop)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int longestValidSubstringSingleLoop(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.add(-1);
        int maxLength = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
                if(!stack.isEmpty()) {
                    maxLength = Math.max(maxLength, i-stack.peek());
                } else {
                    //base for the next valid substring
                    stack.push(i);
                }
            }
        }
        return maxLength;
    }

    /**
     * Length of the longest valid substring (Using array)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int longestValidSubstringArray(String s) {
        //longest[i] represents a valid substring length that ends at i.
        int[] longest = new int[s.length()];
        int maxLength = 0;
        for(int i=1; i<s.length(); i++) {
            if(s.charAt(i) == ')' && i-longest[i-1]-1 >= 0 && s.charAt(i-longest[i-1]-1) == '(') {
                //condition is to check if there is a valid substring before the start of current substring, i-longest[i-1]-1
                longest[i] = longest[i-1] + 2 + (i-longest[i-1]-2 >= 0 ? longest[i-longest[i-1]-2] : 0);
                maxLength = Math.max(longest[i], maxLength);
            }
        }
        return maxLength;
    }

    /**
     * Length of the longest valid substring (Space optimization)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int longestValidSubstring(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if(left == right) {
                maxLength = Math.max(maxLength, left + right);
            }
            if(right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for(int i=s.length()-1; i>=0; i--) {
            if(s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if(left == right) {
                maxLength = Math.max(maxLength, left + right);
            }
            if(left > right) {
                left = right = 0;
            }
        }
        return maxLength;
    }

}
