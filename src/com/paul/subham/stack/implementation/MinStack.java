package com.paul.subham.stack.implementation;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * Implementation of stack to get min element at constant time. (Space Optimized)
 * 
 * 1. Push a data
 * 2. Push a data (Space optimized)
 * 3. Pop a data
 * 4. Pop a data (Space optimized)
 */
public class MinStack extends Stack<Integer> {
    Stack<Integer> minStack = new Stack<>();

    /**
     * Push a data
     * 
     * TC: O(1)
     * SC: O(1)
     */
    void push(int x) {
        super.push(x);
        if(super.isEmpty()) {
            minStack.push(x);
        } else {
            super.push(x);
            int y = minStack.peek();
            minStack.push(Math.min(x, y));
        }
    }

    /**
     * Push a data (Space optimized)
     * 
     * TC: O(1)
     */
    void pushSpaceOptimized(int x) {
        if(super.isEmpty()) {
            minStack.push(x);
        } else {
            if(x <= minStack.peek()) {
                minStack.push(x);
            }
        }
        super.push(x);
    }

    /**
     * Pop a data
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public Integer pop() {
        if(super.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int x = super.pop();
        minStack.pop();
        return x;
    }

    /**
     * Pop a data (Space optimized)
     * 
     * TC: O(1)
     */
    public Integer popSpaceOptimized() {
        if(super.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int x = super.pop();
        if( x == minStack.peek()) {
            minStack.pop();
        }
        return x;
    }

    /**
     * TC: O(1)
     */
    int getMin() {
        return minStack.peek();
    }

    void print() {
        System.out.println(super.toString());
    }
}
