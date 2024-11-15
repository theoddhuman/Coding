package com.paul.subham.array.operations;

import com.paul.subham.searching.binarysearch.OneD;

import java.util.*;

/**
 * 1. Finding pair with given sum in array
 * 2. Finding pair with given sum in array (Using sorting and two pointer)
 * 3. Finding pair with given sum in array (Using binary search)
 * 4. Finding pair with given sum in array (Using hashing)
 * 5. Finding pair with given sum in array (Using Remainder)
 * 6. 3 sum problem (Hashing)
 * 7. 3 sum problem (Two Pointer)
 * 8. 4 Sum Problem (Hashing)
 * 9. 4 Sum Problem (Two pointer)
 */
public class Sum {
    public static void main(String[] args) {

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
            int searchIndex = OneD.searchRecursive(a, i + 1, a.length - 1, searchKey);
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
     * 3 sum problem (hashing)
     *
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
     * and nums[i] + nums[j] + nums[k] == 0.
     *
     * Notice that the solution set must not contain duplicate triplets.
     *
     * TC: O(n^2 * logk), k-> no of unique triplet count
     * SC: O(n + k)
     */
    public static List<List<Integer>> threeSum(int[] a) {
        int n = a.length;
        Set<List<Integer>> res = new HashSet<>();
        for(int i=0; i<n-1; i++) {
            Set<Integer> hash = new HashSet<>();
            for(int j=i+1; j<n; j++) {
                int third = -(a[i]+a[j]);
                if(hash.contains(third)) {
                    List<Integer> list = Arrays.asList(a[i], a[j], third);
                    Collections.sort(list);
                    res.add(list);
                }
                hash.add(a[j]);
            }
        }
        return new ArrayList<>(res);
    }

    /**
     * 3 sum problem (Two Pointer)
     *
     * TC: O(n^2 * nlogn)
     * SC: O(1)
     */
    public static List<List<Integer>> threeSumTwoPointer(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<n; i++) {
            if(i != 0 && a[i] == a[i-1]) {
                continue;
            }
            int low = i+1;
            int high = n-1;
            while(low < high) {
                int sum = a[low] + a[high] + a[i];
                if(sum < 0) {
                    low++;
                } else if(sum > 0) {
                    high--;
                } else {
                    res.add(Arrays.asList(a[i],a[low],a[high]));
                    low++;
                    high--;
                    while(low < high && a[low] == a[low-1]) {
                        low++;
                    }
                    while(low < high && a[high] == a[high+1]) {
                        high--;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 4 Sum Problem (Hashing)
     *
     * TC: (O(n^3 + logk)), k is no of unique quadruple
     * SC: O(n+k)
     */
    public static List<List<Integer>> fourSum(int[] a, int target) {
        int n = a.length;
        Set<List<Integer>> res = new HashSet<>();
        for(int i=0; i<n-2; i++) {
            for(int j= i+1; j<n-1; j++) {
                Set<Long> hash = new HashSet<>();
                for(int k=j+1; k<n; k++) {
                    long sum = a[i] + a[j];
                    sum += a[k];
                    long fourth = target - sum;
                    if (hash.contains(fourth)) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(a[i]);
                        temp.add(a[j]);
                        temp.add(a[k]);
                        temp.add((int) fourth);
                        temp.sort(Integer::compareTo);
                        res.add(temp);
                    }
                    hash.add((long) a[k]);
                }
            }
        }
        return new ArrayList<>(res);
    }

    /**
     * 4 Sum Problem (Two pointer)
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public List<List<Integer>> fourSumTwoPointer(int[] a, int target) {
        int n = a.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(a);
        for(int i=0; i<n-2; i++) {
            if(i > 0 && a[i] == a[i-1]) {
                continue;
            }
            for(int j= i+1; j<n-1; j++) {
                if(j > i+1 && a[j] == a[j-1]) {
                    continue;
                }
                int low = j+1;
                int high = n-1;
                while(low < high) {
                    long sum = a[i] + a[j];
                    sum += a[low];
                    sum += a[high];
                    if(sum < target) {
                        low++;
                    } else if (sum > target) {
                        high--;
                    } else {
                        res.add(Arrays.asList(a[i],a[j],a[low],a[high]));
                        low++;
                        high--;
                        while(low < high && a[low] == a[low-1]) {
                            low++;
                        }
                        while(low < high && a[high] == a[high+1]){
                            high--;
                        }
                    }
                }

            }
        }
        return res;
    }
}
