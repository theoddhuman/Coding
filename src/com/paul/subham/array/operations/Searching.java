package com.paul.subham.array.operations;

import java.util.*;

/**
 * 1. Finding duplicate in an array (Using sorting)
 * 2. Finding duplicate in an array (Using hashing)
 * 3. Finding duplicate in an array (space optimized)
 * 4. Finding most appearing element in an array (using hashing)
 * 5. Finding most appearing element in an array (space optimized)
 * 6. Finding first repeating element in an array (using hashing)
 */
public class Searching {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,2, 3,5, 6, 4, 3, 2, 1, 2,2};
        System.out.println(getFirstRepeatingElement(a));
    }

    /**
     * Finding duplicate in an array (Using sorting)
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
     * Finding duplicate in an array (Using hashing)
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
     * Finding duplicate in an array (space optimized)
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

    /**
     * Finding most appearing element in an array (using hashing)
     * TC: O(n)
     * SC: O(n)
     */
    public static int getMostAppearingElement(int[] a) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i=0; i<a.length; i++) {
            if(countMap.containsKey(a[i])) {
                countMap.put(a[i],countMap.get(a[i])+1);
            } else {
                countMap.put(a[i], 1);
            }
        }
        Optional<Map.Entry<Integer, Integer>> max = countMap.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue));
        return max.get().getKey();
    }

    /**
     * Finding most appearing element in an array (space optimized)
     * All positive elements and ranges from 0 to n-1
     * TC: O(n)
     * SC: O(1)
     */
    public static int getMostAppearingElementSpaceOptimized(int[] a) {
        int n = a.length;
        for(int i=0; i<n; i++) {
            a[a[i]%n] += n;
        }
        int max=0;
        int maxElement=0;
        for(int i=0; i<n; i++) {
            if(a[i]/n > max) {
                max = a[i] / n;
                maxElement = i;
            }
        }
        return maxElement;
    }

    /**
     * Finding first repeating element in an array (using hashing)
     * TC: O(n)
     * SC: O(n)
     */
    public static int getFirstRepeatingElement(int[] a){
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<a.length; i++) {
            if(set.contains(a[i])) {
                return a[i];
            } else {
                set.add(a[i]);
            }
        }
        return -1;
    }
}
