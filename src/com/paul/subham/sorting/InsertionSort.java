package com.paul.subham.sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] a = {5,4,3,2,1};
        sort(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: Worst - O(n^2)
     *     Average - O(n^2)
     *     Best - O(n)
     * SC: O(1)
     */
    static void sort(int[] a) {
        int v;
        int j;
        for(int i=1; i<a.length; i++) {
            v = a[i];
            j = i;
            while(j>=1 && a[j-1] > v) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }
}
