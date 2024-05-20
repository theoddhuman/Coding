package com.paul.subham.queue.operation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Reverse a queue (using stack)
 * 2. Reverse a queue (using recursion)
 * 3. Reverse first K elements of a queue (using recursion)
 * 4. Reverse first K elements of a queue (using stack)
 * 5. Reverse first K elements of a queue (using deque)
 */

public class Reverse {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(5);
        queue.add(2);
        queue.add(4);
        queue.add(3);
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

    /**
     * Reverse first K elements of a queue (using recursion)
     * TC: O(n)
     * SC: O(k)
     */
    public static void reverseFirstK(Queue<Integer> queue, int k) {
        reverseKUtil(queue, k);
        for(int i=0; i<queue.size() - k; i++) {
            queue.add(queue.remove());
        }
    }

    static void reverseKUtil(Queue<Integer> queue, int k) {
        if(k == 0) {
            return;
        }
        int element = queue.remove();
        reverseKUtil(queue, k-1);
        queue.add(element);
    }

    /**
     * Reverse first K elements of a queue (using stack)
     * TC: O(n+k)
     * SC: O(k)
     */
    public static void reverseFirstKUsingStack(Queue<Integer> queue, int k) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<k; i++) {
            stack.push(queue.remove());
        }
        while(!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        for(int i=0; i<queue.size() - k; i++) {
            queue.add(queue.remove());
        }
    }

    /**
     * Reverse first K elements of a queue (using deque)
     * TC: O(n+k)
     * SC: O(k)
     */
    public static void reverseFirstKUsingDeque(Queue<Integer> queue, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0; i<k; i++) {
            deque.push(queue.remove());
        }
        while(!deque.isEmpty()) {
            queue.add(deque.poll());
        }
        for(int i=0; i<queue.size() - k; i++) {
            queue.add(queue.remove());
        }
    }
}
