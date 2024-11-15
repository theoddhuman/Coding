package com.paul.subham.array.operations;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Length of longest sub-array with sum K (brute-force)
 * 2. Length of longest sub-array with sum K (Using Hashing - storing prefix sum)
 * 3. Length of longest sub-array with sum K (2 Pointer - No negative number)
 * 4. Largest Sum Contiguous Sub array (Improved)
 * 5. Largest Sum Contiguous Sub array (Dynamic Programming)
 * 6. Largest Sum Contiguous Sub array (Dynamic Programming - Space optimized)
 * 7. Largest Sum Contiguous Sub array (Kadane's algorithm)
 * 8. Print Largest Sum Contiguous Sub array (Kadane's algorithm)
 * 9. Find maximum consecutive 1's in an array
 * 10. Count sub arrays sum equal to k
 * 11. Count sub arrays sum equal to k (Using Hashing - storing prefix sum)
 * 12. Maximum length of sub array with sum 0 (Prefix sum)
 * 13. Count of sub arrays with xor K
 * 14. Count of sub arrays with xor K (Prefix xor)
 */
public class SubArray {
    public static void main(String[] args) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        printMaxContiguousSum(a, a.length);
    }

    /**
     * Length of longest sub-array with sum K (brute-force)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int longestSubArrayK(int[] a, int k) {
        int n = a.length;
        int max = 0;
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=i; j<n; j++) {
                sum+= a[j];
                if(sum == k) {
                    max = Math.max(max, j-i+1);
                }
                // to handle cases like where k = 15,  a= [4, 5,6,0,0,2,3]
                if(sum > k) {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * Length of longest sub-array with sum K (Using Hashing - storing prefix sum)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int lenOfLongestSubarr(int[] a, int k) {
        int n = a.length;
        int max = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            sum += a[i];
            if(sum == k) {
                max = Math.max(max, i+1);
            }
            int rem = sum-k;
            if(map.containsKey(rem)) {
                max = Math.max(max, i-map.get(rem));
            }
            if(!map.containsKey(sum)) {
                map.put(sum,i);
            }
        }
        return max;
    }

    /**
     * Length of longest sub-array with sum K (2 Pointer - No negative number)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int lenOfLongestSubarray2Pointer(int[] a, int k) {
        // code here
        int n =a.length;
        int sum = 0;
        int max = 0;
        int left = 0;
        int right = 0;
        while(right < n) {
            sum += a[right];
            right++;
            while(sum > k) {
                sum -= a[left];
                left++;
            }
            if(sum == k) {
                max = Math.max(max, right-left);
            }
        }
        return max;
    }

    /**
     * Largest Sum Contiguous Sub array (Improved)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int maxContiguousSumImproved(int[] a, int n) {
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=i; j<n; j++) {
                sum += a[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    /**
     * Largest Sum Contiguous Sub array (Dynamic Programming)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxContiguousSumDP(int[] a, int n) {
        int[] dp = new int[n];
        dp[0] = a[0];
        int maxSum = dp[0];
        for(int i=1; i<n; i++) {
            dp[i] = Math.max(dp[i-1] + a[i], a[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    /**
     * Largest Sum Contiguous Sub array (Dynamic Programming - Space optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxContiguousSumDPSpaceOptimized(int[] a, int n) {
        int sum = a[0];
        int maxSum = sum;
        for(int i=1; i<n; i++) {
            sum = Math.max(sum + a[i], a[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    /**
     * Largest Sum Contiguous Sub array (Kadane's algorithm)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxContiguousSumKadane(int[] a, int n) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += a[i];
            maxSum = Math.max(sum, maxSum);
            if(sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    /**
     * Print Largest Sum Contiguous Sub array (Kadane's algorithm)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void printMaxContiguousSum(int[] a, int n) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int ansStart = 0;
        int ansEnd = 0;
        for(int i=0; i<n; i++) {
            sum += a[i];
            if(sum > maxSum) {
                maxSum = sum;
                ansStart = start;
                ansEnd = i;
            }
            if(sum < 0) {
                sum = 0;
                start = i+1;
            }
        }
        for(int i=ansStart; i<=ansEnd; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * Find maximum consecutive 1's in an array
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxCon = 0;
        int count=0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==1) {
                count++;
            } else {
                count=0;
            }
            maxCon = Math.max(count, maxCon);
        }
        return maxCon;
    }

    /**
     * Count sub arrays sum equal to k
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int countSubArraySum(int[] a, int k) {
        int n = a.length;
        int count = 0;
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=i;j<n;j++) {
                sum += a[j];
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Count sub arrays sum equal to k (Using Hashing - storing prefix sum)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int countSubArraySumPrefixSum(int[] a, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        for(int i=0; i<a.length; i++) {
            sum += a[i];
            if(sum == k) {
                count++;
            }
            int rem = sum - k;
            if(map.containsKey(rem)) {
                count += map.get(rem);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * Maximum length of sub array with sum 0 (Prefix sum)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int getLongestZeroSumSubarrayLength(int[] a){
        Map<Integer, Integer> prefixSum = new HashMap<>();
        int sum = 0;
        int max = 0;
        for(int i=0; i<a.length; i++) {
            sum += a[i];
            if(sum == 0) {
                max = i+1;
            } else {
                if(prefixSum.containsKey(sum)) {
                    max = Math.max(max, i-prefixSum.get(sum));
                } else {
                    prefixSum.put(sum, i);
                }
            }
        }
        return max;
    }

    /**
     * Count of sub arrays with xor K
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int subarraysWithSumK(int []a, int k) {
        int n = a.length;
        int count = 0;
        for(int i=0; i<n; i++) {
            int xor = 0;
            for(int j=i; j<n; j++) {
                xor ^= a[j];
                if(xor == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Count of sub arrays with xor K (Prefix xor)
     *
     * x ^ k = xor => x^k^k = xor ^ k => x = xor ^ k
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int subarraysWithSumKPrefix(int []a, int k) {
        int n = a.length;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int xor = 0;
        for(int i=0; i<n; i++) {
            xor ^= a[i];
            int x = xor ^ k;
            if(map.containsKey(x)) {
                count += map.get(x);
            }
            map.put(xor, map.getOrDefault(xor, 0)+1);
        }
        return count;
    }
}
