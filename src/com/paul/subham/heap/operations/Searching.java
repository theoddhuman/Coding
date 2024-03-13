package com.paul.subham.heap.operations;

import com.paul.subham.heap.implementation.BinaryHeap;
import com.paul.subham.heap.implementation.HeapType;

/**
 * @author subham.paul
 *
 * 1. Find max in Min Heap
 * 2. Print all elements less than K in Min Heap
 * 3. Find kth smallest element in min heap (by deletion)
 * 4. Find kth smallest element in min heap (By reducing heap size)
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

    /**
     * Find kth smallest element in min heap (by deletion)
     *
     * TC: O(k*logn)
     * SC: O(k*logn)
     */
    public static int kthSmallestByDeletion(BinaryHeap heap, int k) {
        for(int i=0; i<k-1; i++) {
            heap.delete();
        }
        return heap.delete();
    }

    /**
     * Find kth smallest element in min heap (By reducing heap size)
     *
     * An element x at ith level has i – 1 ancestor.
     * By the property of min-heaps, these i – 1 ancestors are guaranteed to be less than x.
     * This implies that x cannot be among the least i – 1 element of the heap.
     * Using this property, we can conclude that the kth least element can have a level of at most k.
     * If n >> k, then this approach performs better than the previous one
     *
     * TC: O(k^2), Size of the heap is reduced to a maximum of 2k – 1, therefore each heapify operation will take O(log 2k) = O(k) time.
     * SC: O(k^2)
     */
    public static int kthSmallestByLeveling(BinaryHeap binaryHeap, int k) {
        binaryHeap.setCount(Math.min(binaryHeap.getCount(), (int) (Math.pow(2, k) - 1)));
        for(int i=0; i<k-1; i++) {
            binaryHeap.delete();
        }
        return binaryHeap.delete();
    }
}
