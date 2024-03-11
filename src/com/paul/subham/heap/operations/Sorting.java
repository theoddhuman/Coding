package com.paul.subham.heap.operations;

import com.paul.subham.heap.implementation.BinaryHeap;
import com.paul.subham.heap.implementation.HeapType;

/**
 * 1. heap sort
 */
public class Sorting {
    public static void main(String[] args) {
        int[] arr = {7,4,5,6,1,2,3};
        arr = heapSort(arr, 7);
        for(int i=0; i<7; i++){
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * heap sort
     * TC: O(nlogn)
     * SC: O(1)
     */
    private static int[] heapSort(int[] arr, int n) {
        BinaryHeap binaryHeap = new BinaryHeap(arr.length, HeapType.MAX);
        binaryHeap.buildHeap(arr);
        for(int i=n-1; i>0; i--){
            int temp = binaryHeap.getA()[0];
            binaryHeap.getA()[0] = binaryHeap.getA()[i];
            binaryHeap.getA()[i] = temp;
            binaryHeap.setCount(binaryHeap.getCount()-1);
            binaryHeap.percolateDownMax(0);
        }
        return binaryHeap.getA();
    }
}
