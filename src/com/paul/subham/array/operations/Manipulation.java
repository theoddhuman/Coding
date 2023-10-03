package com.paul.subham.array.operations;

import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 1. Rotating array to left (one by one)
 * 2. Rotating array to left (Using temporary array)
 */
public class Manipulation {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        rotateUsingTempArray(a, 3);
        System.out.println(Arrays.toString(a));
    }

    /**
     * Rotating array to left (one by one)
     * TC: O(n*d), d is no of rotations
     * SC: O(1)
     */
    public static void rotateOneByOne(int[] a, int d) {
        for(int i=0; i<d; i++) {
            leftRotateByOne(a, a.length);
        }
    }

    private static void leftRotateByOne(int[] a, int n) {
        int temp = a[0];
        for(int i=0; i<n-1; i++) {
            a[i] = a[i+1];
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
        for(int i=d; i<a.length; i++) {
            temp[j] =a [i];
            j++;
        }
        for(int i=0; i<d; i++) {
            temp[j] =a [i];
            j++;
        }
        for(int i=0; i<a.length; i++) {
            a[i] = temp[i];
        }
    }
}
