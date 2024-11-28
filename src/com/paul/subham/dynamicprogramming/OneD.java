package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * @author subham.paul
 * <p>
 * 1. Climbing stairs (Memoization)
 * 2. Climbing stairs (Tabulation)
 * 3. Climbing stairs (Tabulation - space optimized)
 * 4. Frog Jump (Memoization)
 * 5. Frog Jump (Tabulation)
 * 6. Frog Jump (Tabulation - Space optimized)
 * 7. Frog Jump K times (Memoization)
 * 8. Frog Jump K times (Tabulation)
 * 9. Maximum sum of non-adjacent elements (Memoization)
 * 10. Maximum sum of non-adjacent elements (Tabulation)
 * 11. Maximum sum of non-adjacent elements (Tabulation - Space Optimized)
 * 12. Maximum sum of non-adjacent elements of a circular array (Memoization)
 * 13. Maximum sum of non-adjacent elements of a circular array (Tabulation)
 */
public class OneD {
    public static void main(String[] args) {
        int[] a = {10, 20, 30, 10};
        System.out.println(minimumEnergyMemoization(a, 4));
    }

    /**
     * Climbing stairs (Memoization)
     * <p>
     * Given a number of stairs. Starting from the 0th stair we need to climb to the “Nth” stair.
     * At a time we can climb either one or two steps. We need to return the total number of distinct ways to reach from 0th to Nth stair.
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return climbUtil(n, dp);
    }

    private static int climbUtil(int n, int[] dp) {
        if (n == 1 || n == 0) {
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = climbUtil(n - 1, dp) + climbUtil(n - 2, dp);
    }

    /**
     * Climbing stairs (Tabulation)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public int climbStairsTabulation(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Climbing stairs (Tabulation - space optimized)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public int climbStairsTabulationSpaceOptimized(int n) {
        int prev = 1;
        int current = 1;
        for (int i = 2; i <= n; i++) {
            int temp = current;
            current = prev + current;
            prev = temp;
        }
        return current;
    }

    /**
     * Frog Jump (Memoization)
     * <p>
     * Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair.
     * At a time the frog can climb either one or two steps.
     * A height[N] array is also given. Whenever the frog jumps from a stair i to stair j,
     * the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute difference.
     * We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static int minimumEnergyMemoization(int[] a, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return minEnergy(a, n - 1, dp);
    }

    private static int minEnergy(int[] a, int i, int[] dp) {
        if (i == 0) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        int jumpOne = Math.abs(a[i] - a[i - 1]) + minEnergy(a, i - 1, dp);
        int jumpTwo = Integer.MAX_VALUE;
        if (i > 1) {
            jumpTwo = Math.abs(a[i] - a[i - 2]) + minEnergy(a, i - 2, dp);
        }
        return dp[i] = Math.min(jumpOne, jumpTwo);
    }

    /**
     * Frog Jump (Tabulation)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public int minimumEnergyTabulation(int a[], int n) {
        //code here
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        if (n > 1) {
            dp[1] = Math.abs(a[1] - a[0]);
        }
        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(Math.abs(a[i] - a[i - 1]) + dp[i - 1], Math.abs(a[i] - a[i - 2]) + dp[i - 2]);
        }
        return dp[n - 1];
    }

    /**
     * Frog Jump (Tabulation - Space optimized)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public int minimumEnergyTabSpaceOpt(int a[], int n) {
        int jump2 = 0;
        int jump1 = 0;
        if (n > 1) {
            jump1 = Math.abs(a[1] - a[0]);
        }
        for (int i = 2; i < n; i++) {
            int temp = jump1;
            jump1 = Math.min(Math.abs(a[i] - a[i - 1]) + jump1, Math.abs(a[i] - a[i - 2]) + jump2);
            jump2 = temp;
        }
        return jump1;
    }

    /**
     * Frog Jump K times (Memoization)
     *
     * Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair.
     * At a time the frog can climb 1, 2, ... or K steps
     * A height[N] array is also given. Whenever the frog jumps from a stair i to stair j,
     * the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute difference.
     * We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1
     *
     * TC: O(nk)
     * SC: O(n)
     */
    public static int minimizeCost(int arr[], int k) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return minimizeCost(arr, k, arr.length - 1, dp);
    }

    private static int minimizeCost(int a[], int k, int i, int[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        int minCost = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i == j) {
                minCost = Math.min(minCost, Math.abs(a[i] - a[i - j]));
            } else if (i > j) {
                minCost = Math.min(minCost, Math.abs(a[i] - a[i - j]) + minimizeCost(a, k, i - j, dp));
            }
        }
        return dp[i] = minCost;
    }

    /**
     * Frog Jump K times (Tabulation)
     *
     * TC: O(nk)
     * SC: O(n)
     */
    public static int minimizeCostTab(int a[], int k) {
        int[] dp = new int[a.length];

        for(int i=1; i<a.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for(int j=1; j<=k; j++)  {
                if(i==j) {
                    dp[i] = Math.min(dp[i], Math.abs(a[i] - a[i-j]));
                } else if (i > j) {
                    dp[i] = Math.min(dp[i], Math.abs(a[i] - a[i-j]) + dp[i-j]);
                }
            }
        }
        return dp[a.length-1];
    }


    /**
     * Maximum sum of non-adjacent elements (Memoization)
     *
     * Given an array of ‘N’  positive integers,
     * we need to return the maximum sum of the subsequence such that no two elements of the subsequence are adjacent elements in the array.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int robMemo(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return rob(a, n - 1, dp);
    }

    private static int rob(int[] a, int i, int[] dp) {
        if (i == 0) {
            return a[i];
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        if (i == 1) {
            return dp[i] = Math.max(rob(a, 0, dp), a[i]);
        }
        return dp[i] = Math.max(rob(a, i - 1, dp), a[i] + rob(a, i - 2, dp));
    }

    /**
     * Maximum sum of non-adjacent elements (Tabulation)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int robTab(int[] a) {
        int n = a.length;
        if (n == 1) {
            return a[0];
        }
        int[] dp = new int[n];
        dp[0] = a[0];
        dp[1] = Math.max(dp[0], a[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], a[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }

    /**
     * Maximum sum of non-adjacent elements (Tabulation - Space Optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int robTabSpaceOpt(int[] a) {
        int n = a.length;
        if(n==1) {
            return a[0];
        }
        int alt = a[0];
        int next = Math.max(a[0], a[1]);
        for(int i=2; i<n; i++) {
            int temp = next;
            next = Math.max(next, a[i] + alt);
            alt = temp;
        }
        return next;
    }

    /**
     * Maximum sum of non-adjacent elements of a circular array (Memoization)
     *
     * Given an array of ‘N’  positive integers, and first and last elements are adjacent (circular array)
     * we need to return the maximum sum of the subsequence such that no two elements of the subsequence are adjacent elements in the array.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int rob(int[] a) {
        if (a.length == 1) {
            return a[0];
        }
        int[] dp1 = new int[a.length];
        Arrays.fill(dp1, -1);
        int[] dp2 = new int[a.length];
        Arrays.fill(dp2, -1);

        return Math.max(rob(a, a.length - 1, dp1, false), rob(a, a.length - 2, dp2, true));
    }

    private static int rob(int[] a, int i, int[] dp, boolean considerLast) {
        if (i == 0) {
            return considerLast ? a[i] : 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        if (i == 1) {
            return dp[i] = Math.max(rob(a, 0, dp, considerLast), a[i]);
        }
        return dp[i] = Math.max(rob(a, i - 1, dp, considerLast), a[i] + rob(a, i - 2, dp, considerLast));
    }

    /**
     * Maximum sum of non-adjacent elements of a circular array (Tabulation)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int robCircularTab(int[] a) {
        int n = a.length;
        if(n == 1) {
            return a[0];
        }
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = 0;
        dp2[0] = a[0];
        dp1[1] = a[1];
        dp2[1] = Math.max(a[1], dp2[0]);
        for(int i=2; i<n; i++) {
            dp1[i] = Math.max(dp1[i-1], a[i] + dp1[i-2]);
            if(i == n-1) {
                dp2[i] = dp2[i-1];
            } else {
                dp2[i] = Math.max(dp2[i-1], a[i] + dp2[i-2]);
            }

        }
        return Math.max(dp1[n-1], dp2[n-1]);
    }


}
