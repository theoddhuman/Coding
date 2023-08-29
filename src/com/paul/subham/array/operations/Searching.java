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
 * 11. Finding odd occurring number in an array
 * 12. Finding two repeating elements in an array
 * 13. Finding two repeating elements in an array (Hashing Technique)
 * 14. Finding two repeating elements in an array (Using Mathematics)
 * 15. Finding two repeating elements in an array (Using XOR)
 * 16. Finding two repeating elements in an array (Using Index)
 * 17. Finding two repeating elements in an array (By modifying array)
 * 18. Finding pair with given sum in array
 * 19. Finding pair with given sum in array (Using sorting and two pointer)
 * 20. Finding pair with given sum in array (Using binary search)
 * 21. Finding pair with given sum in array (Using hashing)
 * 22. Finding pair with given sum in array (Using Remainder)
 * 23. Finding two elements with sum closest to zero
 * 24. Finding two elements with sum closest to zero (By sorting and two pointer)
 */
public class Searching {
    public static void main(String[] args) {
        int[] a = {1, 4, -2, -3, 2};
        twoSumClosestToZeroBySorting(a);
    }

    /**
     * Finding duplicate in an array (Using sorting)
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static boolean findDuplicateUsingSorting(int[] a) {
        Arrays.sort(a);
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
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
        for (int i = 0; i < a.length; i++) {
            if (set.contains(a[i])) {
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
        for (int i = 0; i < a.length; i++) {
            if (a[Math.abs(a[i])] < 0) {
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
        for (int i = 0; i < a.length; i++) {
            if (countMap.containsKey(a[i])) {
                countMap.put(a[i], countMap.get(a[i]) + 1);
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
        for (int i = 0; i < n; i++) {
            a[a[i] % n] += n;
        }
        int max = 0;
        int maxElement = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] / n > max) {
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
    public static int getFirstRepeatingElement(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            if (set.contains(a[i])) {
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
        for (int i = 1; i <= a.length + 1; i++) {
            int index = BinarySearch.search(a, a.length, i);
            if (index == -1) {
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
        int[] count = new int[a.length + 2];
        for (int j : a) {
            count[j]++;
        }
        for (int i = 1; i < count.length; i++) {
            if (count[i] == 0) {
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
        int n = a.length + 1;
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
        for (int i = 1; i < a.length; i++) {
            x ^= a[i];
        }
        for (int i = 2; i <= a.length + 1; i++) {
            y ^= i;
        }
        return x ^ y;
    }

    /**
     * Finding odd occurring number in an array
     * TC: O(n)
     * SC: O(1)
     */
    public static int getOddOccurringNumber(int[] a) {
        int x = a[0];
        for (int i = 1; i < a.length; i++) {
            x ^= a[i];
        }
        return x;
    }

