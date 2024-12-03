package com.paul.subham.stack.operation;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1. Sum of sub array minimums
 * 2. Sum of sub array minimums (Using stack)
 * 3. Sum of sub array maximums (Using stack)
 * 4. Sum of sub array ranges
 * 5. Largest Rectangle in Histogram
 * 6. Largest Rectangle in Histogram (Single iteration)
 * 7. Maximal rectangle
 */
public class SubArray {
    public static void main(String[] args) {

    }

    /**
     * Sum of sub array minimums
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int sumSubArrayMinimums(int[] a) {
        int n = a.length;
        int sum = 0;
        for(int i=0; i<n; i++) {
            int min = a[i];
            for(int j=i; j<n; j++) {
                min = Math.min(min, a[j]);
                sum = (sum+min)%1000000007;
            }
        }
        return sum;
    }

    /**
     * Sum of sub array minimums (Using stack)
     *
     * TC: O(5n)
     * SC: O(5n)
     */
    public static int sumSubArrayMinStack(int[] a) {
        int mod = 1000000007;
        int n = a.length;
        int[] nse = nextSmaller(a,n);
        int[] pse = prevSmaller(a,n);
        long sum = 0;
        for(int i=0; i<n; i++) {
            int pre = i - pse[i];
            int next = nse[i] - i;
            sum = (sum + ((long) pre * next * a[i])%mod)%mod;
        }
        return (int)sum;
    }

    public static int[] prevSmaller(int[] a, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        Stack<Integer> stack = new Stack<>();
        stack.add(n-1);
        for(int i=n-2; i>=0; i--) {
            while(!stack.isEmpty() && a[stack.peek()] > a[i]) {
                prev[stack.pop()] = i;
            }
            stack.push(i);
        }
        return prev;
    }

    public static int[] nextSmaller(int[] a, int n) {
        int[] next = new int[n];
        Arrays.fill(next,n);
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for(int i=1; i<n; i++) {
            while(!stack.isEmpty() && a[stack.peek()] >= a[i]) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }
        return next;
    }

    /**
     * Sum of sub array maximums (Using stack)
     *
     * TC: O(5n)
     * SC: O(5n)
     */
    public static int sumSubArrayMaxStack(int[] a) {
        int mod = 1000000007;
        int n = a.length;
        int[] nse = nextGreater(a,n);
        int[] pse = prevGreater(a,n);
        long sum = 0;
        for(int i=0; i<n; i++) {
            int pre = i - pse[i];
            int next = nse[i] - i;
            sum = (sum + ((long) pre * next * a[i])%mod)%mod;
        }
        return (int)sum;
    }

    public static int[] prevGreater(int[] a, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        Stack<Integer> stack = new Stack<>();
        stack.add(n-1);
        for(int i=n-2; i>=0; i--) {
            while(!stack.isEmpty() && a[stack.peek()] < a[i]) {
                prev[stack.pop()] = i;
            }
            stack.push(i);
        }
        return prev;
    }

    public static int[] nextGreater(int[] a, int n) {
        int[] next = new int[n];
        Arrays.fill(next,n);
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for(int i=1; i<n; i++) {
            while(!stack.isEmpty() && a[stack.peek()] <= a[i]) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }
        return next;
    }

    /**
     * Sum of sub array ranges
     *
     * sum of difference between largest and smallest of all sub arrays
     *
     * TC: O(10n)
     * SC: O(10n)
     */
    public static long subArrayRanges(int[] nums) {
        return sumSubArrayMaxStack(nums) - sumSubArrayMinStack(nums);
    }

    /**
     * Largest Rectangle in Histogram
     *
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
     * return the area of the largest rectangle in the histogram.
     *
     * TC: O(5n)
     * SC: O(5n)
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] pse = prevSmaller(heights, n);
        int[] nse = nextSmaller(heights, n);
        int max = 0;
        for(int i=0; i<n; i++) {
            max = Math.max(max, (nse[i]-pse[i]-1)*heights[i]);
        }
        return max;
    }

    /**
     * Largest Rectangle in Histogram (Single iteration)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int largestRectangleAreaOptimized(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for(int i=0; i<=n; i++) {
            while(!stack.isEmpty() && (i==n || heights[stack.peek()] > heights[i])) {
                int x = stack.pop();
                int nse = i;
                int pse;
                if(!stack.isEmpty()) {
                    pse = stack.peek();
                } else {
                    pse = -1;
                }
                max = Math.max(max, heights[x]*(nse-pse-1));
            }
            stack.push(i);
        }
        return max;
    }


    /**
     * Maximal rectangle
     *
     * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] prefixSum = new int[m][n];
        for(int j=0; j<n; j++) {
            int sum = 0;
            for(int i=0; i<m; i++) {
                sum += Character.getNumericValue(matrix[i][j]);
                if(matrix[i][j] == '0') {
                    sum = 0;
                }
                prefixSum[i][j] = sum;
            }
        }
        int max = 0;
        for(int i=0; i<m; i++) {
            max = Math.max(max, largestRectangleAreaOptimized(prefixSum[i]));
        }
        return max;
    }
}
