package com.paul.subham.heap.operations;

import com.paul.subham.heap.implementation.BinaryHeap;
import com.paul.subham.heap.implementation.HeapType;

/**
 * @author subham.paul
 *
 * 1. Find max in Min Heap
 * 2. Print all elements less than K in Min Heap
 */
public class Searching {
    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap(10, HeapType.MIN);
        heap.insert(1);
        heap.insert(11);
        heap.insert(10);
        heap.insert(21);
        heap.insert(100);
        heap.insert(6);
        heap.print();
        System.out.println();
        printLessThan(22, 0, heap);
    }

    /**
     * Find max in Min Heap
     * TC: O(n)
     * SC: O(1)
     */
    public static int findMaxInMinHeap(BinaryHeap binaryHeap) {
        int max = Integer.MIN_VALUE;
        for(int i=binaryHeap.getCount()/2; i<binaryHeap.getCount(); i++) {
            max = Math.max(binaryHeap.getA()[i], max);
        }
        return max;
    }

    /**
     * Print all elements less than K in Min Heap
     * TC: O(n)
     * SC: O(1)
     */
    public static void printLessThan(int k, int pos, BinaryHeap binaryHeap) {
        if(binaryHeap.getCount() <= pos || pos == -1) {
            return;
        }
        if(binaryHeap.getA()[pos] >= k) {
            return;
        }
        System.out.print(binaryHeap.getA()[pos] + " ");
        printLessThan(k, binaryHeap.leftChild(pos), binaryHeap);
        printLessThan(k, binaryHeap.rightChild(pos), binaryHeap);
    }
}
