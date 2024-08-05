package com.paul.subham.recursion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author subham.paul
 *
 * 1. Sum of all subsets (Sorting)
 * 2. Sum of all subsets
 */
public class Subset {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(5);
        al.add(2);
        al.add(1);
        System.out.println(subsetSum(al, 3));
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
}
