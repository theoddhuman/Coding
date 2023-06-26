package com.paul.subham.array.operations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. Find duplicate in an array (Using sorting)
 * 2. Find duplicate in an array (Using hashing)
 * 3. Find duplicate in an array (space optimized)
 */
public class Searching {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,3,5};
        System.out.println(findDuplicateAllPositive(a));
    }

    /**
     * Find duplicate in an array (Using sorting)
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static boolean findDuplicateUsingSorting(int[] a) {
        Arrays.sort(a);
        for(int i=0; i<a.length-1; i++) {
            if(a[i] == a[i+1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find duplicate in an array (Using hashing)
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean findDuplicateUsingHashing(int[] a) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<a.length; i++) {
            if(set.contains(a[i])) {
                return true;
            } else {
                set.add(a[i]);
            }
        }
        return false;
    }

    /**
     * Find duplicate in an array (space optimized)
     * All positive elements and ranges from 0 to n-1
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean findDuplicateAllPositive(int[] a) {
        for(int i=0; i<a.length; i++) {
            if(a[Math.abs(a[i])] < 0) {
                return true;
            }
            a[Math.abs(a[i])] = -a[Math.abs(a[i])];
        }
        return false;
    }
}
