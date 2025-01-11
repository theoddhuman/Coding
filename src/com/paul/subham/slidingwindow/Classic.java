package com.paul.subham.slidingwindow;

/**
 * 1. Maximum points you can obtain from cards
 */
public class Classic {
    public static void main(String[] args) {

    }

    /**
     * Maximum points you can obtain from cards
     *
     * There are several cards arranged in a row, and each card has an associated number of points.
     * The points are given in the integer array cardPoints.
     * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
     * Your score is the sum of the points of the cards you have taken.
     * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
     *
     * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
     * Output: 12   [1,5,6,1]
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxScore(int[] a, int k) {
        int n = a.length;
        int lsum = 0;
        for(int i=0; i<k && i<n; i++) {
            lsum += a[i];
        }
        int max = lsum;
        int rsum = 0;
        int right = n-1;
        for(int i=k-1; i>=0; i--) {
            lsum -= a[i];
            rsum += a[right];
            right--;
            max = Math.max(max, lsum+rsum);
        }
        return max;
    }
}
