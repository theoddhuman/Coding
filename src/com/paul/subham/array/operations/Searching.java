package com.paul.subham.array.operations;

import com.paul.subham.searching.BinarySearch;
import com.paul.subham.tree.implementation.tournament.TNode;
import com.paul.subham.tree.implementation.tournament.TournamentTree;

import java.util.*;

/**
 * 1. Finding duplicate in an array (Using sorting)
 * 2. Finding duplicate in an array (Using hashing)
 * 3. Finding duplicate in an array (space optimized)
 * 4. Finding duplicate in an array (Circular list method)
 * 5. Finding most appearing element in an array (using hashing)
 * 6. Finding most appearing element in an array (space optimized)
 * 7. Finding first repeating element in an array (using hashing)
 * 8. Finding missing number in an array (Sorting Technique)
 * 9. Finding missing number in an array (Hashing Technique)
 * 10. Finding missing number in an array (Summation Technique)
 * 11. Finding missing number in an array (XOR Technique)
 * 12. Finding odd occurring number in an array
 * 13. Finding two repeating elements in an array
 * 14. Finding two repeating elements in an array (Hashing Technique)
 * 15. Finding two repeating elements in an array (Using Mathematics)
 * 16. Finding two repeating elements in an array (Using XOR)
 * 17. Finding two repeating elements in an array (Using Index)
 * 18. Finding two repeating elements in an array (By modifying array)
 * 19. Finding pair with given sum in array
 * 20. Finding pair with given sum in array (Using sorting and two pointer)
 * 21. Finding pair with given sum in array (Using binary search)
 * 22. Finding pair with given sum in array (Using hashing)
 * 23. Finding pair with given sum in array (Using Remainder)
 * 24. Finding two elements with sum closest to zero
 * 25. Finding two elements with sum closest to zero (By sorting and two pointer)
 * 26. Finding an element which appears odd no of times in an array (Nested loop)
 * 27. Finding an element which appears odd no of times in an array (Hashing)
 * 28. Finding an element which appears odd no of times in an array (Bit manipulation - XOR)
 * 29. Finding an element which appears odd no of times in an array (Binary search)
 * 30. Finding maximum in an array which is increasing then decreasing (Bitonic Search - Recursive)
 * 31. Finding maximum in an array which is increasing then decreasing (Bitonic Search - Iterative)
 * 32. Searching an element in sorted and rotated array (Using pivot)
 * 33. Maximum in sorted and rotated array (Binary Search)
 * 34. Searching an element in sorted and rotated array (Binary Search - Recursive)
 * 35. Searching an element in sorted and rotated array (Binary Search - Iterative)
 * 36. Median of sequence of elements
 * 37. First occurrence of an element in an array (Binary Search - Recursive)
 * 38. First occurrence of an element in an array (Binary Search - Iterative)
 * 39. Last occurrence of an element in an array (Binary Search - Recursive)
 * 40. Last occurrence of an element in an array (Binary Search - Iterative)
 * 41. Count no of occurrences of an element in an array (Linear search)
 * 42. Count no of occurrences of an element in an array (Binary Search)
 * 43. Count no of occurrences of an element in an array (Binary Search - improved)
 * 44. Find smallest and second-smallest elements in an array (Sorting)
 * 45. Find smallest and second-smallest elements in an array (Scanning twice)
 * 46. Find smallest and second-smallest elements in an array (Scanning once)
 * 47. Find smallest and second-smallest elements in an array (Using priority queue)
 * 48. Find the smallest element in sorted and rotated array (Binary search - iterative)
 * 49. Find the smallest element in sorted and rotated array (Binary search - recursive)
 * 50. Majority element of an array
 * 51. Majority element of an array (Using binary search tree)
 * 52. Majority element of an array (Using sorting)
 * 53. Majority element of an array (Moore's Voting algorithm)
 * 54. Majority element of an array (Using hashing)
 * 55. Majority element of an array (Using bit manipulation)
 * 56. The second-largest element of an array (Using tournament tree)
 * 57. Print all pairs with given sum in an array (Hashing)
 * 58. Print all pairs with given sum in an array (Two Pointer)
 * 59. Median of two sorted arrays (Merging)
 * 60. Median of two sorted arrays of same size (Count while merging)
 * 61. Median of two sorted arrays of same size (Binary search)
 * 62. Median of two sorted arrays of different size (Count while merging)
 * 63. Median of two sorted arrays of different size (Binary search)
 * 64. Local minima of an array (Binary search - recursive)
 * 65. Local minima of an array (Binary search - iterative)
 * 66. Pairs with given sum in a sorted and rotated array
 * 67. Pairs with given sum in a sorted and rotated array (Max using Binary Search)
 * 68. Count pairs with given sum in a sorted and rotated array (Max using Binary Search)
 * 69. Search an element in infinite array
 * 70. Find repeated and missing number (Brute force)
 * 71. Find repeated and missing number (Hashing)
 * 72. Find repeated and missing number (Array manipulation)
 * 73. Find repeated and missing number (Mathematics)
 * 74. Find repeated and missing number (Using XOR)
 */
