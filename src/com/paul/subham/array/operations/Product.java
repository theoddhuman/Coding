package com.paul.subham.array.operations;

/**
 * @author subham.paul
 *
 * 1. Product of array except self
 * 2. Product of array except self (Space efficient)
 * 3. Product of array except self (Counting zero)
 * 4. Maximum product sub array
 * 5. Maximum product sub array (efficient)
 */
public class Product {
    public static void main(String[] args) {
        int[] a = { -10, 3, -1, 6, 0 };
        System.out.println(maxProductSubArrayEfficient(a));
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

    /**
     * Maximum product sub array
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int maxProductSubArray(int[] a) {
        int maxProduct = a[0];
        for(int i=0; i<a.length; i++) {
            int mul = a[i];
            for(int j=i+1; j<a.length; j++) {
                mul *= a[j];
                maxProduct = Math.max(maxProduct, mul);
            }
        }
        return maxProduct;
    }

    /**
     * Maximum product sub array (efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxProductSubArrayEfficient(int[] a) {
        int n = a.length;
        int left = 1;
        int right = 1;
        int maxProduct = a[0];
        for(int i=0; i<n; i++) {
            //if we encounter a zero we will start a new substring, so left or right will be set to 1.
            left = (left == 0? 1: left) * a[i];
            //odd number of negative values can be problematic, that's why if we start from left 1 odd negative can be eliminated and from
            // right another one can be eliminated.
            right = (right == 0? 1: right) * a[n-1-i];
            maxProduct = Math.max(maxProduct, Math.max(left, right));
        }
        return maxProduct;
    }
}
