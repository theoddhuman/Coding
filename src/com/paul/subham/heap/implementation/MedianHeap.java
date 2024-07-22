package com.paul.subham.heap.implementation;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author subham.paul
 *
 * 1. Insert data
 * 2. Get median
 *
 */
public class MedianHeap {
    //it will have data larger than median
    PriorityQueue<Integer> minHeap;
    //it will have data greater than median
    PriorityQueue<Integer> maxHeap;

    MedianHeap() {
        minHeap = new PriorityQueue<>(Comparator.comparing(a -> a));
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public boolean isEmpty() {
        return minHeap.isEmpty() && maxHeap.isEmpty();
    }

    /**
     * Insert data
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public void insert(int data) {
        if(isEmpty()) {
            minHeap.add(data);
        } else {
            double median = median();
            if(data <= median) {
                maxHeap.add(data);
            } else {
                minHeap.add(data);
            }
        }
        fixChaos();
    }

    private void fixChaos() {
        if(Math.abs(minHeap.size() - maxHeap.size()) > 1) {
            if(maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            } else {
                maxHeap.add(minHeap.poll());
            }
        }
    }

    /**
     * Get Median
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public double median() {
        if(isEmpty()) {
            return 0.0;
        }
        if(minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }
}
