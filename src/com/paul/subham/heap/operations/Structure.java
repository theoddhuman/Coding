package com.paul.subham.heap.operations;

/**
 * 1. Check if an array represents heap
 */
public class Structure {
    public static void main(String[] args) {

    }

    /**
     * Check if an array represents heap
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean countSub(int[] arr, int n) {
        for (int i = 0; i <= n / 2; i++) {
            if ((2 * i + 1 < n && arr[i] < arr[2 * i + 1]) || (2 * i + 2 < n && arr[i] < arr[2 * i + 2])) {
                return false;
            }
        }
        return true;
    }
}
