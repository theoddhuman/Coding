package com.paul.subham.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author subham.paul
 *
 * 1. Sum of all subsets (Sorting)
 * 2. Sum of all subsets
 * 3. All unique subsets of an array with duplicate elements
 * 4. All unique subsets of an array with duplicate elements (Add subset at each iteration)
 * 5. Combination with sum k from an array
 * 6. Combination with sum k from an array (Striver's)
 */
public class Subset {
    public static void main(String[] args) {
        int[] a = {2,3,5};
        System.out.println(combinationSumStriver(a, 8));
    }

    /**
     * Sum of all subsets
     * [5,2,1] -> [0,1,2,3,5,6,7,8]
     *
     * TC: O(2^n)
     * SC: O(2^n)
     */
    public static ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int n) {
        Collections.sort(arr);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        for(int i=0; i<arr.size(); i++) {
            subSetSum(arr,i,n,0,result);
        }
        return result;
    }

    private static void subSetSum(ArrayList<Integer> arr, int i, int n, int count, ArrayList<Integer> result) {
        if(i==n) {
            return;
        }
        int sum = count + arr.get(i);
        result.add(sum);
        for(int j=i+1; j<arr.size(); j++) {
            subSetSum(arr, j, n, sum, result);
        }
    }
    /**
     * Sum of all subsets 2
     * [5,2,1] -> [0,1,2,3,5,6,7,8]
     *
     * TC: O(2^n)
     * SC: O(2^n)
     */

    public static ArrayList<Integer> subsetSum(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        subSetSumUtil(arr,0,n,0,result);
        return result;
    }

    private static void subSetSumUtil(ArrayList<Integer> arr, int i, int n, int sum, ArrayList<Integer> result) {
        if(i==n) {
            result.add(sum);
            return;
        }
        subSetSumUtil(arr, i+1, n, sum+arr.get(i), result);
        subSetSumUtil(arr, i+1, n, sum, result);
    }

    /**
     * All unique subsets of an array with duplicate elements
     *
     * Given an array of integers that may contain duplicates the task is to return all possible subsets.
     * Return only unique subsets, and they can be in any order.
     * [1,2,2] -> [ [ ],[1],[1,2],[1,2,2],[2],[2,2] ]
     *
     * TC: O(2^n*k+n^2) ~ O(2^n*k), 2^n is required to traverse all subsets, n^2 is required to sort array, k is average size of subset
     * SC: O(2^n*k)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> subSet = new ArrayList<>();
        Arrays.sort(nums);
        powerSet(nums, 0, nums.length, result, subSet);
        return new ArrayList<>(result);
    }

    private void powerSet(int[] nums, int index, int n, Set<List<Integer>> result, List<Integer> subSet) {
        if(n==index) {
            result.add(new ArrayList<>(subSet));
            return;
        }
        powerSet(nums, index+1, n, result, subSet);
        subSet.add(nums[index]);
        powerSet(nums, index+1, n, result, subSet);
        subSet.remove(subSet.size()-1);
    }

    /**
     * All unique subsets of an array with duplicate elements (Add subset at each iteration)
     *
     * TC: O(2^n*k+n^2) ~ O(2^n), 2^n is required to traverse all subsets, n^2 is required to sort array
     * SC: O(2^n*k)
     */
    public static List<List<Integer>> subsetDuplicate(int[] arr, int n) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        powerSet(arr, 0 ,n, result, new ArrayList<>());
        return result;
    }

    private static void powerSet(int[] arr, int index, int n, List<List<Integer>> result, List<Integer> subSet) {
        result.add(new ArrayList<>(subSet));
        for(int i=index; i<n; i++) {
            if(i != index && arr[i] == arr[i-1]) {
                continue;
            }
            subSet.add(arr[i]);
            powerSet(arr, i+1, n, result, subSet);
            subSet.remove(subSet.size() - 1);
        }
    }

    /**
     * Combination with sum k from an array
     *
     * Given an array of distinct integers and a target, you have to return the list of all unique combinations where the chosen numbers sum to target.
     * You may return the combinations in any order.
     *
     * TC: O(2^n*k+n^2) ~ O(2^n), 2^n is required to traverse all subsets, n^2 is required to sort array, k is avg size of subset or combination
     * SC: O(2^n)
     */
    public static List<List<Integer>> combinationSum(int[] a, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(a);
        combinationSumUtil(a, target, 0, 0, result, new ArrayList<>());
        return result;
    }

    private static void combinationSumUtil(int[] a, int target, int index, int sum, List<List<Integer>> result, List<Integer> combination) {
        for(int i=index; i<a.length; i++) {
            sum += a[i];
            if(sum>target) {
                break;
            }
            combination.add(a[i]);
            if(sum == target) {
                result.add(new ArrayList<>(combination));
                combination.remove(combination.size()-1);
                break;
            }
            combinationSumUtil(a, target, i, sum, result, combination);
            combination.remove(combination.size()-1);
            sum-=a[i];
        }
    }

    /**
     * Combination with sum k from an array (Striver's)
     *
     * TC: O(2^n*k+n^2) ~ O(2^n), 2^n is required to traverse all subsets, n^2 is required to sort array, k is avg size of subset or combination
     * SC: O(2^n)
     */
    public static List<List<Integer>> combinationSumStriver(int[] a, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumStriver(a, target, 0, result, new ArrayList<>());
        return result;
    }

    private static void combinationSumStriver(int[] a, int target, int index, List<List<Integer>> result, List<Integer> combination) {
        if(a.length == index) {
            if(target == 0) {
                result.add(new ArrayList<>(combination));
            }
            return;
        }
        if(a[index] <= target) {
            combination.add(a[index]);
            combinationSumStriver(a, target-a[index], index, result, combination);
            combination.remove(combination.size()-1);
        }
        combinationSumStriver(a, target, index+1, result, combination);
    }
}
