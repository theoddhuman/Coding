package com.paul.subham.queue.operation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Reverse a queue (using stack)
 * 2. Reverse a queue (using recursion)
 */

public class Manipulation {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        System.out.println(queue);
        queue = reverseRecursive(queue);
        System.out.println(queue);
    }

    /**
     * Reverse a queue (using stack)
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverse(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while(!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        while(!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    /**
     * Reverse a queue (using recursion)
     * TC: O(n)
     * SC: O(n)
     */
    public static Queue<Integer> reverseRecursive(Queue<Integer> queue) {
        if(queue.isEmpty()) {
            return queue;
        }
        int front = queue.remove();
        queue = reverseRecursive(queue);
        queue.add(front);
        return queue;
    }
}
