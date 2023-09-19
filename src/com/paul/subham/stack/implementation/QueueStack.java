package com.paul.subham.stack.implementation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 * 
 * 1. Push an element
 * 2. Pop an element
 */
public class QueueStack {
    Queue<Integer> queue = new LinkedList<>();

    /**
     * Push an element
     * TC: O(n)
     * SC: O(1)
     */
    void push(int data) {
        int size = queue.size();
        queue.add(data);
        for(int i=0; i<size; i++) {
            queue.add(queue.remove());
        }
    }

    /**
     * Pop an element
     * TC: O(1)
     * SC: O(1)
     */
    int pop() {
        if(queue.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return queue.remove();
    }
}
