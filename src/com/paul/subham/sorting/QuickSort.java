package com.paul.subham.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = {5,4,3,2,7, 11, 5, 2,1};
        sort(a, 0, a.length-1);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: Worst - O(n^2)
     *     Average - O(nlogn)
     *     Best - O(nlogn)
     * SC: O(1)
     */
    static void sort(int[] a, int start, int end) {
        if(start < end) {
            int pIndex = partition(a, start, end);
            sort(a, start, pIndex-1);
            sort(a, pIndex+1, end);
        }
    }

    static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int pIndex = start;
        for(int i=start; i<=end-1; i++) {
            if(a[i] <= pivot) {
                int temp = a[pIndex];
                a[pIndex] = a[i];
                a[i] = temp;
                pIndex++;
            }
        }
        int temp = a[pIndex];
        a[pIndex] = a[end];
        a[end] = temp;
        return pIndex;
    }
}
