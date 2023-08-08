package com.paul.subham.stack.implementation;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * Implementation of stack to get min element at constant time. (Space Optimized)
 */
public class MinStack extends Stack<Integer> {
    Stack<Integer> minStack = new Stack<>();


    /**
     * TC: O(1)
     */
    void push(int x) {
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
     * TC: O(1)
     */
    public Integer pop() {
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
