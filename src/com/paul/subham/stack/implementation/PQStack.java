package com.paul.subham.stack.implementation;

import com.paul.subham.heap.implementation.HeapType;
import com.paul.subham.heap.implementation.SpecialPriorityQueue;

/**
 * @author subham.paul
 *
 * 1. Pushing an element
 * 2. Popping an element
 * 3. Top element
 */
public class PQStack {
    int capacity;

    SpecialPriorityQueue priorityQueue;

    int priority;

    public PQStack(int capacity) {
        this.capacity = capacity;
        priority = 0;
        priorityQueue = new SpecialPriorityQueue(capacity, HeapType.MAX);
    }

    /**
     * Pushing an element
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public void push(int data) {
        priorityQueue.insert(data, priority);
        priority++;
    }

    /**
     * Popping an element
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public int pop() {
        return priorityQueue.delete();
    }

    /**
     *Top element
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public int top() {
        return priorityQueue.maxPriorityElement();
    }

    public int size() {
        return priorityQueue.getCount();
    }
}
