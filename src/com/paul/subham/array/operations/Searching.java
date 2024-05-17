package com.paul.subham.array.operations;

import com.paul.subham.searching.BinarySearch;
import com.paul.subham.tree.implementation.binarysearch.BSTNode;
import com.paul.subham.tree.implementation.binarysearch.BinarySearchTree;

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
 * 25. Finding an element which appears odd no of times in an array (Nested loop)
 * 26. Finding an element which appears odd no of times in an array (Hashing)
 * 27. Finding an element which appears odd no of times in an array (Bit manipulation - XOR)
 * 28. Finding an element which appears odd no of times in an array (Binary search)
 * 29. Finding maximum in an array which is increasing then decreasing (Bitonic Search - Recursive)
 * 30. Finding maximum in an array which is increasing then decreasing (Bitonic Search - Iterative)
 * 31. Searching an element in sorted and rotated array (Using pivot)
 * 32. Searching an element in sorted and rotated array (Binary Search - Recursive)
 * 33. Searching an element in sorted and rotated array (Binary Search - Iterative)
 * 34. Median of sequence of elements
 * 35. First occurrence of an element in an array (Binary Search - Recursive)
 * 36. First occurrence of an element in an array (Binary Search - Iterative)
 * 37. Last occurrence of an element in an array (Binary Search - Recursive)
 * 38. Last occurrence of an element in an array (Binary Search - Iterative)
 * 39. Count no of occurrences of an element in an array (Linear search)
 * 40. Count no of occurrences of an element in an array (Binary Search)
 * 41. Count no of occurrences of an element in an array (Binary Search - improved)
 * 42. Find smallest and second-smallest elements in an array (Sorting)
 * 43. Find smallest and second-smallest elements in an array (Scanning twice)
 * 44. Find smallest and second-smallest elements in an array (Scanning once)
 * 45. Find smallest and second-smallest elements in an array (Using priority queue)
 * 46. Find the smallest element in sorted and rotated array (Binary search - iterative)
 * 47. Find the smallest element in sorted and rotated array (Binary search - recursive)
 * 48. Majority element of an array
 * 49. Majority element of an array (Using binary search tree)
 * 50. Majority element of an array (Using sorting)
 * 51. Majority element of an array (Moore's Voting algorithm)
 * 52. Majority element of an array (Using hashing)
 * 53. Majority element of an array (Using bit manipulation)
 * 54. The largest K elements in an array (Using tree sorting)
 * 55. The smallest K elements in an array (Using tree sorting)
 * 55. The largest K elements in an array (Using tree sorting - space optimized)
 * 56. The smallest K elements in an array (Using tree sorting - space optimized)
 * 56. The largest K elements in an array (Using sorting)
 * 57. The smallest K elements in an array (Using sorting)
 * 58. The largest K elements in an array (Bubble sort)
 * 59. The smallest K elements in an array (Bubble sort)
 * 60. The largest K elements in an array (Selection sort)
 * 61. The smallest K elements in an array (Selection sort)
 * 62. The largest K elements in an array (Priority Queue)
 * 63. The smallest K elements in an array (Priority Queue)
 * 64. The largest K elements in an array (Binary search)
 * 65. The smallest K elements in an array (Binary search)
 * 66. The largest K elements in an array (Quick sort)
 * 67. The smallest K elements in an array (Quick sort)
 */
public class Searching {
    public static void main(String[] args) {
        int[] a = {8,9,2,1,7,11,4,14};
        kSmallestElementsQuickSort(a, 4);
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

    /**
     * Finding an element which appears odd no of times in an array (Nested loop)
     *
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
     *
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
        for(Integer i : map.keySet()) {
            if(map.get(i)%2 != 0) {
                return i;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Finding an element which appears odd no of times in an array (Bit manipulation - XOR)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int oddAppearingElementXor(int[] a) {
        int res = 0;
        for(int i: a) {
            res = res ^ i;
        }
        return res;
    }

    /**
     * Finding an element which appears odd no of times in an array (Binary search)
     *
     * Given an array where all elements appear even number of times except one.
     * All repeating occurrences of elements appear in pairs and these pairs are not adjacent.
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int oddAppearingElementBinarySearch(int[] a, int low, int high) {
        if(low > high) {
            return Integer.MIN_VALUE;
        }
        if(low == high) {
            return a[low];
        }
        int mid = low + (high - low)/2;
        if(mid%2 == 0) {
            if(a[mid] == a[mid+1]){
                return oddAppearingElementBinarySearch(a, mid+2, high);
            } else {
                return oddAppearingElementBinarySearch(a, low, mid);
            }
        } else {
            if(a[mid] == a[mid-1]) {
                return oddAppearingElementBinarySearch(a, mid+1,high);
            } else {
                return oddAppearingElementBinarySearch(a, low, mid-1);
            }
        }
    }

    /**
     * Finding maximum in an array which is increasing then decreasing (Bitonic Search - Recursive)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int maxBitonicRecursive(int[] a, int low, int high) {
        if(low == high) {
            return a[low];
        }
        if(low + 1== high) {
            return Math.max(a[low], a[high]);
        }
        int mid = low + (high - low)/2;
        if(a[mid] > a[mid-1] && a[mid] > a[mid+1]) {
            return a[mid];
        }
        if(a[mid] > a[mid-1] && a[mid] < a[mid+1]) {
            return maxBitonicRecursive(a, mid+1, high);
        } else {
            return maxBitonicRecursive(a, low, mid-1);
        }
    }

    /**
     * Finding maximum in an array which is increasing then decreasing (Bitonic Search - Iterative)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int maxBitonicIterative(int[] a, int low, int high) {
        if(low == high) {
            return a[low];
        }
        while(low <= high) {
            if(low + 1== high) {
                return Math.max(a[low], a[high]);
            }
            int mid = low + (high-low)/2;
            if(a[mid] > a[mid-1] && a[mid] > a[mid+1]) {
                return a[mid];
            }
            if(a[mid] > a[mid-1] && a[mid] < a[mid+1]) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return a[high];
    }

    /**
     * Searching an element in sorted and rotated array (Using pivot)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int searchSortedAndRotatedPivot(int[] a, int data) {
        int pivot = findPivot(a, 0, a.length-1);
        if(pivot == -1) {
            return BinarySearch.searchRecursive(a, 0, a.length-1, data);
        }
        if(a[pivot] == data) {
            return pivot;
        }
        if(a[0] <= data) {
            return BinarySearch.searchRecursive(a, 0, pivot-1, data);
        } else {
            return BinarySearch.searchRecursive(a, pivot+1, a.length-1, data);
        }
    }

    private static int findPivot(int[] a, int low, int high) {
        if(low > high) {
            return -1;
        }
        if(low == high) {
            return low;
        }
        int mid = low + (high-low)/2;
        if(mid < high && a[mid] > a[mid+1]) {
            return mid;
        }
        if(mid > low && a[mid] < a[mid-1]) {
            return mid-1;
        }
        if(a[low] >= a[mid]) {
            return findPivot(a, low, mid-1);
        } else {
            return findPivot(a, mid+1, high);
        }
    }
    /**
     * Searching an element in sorted and rotated array (Binary Search - Recursive)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int searchSortedAndRotatedBinaryRecursive(int[] a, int data, int low, int high) {
        if(low>high) {
            return -1;
        }
        int mid = low + (high-low)/2;
        if(a[mid] == data) {
            return mid;
        }
        if(a[low] <= a[mid]) {
            if(a[low] <= data && data < a[mid]) {
                return searchSortedAndRotatedBinaryRecursive(a, data, low, mid-1);
            } else {
                return searchSortedAndRotatedBinaryRecursive(a, data, mid+1, high);
            }
        } else {
            if(a[mid] < data && data <= a[high]) {
                return searchSortedAndRotatedBinaryRecursive(a, data, mid+1, high);
            } else {
                return searchSortedAndRotatedBinaryRecursive(a, data, low, mid-1);
            }
        }
    }

    /**
     * Searching an element in sorted and rotated array (Binary Search - Iterative)
     *
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
     *
     * TC: O(1)
     * SC: O(1)
     */
    public static double median(int[] a) {
        int n = a.length;
        if(n%2 != 0) {
            return a[n/2];
        } else {
            return (double)(a[(n-1)/2] + a[n/2])/2.0;
        }
    }

    /**
     * First occurrence of an element in an array (Binary Search - Recursive)
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int firstOccurrenceRecursive(int[] a, int low, int high, int data) {
        if(low > high) {
            return -1;
        }
        int mid = low + (high-low) / 2;
        if((mid == low && a[mid] == data) || (a[mid] == data && a[mid-1] < data)) {
            return mid;
        } else if (a[mid] >= data) {
            return firstOccurrenceRecursive(a, low, mid-1, data);
        } else {
            return firstOccurrenceRecursive(a, mid+1, high, data);
        }
    }

    /**
     * First occurrence of an element in an array (Binary Search - Iterative)
     *
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
     *
     * TC: O(logn)
     * SC: O(logn)
     */
    public static int lastOccurrenceRecursive(int[] a, int low, int high, int data) {
        if(low > high) {
            return -1;
        }
        int mid = low + (high-low) / 2;
        if((mid == high && a[mid] == data) || (a[mid] == data && a[mid+1] > data)) {
            return mid;
        } else if (a[mid] >= data) {
            return lastOccurrenceRecursive(a, low, mid-1, data);
        } else {
            return lastOccurrenceRecursive(a, mid+1, high, data);
        }
    }

    /**
     * Last occurrence of an element in an array (Binary Search - Iterative)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int lastOccurrenceIterative(int[] a, int data) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if((mid == high && a[mid] == data) || (a[mid] == data && a[mid+1] > data)) {
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
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int countOccurrences(int[] a, int data) {
        int count = 0;
        for(int i: a) {
            if(i == data) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count no of occurrences of an element in an array (Binary Search)
     *
     * TC: O(logn + k), where k is no of occurrences
     * SC: O(1)
     */
    public static int countOccurrencesBinarySearch(int[] a, int data) {
        int index = BinarySearch.search(a, a.length, data);
        int count = 1;
        int left = index-1;
        while(left>=0 && a[left] == data) {
            count++;
            left--;
        }
        int right = index+1;
        while(index<a.length && a[right] == data) {
            count++;
            right++;
        }
        return count;
    }

    /**
     * Count no of occurrences of an element in an array (Binary Search - improved)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int countOccurrencesBinarySearchImproved(int[] a, int data) {
        int firstIndex = firstOccurrenceIterative(a, data);
        if(firstIndex == -1) {
            return 0;
        }
        int lastIndex = lastOccurrenceIterative(a, data);
        return lastIndex - firstIndex  + 1;
    }

    /**
     * Find smallest and second-smallest elements in an array (Sorting)
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static void findSmallestAndSecondSmallest(int[] a) {
        Arrays.sort(a);
        int smallest = a[0];
        int secondSmallest = Integer.MAX_VALUE;
        for(int i=1; i<a.length; i++) {
            if(a[i] > smallest) {
                secondSmallest = a[i];
                break;
            }
        }
        System.out.println("Smallest: "+smallest);
        if(secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find smallest and second-smallest elements in an array (Scanning twice)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void findSmallestAndSecondSmallestTwoScan(int[] a) {
        int smallest = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            smallest = Math.min(smallest, a[i]);
        }
        int secondSmallest = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            if(a[i] > smallest) {
                secondSmallest = Math.min(secondSmallest, a[i]);
            }
        }
        System.out.println("Smallest: "+smallest);
        if(secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find smallest and second-smallest elements in an array (Scanning once)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void findSmallestAndSecondSmallestSingleScan(int[] a) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        for(int i=0; i<a.length; i++) {
            if(a[i] < smallest) {
                secondSmallest = smallest;
                smallest = a[i];
            } else if(a[i] < secondSmallest && a[i] != smallest) {
                secondSmallest = a[i];
            }
        }
        System.out.println("Smallest: "+smallest);
        if(secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find smallest and second-smallest elements in an array (Using priority queue)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void findSmallestAndSecondSmallestPriorityQueue(int[] a) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0; i<a.length; i++) {
            priorityQueue.add(a[i]);
        }
        int smallest = priorityQueue.poll();
        int secondSmallest = Integer.MAX_VALUE;
        while(!priorityQueue.isEmpty()) {
            int x = priorityQueue.poll();
            if(x != smallest) {
                secondSmallest = x;
                break;
            }
        }
        System.out.println("Smallest " + smallest);
        if(secondSmallest < Integer.MAX_VALUE) {
            System.out.println("Second Smallest " + secondSmallest);
        }
    }

    /**
     * Find the smallest element in sorted and rotated array (Binary search - iterative)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int minSortedRotated(int[] a) {
        int low = 0;
        int high = a.length-1;
        while(low < high) {
            int mid = low + (high-low)/2;
            if(low < mid && a[mid] < a[mid-1]) {
                return a[mid];
            }
            if(high > mid && a[mid] > a[mid+1]) {
                return a[mid+1];
            }
            if(a[mid] < a[high]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return a[0];
    }

    /**
     * Find the smallest element in sorted and rotated array (Binary search - recursive)
     *
     * TC: O(logn)
     * SC: O(1)
     */
    public static int minSortedRotated(int[] a, int low, int high) {
        if(low < high) {
            int mid = low + (high-low)/2;
            if(low < mid && a[mid] < a[mid-1]) {
                return a[mid];
            }
            if(high > mid && a[mid] > a[mid+1]) {
                return a[mid+1];
            }
            if(a[mid] < a[high]) {
                return minSortedRotated(a, low, mid-1);
            } else {
                return minSortedRotated(a, mid+1, high);
            }
        }
        return a[low];
    }

    /**
     * Majority element of an array
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int majority(int[] a) {
        int maxCount = Integer.MIN_VALUE;
        int max = a[0];
        for(int i=0; i<a.length; i++) {
            int count = 0;
            for(int j=0; j<a.length; j++) {
                if(a[i] == a[j]) {
                    count++;
                }
            }
            if(count > maxCount) {
                maxCount = count;
                max = a[i];
            }
        }
        if(maxCount > a.length/2) {
            return max;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority element of an array (Using binary search tree)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int majorityBST(int[] a) {
        BinarySearchFrequencyTree bst = new BinarySearchFrequencyTree();
        for(int i=0; i<a.length; i++) {
            bst.insert(a[i]);
        }
        return inOrder(bst.root, a.length);
    }

    private static int inOrder(BSFrequencyNode node, int n) {
        if(node != null) {
            int data = inOrder(node.left, n);
            if(data > Integer.MIN_VALUE){
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
     *
     * TC: O(nlogn), average case
     * SC: O(n)
     */
    public static int majorityUsingSorting(int[] a) {
        Arrays.sort(a);
        int count = 1;
        for(int i=1; i<a.length; i++) {
            if(a[i] == a[i-1]) {
                count++;
            } else {
                count = 1;
            }
            if(count > a.length/2) {
                return a[i];
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority element of an array (Moore's Voting algorithm)
     *
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
        for(int i=1; i<a.length; i++) {
            if(a[i] == a[majorityIndex]) {
                count++;
            } else {
                count--;
            }
            if(count == 0) {
                majorityIndex = i;
                count = 1;
            }
        }
        return a[majorityIndex];
    }

    /**
     * Majority element of an array (Using hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int majorityHashing(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<a.length; i++) {
            if(!map.containsKey(a[i])) {
                map.put(a[i], 1);
            } else {
                map.put(a[i], map.get(a[i]) + 1);
            }
            if(map.get(a[i]) > a.length/2) {
                return a[i];
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Majority element of an array (Using bit manipulation)
     *
     * TC: O(n*k), k is number of bits
     * SC: O(1)
     */
    public static int majorityBitManipulation(int[] a) {
         int majority = 0;
         for(int i=0; i<32; i++) {
             int numZeros = 0;
             int numOnes = 0;
             for(int num : a) {
                 if(((num >> i) & 1) == 1) {
                     numOnes++;
                 } else {
                     numZeros++;
                 }
             }
             if(numOnes > numZeros) {
                 majority |=(1<<i);
             }
         }
         int count = 0;
         for(int num : a) {
             if(num==majority) {
                 count++;
             }
             if(count > a.length/2) {
                 return majority;
             }
         }
         return Integer.MIN_VALUE;
    }

    /**
     * The largest K elements in an array (Using tree sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(n)
     */
    public static void kLargestElementsTreeSort(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for(int i=0; i<a.length; i++) {
            binarySearchTree.insert(a[i]);
        }
        inOrderReverse(binarySearchTree.getRoot(), k);
    }
    private static int count = 0;
    private static void inOrderReverse(BSTNode node, int k) {
        if(node != null) {
            inOrderReverse(node.right, k);
            if(count < k) {
                System.out.print(node.data + " ");
                count++;
            }
            inOrderReverse(node.left, k);
        }
    }

    /**
     * The smallest K elements in an array (Using tree sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(n)
     */
    public static void kSmallestElementsTreeSort(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for(int i=0; i<a.length; i++) {
            binarySearchTree.insert(a[i]);
        }
        inOrder(binarySearchTree.getRoot(), k);
    }
    private static void inOrder(BSTNode node, int k) {
        if(node != null) {
            inOrder(node.left, k);
            if(count < k) {
                System.out.print(node.data + " ");
                count++;
            }
            inOrder(node.right, k);
        }
    }

    /**
     * The largest K elements in an array (Using tree sorting - space optimized)
     *
     * TC: O(nlogk), average case
     * SC: O(k)
     */
    public static void kLargestElementsTreeSortEfficient(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int i = 0;
        for(;i<k;i++) {
            binarySearchTree.insert(a[i]);
        }
        for(;i<a.length;i++) {
            int min = binarySearchTree.minRecursive();
            if(a[i] > min) {
                binarySearchTree.delete(min);
                binarySearchTree.insert(a[i]);
            }
        }
        binarySearchTree.inOrder();
    }

    /**
     * The smallest K elements in an array (Using tree sorting - space optimized)
     *
     * TC: O(nlogk), average case
     * SC: O(k)
     */
    public static void kSmallestElementsTreeSortEfficient(int[] a, int k) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        int i = 0;
        for(;i<k;i++) {
            binarySearchTree.insert(a[i]);
        }
        for(;i<a.length;i++) {
            int max = binarySearchTree.maxRecursive();
            if(a[i] < max) {
                binarySearchTree.delete(max);
                binarySearchTree.insert(a[i]);
            }
        }
        binarySearchTree.inOrder();
    }

    /**
     * The largest K elements in an array (Using sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(1)
     */
    public static void kLargestElementsSorting(int[] a, int k) {
        Arrays.sort(a);
        for(int i=a.length-1; i>=a.length-k; i--) {
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The smallest K elements in an array (Using sorting)
     *
     * TC: O(nlogn), average case
     * SC: O(1)
     */
    public static void kSmallestElementsSorting(int[] a, int k) {
        Arrays.sort(a);
        for (int j : a) {
            System.out.print(j + " ");
        }
    }

    /**
     * The largest K elements in an array (Bubble sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kLargestElementsBubbleSort(int[] a, int k) {
        for(int i=a.length-1; i>=a.length-k; i--) {
            for(int j = 0; j<i; j++) {
                if(a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The smallest K elements in an array (Bubble sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kSmallestElementsBubbleSort(int[] a, int k) {
        for(int i=a.length-1; i>=a.length-k; i--) {
            for(int j = 0; j<i; j++) {
                if(a[j] < a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The largest K elements in an array (Selection sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kLargestElementsSelectionSort(int[] a, int k) {
        int maxIndex;
        for(int i=0; i<k; i++) {
            maxIndex = i;
            for(int j = i+1; j<a.length; j++) {
                if(a[j] > a[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = a[maxIndex];
            a[maxIndex] = a[i];
            a[i] = temp;
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The smallest K elements in an array (Selection sort)
     *
     * TC: O(nk)
     * SC: O(1)
     */
    public static void kSmallestElementsSelectionSort(int[] a, int k) {
        int minIndex;
        for(int i=0; i<k; i++) {
            minIndex = i;
            for(int j = i+1; j<a.length; j++) {
                if(a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
            System.out.print(a[i] + " ");
        }
    }

    /**
     * The largest K elements in an array (Priority Queue)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void kLargestElementsPriorityQueue(int[] a, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0; i<a.length; i++) {
            priorityQueue.add(a[i]);
            if(priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        while(!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.remove() + " ");
        }
    }

    /**
     * The smallest K elements in an array (Priority Queue)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void kSmallestElementsPriorityQueue(int[] a, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i=0; i<a.length; i++) {
            priorityQueue.add(a[i]);
            if(priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        while(!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.remove() + " ");
        }
    }

    /**
     * The largest K elements in an array (Binary search)
     *
     * TC: O(nlog(max-min))
     * SC: O(1)
     */
    public static void kLargestElementsBinarySearch(int[] a, int k) {
        int kthLargest = kthLargestBinarySearch(a, k);
        for(int i : a) {
            if(i >= kthLargest) {
                System.out.print(i + " ");
            }
        }
    }

    private static int kthLargestBinarySearch(int[] a, int k) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i : a) {
            low = Math.min(low, i);
            high = Math.max(high, i);
        }
        int mid = 0;
        while(low <= high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for(int j: a) {
                if(j >= mid) {
                    count ++;
                }
            }
            if(count == k) {
                return mid;
            } else if(count > k) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return mid;
    }

    /**
     * The smallest K elements in an array (Binary search)
     *
     * TC: O(nlog(max-min))
     * SC: O(1)
     */
    public static void kSmallestElementsBinarySearch(int[] a, int k) {
        int kthSmallest = kthSmallestBinarySearch(a, k);
        for(int i : a) {
            if(i <= kthSmallest) {
                System.out.print(i + " ");
            }
        }
    }

    private static int kthSmallestBinarySearch(int[] a, int k) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i : a) {
            low = Math.min(low, i);
            high = Math.max(high, i);
        }
        int mid = 0;
        while(low <= high) {
            mid = low + (high - low) / 2;
            int count = 0;
            for(int j: a) {
                if(j <= mid) {
                    count ++;
                }
            }
            if(count == k) {
                return mid;
            } else if(count < k) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return mid;
    }

    /**
     * The largest K elements in an array (Quick sort)
     *
     * TC: O(n^2), worst case   O(n), average case
     * SC: O(n)
     */
    public static void kLargestElementsQuickSort(int[] a, int k) {
        kthLargestQuickSort(a, 0, a.length-1, k);
        for(int i = a.length-1; i>=a.length-k; i--) {
            System.out.print(a[i] + " ");
        }
    }

    private static int kthLargestQuickSort(int[] a, int l, int r, int k) {
        int pivot = partition(a, l, r, k, false);
        if(pivot == r - k +1) {
            return pivot;
        } else if (pivot > r - k + 1) {
            return kthLargestQuickSort(a, l, pivot-1, k - (r - (pivot - 1)));
        } else {
            return kthLargestQuickSort(a, pivot+1, r, k);
        }
    }

    /**
     * The smallest K elements in an array (Quick sort)
     *
     * TC: O(n^2), worst case   O(n), average case
     * SC: O(n)
     */
    public static void kSmallestElementsQuickSort(int[] a, int k) {
        kthSmallestQuickSort(a, 0, a.length-1, k);
        for(int i = a.length-1; i>=a.length-k; i--) {
            System.out.print(a[i] + " ");
        }
    }

    private static int kthSmallestQuickSort(int[] a, int l, int r, int k) {
        int pivot = partition(a, l, r, k, true);
        if(pivot == r - k +1) {
            return pivot;
        } else if (pivot > r - k + 1) {
            return kthSmallestQuickSort(a, l, pivot-1, k - (r - (pivot - 1)));
        } else {
            return kthSmallestQuickSort(a, pivot+1, r, k);
        }
    }

    private static int partition(int[] a, int l, int r, int k, boolean reverse) {
        int pIndex = l;
        for(int i=l; i<r; i++) {
            if(reverse) {
                if (a[i] >= a[r]) {
                    int temp = a[i];
                    a[i] = a[pIndex];
                    a[pIndex] = temp;
                    pIndex++;
                }
            } else {
                if (a[i] <= a[r]) {
                    int temp = a[i];
                    a[i] = a[pIndex];
                    a[pIndex] = temp;
                    pIndex++;
                }
            }
        }
        int temp = a[pIndex];
        a[pIndex] = a[r];
        a[r] = temp;
        return pIndex;
    }


}

class BinarySearchFrequencyTree {
    BSFrequencyNode root;

    void insert(int data) {
        root = insertUtil(root, data);
    }

    private BSFrequencyNode insertUtil(BSFrequencyNode node, int data) {
        if(node == null) {
            return new BSFrequencyNode(data);
        }
        if(data == node.data) {
            node.frequency ++;
        } else if(data <node.data) {
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
        this.frequency =1;
        this.left = this.right = null;
    }
}
