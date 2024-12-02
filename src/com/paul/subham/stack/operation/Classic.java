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
 * 3. Previous smaller element (Using Stack)
 * 3. Next more frequent element
 * 4. Next more frequent element (Using Stack)
 * 5. Next greater element in circular array (Using Stack)
 * 6. Trapping rainwater
 * 7. Trapping rainwater (Using two pointer)
 * 9. Asteroid Collision
 * 10. Remove K Digits
 */

public class Classic {
    public static void main(String[] args) {
        int[] a = {2,4,3,8,5,7,3,7};
        int[] next = prevSmallerElementStack(a);
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
     * Previous smaller element (Using Stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] prevSmallerElementStack(int[] a) {
        int[] next = new int[a.length];
        Arrays.fill(next, Integer.MIN_VALUE);
        Stack<Integer> stack = new Stack<>();
        stack.add(a.length-1);
        for (int i = a.length-2; i >=0; i--) {
            while (!stack.isEmpty() && a[i] < a[stack.peek()]) {
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

    /**
     * Next greater element in circular array (Using Stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int[] nextGreaterElements(int[] a) {
        int n = a.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i=1;i<n;i++) {
            while(!stack.isEmpty() && a[i] > a[stack.peek()]) {
                next[stack.pop()] = a[i];
            }
            stack.push(i);
        }
        for(int i=0; i<stack.peek(); i++) {
            while(!stack.isEmpty() && a[i] > a[stack.peek()]) {
                next[stack.pop()] = a[i];
            }
        }
        return next;
    }

    /**
     * Trapping rainwater
     *
     * Given an array of non-negative integers representation elevation of ground.
     * Your task is to find the water that can be trapped after rain.
     *
     * Input: height= [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     *
     * TC: O(3n)
     * SC: O(2n)
     */
    public static int trap(int[] height) {
        int n = height.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = height[0];
        for(int i=1; i<n; i++) {
            prefix[i] = Math.max(prefix[i-1], height[i]);
        }
        suffix[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--) {
            suffix[i] = Math.max(suffix[i+1], height[i]);
        }
        int trap = 0;
        for(int i=0; i<n; i++) {
            trap += Math.min(suffix[i], prefix[i])-height[i];
        }
        return trap;
    }

    /**
     * Trapping rainwater (Using two pointer)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int trap2Pointer(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n-1;
        int leftMax = 0;
        int rightMax = 0;
        int trap = 0;
        while(l <= r) {
            if(height[l] <= height[r]) {
                if(height[l] >= leftMax) {
                    leftMax = height[l];
                } else {
                    trap += (leftMax - height[l]);
                }
                l++;
            } else {
                if(height[r] >= rightMax) {
                    rightMax = height[r];
                } else {
                    trap += (rightMax - height[r]);
                }
                r--;
            }
        }
        return trap;
    }

    /**
     * Asteroid Collision
     *
     * We are given an array asteroids of integers representing asteroids in a row.
     * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
     * Each asteroid moves at the same speed.
     * Find out the state of the asteroids after all collisions.
     * a. If two asteroids meet, the smaller one will explode.
     * b. If both are the same size, both will explode.
     * c. Two asteroids moving in the same direction will never meet.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for(int i=1; i<n; i++) {
            int x = asteroids[i];
            while(!stack.isEmpty() && stack.peek() > 0 && x < 0) {
                int popped = stack.pop();
                if(Math.abs(popped) > Math.abs(x)) {
                    x = popped;
                } else if(Math.abs(popped) == Math.abs(x)) {
                    x = 0;
                }
            }
            if(x != 0) {
                stack.push(x);
            }
        }
        int[] res = new int[stack.size()];
        int i = res.length-1;
        while(!stack.isEmpty()) {
            res[i--] = stack.pop();
        }
        return res;
    }

    /**
     * Remove K Digits
     *
     * Given string num representing a non-negative integer num, and an integer k,
     * return the smallest possible integer after removing k digits from num.
     *
     * Input: num = "1432219", k = 3
     * Output: "1219"
     *
     * TC: O(n)
     * SC: O(n)
     */
    public String removeKDigits(String num, int k) {
        if(k==num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));
        for(int i=1; i<num.length(); i++) {
            char c = num.charAt(i);
            while(!stack.isEmpty() && k>0 && Character.getNumericValue(stack.peek()) > Character.getNumericValue(c) ) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while(k>0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        String result = "";
        while(!stack.isEmpty()) {
            result = stack.pop()+result;
        }
        int zeroCount=0;
        for(int i=0; i<result.length(); i++) {
            if(result.charAt(i) != '0') {
                break;
            }
            zeroCount++;
        }
        if(zeroCount == result.length()) {
            return "0";
        }
        return result.substring(zeroCount);
    }
}
