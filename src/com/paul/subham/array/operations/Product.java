package com.paul.subham.array.operations;

import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 1. Product of array except self
 * 2. Product of array except self (Space efficient)
 * 3. Product of array except self (Counting zero)
 */
public class Product {
    public static void main(String[] args) {
        int[] a = { 10, 3, 0, 6, 0 };
        System.out.println(Arrays.toString(productCountingZero(a, 5)));
    }

    /**
     * Product of array except self
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] product(int[] a, int n) {
        if(n==1) {
            return new int[]{0};
        }
        int[] left = new int[n];
        int[] right = new int[n];
        int[] product = new int[n];
        left[0] = 1;
        right[n-1] = 1;
        for(int i=1; i<n; i++) {
            left[i] = a[i-1] * left[i-1];
        }
        for(int i=n-2; i>=0; i--) {
            right[i] = a[i+1] * right[i+1];
        }
        for(int i=0; i<n; i++) {
            product[i] = left[i] * right[i];
        }
        return product;
    }

    /**
     * Product of array except self (Space efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] productSpaceOptimization(int[] a, int n) {
        if(n==1) {
            return new int[]{0};
        }
        int[] product = new int[n];
        int temp = 1;
        for(int i=0; i<n; i++) {
            product[i]=temp;
            temp*=a[i];
        }
        temp = 1;
        for(int i=n-1; i>=0; i--) {
            product[i] *= temp;
            temp *= a[i];
        }
        return product;
    }

    /**
     * Product of array except self (Counting zero)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] productCountingZero(int[] a, int n) {
        if(n==1) {
            return new int[]{0};
        }
        int zeroCount = 0;
        int product = 1;
        for(int i=0; i<n; i++) {
            if(a[i] == 0) {
                zeroCount++;
            } else {
                product *= a[i];
            }
        }
        int[] productArray = new int[n];
        if(zeroCount > 1) {
            return productArray;
        }
        for(int i=0; i<n; i++) {
            if(zeroCount == 0) {
                productArray[i] = product/a[i];
            } else if (zeroCount == 1 && a[i] != 0) {
                productArray[i] = 0;
            } else {
                productArray[i] = product;
            }
        }
        return productArray;
    }
}
