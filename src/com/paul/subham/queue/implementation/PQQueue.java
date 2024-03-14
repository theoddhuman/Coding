package com.paul.subham.queue.implementation;

import com.paul.subham.heap.implementation.HeapType;
import com.paul.subham.heap.implementation.SpecialPriorityQueue;

/**
 * @author subham.paul
 * 
 * 1. Enqueue an element
 * 2. Dequeue an element
 */
public class PQQueue {
    int capacity;
    
    SpecialPriorityQueue priorityQueue;
    
    int priority;
    
    public PQQueue(int capacity) {
        this.capacity = capacity;
        priority = Integer.MAX_VALUE;
        priorityQueue = new SpecialPriorityQueue(capacity, HeapType.MAX);
    }

    /**
     * Enqueue an element
     * 
     * TC: O(logn)
     * SC: O(logn)
     */
    public void enqueue(int data) {
        priorityQueue.insert(data, priority);
        priority--;
    }

    /**
     * Dequeue an element
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public int dequeue() {
        return priorityQueue.delete();
    }
}
