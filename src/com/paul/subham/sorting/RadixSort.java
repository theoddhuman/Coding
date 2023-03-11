package com.paul.subham.sorting;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] a = {5,1,4,3,2,7, 11, 5,1};
        sort(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: O(n)
     * SC: O(n)
     */
    static void sort(int[] a) {
        int max = Arrays.stream(a).max().getAsInt();
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSort(a, exp);
    }

    static void countingSort(int[] a, int exp) {
        int[] b = new int[a.length];
        int[] c = new int[10];
        for(int i=0; i<a.length; i++) {
            c[(a[i]/exp)%10]++;
        }
        for(int i=1; i<10; i++) {
            c[i] += c[i-1];
        }
        for(int i=a.length-1; i>=0; i--) {
            b[c[(a[i]/exp)%10]-1] = a[i];
            c[(a[i]/exp)%10]--;
        }
        for(int i=0; i<a.length; i++) {
            a[i] = b[i];
        }
    }
}
