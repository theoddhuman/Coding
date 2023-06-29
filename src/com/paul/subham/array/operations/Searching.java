package com.paul.subham.array.operations;

import com.paul.subham.searching.BinarySearch;

import java.util.*;

/**
 * 1. Finding duplicate in an array (Using sorting)
 * 2. Finding duplicate in an array (Using hashing)
 * 3. Finding duplicate in an array (space optimized)
 * 4. Finding most appearing element in an array (using hashing)
 * 5. Finding most appearing element in an array (space optimized)
 * 6. Finding first repeating element in an array (using hashing)
 * 7. Finding missing number in an array (Sorting Technique)
 * 8. Finding missing number in an array (Hashing Technique)
 * 9. Finding missing number in an array (Summation Technique)
 * 10. Finding missing number in an array (XOR Technique)
 */
public class Searching {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,6};
        System.out.println(getMissingNumberXOR(a));
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

    /**
     * Finding missing number in an array (Sorting Technique)
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static int getMissingNumberSorting(int[] a) {
        Arrays.sort(a);
        for(int i=1; i<=a.length+1; i++) {
            int index = BinarySearch.search(a, a.length, i);
            if(index == -1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finding missing number in an array (Hashing Technique)
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static int getMissingNumberHashing(int[] a) {
        int[] count = new int[a.length+2];
        for (int j : a) {
            count[j]++;
        }
        for(int i=1; i<count.length; i++) {
            if(count[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finding missing number in an array (Summation Technique)
     * TC: O(n)
     * SC: O(1)
     */
    public static int getMissingNumberSummation(int[] a) {
        int n = a.length+1;
        int expectedSum = n * (n + 1) / 2;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        int diff = expectedSum - sum;
        return diff == 0 ? -1 : diff;
    }

    /**
     * Finding missing number in an array (XOR Technique)
     * TC: O(n)
     * SC: O(1)
     */
    public static int getMissingNumberXOR(int[] a) {
        int x = a[0];
        int y = 1;
        for(int i=1; i<a.length; i++) {
            x ^= a[i];
        }
        for(int i=2; i<=a.length+1; i++) {
            y ^= i;
        }
        return x ^ y;
    }
}