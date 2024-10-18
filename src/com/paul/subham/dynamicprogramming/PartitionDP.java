package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * @author subham.paul
 *
 * 1. Matrix chain multiplication (Recursion)
 * 2. Matrix chain multiplication (Memoization)
 * 3. Matrix chain multiplication (Tabulation)
 * 4. Minimum cost to cut the stick (Memoization)
 * 5. Minimum cost to cut the stick (Tabulation)
 * 6. Burst Balloons (Memoization)
 * 7. Burst Balloons (Tabulation)
 */
public class PartitionDP {
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

    public static int matrixChainMultiplicationTabulation2(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        for(int i = n-2; i>=0; i--) {
            for(int j=i+2; j<n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i+1; k<j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + a[i] * a[k] * a[j]);
                }
            }
        }
        return dp[0][n-1];
    }

    /**
     * Minimum cost to cut the stick (Memoization)
     *
     * Given a wooden stick of length n units. The stick is labelled from 0 to n.
     * You should perform the cuts in order, you can change the order of the cuts as you wish.
     * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
     * When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut).
     * n = 7, cuts = [1,3,4,5], output: 16
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public static int minCostMemoization(int n, int[] cuts) {
        //add 0 and n to start and end of the array
        int[] a = new int[cuts.length+2];
        int l = a.length;
        for(int i=0;i<cuts.length;i++) {
            a[i+1] = cuts[i];
        }
        a[l-1] = n;
        //sorting will remove dependency among cuts
        Arrays.sort(a);
        int[][] dp = new int[l][l];
        for(int i=0;i<l;i++) {
            Arrays.fill(dp[i], -1);
        }
        return minCost(a, 1, l-2, dp);
    }

    private static int minCost(int[] a, int i, int j, int[][] dp) {
        if(i>j) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for(int k=i;k<=j;k++) {
            min = Math.min(min, minCost(a, i, k-1, dp) + minCost(a, k+1, j, dp));
        }
        return dp[i][j] = a[j+1] - a[i-1] + min;
    }


    /**
     * Minimum cost to cut the stick (Tabulation)
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public static int minCost(int n, int[] cuts) {
        int[] a = new int[cuts.length+2];
        int l = a.length;
        for(int i=0;i<cuts.length;i++) {
            a[i+1] = cuts[i];
        }
        a[l-1] = n;
        Arrays.sort(a);
        int[][] dp = new int[l][l];
        for(int i=l-2;i>=1;i--) {
            for(int j=i;j<=l-2;j++) {
                int min = Integer.MAX_VALUE;
                for(int k=i;k<=j;k++) {
                    min = Math.min(min, dp[i][k-1] + dp[k+1][j]);
                }
                dp[i][j] = a[j+1] - a[i-1] + min;
            }
        }
        return dp[1][l-2];
    }

    /**
     * Burst Balloons (Memoization)
     *
     * You are given n balloons, indexed from 0 to n - 1.
     * Each balloon is painted with a number on it represented by an array nums.
     * You are asked to burst all the balloons.
     *
     * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
     * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
     *
     * Return the maximum coins you can collect by bursting the balloons wisely.
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public static int maxCoinsMemoization(int[] nums) {
        int[] a = new int[nums.length+2];
        int n = a.length;
        for(int i=0;i<nums.length;i++) {
            a[i+1] = nums[i];
        }
        a[n-1] = 1;
        a[0] = 1;
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return maxCoins(a, 1, n-2, dp);
    }

    private static int maxCoins(int[] a, int i, int j, int[][] dp) {
        if(i > j) {
            return 0;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        int max = Integer.MIN_VALUE;
        for(int k=i; k<=j; k++) {
            max = Math.max(max, (a[i-1] * a[k] * a[j+1]) + maxCoins(a, i, k-1, dp)+maxCoins(a, k+1, j, dp));
        }
        return dp[i][j] = max;
    }

    /**
     * Burst Balloons (Tabulation)
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public int maxCoinsTab(int[] nums) {
        int[] a = new int[nums.length+2];
        int n = a.length;
        for(int i=0;i<nums.length;i++) {
            a[i+1] = nums[i];
        }
        a[n-1] = 1;
        a[0] = 1;
        int[][] dp = new int[n][n];
        for(int i=n-2; i>=1; i--) {
            for(int j=i; j<n-1; j++) {
                int max = Integer.MIN_VALUE;
                for(int k=i; k<=j; k++) {
                    max = Math.max(max, (a[i-1]*a[k]*a[j+1])+dp[i][k-1] + dp[k+1][j]);
                }
                dp[i][j] = max;
            }
        }
        return dp[1][n-2];
    }
}
