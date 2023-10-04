package com.paul.subham.array.operations;

import com.paul.subham.mathematics.Basic;

import java.util.Arrays;

/**
 * @author subham.paul
 * <p>
 * 1. Rotating array to left (one by one)
 * 2. Rotating array to left (Using temporary array)
 * 3. Rotating array to left (Juggling algorithm)
 * 4. Rotating array to left (Reversal algorithm)
 * 5. Reverse an array (Iterative)
 * 6. Reverse an array (Recursive)
 */
public class Manipulation {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        rotateReversal(a, 3);
        System.out.println(Arrays.toString(a));
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
}
