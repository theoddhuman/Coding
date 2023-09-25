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
 * 6. Sorting a queue (Using recursion)
 * 7. Sorting a queue (Without extra space)
 */

public class Manipulation {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(5);
        queue.add(2);
        queue.add(4);
        queue.add(3);

        System.out.println(queue);
        sortSpaceOptimized(queue);
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

    /**
     * Sorting a queue (Using recursion)
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void sort(Queue<Integer> queue) {
        if(queue.isEmpty()) {
            return;
        }
        int data = queue.remove();
        sort(queue);
        push(queue, data, queue.size());
    }

    private static void push(Queue<Integer> queue, int data, int size) {
        if(queue.isEmpty() || size == 0) {
            queue.add(data);
            return;
        }
        if(data <= queue.peek()) {
            queue.add(data);
            frontToLast(queue, size);
        } else {
            queue.add(queue.remove());
            push(queue, data, size-1);
        }

    }

    private static void frontToLast(Queue<Integer> queue, int size) {
        if(queue.isEmpty() || size == 0) {
            return;
        }
        queue.add(queue.remove());
        frontToLast(queue, size-1);
    }

    /**
     * Sorting a queue (Without extra space)
     * TC: O(n^2)
     * SC: O(n)
     */
    private static void sortSpaceOptimized(Queue<Integer> queue) {
        for(int i=1; i<= queue.size(); i++) {
            int minIndex = minIndex(queue, queue.size() - i);
            insertMinToRear(queue, minIndex);
        }
    }

    private static void insertMinToRear(Queue<Integer> queue, int minIndex) {
        int minValue = 0;
        int size = queue.size();
        for(int i=0; i< size; i++) {
            int current = queue.remove();
            if(i != minIndex) {
                queue.add(current);
            } else {
                minValue = current;
            }
        }
        queue.add(minValue);
    }

    private static int minIndex(Queue<Integer> queue, int sortIndex) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        for(int i=0; i< queue.size(); i++) {
            int current = queue.remove();
            if(current <= minValue && i <= sortIndex) {
                minIndex = i;
                minValue = current;
            }
            queue.add(current);
        }
        return minIndex;
    }
}
