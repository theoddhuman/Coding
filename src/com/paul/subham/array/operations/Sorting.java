package com.paul.subham.array.operations;

/**
 * 1. Check if an array is sorted and rotated
 */
public class Sorting {
    public static void main(String[] args) {

    }

    /**
     * Check if an array is sorted and rotated
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean check(int[] a) {
        int n = a.length;
        int i;
        for(i=1; i<n; i++) {
            if(a[i] < a[i-1]) {
                break;
            }
        }
        if(i==n) {
            return true;
        }
        for(int j=i+1;j<n;j++) {
            if(a[j] < a[j-1]) {
                return false;
            }
        }
        if(a[n-1] > a[0]) {
            return false;
        }
        return true;
    }
}
