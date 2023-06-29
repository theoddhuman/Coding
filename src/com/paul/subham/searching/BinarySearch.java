package com.paul.subham.searching;

/**
 * 1. binary search iterative
 * 2. binary search recursive
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6};
        System.out.println(searchRecursive(a, 0, a.length-1, 5));
    }

    /**
     * binary search iterative
     * TC: O(n)
     * SC: O(1)
     */
    public static int search(int[] a, int n, int data) {
        int low = 0;
        int high = n-1;
        int mid;
        while(high >= low) {
            mid = low + (high-low)/2;
            if(a[mid] == data) {
                return mid;
            } else if(a[mid] > data) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }

    /**
     * binary search recursive
     * TC: O(log n)
     * SC: O(1)
     */
    static int searchRecursive(int a[], int low, int high, int data) {
        if(low < 0 || high >= a.length) {
            return -1;
        }
        int mid = low + (high - low)/data;
        if(a[mid] == data) {
            return mid;
        } else if(a[mid] > data) {
            return searchRecursive(a, low, mid-1, data);
        } else {
            return searchRecursive(a, mid+1, high, data);
        }
    }
}
