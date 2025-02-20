package com.paul.subham.heap.operations;


import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Author: the_odd_human
 * Date: 21/02/25
 *
 * Find median from data stream
 */
class MedianFinder {
    private int size;
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;


    public MedianFinder() {
        size = 0;
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if(size == 0) {
            maxHeap.add(num);
        } else if(size % 2 == 1) {
            if(minHeap.size() > maxHeap.size()) {
                if(minHeap.peek() < num) {
                    maxHeap.add(minHeap.poll());
                    minHeap.add(num);
                } else {
                    maxHeap.add(num);
                }
            } else {
                if(maxHeap.peek() > num) {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(num);
                } else {
                    minHeap.add(num);
                }
            }
        } else {
            if(minHeap.peek() > num) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
        }
        size++;
    }

    public double findMedian() {
        if(size % 2 == 0) {
            return (minHeap.peek() + maxHeap.peek())/2.0;
        }
        return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
    }
}
