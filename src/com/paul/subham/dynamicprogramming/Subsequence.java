package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 *
 * 1. Subset sum equal to k (Memoization)
 * 2. Subset sum equal to k (Tabulation)
 * 3. Subset sum equal to k (Tabulation - Space Optimized)
 * 4. Partition equal subset sum (Tabulation - Space Optimized)
 * 5. Partition Set Into 2 Subsets With Min Absolute Sum Diff (Tabulation - space optimized)
 * 6. Count subsets with sum k (Memoization)
 * 7. Count subsets with sum k (Tabulation)
 * 8. Count subsets with sum k (Tabulation - Space optimized)
 */
public class Subsequence {
    public static void main(String[] args) {

    }

    /**
     * Subset sum equal to k (Memoization)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subSetSumK(int[] a, int k) {
        int n = a.length;
        Boolean[][] dp = new Boolean[n+1][k+1];
        return subSetSumK(a, n, k, dp);
    }

    private static boolean subSetSumK(int[] a, int i, int target, Boolean[][] dp) {
        if(target == 0) {
            return true;
        }
        if(i==0) {
            return false;
        }
        if(dp[i][target] != null) {
            return dp[i][target];
        }
        if(a[i-1] > target) {
            return dp[i][target] = subSetSumK(a, i-1, target, dp);
        }
        return dp[i][target] = subSetSumK(a, i-1, target-a[i-1], dp) || subSetSumK(a, i-1, target, dp);
    }

    /**
     * Subset sum equal to k (Tabulation)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subsetSumToKTab(int[] a, int k){
        int n = a.length;
        boolean[][] dp = new boolean[n+1][k+1];
        for(int i=0; i<=n; i++) {
            dp[i][0] = true;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                if(a[i-1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-a[i-1]];
                }
            }
        }
        return dp[n][k];
    }

    /**
     * Subset sum equal to k (Tabulation - Space Optimized)
     *
     * TC: O(nk)
     * SC: O(nk)
     */
    public static boolean subsetSumToK(int[] a, int k){
        int n = a.length;
        boolean[] pre = new boolean[k+1];
        boolean[] cur = new boolean[k+1];
        pre[0] = cur[0] = true;

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=k; j++) {
                if(a[i-1] > j) {
                    cur[j] = pre[j];
                } else {
                    cur[j] = pre[j] || pre[j-a[i-1]];
                }
            }
            System.arraycopy(cur, 0, pre, 0, k + 1);
        }
        return pre[k];
    }

    /**
     * Partition equal subset sum (Tabulation - Space Optimized)
     *
     * TC: O(nk)
     * SC: O(k)
     */
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += nums[i];
        }
        if(sum % 2 == 1) {
            return false;
        }
        return subsetSumToK(nums, sum/2);
    }

    /**
     * Partition Set Into 2 Subsets With Min Absolute Sum Diff (Tabulation - space optimized)
     *
     * TC: O(nK)
     * SC: O(k)
     */
    public static int minSubsetSumDifference(int[] a, int n) {
        int sum = 0;
        for(int i=0; i<a.length; i++) {
            sum+=a[i];
        }
        boolean[] pre = new boolean[sum+1];
        boolean[] cur = new boolean[sum+1];
        pre[0] = true;
        cur[0] = true;
        for(int i=0; i<n; i++) {
            for(int j=1; j<=sum; j++) {
                if(a[i] > j) {
                    cur[j] = pre[j];
                } else {
                    cur[j] = pre[j] || pre[j-a[i]];
                }
            }
            for(int j=0; j<=sum; j++) {
                pre[j] = cur[j];
            }
        }
        int min = sum;
        for(int i=0; i<=sum; i++) {
            if(pre[i]) {
                int sum2 = sum-i;
                min = Math.min(min, Math.abs(sum2-i));
            }
        }
        return min;
    }

    /**
     * Count subsets with sum k (Memoization)
     *
     * TC: O(nK)
     * SC: O(nk)
     */
    public static int findWays(int[] a, int k) {
        int n = a.length;
        int[][] dp = new int[n+1][k+1];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return findWays(a, n, k, dp);
    }

    private static int findWays(int[] a, int i, int target, int[][] dp) {
        //need to go till last index for cases where starting elements of the array are 0, [0,0,1,2,3], k=6
        if(target == 0 && i==0) {
            return 1;
        }
        if(i==0) {
            return 0;
        }
        if(dp[i][target] != -1) {
            return dp[i][target];
        }

        if(a[i-1] > target) {
            return dp[i][target] = findWays(a, i-1, target, dp)%1000000007;
        }
        return dp[i][target] = (findWays(a, i-1, target, dp) + findWays(a, i-1, target-a[i-1], dp))%1000000007;
    }

    /**
     * Count subsets with sum k (Tabulation)
     *
     * TC: O(nK)
     * SC: O(nk)
     */
    public static int findWaysTab(int a[], int k) {
        int n = a.length;
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 1;
        for(int i=1;i<=n;i++) {
            for(int j=0; j<=k;j++) {
                if(a[i-1] > j) {
                    dp[i][j] = dp[i-1][j] % 1000000007;
                } else {
                    dp[i][j] = (dp[i-1][j] + dp[i-1][j-a[i-1]]) % 1000000007;
                }
            }
        }
        return dp[n][k];
    }

    /**
     * Count subsets with sum k (Tabulation - Space optimized)
     *
     * TC: O(nk)
     * SC: O(k)
     */
    public static int findWaysTabSpace(int[] a, int k) {
        int n = a.length;
        int[] pre = new int[k+1];
        int[] cur = new int[k+1];
        pre[0] = 1;
        for(int i=1;i<=n;i++) {
            for(int j=0; j<=k;j++) {
                if(a[i-1] > j) {
                    cur[j] = pre[j]%1000000007;
                } else {
                    cur[j] = (pre[j] + pre[j-a[i-1]])%1000000007;
                }
            }
            System.arraycopy(cur, 0, pre, 0, k+1);
        }
        return pre[k];
    }
}
