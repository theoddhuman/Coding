package com.paul.subham.classic;

/**
 * @author subham.paul
 *
 * 1. Buy and sell stock to maximize profit (Efficient)
 * 2. Buy and sell stock to maximize profit (Efficient)
 *
 */
public class Stock {
    public static void main(String[] args) {

    }

    /**
     * Buy and sell stock to maximize profit
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int maxProfit(int[] a) {
        int maxProfit = 0;
        for(int i=0; i<a.length-1; i++) {
            for(int j=i+1; j<a.length; j++) {
                maxProfit = Math.max(a[j]-a[i], maxProfit);
            }
        }
        return maxProfit;
    }

    /**
     * Buy and sell stock to maximize profit (Efficient)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int maxProfitOptimal(int[] prices) {
        int maxProfit = 0;
        int buy = prices[0];
        for(int i=1; i<prices.length; i++) {
            if(prices[i] > buy) {
                maxProfit = Math.max(maxProfit, prices[i]-buy);
            }
            buy = Math.min(buy, prices[i]);
        }
        return maxProfit;
    }
}
