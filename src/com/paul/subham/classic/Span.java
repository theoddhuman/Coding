package com.paul.subham.classic;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Calculate span (Naive approach)
 * 2. Calculate span (Using stack)
 * 3. Calculate span (Using dynamic programming)
 */
public class Span {
    public static void main(String[] args) {
        int[] price = {100, 80, 60, 70, 60, 75, 85};
        int[] span = calculateSpanByDp(price);
        Arrays.stream(span).forEach(s -> System.out.print(s + " "));

    }

    /**
     * The stock span problem is a financial problem where we have a series of N daily price quotes for a stock and
     * we need to calculate the span of the stock’s price for all N days.
     * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day,
     * for which the price of the stock on the current day is less than or equal to its price on the given day
     *
     * Input: N = 7, price[] = [100 80 60 70 60 75 85]
     * Output: 1 1 1 2 1 4 6
     *
     * Calculate span (Naive approach)
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int[] calculateSpan(int[] price) {
        int[] span = new int[price.length];
        span[0]= 1;
        for(int i = 1; i< price.length; i++) {
            span[i] = 1;
            for(int j=i-1; j>=0 && price[i] >= price[j]; j--) {
                span[i]++;
            }
        }
        return span;
    }

    /**
     * Calculate span (Using stack)
     * TC: O(n), every element can be added and removed from the stack at most once. So, in the worst case scenario, 2n.
     * SC: O(n)
     */
    public static int[] calculateSpanByStack(int[] price) {
        int[] span = new int[price.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i=0; i<price.length; i++) {
            while(!stack.isEmpty() && price[i] >= price[stack.peek()]) {
                stack.pop();
            }
            span[i] = stack.isEmpty() ? i+1 : i-stack.peek();
            stack.push(i);
        }
        return span;
    }

    /**
     * Calculate span (Using dynamic programming)
     * TC: O(n), every element can be traced at most twice. So, in the worst case scenario, 2n.
     * SC: O(1)
     */
    public static int[] calculateSpanByDp(int[] price) {
        int[] span = new int[price.length];
        for(int i=0; i< price.length; i++) {
            int counter = 1;
            while((i-counter) >= 0 && price[i] >= price[i-counter]) {
                counter += span[i-counter];
            }
            span[i] = counter;
        }
        return span;
    }
}
