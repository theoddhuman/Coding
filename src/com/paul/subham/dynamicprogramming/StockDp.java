package com.paul.subham.dynamicprogramming;

import java.util.Arrays;

/**
 * 1. Buy and sell stock to maximize profit - buy/sell once
 * 2. Buy and sell stock to maximize profit - buy/sell multiple time (Memoization)
 * 3. Buy and sell stock to maximize profit - buy/sell multiple time (Tabulation)
 * 4. Buy and sell stock to maximize profit - buy/sell multiple time (Tabulation - space optimized)
 * 5. Buy and sell stock to maximize profit - buy/sell at max 2 times (Memoization)
 * 6. Buy and sell stock to maximize profit - buy/sell at max 2 times (Tabulation)
 * 7. Buy and sell stock to maximize profit - buy/sell at max 2 times (Tabulation - Space optimized)
 */
public class StockDp {
    public static void main(String[] args) {

    }

    /**
     * Buy and sell stock to maximize profit - buy/sell once
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxProfitOptimal(int[] prices) {
        int maxProfit = 0;
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - buy);
            buy = Math.min(buy, prices[i]);
        }
        return maxProfit;
    }

    /**
     * Buy and sell stock to maximize profit - buy/sell multiple time (Memoization)
     *
     * hold at most one share of the stock at any time
     *
     * TC: O(2n)
     * SC: O(2n+n)
     */
    public static int maxProfitMem(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        return maxProfit(prices,0,0,dp);
    }

    private static int maxProfit(int[] prices, int i, int buy, int[][] dp) {
        if(i==prices.length) {
            return 0;
        }
        if(dp[i][buy] != -1) {
            return dp[i][buy];
        }
        if(buy == 0) {
            return dp[i][buy] = Math.max(-prices[i]+maxProfit(prices,i+1,1,dp), maxProfit(prices,i+1,0,dp));
        } else {
            return dp[i][buy] = Math.max(prices[i]+maxProfit(prices,i+1,0,dp), maxProfit(prices,i+1,1,dp));
        }
    }

    /**
     * Buy and sell stock to maximize profit - buy/sell multiple time (Tabulation)
     *
     * TC: O(2n)
     * SC: O(2n)
     */
    public static int maxProfitTab(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n+1][2];
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++) {
                if(buy==0) {
                    dp[i][0] = Math.max(-prices[i]+dp[i+1][1], dp[i+1][0]);
                } else {
                    dp[i][1] = Math.max(prices[i]+dp[i+1][0], dp[i+1][1]);
                }
            }
        }
        return dp[0][0];
    }

    /**
     * Buy and sell stock to maximize profit - buy/sell multiple time (Tabulation - space optimized)
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public static int maxProfitTabSpaceOpt(int[] prices) {
        int n = prices.length;
        int[] pre = new int[2];
        int[] cur = new int[2];
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++) {
                if(buy==0) {
                    cur[0] = Math.max(-prices[i]+pre[1], pre[0]);
                } else {
                    cur[1] = Math.max(prices[i]+pre[0], pre[1]);
                }
            }
            System.arraycopy(cur,0,pre,0,2);
        }
        return pre[0];
    }

    /**
     * Buy and sell stock to maximize profit - buy/sell at max 2 times (Memoization)
     *
     * TC: O(6n)
     * SC: O(6n+n)
     */
    public static int maxProfit2TxnMem(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3];
        for(int i=0;i<n;i++){
            for(int j=0;j<=1;j++) {
                Arrays.fill(dp[i][j],-1);
            }
        }
        return maxProfit(prices,0,0,2,dp);
    }
    private static int maxProfit(int[] prices, int i, int buy, int count, int[][][] dp) {
        if(i==prices.length) {
            return 0;
        }
        if(count == 0) {
            return 0;
        }
        if(dp[i][buy][count] != -1) {
            return dp[i][buy][count];
        }
        if(buy == 0) {
            return dp[i][buy][count] = Math.max(-prices[i]+maxProfit(prices,i+1,1,count,dp), maxProfit(prices,i+1,0,count,dp));
        } else {
            return dp[i][buy][count] = Math.max(prices[i]+maxProfit(prices,i+1,0,count-1,dp), maxProfit(prices,i+1,1,count,dp));
        }
    }

    /**
     * Buy and sell stock to maximize profit - buy/sell at max 2 times (Tabulation)
     *
     * TC: O(6n)
     * SC: O(6n)
     */
    public static int maxProfit2TxnTab(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n+1][2][3];
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++) {
                for(int count=1;count<=2;count++) {
                    if(buy == 0) {
                        dp[i][buy][count] = Math.max(-prices[i]+dp[i+1][1][count], dp[i+1][0][count]);
                    } else {
                        dp[i][buy][count] = Math.max(prices[i]+dp[i+1][0][count-1], dp[i+1][1][count]);
                    }
                }
            }
        }
        return dp[0][0][2];
    }

    /**
     * Buy and sell stock to maximize profit - buy/sell at max 2 times (Tabulation - Space optimized)
     *
     * TC: O(6n)
     * SC: O(1)
     */
    public static int maxProfit2TxnTabSpaceOpt(int[] prices) {
        int n = prices.length;
        int[][] pre = new int[2][3];
        int[][] cur = new int[2][3];
        for(int i=n-1;i>=0;i--){
            for(int buy=0;buy<=1;buy++) {
                for(int count=1;count<=2;count++) {
                    if(buy == 0) {
                        cur[buy][count] = Math.max(-prices[i]+pre[1][count], pre[0][count]);
                    } else {
                        cur[buy][count] = Math.max(prices[i]+pre[0][count-1], pre[1][count]);
                    }
                }
            }
            for(int buy=0;buy<=1;buy++) {
                System.arraycopy(cur[buy], 0, pre[buy],0,3);
            }
        }
        return pre[0][2];
    }
}

