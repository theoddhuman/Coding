package com.paul.subham.queue.implementation;

import java.util.Stack;

/**
 * @author subham.paul
 * 
 * Implementation of queue using stack (enqueue costly)
 */
public class StackEnqueueQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public void enqueue(int data) {
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(data);
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    public int dequeue() {
        if(stack1.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return stack1.pop();
    }

    public void print() {
        System.out.println(stack1);
    }
}
