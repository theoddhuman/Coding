package com.paul.subham.queue.implementation;

import java.util.Stack;

/**
 * @author subham.paul
 * 
 * Implementation of queue using one stack
 */

public class OneStackQueue {
    Stack<Integer> stack = new Stack<>();

    /**
     * TC: O(1)
     * SC: O(1)
     */
    void enqueue(int data) {
        stack.push(data);
    }

    /**
     * TC: O(n)
     * SC: O(n)
     */
    int dequeue() {
        if(stack.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        if (stack.size() == 1) {
            return stack.pop();
        }
        int x = stack.pop();
        int res = dequeue();
        stack.push(x);
        return res;
    }

    public void print() {
        System.out.println(stack);
    }
}
