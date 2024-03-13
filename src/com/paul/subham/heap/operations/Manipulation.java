package com.paul.subham.heap.operations;

import com.paul.subham.heap.implementation.BinaryHeap;
import com.paul.subham.heap.implementation.HeapType;

/**
 * @author subham.paul
 *
 * 1. Merge two binary heaps (heapifying an array)
 */
public class Manipulation {
    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap(10, HeapType.MIN);
        heap.insert(1);
        heap.insert(11);
        heap.insert(10);
        heap.insert(21);
        heap.insert(100);
        heap.insert(6);
        heap.print();
//        BinaryHeap heap1 = new BinaryHeap(10, HeapType.MAX);
//        heap1.insert(2);
//        heap1.insert(12);
//        heap1.insert(14);
//        heap1.insert(20);
//        heap1.insert(130);
//        heap1.insert(24);
//        heap1.print();
        //System.out.println(kthSmallestByLeveling(heap, 4));
    }

    /**
     * Merge two binary heaps (heapifying an array)
     *
     * TC: O(n+m), n, size of heap1  and m, size of heap2
     * SC: O(n+m)
     */
    public static BinaryHeap mergeHeapByHeapifyingArray(BinaryHeap binaryHeap1, BinaryHeap binaryHeap2) {
        int[] a = new int[binaryHeap1.getCount() + binaryHeap2.getCount()];
        int k = 0;
        for(int i = 0; i<binaryHeap1.getCount(); i++) {
            a[k] = binaryHeap1.getA()[i];
            k++;
        }
        for(int i = 0; i<binaryHeap2.getCount(); i++) {
            a[k] = binaryHeap2.getA()[i];
            k++;
        }
        BinaryHeap binaryHeap = new BinaryHeap(a.length, HeapType.MAX);
        binaryHeap.buildHeap(a);
        return binaryHeap;
    }

}
