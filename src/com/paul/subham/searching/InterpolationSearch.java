package com.paul.subham.searching;

/**
 * @author subham.paul
 * 
 * 1. Interpolation Search (Recursive)
 * 2. Interpolation Search (Recursive)
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,7,8};
        System.out.println(interpolationSearchRecursive(a, 4));
    }

    /**
     * Interpolation Search (Iterative)
     *
     *
     * TC: average- O(log(logn)), worst- O(n)
     * SC: O(1)
     */
    public static int interpolationSearchIterative(int[] a, int data) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high && data >= a[low] && data <= a[high]) {
            if (low == high) {
                if (a[low] == data) {
                    return low;
                } else {
                    return -1;
                }
            }
            int pos = low + (data - a[low]) * (high - low) / (a[high] - a[low]);
            if (a[pos] == data) {
                return pos;
            } else if (a[pos] < data) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1;
    }

    /**
     * Interpolation Search (Recursive)
     *
     *
     * TC: average- O(log(logn)), worst- O(n)
     * SC: O(log(logn))
     */
    public static int interpolationSearchRecursive(int[] a, int data) {
        return interpolationSearchRecursiveUtil(a, data, 0, a.length-1);
    }

    private static int interpolationSearchRecursiveUtil(int[] a, int data, int low, int high) {
        if (low <= high && data >= a[low] && data <= a[high]) {
            if (low == high) {
                if (a[low] == data) {
                    return low;
                } else {
                    return -1;
                }
            }
            int pos = low + (data - a[low]) * (high - low) / (a[high] - a[low]);
            if (a[pos] == data) {
                return pos;
            } else if (a[pos] < data) {
                return interpolationSearchRecursiveUtil(a, data, pos+1, high);
            } else {
                return interpolationSearchRecursiveUtil(a, data, low, pos-1);
            }
        }
        return -1;
    }
}
