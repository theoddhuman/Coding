package com.paul.subham.sorting;

import java.util.Arrays;

public class BucketSort {
    public static void main(String[] args) {
        int[] a = {5,1,4,3,2,7, 11, 5,1};
        int maxValue = 20;
        sort(a, maxValue);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }


    /**
     * TC: O(n)
     * SC: O(n)
     */
    static void sort(int[] a, int bucketSize) {
        int[] bucket = new int[bucketSize];
        for(int i=0; i<a.length; i++) {
            bucket[a[i]]++;
        }
        for(int i=0, j=0; i<bucketSize; i++) {
            for(int k=bucket[i]; k>0; k--) {
                a[j++] = i;
            }
        }
    }
}
