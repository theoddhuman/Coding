package com.paul.subham.array.operations;

import java.util.*;

/**
 * 1. Majority element of an array
 * 2. Majority element of an array (Using binary search tree)
 * 3. Majority element of an array (Using sorting)
 * 4. Majority element of an array (Moore's Voting algorithm)
 * 5. Majority element of an array (Using hashing)
 * 6. Majority element of an array (Using bit manipulation)
 * 7. Majority n/3 elements of an array (Hashing)
 * 8. Majority n/3 elements of an array (Moore's Voting algorithm)
 */
public class Majority {
    public static void main(String[] args) {

    }

    /**
     * Majority element of an array
     * <p>
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int majority(int[] a) {
        int maxCount = Integer.MIN_VALUE;
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            int count = 0;
            for (int j = 0; j < a.length; j++) {
                if (a[i] == a[j]) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                max = a[i];
            }
        }
        if (maxCount > a.length / 2) {
            return max;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority element of an array (Using binary search tree)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static int majorityBST(int[] a) {
        BinarySearchFrequencyTree bst = new BinarySearchFrequencyTree();
        for (int i = 0; i < a.length; i++) {
            bst.insert(a[i]);
        }
        return inOrder(bst.root, a.length);
    }

    private static int inOrder(BSFrequencyNode node, int n) {
        if (node != null) {
            int data = inOrder(node.left, n);
            if (data > Integer.MIN_VALUE) {
                return data;
            }
            if (node.frequency > n / 2) {
                return node.data;
            }
            return inOrder(node.right, n);
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority element of an array (Using sorting)
     * <p>
     * TC: O(nlogn), average case
     * SC: O(n)
     */
    public static int majorityUsingSorting(int[] a) {
        Arrays.sort(a);
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count > a.length / 2) {
                return a[i];
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority element of an array (Moore's Voting algorithm)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public static int majorityMoore(int[] a) {
        int candidate = getCandidate(a);
        return isMajority(a, candidate) ? candidate : Integer.MIN_VALUE;
    }

    private static boolean isMajority(int[] a, int candidate) {
        int count = 0;
        for (int j : a) {
            if (j == candidate) {
                count++;
            }
            if (count > a.length / 2) {
                return true;
            }
        }
        return false;
    }

    private static int getCandidate(int[] a) {
        int majorityIndex = 0;
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[majorityIndex]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                majorityIndex = i;
                count = 1;
            }
        }
        return a[majorityIndex];
    }

    /**
     * Majority element of an array (Using hashing)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static int majorityHashing(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!map.containsKey(a[i])) {
                map.put(a[i], 1);
            } else {
                map.put(a[i], map.get(a[i]) + 1);
            }
            if (map.get(a[i]) > a.length / 2) {
                return a[i];
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority element of an array (Using bit manipulation)
     * <p>
     * TC: O(n*k), k is number of bits
     * SC: O(1)
     */
    public static int majorityBitManipulation(int[] a) {
        int majority = 0;
        for (int i = 0; i < 32; i++) {
            int numZeros = 0;
            int numOnes = 0;
            for (int num : a) {
                if (((num >> i) & 1) == 1) {
                    numOnes++;
                } else {
                    numZeros++;
                }
            }
            if (numOnes > numZeros) {
                majority |= (1 << i);
            }
        }
        int count = 0;
        for (int num : a) {
            if (num == majority) {
                count++;
            }
            if (count > a.length / 2) {
                return majority;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority n/3 elements of an array (Hashing)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static List<Integer> majorityElement(int[] a) {
        int n = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        double majorityThreshold = Math.floor(n/3)+1;

        List<Integer> majority = new ArrayList<>();
        for(int i=0; i<n; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            if(map.get(a[i]) == majorityThreshold) {
                majority.add(a[i]);
            }
        }
        return majority;
    }

    /**
     * Majority n/3 elements of an array (Moore's Voting algorithm)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public static  List<Integer> majorityElementMoore(int[] a) {
        int n = a.length;
        int count1 = 0;
        int count2 = 0;
        int element1= Integer.MIN_VALUE;
        int element2 = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            if(count1 == 0 && element2 != a[i]) {
                count1 = 1;
                element1 = a[i];
            } else if (count2 == 0 && element1 != a[i]) {
                count2 = 1;
                element2 = a[i];
            } else if(element1 == a[i]) {
                count1++;
            } else if(element2 == a[i]) {
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for(int i=0; i<n; i++) {
            if(a[i] == element1) {
                count1++;
            }
            if(a[i] == element2) {
                count2++;
            }
        }
        double n3 = Math.floor(n/3)+1;
        List<Integer> res = new ArrayList<>();
        if(count1 >= n3) {
            res.add(element1);
        }
        if(count2 >= n3) {
            res.add(element2);
        }
        Collections.sort(res);
        return res;
    }
}
