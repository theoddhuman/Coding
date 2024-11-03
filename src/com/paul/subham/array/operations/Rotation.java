package com.paul.subham.array.operations;

import com.paul.subham.mathematics.Basic;

/**
 * @author subham.paul
 *
 * 1. Rotating array to left (one by one)
 * 2. Rotating array to left (Using temporary array)
 * 3. Rotating array to left (Juggling algorithm)
 * 4. Rotating array to left (Reversal algorithm)
 * 5. Rotating array to left (Block swap algorithm recursive)
 * 6. Rotating array to left (Block swap algorithm iterative)
 * 7. Find rotation count in sorted and rotated array (Linear search - minimum element)
 * 8. Find rotation count in sorted and rotated array (Linear search - next element smaller)
 * 9. Find rotation count in sorted and rotated array (Binary search - recursive)
 * 10. Find rotation count in sorted and rotated array (Binary search - iterative)
 */
public class Rotation {
    public static void main(String[] args) {
        int[] a = {5,6,7,1,2,3,4};
        System.out.println(rotationCountBinaryIterative(a));
    }

    /**
     * Rotating array to left (one by one)
     * TC: O(n*d), d is no of rotations
     * SC: O(1)
     */
    public static void rotateOneByOne(int[] a, int d) {
        for (int i = 0; i < d; i++) {
            leftRotateByOne(a, a.length);
        }
    }

    private static void leftRotateByOne(int[] a, int n) {
        int temp = a[0];
        for (int i = 0; i < n - 1; i++) {
            a[i] = a[i + 1];
        }
        a[n - 1] = temp;
    }


    /**
     * Rotating array to left (Using temporary array)
     * TC: O(n)
     * SC: O(n)
     */
    public static void rotateUsingTempArray(int a[], int d) {
        int[] temp = new int[a.length];
        int j = 0;
        for (int i = d; i < a.length; i++) {
            temp[j] = a[i];
            j++;
        }
        for (int i = 0; i < d; i++) {
            temp[j] = a[i];
            j++;
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = temp[i];
        }
    }

    /**
     * Rotating array to left (Juggling algorithm)
     * TC: O(n)
     * SC: O(1)
     */
    public static void rotateJuggling(int[] a, int d) {
        int n = a.length;
        //to handle if d >= n
        d = d % n;
        int gcd = Basic.gcdUsingDivision(n, d);
        int j, k;
        for (int i = 0; i < gcd; i++) {
            j = i;
            int temp = a[i];
            while (true) {
                k = j + d;
                if (k >= n) {
                    k -= n;
                }
                if (k == i) {
                    break;
                }
                a[j] = a[k];
                j = k;
            }
            a[j] = temp;
        }
    }

    /**
     * Rotating array to left (Reversal algorithm)
     * TC: O(n)
     * SC: O(1)
     */
    public static void rotateReversal(int[] a, int d) {
        Manipulation.reverseIterative(a, 0, d - 1);
        Manipulation.reverseIterative(a, d, a.length - 1);
        Manipulation.reverseIterative(a, 0, a.length - 1);
    }

    /**
     * Rotating array to left (Block swap algorithm recursive)
     * TC: O(n)
     * SC: O(logn)
     */
    public static void rotateBlockSwapRecursive(int[] a, int d) {
        rotateUtil(a, 0, a.length, d);
    }

    private static void rotateUtil(int[] a, int start, int size, int d) {
        if(d==0 || d==size) {
            return;
        }
        if(d == size - d) {
            blockSwap(a, start, size-d+start, d);
        } else if (d < size-d) {
            blockSwap(a, start, size-d+start, d);
            rotateUtil(a, start, size-d, d);
        } else {
            blockSwap(a, start, d+start, size-d);
            //size - (size -d) = d, d - (size-d) = 2*d- size
            rotateUtil(a, size-d+start, d, 2*d-size);
        }
    }

    /**
     * Rotating array to left (Block swap algorithm iterative)
     * TC: O(n)
     * SC: O(logn)
     */
    public static void rotateBlockSwapIterative(int[] a, int d) {
        if(d==0 || d==a.length) {
            return;
        }
        int i = d;
        int j = a.length -d;
        while(i != j) {
            if(i < j) {
                blockSwap(a, d-i, d+j-i, i);
                j-=i;
            } else {
                blockSwap(a, d-i, d, j);
                i-=j;
            }
        }
        blockSwap(a, d-i, d, i);
    }

    private static void blockSwap(int[] a, int startL, int startR, int d) {
        for(int i=0; i<d; i++) {
            int temp = a[startL + i];
            a[startL + i] = a[startR + i];
            a[startR + i] = temp;
        }
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
            } else {
                low = mid + 1;
            }
        }
        return 0;
     }


}
