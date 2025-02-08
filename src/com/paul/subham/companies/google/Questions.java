package com.paul.subham.companies.google;

/**
 * 1. Minimum Number of Increments on Sub arrays to Form a Target Array (Monotonic stack)
 */
public class Questions {

    public static void main(String[] args) {

    }

    /**
     * Minimum Number of Increments on Sub arrays to Form a Target Array (Monotonic stack)
     *
     * You are given an integer array target. You have an integer array initial of the same size as target with all elements initially zeros.
     *
     * In one operation you can choose any subarray from initial and increment each value by one.
     *
     * Return the minimum number of operations to form a target array from initial.
     *
     * The test cases are generated so that the answer fits in a 32-bit integer.
     *
     * Example 1:
     * Input: target = [1,2,3,2,1]
     * Output: 3
     * Explanation: We need at least 3 operations to form the target array from the initial array.
     * [0,0,0,0,0] increment 1 from index 0 to 4 (inclusive).
     * [1,1,1,1,1] increment 1 from index 1 to 3 (inclusive).
     * [1,2,2,2,1] increment 1 at index 2.
     * [1,2,3,2,1] target array is formed.
     */
    public static int minNumberOperations(int[] a) {
        int count = a[0];
        for(int i=1; i<a.length; i++) {
            count+= Math.max(a[i]-a[i-1], 0);
        }
        return count;
    }
}
