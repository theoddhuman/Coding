package com.paul.subham.array.operations;

import com.paul.subham.mathematics.Basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

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
 * 12. Re-arrange an array such that a[i] = i
 * 13. Re-arrange an array such that a[i] = i (Efficient)
 * 14. Re-arrange an array such that a[i] = i (Hashing)
 * 15. Re-arrange an array such that a[i] = i (Swapping)
 * 16. Remove adjacent duplicates from an array
 * 17. Merge two sorted arrays
 * 18. Move zeros to end of an array
 * 19. Move zeros to end of an array (Single traversal)
 * 20. Segregate 0's and 1's in an array (Count 0)
 * 21. Segregate 0's and 1's in an array (Two indices)
 * 22. Segregate 0's and 1's in an array (Single traversal)
 */
public class Manipulation {
    public static void main(String[] args) {
        int[] a = {1,0, 1,1,0,0,1,0};
//        int[] b = {3,4,11,23};
//        int[] res = merge(a, b);
        segregate0And1SingleTraversal(a);
        for(int i: a) {
            System.out.print(i + " ");
        }
//        int[][] a = {{1, 3, 45, 46},
//                {21, 34, 35, 89},
//                {1, 2, 3, 11},
//                {4, 6, 7, 8}};
//        int[] output = mergeKSortedArraysPQ(a);
//        System.out.println(Arrays.toString(output));
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

    /**
     * Re-arrange an array such that a[i] = i
     *
     * Given an array of elements of length N, ranging from 0 to N – 1. All elements may not be present in the array.
     * If the element is not present then there will be -1 present in the array.
     * Rearrange the array such that A[i] = i and if i is not present, display -1 at that place.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void reArrange(int[] a) {
        for(int i=0; i<a.length; i++) {
            for(int j=0; j<a.length; j++) {
                if(a[j] == i) {
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                    break;
                }
            }
        }
        for(int i=0; i<a.length; i++) {
            if(a[i] != i) {
                a[i] = -1;
            }
        }
    }

    /**
     * Re-arrange an array such that a[i] = i (Efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reArrangeEfficient(int[] a) {
        for(int i=0; i<a.length; i++) {
            if(a[i] != -1 && a[i] != i) {
                int x = a[i];
                while(a[x] != -1 && a[x] != x) {
                    int y = a[x];
                    a[x] = x;
                    x = y;
                }
                a[x] = x;
            }
            if(a[i] != i) {
                a[i] = -1;
            }
        }
    }

    /**
     * Re-arrange an array such that a[i] = i (Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void reArrangeHashing(int[] a) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<a.length; i++) {
            set.add(a[i]);
        }
        for(int i=0; i<a.length; i++) {
            if(set.contains(i)) {
                a[i] = i;
            } else {
                a[i] = -1;
            }
        }
    }

    /**
     * Re-arrange an array such that a[i] = i (Swapping)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reArrangeSwap(int[] a) {
        for(int i=0; i<a.length;) {
            if(a[i] != -1 && a[i] != i) {
                int temp = a[a[i]];
                a[a[i]] = a[i];
                a[i] = temp;
            } else {
                i++;
            }
        }
    }

    /**
     * Remove adjacent duplicates from an array
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void removeAdjacentDuplicates(int[] a) {
        int stack = 0;
        for(int i=1; i<a.length; i++) {
            if(a[i] != a[stack]) {
                stack++;
                a[stack] = a[i];
            }
        }
        for(int i=stack+1; i<a.length; i++) {
            a[i] = -1;
        }
    }

    /**
     * Merge two sorted arrays
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] merge(int[] a, int[] b) {
        int l = 0;
        int r = 0;
        int[] temp = new int[a.length + b.length];
        int index = 0;
        while(l < a.length && r < b.length) {
            if(a[l] <= b[r]) {
                temp[index++] = a[l];
                l++;
            } else {
                temp[index++] = b[r];
                r++;
            }
        }
        while(l < a.length) {
            temp[index++] = a[l];
            l++;
        }
        while(r < b.length) {
            temp[index++] = b[r];
            r++;
        }
        return temp;
    }

    /**
     * Move zeros to end of an array
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void moveZerosEnd(int[] a) {
        int count = 0;
        for(int i=0; i<a.length; i++) {
            if(a[i] != 0) {
                a[count++] = a[i];
            }
        }
        while(count < a.length) {
            a[count++] = 0;
        }
    }

    /**
     * Move zeros to end of an array (Single traversal)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void moveZerosEndSingleTraversal(int[] a) {
        int count = 0;
        for(int i=0; i<a.length; i++) {
            if(a[i] != 0) {
                int temp = a[count];
                a[count] = a[i];
                a[i] = temp;
                count++;
            }
        }
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
}
