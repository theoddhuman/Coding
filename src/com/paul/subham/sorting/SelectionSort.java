package com.paul.subham.sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] a = {5,4,3,2,1};
        sort(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: O(n^2)
     * SC: O(1)
     */
    static void sort(int[] a) {
        int minIndex;
        for(int i=0; i<a.length - 1; i++) {
            minIndex = i;
            for(int j=i+1; j<a.length; j++) {
                if(a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }
}
