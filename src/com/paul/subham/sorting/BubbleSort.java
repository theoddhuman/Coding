package com.paul.subham.sorting;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {6, 1 , 5, 2, 3};
        sortImproved(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: O(n^2)
     * SC: O(1)
     */
    static void sort(int[] a) {
        for (int i = a.length - 1; i >= 1; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    /**
     * TC: O(n^2)
     * SC: O(1)
     */
    static void sortImproved(int[] a) {
        boolean swapped = true;
        for (int i = a.length - 1; i >= 1 && swapped; i--) {
            swapped = false;
            for (int j = 0; j <= i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
        }
    }
}
