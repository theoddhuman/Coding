package com.paul.subham.array.operations;

/**
 * @author subham.paul
 *
 * 1. Find rotation count in sorted and rotated array (Linear search - minimum element)
 * 2. Find rotation count in sorted and rotated array (Linear search - next element smaller)
 * 3. Find rotation count in sorted and rotated array (Binary search - recursive)
 * 4. Find rotation count in sorted and rotated array (Binary search - iterative)
 */
public class Rotation {
    public static void main(String[] args) {
        int[] a = {5,6,7,1,2,3,4};
        System.out.println(rotationCountBinaryIterative(a));
    }

    /**
     * Find rotation count in sorted and rotated array (Linear search - minimum element)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int rotationCount(int[] a) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i=1; i<a.length; i++) {
            if(a[i] < a[minIndex]) {
                min = a[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * Find rotation count in sorted and rotated array (Linear search - next element smaller)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int rotationCount1(int[] a) {
        if (a[0] > a[a.length - 1]) {
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    /**
     * Find rotation count in sorted and rotated array (Binary search - recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int rotationCountBinaryRecursive(int[] a, int low, int high) {
        if(high < low) {
            return 0;
        }
        int mid = low + (high-=low)/2;
        if(low<mid && a[mid] < a[mid-1]) {
            return mid;
        }
        if(high > mid && a[mid] > a[mid+1]) {
            return mid+1;
        }
        if(a[mid] < a[high]) {
            return rotationCountBinaryRecursive(a, low, mid-1);
        }
        return rotationCountBinaryRecursive(a, mid+1, high);
     }

    /**
     * Find rotation count in sorted and rotated array (Binary search - iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
     public static int rotationCountBinaryIterative(int[] a) {
        int low = 0;
        int high = a.length - 1;
        while(low < high) {
            int mid = low + (high-=low)/2;
            if(low<mid && a[mid] < a[mid-1]) {
                return mid;
            }
            if(high > mid && a[mid] > a[mid+1]) {
                return mid+1;
            }
            if(a[mid] < a[high]) {
                high = mid-1;
            }
            low = mid+1;
        }
        return 0;
     }


}
