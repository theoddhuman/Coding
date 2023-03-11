package com.paul.subham.sorting;

import java.util.Arrays;

public class CountingSort {
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
    static void sort(int[] a, int k) {
        int[] b = new int[a.length];
        int[] c = new int[k];
        for(int i=0; i<a.length; i++) {
            c[a[i]]++;
        }
        for(int i=1; i<k; i++) {
            c[i] += c[i-1];
        }
        for(int i=a.length-1; i>=0; i--) {
            b[c[a[i]]-1] = a[i];
            c[a[i]]--;
        }
        for(int i=0; i<a.length; i++) {
            a[i] = b[i];
        }
    }
}
