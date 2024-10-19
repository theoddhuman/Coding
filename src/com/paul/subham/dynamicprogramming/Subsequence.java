package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * 1. Subset sum equal to k (Memoization)
 * 2. Subset sum equal to k (Tabulation)
 * 3. Subset sum equal to k (Tabulation - Space Optimized)
 * 4. Partition equal subset sum (Tabulation - Space Optimized)
 * 5. Partition Set Into 2 Subsets With Min Absolute Sum Diff (Tabulation - space optimized)
 * 6. Count subsets with sum k (Memoization)
 * 7. Count subsets with sum k (Tabulation)
 * 8. Count subsets with sum k (Tabulation - Space optimized)
 * 9. Count partitions with given difference (Tabulation - Space optimized)
 * 10. 0/1 Knapsack Problem (Memoization)
 * 11. 0/1 Knapsack Problem (Tabulation)
 * 12. 0/1 Knapsack Problem (Tabulation - Space optimization)
 * 13. Coin change - Minimum coins (Memoization - take/not-take approach)
 * 14. Coin change - Minimum coins (Tabulation - take/not-take approach)
 * 15. Coin change - Minimum coins (Tabulation - take/not-take approach)(Striver's)
 * 16. Coin change - Minimum coins (Tabulation - space optimized - take/not-take approach)
 * 17. Coin change - Minimum coins (Memoization - Iteration approach)
 * 18. Coin change - Minimum coins (Tabulation - Iteration approach)
 * 19. Target sum - no of ways(Tabulation - Space optimized)
 */
public class Subsequence {
    public static void main(String[] args) {
        int[] weight = {20, 24, 36, 40, 42};
        int[] value = {12, 35, 41, 25, 32};
        System.out.println(knapsackMemoization(weight, value, 100, 5));
    }

    /**
     * Subset sum equal to k (Memoization)
     * <p>
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subSetSumK(int[] a, int k) {
        int n = a.length;
        Boolean[][] dp = new Boolean[n + 1][k + 1];
        return subSetSumK(a, n, k, dp);
    }

    private static boolean subSetSumK(int[] a, int i, int target, Boolean[][] dp) {
        if (target == 0) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        if (dp[i][target] != null) {
            return dp[i][target];
        }
        if (a[i - 1] > target) {
            return dp[i][target] = subSetSumK(a, i - 1, target, dp);
        }
        return dp[i][target] = subSetSumK(a, i - 1, target - a[i - 1], dp) || subSetSumK(a, i - 1, target, dp);
    }

    /**
     * Subset sum equal to k (Tabulation)
     * <p>
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subsetSumToKTab(int[] a, int k) {
        int n = a.length;
        boolean[][] dp = new boolean[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (a[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - a[i - 1]];
                }
            }
        }
        return dp[n][k];
    }

    /**
     * Subset sum equal to k (Tabulation - Space Optimized)
     * <p>
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subsetSumToK(int[] a, int k) {
        int n = a.length;
        boolean[] pre = new boolean[k + 1];
        boolean[] cur = new boolean[k + 1];
        pre[0] = cur[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (a[i - 1] > j) {
                    cur[j] = pre[j];
                } else {
                    cur[j] = pre[j] || pre[j - a[i - 1]];
                }
            }
            System.arraycopy(cur, 0, pre, 0, k + 1);
        }
        return pre[k];
    }

    /**
     * Partition equal subset sum (Tabulation - Space Optimized)
     * <p>
     * TC: O(nk)
     * SC: O(k)
     */
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        return subsetSumToK(nums, sum / 2);
    }

    /**
     * Partition Set Into 2 Subsets With Min Absolute Sum Diff (Tabulation - space optimized)
     * <p>
     * TC: O(nK)
     * SC: O(k)
     */
    public static int minSubsetSumDifference(int[] a, int n) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        boolean[] pre = new boolean[sum + 1];
        boolean[] cur = new boolean[sum + 1];
        pre[0] = true;
        cur[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (a[i] > j) {
                    cur[j] = pre[j];
                } else {
                    cur[j] = pre[j] || pre[j - a[i]];
                }
            }
            for (int j = 0; j <= sum; j++) {
                pre[j] = cur[j];
            }
        }
        int min = sum;
        for (int i = 0; i <= sum; i++) {
            if (pre[i]) {
                int sum2 = sum - i;
                min = Math.min(min, Math.abs(sum2 - i));
            }
        }
        return min;
    }

    /**
     * Count subsets with sum k (Memoization)
     * <p>
     * TC: O(nK)
     * SC: O(nk)
     */
    public static int findWays(int[] a, int k) {
        int n = a.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return findWays(a, n, k, dp);
    }

    private static int findWays(int[] a, int i, int target, int[][] dp) {
        //need to go till last index for cases where starting elements of the array are 0, [0,0,1,2,3], k=6
        if (target == 0 && i == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        if (dp[i][target] != -1) {
            return dp[i][target];
        }

        if (a[i - 1] > target) {
            return dp[i][target] = findWays(a, i - 1, target, dp) % 1000000007;
        }
        return dp[i][target] = (findWays(a, i - 1, target, dp) + findWays(a, i - 1, target - a[i - 1], dp)) % 1000000007;
    }

    /**
     * Count subsets with sum k (Tabulation)
     * <p>
     * TC: O(nK)
     * SC: O(nk)
     */
    public static int findWaysTab(int a[], int k) {
        int n = a.length;
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (a[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j] % 1000000007;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - a[i - 1]]) % 1000000007;
                }
            }
        }
        return dp[n][k];
    }

    /**
     * Count subsets with sum k (Tabulation - Space optimized)
     * <p>
     * TC: O(nk)
     * SC: O(k)
     */
    public static int findWaysTabSpace(int[] a, int k) {
        int n = a.length;
        int[] pre = new int[k + 1];
        int[] cur = new int[k + 1];
        pre[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (a[i - 1] > j) {
                    cur[j] = pre[j] % 1000000007;
                } else {
                    cur[j] = (pre[j] + pre[j - a[i - 1]]) % 1000000007;
                }
            }
            System.arraycopy(cur, 0, pre, 0, k + 1);
        }
        return pre[k];
    }

    /**
     * Count partitions with given difference (Tabulation - Space optimized)
     * <p>
     * a = [5,2,6,4], d = 3   -> [5,2] [6,4]
     * S1 -> sum of first partition
     * S2 -> sum of second partition
     * S1 + S2 = totalSum
     * S1 - S2 = d
     * S1 = (totalSum + d)/2
     * <p>
     * TC: O(nk)
     * SC: O(k)
     */
    public static int countPartitions(int n, int d, int[] a) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum < d || (sum + d) % 2 == 1) {
            return 0;
        }
        return findWaysTabSpace(a, (sum + d) / 2);
    }

    /**
     * 0/1 Knapsack Problem (Memoization)
     * <p>
     * Given n items where each item has some weight and value associated with it and also given a bag with capacity W,
     * The task is to put the items into the bag such that the sum of values associated with them is the maximum possible.
     * weight = [20,24,36,40,42]
     * value = [12,35,41,25,32], w=100
     * maximum Possible value = 101 ( weight 24 + 36 + 40)
     * <p>
     * TC: O(nw)
     * SC: O(nw)
     */
    public static int knapsackMemoization(int[] weight, int[] value, int w, int n) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return knapsackUtil(weight, value, w, n, dp);
    }

    private static int knapsackUtil(int[] weight, int[] value, int w, int i, int[][] dp) {
        if (i == 0 || w == 0) {
            return 0;
        }
        if (dp[i][w] != -1) {
            return dp[i][w];
        }
        if (weight[i - 1] > w) {
            return dp[i][w] = knapsackUtil(weight, value, w, i - 1, dp);
        }
        return dp[i][w] = Math.max(knapsackUtil(weight, value, w, i - 1, dp),
                value[i - 1] + knapsackUtil(weight, value, w - weight[i - 1], i - 1, dp));
    }

    /**
     * 0/1 Knapsack Problem (Tabulation)
     * <p>
     * TC: O(nw)
     * SC: O(nw)
     */
    public static int knapsackTab(int[] weight, int[] value, int w, int n) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], value[i - 1] + dp[i - 1][j - weight[i - 1]]);
                }
            }

        }
        return dp[n][w];
    }

    /**
     * 0/1 Knapsack Problem (Tabulation - Space optimization)
     * <p>
     * TC: O(nw)
     * SC: O(w)
     */
    public static int knapsackTabSpaceOpt(int[] weight, int[] value, int w, int n) {
        int[] pre = new int[w + 1];
        int[] cur = new int[w + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (weight[i - 1] > j) {
                    cur[j] = pre[j];
                } else {
                    cur[j] = Math.max(pre[j], value[i - 1] + pre[j - weight[i - 1]]);
                }
            }
            System.arraycopy(cur, 0, pre, 0, w + 1);
        }
        return pre[w];
    }

    /**
     * Coin change - Minimum coins (Memoization - take/not-take approach)
     * <p>
     * Given an array coins[] of size N and a target value sum, where coins[i] represents the coins of different denominations.
     * You have an infinite supply of each of coins. The task is to find minimum number of coins required to make the given value sum.
     * If it’s not possible to make a change, print -1
     * <p>
     * TC: O(nk)
     * SC: O(nk)
     */
    public static int minCoins(int[] a, int k) {
        int n = a.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int min = minCoins(a, n, k, dp);
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    private static int minCoins(int[] a, int i, int target, int[][] dp) {
        if (target == 0) {
            return 0;
        }
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        if (dp[i][target] != -1) {
            return dp[i][target];
        }
        int notTake = minCoins(a, i - 1, target, dp);
        if (a[i - 1] > target) {
            return dp[i][target] = notTake;
        }
        int x = minCoins(a, i, target - a[i - 1], dp);
        int take;
        if (x == Integer.MAX_VALUE) {
            take = Integer.MAX_VALUE;
        } else {
            take = 1 + x;
        }
        return dp[i][target] = Math.min(take, notTake);
    }

    /**
     * Coin change - Minimum coins (Tabulation - take/not-take approach)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static int coinChangeTab(int[] a, int k) {
        int n = a.length;
        int[][] dp = new int[n+1][k+1];
        for(int i=1; i<=k; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for(int i=1; i<=n; i++) {
            for(int j =1; j<=k; j++) {
                int notTake = dp[i-1][j];
                if(a[i-1] > j) {
                    dp[i][j] = notTake;
                } else {
                    int x = dp[i][j-a[i-1]];
                    int take;
                    if(x == Integer.MAX_VALUE) {
                        take = Integer.MAX_VALUE;
                    } else {
                        take = 1 + x;
                    }
                    dp[i][j] = Math.min(take, notTake);
                }
            }
        }
        if(dp[n][k] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[n][k];
    }

    /**
     * Coin change - Minimum coins (Tabulation - take/not-take approach)(Striver's)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static int coinChangeTabStriver(int[] a, int k) {
        int n = a.length;
        int[][] dp = new int[n][k+1];
        for(int i=0; i<=k; i++) {
            if(i % a[0] == 0) {
                dp[0][i] = i/a[0];
            } else {
                dp[0][i] = Integer.MAX_VALUE;
            }
        }
        for(int i=1; i<n; i++) {
            for(int j =1; j<=k; j++) {
                int notTake = dp[i-1][j];
                if(a[i] > j) {
                    dp[i][j] = notTake;
                } else {
                    int x = dp[i][j-a[i]];
                    int take;
                    if(x == Integer.MAX_VALUE) {
                        take = Integer.MAX_VALUE;
                    } else {
                        take = 1 + x;
                    }
                    dp[i][j] = Math.min(take, notTake);
                }
            }
        }
        if(dp[n-1][k] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[n-1][k];
    }

    /**
     * Coin change - Minimum coins (Tabulation - space optimized - take/not-take approach)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static int coinChange(int[] a, int k) {
        int n = a.length;
        int[] pre = new int[k+1];
        int[] cur = new int[k+1];
        for(int i=1; i<=k; i++) {
            pre[i] = Integer.MAX_VALUE;
        }
        for(int i=1; i<=n; i++) {
            for(int j =1; j<=k; j++) {
                int notTake = pre[j];
                if(a[i-1] > j) {
                    cur[j] = notTake;
                } else {
                    int x = cur[j-a[i-1]];
                    int take;
                    if(x == Integer.MAX_VALUE) {
                        take = Integer.MAX_VALUE;
                    } else {
                        take = 1 + x;
                    }
                    cur[j] = Math.min(take, notTake);
                }
            }
            System.arraycopy(cur, 0, pre, 0, k+1);
        }
        if(pre[k] == Integer.MAX_VALUE) {
            return -1;
        }
        return pre[k];
    }

    /**
     * Coin change - Minimum coins (Memoization - Iteration approach)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static int minCoinMemoization(int[] a, int sum) {
        int[] dp = new int[sum+1];
        Arrays.fill(dp, -1);
        int coinCount = minCoinUtil(a, sum, dp);
        return  coinCount == Integer.MAX_VALUE? -1: coinCount;
    }

    private static int minCoinUtil(int[] a, int sum, int[] dp) {
        if(sum == 0) {
            return 0;
        }
        if(dp[sum] != -1) {
            return dp[sum];
        }
        int count = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            if(a[i] <= sum) {
                int c = minCoinUtil(a, sum-a[i], dp);
                if(c != Integer.MAX_VALUE) {
                    count = Math.min(count, 1 + c);
                }
            }
        }
        return dp[sum] = count;
    }

    /**
     * Coin change - Minimum coins (Tabulation - Iteration approach)
     *
     * TC: O(nk)
     * SC: O(k)
     */
    public static int minCoinTab(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=k; i++) {
            for(int j = 0; j <n; j++) {
                if(a[j] <= i) {
                    int c = dp[i-a[j]];
                    if(c != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], 1 + c);
                    }
                }
            }
        }
        if(dp[k] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[k];
    }

    /**
     * Target sum - no of ways(Tabulation - Space optimized)
     *
     * You are given an integer array a[] and an integer target.
     * To build an expression out of the array by adding one of the symbols '+' and '-' before each integer in the array
     * and then concatenate all the integers.
     *
     * For example, if a = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
     * Return the number of different expressions that can be built, which evaluates to target.
     *
     * a = [1,2,3,1], target = 3
     * two ways, -1+2+3-1 = (2+3) - (1+1)
     * or 1-2+3+1 = (1+1+3) - (2)
     * S1 - S2 = target
     * S1 + S2 = totalSum
     * S1 = (target + totalSum)/2
     *
     * TC: O(nk)
     * SC: O(k)
     */
    public static int findTargetSumWays(int[] a, int target) {
        int n = a.length;
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += a[i];
        }
        target = Math.abs(target);
        if(sum < target || (sum+target) % 2 == 1) {
            return 0;
        }
        return findWaysTabSpace(a, (sum+target)/2);
    }
}
