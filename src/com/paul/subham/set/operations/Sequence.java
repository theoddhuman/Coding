package com.paul.subham.set.operations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. Longest consecutive sequence (Using linear search)
 * 2. Longest consecutive sequence (Sorting)
 * 3. Longest consecutive sequence (Using set)
 */
public class Sequence {
    public static void main(String[] args) {

    }

    /**
     * Longest consecutive sequence (Using linear search)
     *
     * [100,4,200,1,3,2] -> 4 (1,2,3,4)
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public static int longestConsecutive(int[] a) {
        int n = a.length;
        if(n==0) {
            return 0;
        }
        int longest = 1;
        for(int i=0; i<n; i++) {
            int x = a[i];
            int count=1;
            while(search(a, x+1)) {
                x++;
                count++;
            }
            longest = Math.max(count, longest);
        }
        return longest;
    }

    private static boolean search(int[] a, int x) {
        for(int i=0; i<a.length; i++) {
            if(a[i] == x) {
                return true;
            }
        }
        return false;
    }

    /**
     * Longest consecutive sequence (Sorting)
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public int longestConsecutiveSorting(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int lastSmaller = Integer.MIN_VALUE;
        int count = 0;
        int longest = 0;
        for(int i=0; i<n; i++) {
            if(a[i] - 1 == lastSmaller) {
                count++;
                lastSmaller = a[i];
            } else if(lastSmaller != a[i]){
                count=1;
                lastSmaller = a[i];
            }
            longest = Math.max(count, longest);
        }
        return longest;
    }

    /**
     * Longest consecutive sequence (Using set)
     *
     * TC: O(n+2n), n for inserting in set, 2n for traversing set
     * SC: O(n)
     */
    public int longestConsecutiveUsingSet(int[] a) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n; i++) {
            set.add(a[i]);
        }
        int longest = 0;
        for(Integer i : set) {
            if(!set.contains(i-1)) {
                int count = 1;
                int x = i;
                while(set.contains(x+1)) {
                    x++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }
}