    /**
     * Finding two repeating elements in an array
     * Given an array arr[] of N+2 elements.
     * All elements of the array are in the range of 1 to N.
     * And all elements occur once except two numbers which occur twice.
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void getTwoRepeatingNumber(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == a[j]) {
                    System.out.print(a[i] + " ");
                }
            }
        }
    }

    /**
     * Finding two repeating elements in an array (Hashing Technique)
     * TC: O(n)
     * SC: O(n)
     */
    public static void getTwoRepeatingNumbersCountArray(int[] a) {
        int[] count = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            count[a[i]]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 2) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * Finding two repeating elements in an array (Using Mathematics)
     * TC: O(n)
     * SC: O(1)
     */
    public static void getTwoRepeatingNumbersMathematics(int[] a) {
        int n = a.length - 2;
        int sum = 0;
        int product = 1;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            product *= a[i];
        }
        sum = sum - n * (n + 1) / 2;
        product = product / factorial(n);
        int diff = (int) Math.sqrt(sum * sum - 4 * product);
        System.out.println((sum + diff) / 2 + " " + (sum - diff) / 2);
    }

    private static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    /**
     * Finding two repeating elements in an array (Using XOR)
     * TC: O(n)
     * SC: O(1)
     */
    public static void getTwoRepeatingNumbersXOR(int[] a) {
        int xor = a[0];
        int n = a.length - 2;
        for (int i = 1; i < a.length; i++) {
            xor ^= a[i];
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        int setBit = (xor & -xor);
        int x = 0, y = 0;
        for (int i = 0; i < a.length; i++) {
            if ((a[i] & setBit) != 0) {
                x ^= a[i];
            } else {
                y ^= a[i];
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & setBit) != 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }
        System.out.println(y + " " + x);
    }

    /**
     * Finding two repeating elements in an array (Using Index)
     * TC: O(n)
     * SC: O(1)
     */
    public static void getTwoRepeatingNumberUsingIndex(int[] a) {
        for(int i=0; i<a.length; i++) {
            int absolute = Math.abs(a[i]);
            if(a[absolute] > 0) {
                a[absolute] = -a[absolute];
            } else {
                System.out.print(absolute + " ");
            }
        }
    }

    /**
     * Finding two repeating elements in an array (By modifying array)
     * TC: O(n)
     * SC: O(1)
     */
    public static void getTwoRepeatingNumberByModifyingArray(int[] a) {
        int divider = a.length - 1;
        for(int i=0; i<a.length; i++) {
            a[a[i] % divider - 1] += divider;
            if(a[a[i] % divider - 1] / divider == 2) {
                System.out.print(a[i] + " ");
            }
        }
    }

    /**
     * Finding pair with given sum in array
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void twoSum(int[] a, int k) {
        for(int i=0; i<a.length-1; i++) {
            for(int j=i+1; j<a.length; j++) {
                if(a[i] + a[j] == k) {
                    System.out.println(a[i] + " " + a[j]);
                    return;
                }
            }
        }
    }

    /**
     * Finding pair with given sum in array (Using sorting and two pointer)
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static void twoSumBySorting(int[] a, int k) {
        Arrays.sort(a);
        int left = 0;
        int right = a.length-1;
        while(left < right) {
            int sum = a[left] + a[right];
            if(sum == k) {
                System.out.println(a[left] + " " + a[right]);
                return;
            } else if(sum > k) {
                right --;
            } else {
                left ++;
            }
        }
    }

    /**
     * Finding pair with given sum in array (Using binary search)
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static void twoSumByBinarySearch(int[] a, int k) {
        Arrays.sort(a);
        for(int i=0; i<a.length; i++) {
            int searchKey = k - a[i];
            int searchIndex = BinarySearch.searchRecursive(a, i+1, a.length-1, searchKey);
            if(searchIndex != -1) {
                System.out.println(a[i] + " " + a[searchIndex]);
                return;
            }
        }
    }

    /**
     * Finding pair with given sum in array (Using hashing)
     * TC: O(n)
     * SC: O(n)
     */
    public static void twoSumByHashing(int[] a, int k) {
        Set<Integer> set = new HashSet<>();
        for (int j : a) {
            int searchKey = k - j;
            if (set.contains(searchKey)) {
                System.out.println(searchKey + " " + j);
                return;
            }
            set.add(j);
        }
    }

    /**
     * Finding pair with given sum in array (Using Remainder)
     * TC: O(n)
     * SC: O(k)
     */
    public static void twoSumByRemainder(int[] a, int k) {
        int[] rem = new int[k];
        for (int i=0; i<a.length; i++) {
            if(a[i] < k) {
                rem[a[i] % k]++;
            }
        }
        int i;
        for(i=1; i<k/2; i++) {
            if(rem[i] > 0 && rem[k-i] > 0) {
                System.out.println(i + " " + (k-i));
                return;
            }
        }

        if(i >= k/2) {
            if(k%2 == 0) {
                if(rem[k/2] > 1) {
                    System.out.println(k/2 + " " + k/2);
                }
            } else {
                if(rem[k/2] > 0 && rem[k - k/2] > 0) {
                    System.out.println(k/2 + " " + (k - k/2));
                }
            }

        }
    }

    /**
     * Finding two elements with sum closest to zero
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void twoSumClosestToZero(int[] a) {
        if(a.length < 2) {
            System.out.println("Invalid input");
            return;
        }
        int left = 0;
        int right = 1;
        int minSum = a[left] + a[right];
        for(int i=0; i<a.length-1; i++) {
            for(int j=i+1; j<a.length; j++) {
                int sum = a[i] + a[j];
                if(minSum > Math.abs(sum)) {
                    minSum = Math.abs(sum);
                    left = i;
                    right = j;
                }
            }
        }
        System.out.println(a[left] + " " + a[right]);
    }

    /**
     * Finding two elements with sum closest to zero (By sorting and two pointer)
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static void twoSumClosestToZeroBySorting(int[] a) {
        if(a.length < 2) {
            System.out.println("Invalid input");
            return;
        }
        Arrays.sort(a);
        int mini = 0;
        int minj = a.length-1;
        int left = 0;
        int right = a.length-1;
        int minSum = Integer.MAX_VALUE;
        while(left < right) {
            int sum = Math.abs(a[left] + a[right]);
            if(minSum > sum) {
                mini = left;
                minj = right;
                minSum = Math.abs(sum);
                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(a[mini] + " " + a[minj]);
    }
}
