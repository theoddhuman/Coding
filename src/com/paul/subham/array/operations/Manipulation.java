package com.paul.subham.array.operations;

import com.paul.subham.mathematics.Basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author subham.paul
 * <p>
 * 1. Rotating array to left (one by one)
 * 2. Rotating array to left (Using temporary array)
 * 3. Rotating array to left (Juggling algorithm)
 * 4. Rotating array to left (Reversal algorithm)
 * 5. Rotating array to left (Block swap algorithm recursive)
 * 6. Rotating array to left (Block swap algorithm iterative)
 * 7. Reverse an array (Iterative)
 * 8. Reverse an array (Recursive)
 * 9. Merge k sorted arrays (By sorting)
 * 10. Merge k sorted arrays (Merging)
 * 11. Merge k sorted arrays (Using priority queue)
 */
public class Manipulation {
    public static void main(String[] args) {
//        int[] a = {1, 2, 13, 14};
//        rotateBlockSwapRecursive(a, 5);
//        System.out.println(Arrays.toString(a));
        int[][] a = {{1, 3, 45, 46},
                {21, 34, 35, 89},
                {1, 2, 3, 11},
                {4, 6, 7, 8}};
        int[] output = mergeKSortedArraysPQ(a);
        System.out.println(Arrays.toString(output));
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
        reverseIterative(a, 0, d - 1);
        reverseIterative(a, d, a.length - 1);
        reverseIterative(a, 0, a.length - 1);
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
     * Reverse an array (Iterative)
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseIterative(int[] a, int low, int high) {
        int temp;
        while(low < high) {
            temp = a[low];
            a[low] = a[high];
            a[high] = temp;
            low++;
            high--;
        }
    }

    /**
     * Reverse an array (Recursive)
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseRecursive(int[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int temp = a[low];
        a[low] = a[high];
        a[high] = temp;
        reverseRecursive(a, low + 1, high - 1);
    }

    /**
     * Merge k sorted arrays (By sorting)
     * All arrays are of same size
     *
     * TC: O(nk*log(nk))
     * SC: O(nk)
     */
    public static int[] mergeKSortedArrays(int[][] a, int n, int k) {
        int[] output = new int[n*k];
        int index = 0;
        for(int i=0; i<k; i++) {
            for(int j=0; j<n; j++) {
                output[index] = a[i][j];
                index++;
            }
        }
        Arrays.sort(output);
        return output;
    }

    /**
     * Merge k sorted arrays (Merging)
     * All arrays are of same size
     *
     * TC: O(nk*log(k))
     * SC: O(nk*log(k))
     */
    public static int[] mergeKSortedArraysMerging(int[][] a, int n, int k) {
        return mergeKSortedArraysUtil(a, 0, k-1, n);
    }

    public static int[] mergeKSortedArraysUtil(int[][] a, int i, int j, int n) {
        if(i==j) {
            return a[i];
        }
        if(j-i==1) {
            return merge(a[i], a[j], n, n);
        }
        int mid = (i+j)/2;
        int[] output1 = mergeKSortedArraysUtil(a, 0, mid, n);
        int[] output2 = mergeKSortedArraysUtil(a, mid+1, j, n);
        return merge(output1, output2, output1.length, output2.length);
    }

    private static int[] merge(int[] a, int[] b, int m, int n) {
        int[] output = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i<m && j<n) {
            if(a[i] > b[j]) {
                output[k++] = b[j++];
            } else {
                output[k++] = a[i++];
            }
        }
        while (i < m) {
            output[k++] = a[i++];
        }
        while (j < n) {
            output[k++] = b[j++];
        }
        return output;
    }

    /**
     * Merge k sorted arrays (Using priority queue)
     * All arrays may be of different size
     *
     * TC: O(nk*log(k))
     * SC: O(nk*log(k))
     */
    public static int[] mergeKSortedArraysPQ(int[][] a) {
        PriorityQueue<ArrayContainer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> a[o.aIndex][o.index]));
        int total = 0;
        for(int i=0; i<a.length; i++) {
            priorityQueue.add(new ArrayContainer(i, 0));
            total+= a[i].length;
        }
        int[] output = new int[total];
        int oIndex = 0;
        while(!priorityQueue.isEmpty()) {
            ArrayContainer arrayContainer = priorityQueue.poll();
            output[oIndex++] = a[arrayContainer.aIndex][arrayContainer.index];
            if(arrayContainer.index < a[arrayContainer.aIndex].length - 1) {
                priorityQueue.add(new ArrayContainer(arrayContainer.aIndex, arrayContainer.index+1));
            }
        }
        return output;
    }

    static class ArrayContainer {
        Integer aIndex;
        Integer index;
        ArrayContainer(Integer aIndex, Integer index) {
            this.aIndex = aIndex;
            this.index = index;
        }
    }
}
