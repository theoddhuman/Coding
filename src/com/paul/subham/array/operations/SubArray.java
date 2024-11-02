package com.paul.subham.array.operations;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Length of longest sub-array with sum K (brute-force)
 * 2. Length of longest sub-array with sum K (Using Hashing - storing prefix sum)
 * 3. Length of longest sub-array with sum K (2 Pointer - No negative number)
 */
public class SubArray {
    public static void main(String[] args) {
        int[] a = {1, -1, 5, -2, 3};
        System.out.println(lenOfLongestSubarray2Pointer(a, 3));
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
}
