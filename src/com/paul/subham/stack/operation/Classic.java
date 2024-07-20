package com.paul.subham.stack.operation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Next greater element
 * 2. Next greater element (Using Stack)
 * 3. Next more frequent element
 * 4. Next more frequent element (Using Stack)
 */

public class Classic {
    public static void main(String[] args) {
        int[] a = {2,4,3,8,5,7,3,7};
        int[] next = nextMoreFrequentElementStack(a);
        System.out.println(Arrays.toString(next));
    }

    /**
     * Next greater element
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int[] nextGreaterElement(int[] a) {
        int[] next = new int[a.length];
        Arrays.fill(next, Integer.MIN_VALUE);
        for(int i=0; i<a.length; i++) {
            for(int j=i+1; j<a.length; j++) {
                if(a[i] < a[j]) {
                    next[i] = a[j];
                    break;
                }
            }
        }
        return next;
    }

    /**
     * Next greater element (Using Stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] nextGreaterElementStack(int[] a) {
        int[] next = new int[a.length];
        Arrays.fill(next, Integer.MIN_VALUE);
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (int i = 1; i < a.length; i++) {
            while (!stack.isEmpty() && a[i] > a[stack.peek()]) {
                next[stack.pop()] = a[i];
            }
            stack.push(i);
        }
        return next;
    }

    /**
     * Next more frequent element
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int[] nextMoreFrequentElement(int[] a) {
        int[] next = new int[a.length];
        Arrays.fill(next, Integer.MIN_VALUE);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        for(int i=0; i<a.length; i++) {
            for(int j=i+1; j<a.length; j++) {
                if(map.get(a[i]) < map.get(a[j])) {
                    next[i] = a[j];
                    break;
                }
            }
        }
        return next;
    }

    /**
     * Next more frequent element (Using Stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] nextMoreFrequentElementStack(int[] a) {
        int[] next = new int[a.length];
        Arrays.fill(next, Integer.MIN_VALUE);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<a.length; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (int i = 1; i < a.length; i++) {
            while (!stack.isEmpty() && map.get(a[i]) > map.get(a[stack.peek()])) {
                next[stack.pop()] = a[i];
            }
            stack.push(i);
        }
        return next;
    }
}
