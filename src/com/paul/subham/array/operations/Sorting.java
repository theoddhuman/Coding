package com.paul.subham.array.operations;

/**
 * 1. Check if an array is sorted and rotated
 * 2. Segregate 0's and 1's in an array (Count 0)
 * 3. Segregate 0's and 1's in an array (Two indices)
 * 4. Segregate 0's and 1's in an array (Single traversal)
 * 5. Sort an array of 0's, 1's and 2's (counting appearances)
 * 6. Sort an array of 0's, 1's and 2's (Single traversal - 3 pointer)
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

    /**
     * Segregate 0's and 1's in an array (Count 0)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void segregate0And1(int[] a) {
        int count0 = 0;
        for(int i=0; i<a.length; i++) {
            if(a[i] == 0) {
                count0++;
            }
        }
        for(int i=0; i<count0; i++) {
            a[i] = 0;
        }
        for(int i=count0; i<a.length; i++) {
            a[i] = 1;
        }
    }

    /**
     * Segregate 0's and 1's in an array (Two indices)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void segregate0And1TwoIndices(int[] a) {
        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            while (a[l] == 0 && l < r) {
                l++;
            }
            while (a[r] == 1 && l < r) {
                r--;
            }
            if (l < r) {
                a[l] = 0;
                a[r] = 1;
                l++;
                r--;
            }
        }
    }

    /**
     * Segregate 0's and 1's in an array (Single traversal)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void segregate0And1SingleTraversal(int[] a) {
        int l = 0;
        int r = a.length - 1;
        while (l < r) {
            if (a[l] == 1) {
                int temp = a[l];
                a[l] = a[r];
                a[r] = temp;
                r--;
            } else {
                l++;
            }
        }
    }


    /**
     * Sort an array of 0's, 1's and 2's (counting appearances)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] sort0And1And2(int[] a) {
        int[] count = new int[3];
        for (int j : a) {
            count[j]++;
        }
        int k =0;
        for(int i=0; i<count[0]; i++) {
            a[k++] = 0;
        }
        for(int i=0; i<count[1]; i++) {
            a[k++] = 1;
        }
        for(int i=0; i<count[2]; i++) {
            a[k++] = 2;
        }
        return a;
    }

    /**
     * Sort an array of 0's, 1's and 2's (Single traversal - 3 pointer)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] sort0And1And2Efficient(int[] a) {
        int low = 0;
        int mid = 0;
        int high = a.length-1;
        while(mid <= high) {
            if(a[mid] == 0) {
                int temp = a[low];
                a[low] = a[mid];
                a[mid] = temp;
                low++;
                mid++;
            } else if (a[mid] == 1) {
                mid++;
            } else {
                int temp = a[high];
                a[high] = a[mid];
                a[mid] = temp;
                high--;
            }
        }
        return a;
    }
}