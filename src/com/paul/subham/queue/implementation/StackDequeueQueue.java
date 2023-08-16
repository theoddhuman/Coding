package com.paul.subham.queue.implementation;

import java.util.Stack;

/**
 * @author subham.paul
 * 
 * Implementation of queue using stack (dequeue costly)
 */
public class StackDequeueQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /**
     * TC: O(1)
     * SC: O(1)
     */
    public void enqueue(int data) {
        stack1.push(data);
    }

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public int dequeue() {
        if(stack1.isEmpty() && stack2.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public void print() {
        System.out.println(stack1);
        System.out.println(stack2);
    }
}
