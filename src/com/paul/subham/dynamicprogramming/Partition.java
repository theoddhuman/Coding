package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 1. Matrix chain multiplication (Recursion)
 * 2. Matrix chain multiplication (Memoization)
 * 3. Matrix chain multiplication (Tabulation)
 */
public class Partition {
    public static void main(String[] args) {
        int arr[] = {40, 20, 30, 10, 30};
        System.out.println(matrixChainMultiplicationTabulation(arr));
    }

    /**
     * Matrix chain multiplication (Recursion)
     *
     * Given the dimension of a sequence of matrices in an array arr[],
     * where the dimension of the ith matrix is (arr[i-1] * arr[i]),
     * the task is to find the most efficient way to multiply these matrices together such that
     * the total number of element multiplications is minimum.
     * When two matrices of size m*n and n*p when multiplied,
     * they generate a matrix of size m*p and the number of multiplications performed are m*n*p.
     *
     * {2, 1, 3, 4}, There are 3 matrices of dimensions 2×1, 1×3, and 3×4,
     * output will be 20
     *
     * TC: O(2^n)
     * SC: O(n)
     */
    public static int matrixChainMultiplication(int[] a) {
        return mcm(a, 0, a.length-1);
    }

    private static int mcm(int[] a, int i, int j) {
        if(i+1 == j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int k= i+1; k<j; k++) {
            int curr = mcm(a, i, k) + mcm(a, k, j) + a[i] * a[k] * a[j];
            min = Math.min(min, curr);
        }
        return min;
    }

    /**
     * Matrix chain multiplication (Memoization)
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public static int matrixChainMultiplicationMemoization(int[] a) {
        int[][] dp = new int[a.length][a.length];
        for(int i=0; i<a.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return mcm(a, 0, a.length-1, dp);
    }

    private static int mcm(int[] a, int i, int j, int[][] dp) {
        if(i+1 == j) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for(int k= i+1; k<j; k++) {
            int curr = mcm(a, i, k, dp) + mcm(a, k, j, dp) + a[i] * a[k] * a[j];
            min = Math.min(min, curr);
        }
        return dp[i][j] = min;
    }

    /**
     * Matrix chain multiplication (Tabulation)
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public static int matrixChainMultiplicationTabulation(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        for(int len = 2; len<n; len++) {
            for(int i=0; i<n-len; i++) {
                int j = i+len;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i+1; k<j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + a[i] * a[k] * a[j]);
                }
            }
        }
        return dp[0][n-1];
    }
}