public class Searching {
    public static void main(String[] args) {
        int[] a = {3,1,2,5,3};
        int[] b = {3, 7, 9, 11};
        int[] res = findMissingRepeatingNumbers(a, 5);
        System.out.println(res[0] + " " + res[1]);
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
     * Finding duplicate in an array (Circular list method)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int findDuplicate(int[] a) {
        int slow = a[0];
        int fast = a[0];
        do {
            slow = a[slow];
            fast = a[a[fast]];
        } while (slow != fast);
        fast = a[0];
        while(slow != fast) {
            slow = a[slow];
            fast = a[fast];
        }
        return slow;
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
        for (int i = 0; i < a.length; i++) {
            int absolute = Math.abs(a[i]);
            if (a[absolute] > 0) {
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
        for (int i = 0; i < a.length; i++) {
            a[a[i] % divider - 1] += divider;
            if (a[a[i] % divider - 1] / divider == 2) {
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
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == k) {
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
        int right = a.length - 1;
        while (left < right) {
            int sum = a[left] + a[right];
            if (sum == k) {
                System.out.println(a[left] + " " + a[right]);
                return;
            } else if (sum > k) {
                right--;
            } else {
                left++;
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
        for (int i = 0; i < a.length; i++) {
            int searchKey = k - a[i];
            int searchIndex = BinarySearch.searchRecursive(a, i + 1, a.length - 1, searchKey);
            if (searchIndex != -1) {
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
        for (int i = 0; i < a.length; i++) {
            if (a[i] < k) {
                rem[a[i] % k]++;
            }
        }
        int i;
        for (i = 1; i < k / 2; i++) {
            if (rem[i] > 0 && rem[k - i] > 0) {
                System.out.println(i + " " + (k - i));
                return;
            }
        }

        if (i >= k / 2) {
            if (k % 2 == 0) {
                if (rem[k / 2] > 1) {
                    System.out.println(k / 2 + " " + k / 2);
                }
            } else {
                if (rem[k / 2] > 0 && rem[k - k / 2] > 0) {
                    System.out.println(k / 2 + " " + (k - k / 2));
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
        if (a.length < 2) {
            System.out.println("Invalid input");
            return;
        }
        int left = 0;
        int right = 1;
        int minSum = a[left] + a[right];
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int sum = a[i] + a[j];
                if (minSum > Math.abs(sum)) {
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
        if (a.length < 2) {
            System.out.println("Invalid input");
            return;
        }
        Arrays.sort(a);
        int mini = 0;
        int minj = a.length - 1;
        int left = 0;
        int right = a.length - 1;
        int minSum = Integer.MAX_VALUE;
        while (left < right) {
            int sum = Math.abs(a[left] + a[right]);
            if (minSum > sum) {
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

    /**
     * Finding an element which appears odd no of times in an array (Nested loop)
     * <p>
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int oddAppearingElement(int[] a) {
        for (int k : a) {
            int count = 0;
            for (int i : a) {
                if (k == i) {
                    count++;
                }
            }
            if (count % 2 != 0) {
                return k;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Finding an element which appears odd no of times in an array (Hashing)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static int oddAppearingElementHashing(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : a) {
            if (map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
            } else {
                map.put(j, 1);
            }
        }
        for (Integer i : map.keySet()) {
            if (map.get(i) % 2 != 0) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Finding an element which appears odd no of times in an array (Bit manipulation - XOR)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public static int oddAppearingElementXor(int[] a) {
        int res = 0;
        for (int i : a) {
            res = res ^ i;
        }
        return res;
    }

    /**
     * Finding an element which appears odd no of times in an array (Binary search)
     * <p>
     * Given an array where all elements appear even number of times except one.
     * All repeating occurrences of elements appear in pairs and these pairs are not adjacent.
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int oddAppearingElementBinarySearch(int[] a, int low, int high) {
        if (low > high) {
            return Integer.MIN_VALUE;
        }
        if (low == high) {
            return a[low];
        }
        int mid = low + (high - low) / 2;
        if (mid % 2 == 0) {
            if (a[mid] == a[mid + 1]) {
                return oddAppearingElementBinarySearch(a, mid + 2, high);
            } else {
                return oddAppearingElementBinarySearch(a, low, mid);
            }
        } else {
            if (a[mid] == a[mid - 1]) {
                return oddAppearingElementBinarySearch(a, mid + 1, high);
            } else {
                return oddAppearingElementBinarySearch(a, low, mid - 1);
            }
        }
    }

    /**
     * Finding maximum in an array which is increasing then decreasing (Bitonic Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int maxBitonicRecursive(int[] a, int low, int high) {
        if (low == high) {
            return a[low];
        }
        if (low + 1 == high) {
            return Math.max(a[low], a[high]);
        }
        int mid = low + (high - low) / 2;
        if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) {
            return a[mid];
        }
        if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) {
            return maxBitonicRecursive(a, mid + 1, high);
        } else {
            return maxBitonicRecursive(a, low, mid - 1);
        }
    }

    /**
     * Finding maximum in an array which is increasing then decreasing (Bitonic Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int maxBitonicIterative(int[] a, int low, int high) {
        if (low == high) {
            return a[low];
        }
        while (low <= high) {
            if (low + 1 == high) {
                return Math.max(a[low], a[high]);
            }
            int mid = low + (high - low) / 2;
            if (a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) {
                return a[mid];
            }
            if (a[mid] > a[mid - 1] && a[mid] < a[mid + 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return a[high];
    }

    /**
     * Searching an element in sorted and rotated array (Using pivot)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int searchSortedAndRotatedPivot(int[] a, int data) {
        int pivot = findPivot(a, 0, a.length - 1);
        if (pivot == -1) {
            return BinarySearch.searchRecursive(a, 0, a.length - 1, data);
        }
        if (a[pivot] == data) {
            return pivot;
        }
        if (a[0] <= data) {
            return BinarySearch.searchRecursive(a, 0, pivot - 1, data);
        } else {
            return BinarySearch.searchRecursive(a, pivot + 1, a.length - 1, data);
        }
    }

    /**
     * Maximum in sorted and rotated array (Binary Search)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    private static int findPivot(int[] a, int low, int high) {
        if (low > high) {
            return -1;
        }
        if (low == high) {
            return low;
        }
        int mid = low + (high - low) / 2;
        if (mid < high && a[mid] > a[mid + 1]) {
            return mid;
        }
        if (mid > low && a[mid] < a[mid - 1]) {
            return mid - 1;
        }
        if (a[low] >= a[mid]) {
            return findPivot(a, low, mid - 1);
        } else {
            return findPivot(a, mid + 1, high);
        }
    }

    /**
     * Searching an element in sorted and rotated array (Binary Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int searchSortedAndRotatedBinaryRecursive(int[] a, int data, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (a[mid] == data) {
            return mid;
        }
        if (a[low] <= a[mid]) {
            if (a[low] <= data && data < a[mid]) {
                return searchSortedAndRotatedBinaryRecursive(a, data, low, mid - 1);
            } else {
                return searchSortedAndRotatedBinaryRecursive(a, data, mid + 1, high);
            }
        } else {
            if (a[mid] < data && data <= a[high]) {
                return searchSortedAndRotatedBinaryRecursive(a, data, mid + 1, high);
            } else {
                return searchSortedAndRotatedBinaryRecursive(a, data, low, mid - 1);
            }
        }
    }

    /**
     * Searching an element in sorted and rotated array (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int searchSortedAndRotatedBinaryIterative(int[] a, int data, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == data) {
                return mid;
            }
            if (a[low] <= a[mid]) {
                if (a[low] <= data && data < a[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (a[mid] < data && data <= a[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * Median of sequence of elements
     * <p>
     * TC: O(1)
     * SC: O(1)
     */
    public static double median(int[] a) {
        int n = a.length;
        if (n % 2 != 0) {
            return a[n / 2];
        } else {
            return (double) (a[(n - 1) / 2] + a[n / 2]) / 2.0;
        }
    }

    /**
     * First occurrence of an element in an array (Binary Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int firstOccurrenceRecursive(int[] a, int low, int high, int data) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if ((mid == low && a[mid] == data) || (a[mid] == data && a[mid - 1] < data)) {
            return mid;
        } else if (a[mid] >= data) {
            return firstOccurrenceRecursive(a, low, mid - 1, data);
        } else {
            return firstOccurrenceRecursive(a, mid + 1, high, data);
        }
    }

    /**
     * First occurrence of an element in an array (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int firstOccurrenceIterative(int[] a, int data) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((mid == low && a[mid] == data) || (a[mid] == data && a[mid - 1] < data)) {
                return mid;
            } else if (a[mid] >= data) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Last occurrence of an element in an array (Binary Search - Recursive)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int lastOccurrenceRecursive(int[] a, int low, int high, int data) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if ((mid == high && a[mid] == data) || (a[mid] == data && a[mid + 1] > data)) {
            return mid;
        } else if (a[mid] >= data) {
            return lastOccurrenceRecursive(a, low, mid - 1, data);
        } else {
            return lastOccurrenceRecursive(a, mid + 1, high, data);
        }
    }

    /**
     * Last occurrence of an element in an array (Binary Search - Iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int lastOccurrenceIterative(int[] a, int data) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((mid == high && a[mid] == data) || (a[mid] == data && a[mid + 1] > data)) {
                return mid;
            } else if (a[mid] >= data) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Count no of occurrences of an element in an array (Linear search)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int countOccurrences(int[] a, int data) {
        int count = 0;
        for (int i : a) {
            if (i == data) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count no of occurrences of an element in an array (Binary Search)
     * <p>
     * TC: O(logn + k), where k is no of occurrences
     * SC: O(1)
     */
    public static int countOccurrencesBinarySearch(int[] a, int data) {
        int index = BinarySearch.search(a, a.length, data);
        int count = 1;
        int left = index - 1;
        while (left >= 0 && a[left] == data) {
            count++;
            left--;
        }
        int right = index + 1;
        while (index < a.length && a[right] == data) {
            count++;
            right++;
        }
        return count;
    }

    /**
     * Count no of occurrences of an element in an array (Binary Search - improved)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int countOccurrencesBinarySearchImproved(int[] a, int data) {
        int firstIndex = firstOccurrenceIterative(a, data);
        if (firstIndex == -1) {
            return 0;
        }
        int lastIndex = lastOccurrenceIterative(a, data);
        return lastIndex - firstIndex + 1;
    }

    /**
     * Find smallest and second-smallest elements in an array (Sorting)
     * <p>
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static void findSmallestAndSecondSmallest(int[] a) {
        Arrays.sort(a);
        int smallest = a[0];
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > smallest) {
                secondSmallest = a[i];
                break;
            }
        }
        System.out.println("Smallest: " + smallest);
        if (secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find smallest and second-smallest elements in an array (Scanning twice)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public static void findSmallestAndSecondSmallestTwoScan(int[] a) {
        int smallest = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            smallest = Math.min(smallest, a[i]);
        }
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > smallest) {
                secondSmallest = Math.min(secondSmallest, a[i]);
            }
        }
        System.out.println("Smallest: " + smallest);
        if (secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find smallest and second-smallest elements in an array (Scanning once)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public static void findSmallestAndSecondSmallestSingleScan(int[] a) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < smallest) {
                secondSmallest = smallest;
                smallest = a[i];
            } else if (a[i] < secondSmallest && a[i] != smallest) {
                secondSmallest = a[i];
            }
        }
        System.out.println("Smallest: " + smallest);
        if (secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find smallest and second-smallest elements in an array (Using priority queue)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static void findSmallestAndSecondSmallestPriorityQueue(int[] a) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < a.length; i++) {
            priorityQueue.add(a[i]);
        }
        int smallest = priorityQueue.poll();
        int secondSmallest = Integer.MAX_VALUE;
        while (!priorityQueue.isEmpty()) {
            int x = priorityQueue.poll();
            if (x != smallest) {
                secondSmallest = x;
                break;
            }
        }
        System.out.println("Smallest " + smallest);
        if (secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find the smallest element in sorted and rotated array (Binary search - iterative)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int minSortedRotated(int[] a) {
        int low = 0;
        int high = a.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (low < mid && a[mid] < a[mid - 1]) {
                return a[mid];
            }
            if (high > mid && a[mid] > a[mid + 1]) {
                return a[mid + 1];
            }
            if (a[mid] < a[high]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return a[0];
    }

    /**
     * Find the smallest element in sorted and rotated array (Binary search - recursive)
     * <p>
     * TC: O(logn)
     * SC: O(1)
     */
    public static int minSortedRotated(int[] a, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            if (low < mid && a[mid] < a[mid - 1]) {
                return a[mid];
            }
            if (high > mid && a[mid] > a[mid + 1]) {
                return a[mid + 1];
            }
            if (a[mid] < a[high]) {
                return minSortedRotated(a, low, mid - 1);
            } else {
                return minSortedRotated(a, mid + 1, high);
            }
        }
        return a[low];
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
     * The second-largest element of an array (Using tournament tree)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static int secondLargestTournamentTree(int[] a) {
        TournamentTree tournamentTree = new TournamentTree();
        tournamentTree.insertArray(a);
        return a[secondLargestIndex(tournamentTree.root, a)];
    }

    private static int secondLargestIndex(TNode node, int[] a) {
        if (node == null || node.left == null) {
            return -1;
        }
        if (node.left.index == node.index) {
            int leftSecondMin = secondLargestIndex(node.left, a);
            if (leftSecondMin == -1 || a[node.right.index] > a[leftSecondMin]) {
                return node.right.index;
            } else {
                return leftSecondMin;
            }
        } else {
            int rightSecondMin = secondLargestIndex(node.right, a);
            if (rightSecondMin == -1 || a[node.left.index] > a[rightSecondMin]) {
                return node.left.index;
            } else {
                return rightSecondMin;
            }
        }
    }

    /**
     * Print all pairs with given sum in an array (Hashing)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    public static void printPairSums(int[] a, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            int rem = k - a[i];
            if (rem == a[i]) {
                if (hashMap.containsKey(rem) && hashMap.get(rem) == 1) {
                    System.out.println(rem + " " + rem);
                }
            } else {
                if (hashMap.containsKey(rem) && !hashMap.containsKey(a[i])) {
                    System.out.println(rem + " " + a[i]);
                }
            }
            hashMap.put(a[i], hashMap.getOrDefault(a[i], 0) + 1);
        }
    }

    /**
     * Print all pairs with given sum in an array (Two Pointer)
     * <p>
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void printPairsTwoPointer(int[] a, int k) {
        Arrays.sort(a);
        int low = 0;
        int high = a.length - 1;
        while (low < high) {
            int sum = a[low] + a[high];
            if (sum == k) {
                System.out.println(a[low] + " " + a[high]);
                low++;
                high--;
            } else if (sum < k) {
                low++;
            } else {
                high--;
            }
        }
    }

    /**
     * Median of two sorted arrays (Merging)
     * <p>
     * TC: O(n)
     * SC: O(n)
     */
    private static double medianTwoSortedArrays(int[] a, int[] b) {
        int[] temp = Manipulation.merge(a, b);
        return median(temp);
    }

    /**
     * Median of two sorted arrays of same size (Count while merging)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    private static double medianTwoSortedArraysSameSizeMerging(int[] a, int[] b) {
        int l = 0;
        int r = 0;
        int m1 = -1, m2 = -1;
        for (int i = 0; i <= a.length; i++) {
            if (l == a.length) {
                m1 = m2;
                m2 = b[0];
                break;
            }
            if (r == b.length) {
                m1 = m2;
                m2 = a[0];
                break;
            }
            if (a[l] <= b[r]) {
                m1 = m2;
                m2 = a[l];
                l++;
            } else {
                m1 = m2;
                m2 = a[r];
                r++;
            }
        }
        return (m1 + m2) / 2.0;
    }

    /**
     * Median of two sorted arrays of same size (Binary Search)
     * <p>
     * TC: O(logn)
     * SC: O(logn)
     */
    public static double medianTwoSortedArraysSameSizeSelection(int[] a, int[] b) {
        return getMedian(a, b, 0, a.length - 1, 0, b.length - 1);
    }

    private static double getMedian(int[] a, int[] b, int startA, int endA, int startB, int endB) {
        if (endA - startA == 1) {
            return (Math.max(a[startA], b[startB]) + Math.min(a[endA], b[endB])) / 2.0;
        }
        double m1 = median(a, startA, endA);
        double m2 = median(b, startB, endB);
        if (m1 < m2) {
            return getMedian(a, b, (startA + endA + 1) / 2, endA, startB, (startB + endB) / 2);
        }
        return getMedian(a, b, startA, (startA + endA) / 2, (startB + endB + 1) / 2, endB);
    }

    private static double median(int[] a, int start, int end) {
        int n = end - start + 1;
        if (n % 2 == 0) {
            return (a[start + n / 2] + a[start + n / 2 - 1]) / 2.0;
        }
        return a[start + n / 2];
    }

    /**
     * Median of two sorted arrays of different size (Count while merging)
     * <p>
     * TC: O(n)
     * SC: O(1)
     */
    public static double medianTwoSortedArraysDiffSize(int[] a, int[] b) {
        int aLength = a.length;
        int bLength = b.length;
        int n = aLength + bLength;
        int m1 = -1;
        int m2 = -1;
        int i = 0;
        int j = 0;
        if (n % 2 == 1) {
            for (int k = 0; k <= n / 2; k++) {
                if (i < aLength && j < bLength) {
                    m2 = a[i] <= b[j] ? a[i++] : b[j++];
                } else if (i < aLength) {
                    m2 = a[i++];
                } else {
                    m2 = b[j++];
                }
            }
            return m2;
        } else {
            for (int k = 0; k <= n / 2; k++) {
                m1 = m2;
                if (i < aLength && j < bLength) {
                    m2 = a[i] <= b[j] ? a[i++] : b[j++];
                } else if (i < aLength) {
                    m2 = a[i++];
                } else {
                    m2 = b[j++];
                }
            }
            return (m1 + m2) / 2.0;
        }
    }

    /**
     * Median of two sorted arrays of different size (Binary search)
     * <p>
     * TC: O(log(min(m,n)))
     * SC: O(1)
     */
    public static double medianTwoSortedArraysDiffSizeBinarySearch(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        if (m > n) {
            return medianTwoSortedArraysDiffSizeBinarySearch(b, a);
        }
        int low = 0;
        int high = m;
        while (low <= high) {
            int midA = (low + high) / 2;
            int midB = (m + n + 1)/2 - midA;
            int lA = Integer.MIN_VALUE;
            int lB = Integer.MIN_VALUE;
            int rA = Integer.MAX_VALUE;
            int rB = Integer.MAX_VALUE;
            if(midA > 0) {
                lA = a[midA-1];
            }
            if(midB > 0) {
                lB = b[midB-1];
            }
            if(midA < m) {
                rA = a[midA];
            }
            if(midB < n) {
                rB = b[midB];
            }
            if(lA <= rB && lB <= rA) {
                if((m+n)%2 == 0) {
                    return (Math.max(lA, lB) + Math.min(rA, rB)) / 2.0;
                } else {
                    return Math.max(lA, lB);
                }
            } else if (lA > rB) {
                high = midA - 1;
            } else {
                low = midA + 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Local minima of an array (Binary search - recursive)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int localMinima(int[] a) {
        return localMinimaUtil(a, 0, a.length-1, a.length);
    }

    private static int localMinimaUtil(int[] a, int low, int high, int n) {
        if(low > high) {
            return Integer.MIN_VALUE;
        }
        int mid = (low + high)/2;
        if((mid == 0 || a[mid-1] > a[mid]) && (mid == n-1 || a[mid] < a[mid+1])) {
            return a[mid];
        }
        if(mid > 0 && a[mid-1] < a[mid]) {
            return localMinimaUtil(a, low, mid-1, n);
        }
        return localMinimaUtil(a, mid+1, high, n);
    }

    /**
     * Local minima of an array (Binary search - iterative)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int localMinimaIterative(int[] a) {
        int low = 0;
        int high = a.length-1;
        while(low <= high) {
            int mid = (low + high)/2;
            if((mid == 0 || a[mid-1] > a[mid]) && (mid == a.length-1 || a[mid] < a[mid+1])) {
                return a[mid];
            }
            if(mid > 0 && a[mid-1] < a[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Pairs with given sum in a sorted and rotated array
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void pairSumSortedRotated(int[] a, int sum, int n) {
        int i = 0;
        for (; i < n - 1; i++) {
            if (a[i] > a[i + 1]) {
                break;
            }
        }
        int left = (i + 1) % n;
        int right = i;
        while (left != right) {
            int s = a[left] + a[right];
            if (s == sum) {
                System.out.println(a[left] + " " + a[right]);
                if(left == (n + right - 1) % n) {
                    break;
                }
                right = (n + right - 1) % n;
                left = (left + 1) % n;
            } else if (s > sum) {
                right = (n + right - 1) % n;
            } else {
                left = (left + 1) % n;
            }
        }
    }

    /**
     * Pairs with given sum in a sorted and rotated array (Max using Binary Search)
     *
     * TC: O(n)
     * SC: O(logn)
     */
    public static void pairSumSortedRotatedBinary(int[] a, int sum, int n) {
        int i = findPivot(a, 0, n-1);
        int left = (i + 1) % n;
        int right = i;
        while (left != right) {
            int s = a[left] + a[right];
            if (s == sum) {
                System.out.println(a[left] + " " + a[right]);
                if(left == (n + right - 1) % n) {
                    break;
                }
                right = (n + right - 1) % n;
                left = (left + 1) % n;
            } else if (s > sum) {
                right = (n + right - 1) % n;
            } else {
                left = (left + 1) % n;
            }
        }
    }

    /**
     * Count pairs with given sum in a sorted and rotated array (Max using Binary Search)
     *
     * TC: O(n)
     * SC: O(logn)
     */
    public static int countPairSumSortedRotatedBinary(int[] a, int sum, int n) {
        int i = findPivot(a, 0, n-1);
        int left = (i + 1) % n;
        int right = i;
        int count = 0;
        while (left != right) {
            int s = a[left] + a[right];
            if (s == sum) {
                count++;
                if(left == (n + right - 1) % n) {
                    break;
                }
                right = (n + right - 1) % n;
                left = (left + 1) % n;
            } else if (s > sum) {
                right = (n + right - 1) % n;
            } else {
                left = (left + 1) % n;
            }
        }
        return count;
    }

    /**
     * Search an element in infinite array
     *
     * TC: O(logp)
     * SC: O(logn)
     */
    public static int searchInfinite(int[] a, int data) {
        int low = 0;
        int high = 1;
        int val = a[high];
        while(val < data) {
            low = high+1;
            high *=2;
            val = a[high];
        }
        return BinarySearch.searchRecursive(a, low, high, data);
    }

    /**
     * Find repeated and missing number (Brute force)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int[] repeatAndMissBruteForce(int[] a, int n) {
        int[] res = new int[2];
        for(int i=1; i<=n; i++) {
            int count = 0;
            for(int j=0; j<n; j++) {
                if(a[j] == i) {
                    count++;
                }
            }
            if(count == 2) {
                res[0] = i;
            }
            if(count == 0) {
                res[1] = i;
            }
            if(res[0] > 0 && res[1] > 0) {
                break;
            }
        }
        return res;
    }

    /**
     * Find repeated and missing number (Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] repeatAndMissingHashing(int[] a, int n) {
        int[] hash = new int[n+1];
        for(int i=0; i<n; i++) {
            hash[a[i]]++;
        }
        int[] res = new int[2];
        for(int i=1; i<=n; i++) {
            if(hash[i] == 2) {
                res[0] = i;
            }
            if(hash[i] == 0) {
                res[1] = i;
            }
            if(res[0] > 0 && res[1] > 0) {
                break;
            }
        }
        return res;
    }

    /**
     * Find repeated and missing number (Array manipulation)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] repeatAndMissingManipulation(int[] a, int n) {
        int[] res = new int[2];
        for(int i=0; i<n; i++) {
            if(a[Math.abs(a[i]) - 1] < 0) {
                res[0] = Math.abs(a[i]);
                continue;
            }
            a[Math.abs(a[i]) - 1] *= -1;
        }
        for(int i=0; i<n; i++) {
            if(a[i] > 0) {
                res[1] = i+1;
            }
        }
        return res;
    }

    /**
     * Find repeated and missing number (Mathematics)
     *
     * sum of numbers from 1 to n, Sn = n*(n+1)/2
     * sum of numbers from 1 to n^2, Sn2 = n*(n+1)*(2n+1)/6
     * x -> repeated element, y -> missing element
     * x - y = sum of array - Sn
     * x^2 - y^2 = square sum of array - Sn2
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] repeatAndMissingMaths(int[] a, int n) {
        int sumN = n*(n+1)/2;
        int sumN2 = n*(n+1)*(2*n+1)/6;
        int sumA = 0;
        int sumA2 = 0;
        for(int i=0; i<n; i++) {
            sumA += a[i];
            sumA2 += (a[i] * a[i]);
        }
        int[] res = new int[2];
        int val1 = sumA - sumN;
        int val2 = sumA2 - sumN2;
        val2 = val2/val1;
        res[0] = (val1 + val2)/2;
        res[1] = res[0] - val1;
        return res;
    }

    /**
     * Find repeated and missing number (Using XOR)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[] findMissingRepeatingNumbers(int[] a, int n) {
        int xr = 0;
        for (int i = 0; i < n; i++) {
            xr = xr ^ a[i];
            xr = xr ^ (i + 1);
        }
        //Find the differentiating bit number:
        int number = (xr & ~(xr - 1));

        //Group the numbers:
        int zero = 0;
        int one = 0;
        for (int i = 0; i < n; i++) {
            if ((a[i] & number) != 0) {
                one = one ^ a[i];
            }
            else {
                zero = zero ^ a[i];
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & number) != 0) {
                one = one ^ i;
            }
            else {
                zero = zero ^ i;
            }
        }
        // Identify the numbers:
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == zero) cnt++;
        }
        if (cnt == 2) return new int[] {zero, one};
        return new int[] {one, zero};
    }
}

class BinarySearchFrequencyTree {
    BSFrequencyNode root;

    void insert(int data) {
        root = insertUtil(root, data);
    }

    private BSFrequencyNode insertUtil(BSFrequencyNode node, int data) {
        if (node == null) {
            return new BSFrequencyNode(data);
        }
        if (data == node.data) {
            node.frequency++;
        } else if (data < node.data) {
            node.left = insertUtil(node.left, data);
        } else {
            node.right = insertUtil(node.right, data);
        }
        return node;
    }
}

class BSFrequencyNode {
    int data;
    int frequency;
    BSFrequencyNode left, right;

    BSFrequencyNode(int data) {
        this.data = data;
        this.frequency = 1;
        this.left = this.right = null;
    }
}
