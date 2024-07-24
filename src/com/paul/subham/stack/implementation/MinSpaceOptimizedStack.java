package com.paul.subham.stack.implementation;

import java.util.Stack;

/**
 * @author subham.paul
 * Min stack with O(1) space and O(1) time complexity
 *
 * 1. Push data
 * 2. Pop data
 * 3. Top data
 * 4. Min data
 */
public class MinSpaceOptimizedStack {
    Stack<Integer> stack;
    int min;
    public MinSpaceOptimizedStack() {
        stack = new Stack<>();
    }

    /**
     * Push data
     *
     * TC: O(1)
     * SC: O(1)
     */
    public void push(int data) {
        if(stack.isEmpty()) {
            stack.push(data);
            min = data;
            return;
        }
        if(data < min) {
            stack.push(2*data - min);
            min = data;
        } else {
            stack.push(data);
        }
    }

    /**
     * Pop data
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int pop() {
        if(stack.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int x = stack.peek();
        int popped = stack.pop();
        if(x < min) {
            x = min;
            min = 2*min - popped;
        }
        return x;
    }

    /**
     * Top data
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int peek() {
        if(stack.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int x = stack.peek();
        return Math.max(x, min);
    }

    /**
     * Minimum data
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int getMin() {
        if(stack.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return min;
    }
}
