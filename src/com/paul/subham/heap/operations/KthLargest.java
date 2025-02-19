package com.paul.subham.heap.operations;


import java.util.PriorityQueue;

/**
 * Author: the_odd_human
 * Date: 20/02/25
 */
class KthLargest {
    PriorityQueue<Integer> priorityQueue;
    int k;
    public KthLargest(int k, int[] nums) {
        this. k = k;
        priorityQueue = new PriorityQueue<>(k);
        for (int i = 0; i<k && i<nums.length; i++) {
            priorityQueue.add(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > priorityQueue.peek()) {
                priorityQueue.remove();
                priorityQueue.add(nums[i]);
            }
        }
    }

    public int add(int val) {
        if(priorityQueue.size() < k) {
            priorityQueue.add(val);
        } else if (val > priorityQueue.peek()) {
            priorityQueue.remove();
            priorityQueue.add(val);
        }
        return priorityQueue.peek();
    }
}
