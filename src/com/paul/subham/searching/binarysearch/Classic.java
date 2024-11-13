package com.paul.subham.searching.binarysearch;

/**
 * 1. Square root of a number
 * 2. Nth root of a number using binary search
 * 3. Koko eating bananas
 * 4. Minimum days to make M bouquets
 * 5. Smallest Divisor Given a Threshold
 * 6. Capacity to Ship Packages within D Days
 * 7. Find kth missing positive number
 * 8. Find kth missing positive number (Binary Search)
 */
public class Classic {
    public static void main(String[] args) {

    }

    /**
     * Square root of a number
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int sqrtN(long n) {
        if(n==0) {
            return (int)n;
        }
        long low = 1;
        long high = n;
        long ans = 1;
        while(low <= high) {
            long mid = (low+high)/2;
            long square = mid*mid;
            if(square <= n) {
                ans = mid;
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return (int)ans;
    }

    /**
     * Nth root of a number using binary search
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int NthRoot(int n, int m) {
        if(n==0) {
            return n;
        }
        int low = 1;
        int high = m;
        while(low <= high) {
            int mid = (low+high)/2;
            int mul = (int)Math.pow(mid,n);
            if(mul == m) {
                return mid;
            }else if(mul > m){
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }

    /**
     * Koko eating bananas
     *
     * A monkey is given ‘n’ piles of bananas, whereas the 'ith' pile has ‘a[i]’ bananas.
     * An integer ‘h’ is also given, which denotes the time (in hours) for all the bananas to be eaten.
     *
     * Each hour, the monkey chooses a non-empty pile of bananas and eats ‘k’ bananas.
     * If the pile contains less than ‘k’ bananas, then the monkey consumes all the bananas and won’t eat any more bananas in that hour.
     *
     * Find the minimum number of bananas ‘k’ to eat per hour so that the monkey can eat all the bananas within ‘h’ hours.
     *
     * Input: piles = [30,11,23,4,20], h = 5
     * Output: 30
     *
     * TC: O(n + nlog(max))
     * SC: O(1)
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int max = piles[0];
        for(int i : piles) {
            max = Math.max(i, max);
        }
        int low = 1;
        int high = max;
        int ans = max;
        while(low <= high) {
            int mid = (low + high)/2;
            long reqTime = requiredTime(piles, mid);
            if(reqTime <= h) {
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ans;
    }

    private static long requiredTime(int[] piles, int k) {
        long totalTime = 0;
        for (int pile : piles) {
            totalTime += (int) Math.ceil(pile * 1.0 / k);
        }
        return totalTime;
    }

    /**
     * Minimum days to make M bouquets
     *
     * You are given 'N’ roses and you are also given an array 'arr'  where 'arr[i]'  denotes that the 'ith' rose will bloom on the 'arr[i]th' day.
     * You can only pick already bloomed roses that are adjacent to make a bouquet.
     * You are also told that you require exactly 'k' adjacent bloomed roses to make a single bouquet.
     * Find the minimum number of days required to make at least ‘m' bouquets each containing 'k' roses.
     * Return -1 if it is not possible.
     *
     * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
     * Output: 12
     *
     * TC: O(n + nlog(max-min))
     * SC: O(1)
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        int max = bloomDay[0];
        int min = bloomDay[0];
        for(int i=1; i<bloomDay.length; i++) {
            max = Math.max(max, bloomDay[i]);
            min = Math.min(min, bloomDay[i]);
        }
        int low = min;
        int high = max;
        int ans = -1;
        while(low <= high) {
            int mid = (low+high)/2;
            int boutique = boutiqueCount(bloomDay, k, mid);
            if(boutique >= m) {
                ans = mid;
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return ans;
    }

    private static int boutiqueCount(int[] bloomDay, int k, int day) {
        int bouquet = 0;
        int count = 0;
        for(int i=0; i<bloomDay.length; i++) {
            if(bloomDay[i] <= day) {
                count++;
            } else {
                bouquet += (count/k);
                count = 0;
            }
        }
        return bouquet + count/k;
    }

    /**
     * Smallest Divisor Given a Threshold
     *
     * Given an array of integers 'a' and an integer threshold, we will choose a positive integer divisor,
     * divide all the array by it, and sum the division's result.
     * Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
     *
     * Each result of the division is rounded to the nearest integer greater than or equal to that element.
     *
     * Input: a = [1,2,5,9], threshold = 6
     * Output: 5
     *
     * TC: O(n + nlog(max-min))
     * SC: O(1)
     */
    public int smallestDivisor(int[] a, int threshold) {
        int max = a[0];
        for(int i=1; i<a.length; i++) {
            max = Math.max(max, a[i]);
        }
        int low = 1;
        int high = max;
        while(low <= high) {
            int mid = (low+high)/2;
            double sum = divisorSum(a, mid);
            if(sum <= threshold) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return low;
    }

    private static double divisorSum(int[] a, int divisor) {

        int total = 0;
        for (int j : a) {
            total += (int) Math.ceil(j * 1.0 / divisor);
        }
        return total;
    }

    /**
     * Capacity to Ship Packages within D Days
     *
     * The weights of the packages are given in an array 'of weights'.
     * The packages are loaded on the conveyor belts every day in the same order as they appear in the array.
     * The loaded weights must not exceed the maximum weight capacity of the ship.
     * Find out the least-weight capacity so that you can ship all the packages within 'd' days.
     *
     * Input: weights = [3,2,2,4,1,4], days = 3
     * Output: 6
     *
     * TC: O(n + nlog(total-max))
     * SC: O(1)
     */
    public int shipWithinDays(int[] weights, int days) {
        int totalWeights = 0;
        int max = weights[0];
        for(int i=0; i<weights.length; i++) {
            totalWeights += weights[i];
            max = Math.max(max, weights[i]);
        }
        int low = max;
        int high = totalWeights;
        while(low <= high) {
            int mid = (low+high)/2;
            int totalDays = daysCount(weights, mid);
            if(totalDays <= days) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return low;
    }

    private static int daysCount(int[] weights, int capacity) {
        int total = 0;
        int days =1;
        for(int i=0; i<weights.length; i++){
            if(total + weights[i] > capacity) {
                days++;
                total = weights[i];
            } else {
                total += weights[i];
            }
        }
        return days;
    }

    /**
     * Find kth missing positive number
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int findKthPositive(int[] a, int k) {
        for(int i=0; i<a.length; i++) {
            if(a[i] <= k) {
                k++;
            } else {
                break;
            }
        }
        return k;
    }

    /**
     * Find kth missing positive number (Binary Search)
     *
     * high < low changes polarity
     * ans = a[high] + (k-missing till high)
     *     = a[high] + (k- (a[high] - (high+1))
     *     = high + k + 1
     *     = k + low
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public int findKthPositiveBinarySearch(int[] a, int k) {
        int low = 0;
        int high = a.length-1;
        while(low <= high) {
            int mid = (low+high)/2;
            int missing = a[mid] - (mid+1);
            if(missing < k) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return k+low;
    }
}
