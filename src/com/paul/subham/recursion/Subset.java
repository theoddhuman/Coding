package com.paul.subham.recursion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 *
 * 1. Sum of all subsets (Sorting)
 * 2. Sum of all subsets 2
 * 3. All unique subsets of an array with duplicate elements
 * 4. All unique subsets of an array with duplicate elements (Add subset at each iteration)
 * 5. Combination Sum I
 * 6. Combination Sum I(Striver's)
 * 7. Combination Sum II
 * 8. Generate binary nos without consecutive 1's
 * 9. Generate all possible valid parenthesis
 * 10. All possible subsets of an array (Recursion)
 * 11. All possible subsets of an array (Bit manipulation)
 * 12. Sum of all subsets
 * 13. All possible subsets of an array - duplicate subset not allowed (Using set)
 * 14. All possible subsets of an array - duplicate subset not allowed (Efficient)
 * 15. Combination Sum 3
 * 16. Letter Combinations of a Phone Number (Recursion)
 * 17. Letter Combinations of a Phone Number (BFS)
 */
public class Subset {
    public static void main(String[] args) {
        int[] a = {2,3,5};
        System.out.println(generateBinaryNos(4));
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
     * Sum of all subsets
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
     * Combination Sum I
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
     * Combination Sum I (Striver's)
     *
     * Given an array of distinct integers candidates and a target integer target,
     * return a list of all unique combinations of candidates where the chosen numbers sum to target.
     * You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times.
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

    /**
     * Combination Sum II
     *
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sum to target. Each number in candidates may only be used once in the combination.
     * The solution set must not contain duplicate combinations.
     *
     * Input: candidates = [1,1,2,7,6,1,5], target = 8
     * Output:[[1,1,6],[1,2,5],[1,7],[2,6]]
     *
     * TC: O(2^n*k+n^2) ~ O(2^n), 2^n is required to traverse all subsets, n^2 is required to sort array, k is avg size of subset or combination
     * SC: O(kx + n), x is total possible combinations
     */
    public List<List<Integer>> combinationSum2(int[] a, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(a);
        combinationSum2(a,0,target,res,new ArrayList<>());
        return res;
    }

    private static void combinationSum2(int[] a, int i, int target, List<List<Integer>> res, List<Integer> combination) {
        if(target == 0) {
            res.add(new ArrayList<>(combination));
            return;
        }
        if(i==a.length) {
            return;
        }
        for(int j = i; j<a.length; j++) {
            if(j > i && a[j] == a[j-1]) {
                continue;
            }
            if(a[j] > target) {
                break;
            }
            combination.add(a[j]);
            combinationSum2(a,j+1,target-a[j],res,combination);
            combination.remove(combination.size()-1);
        }
    }

    /**
     * Generate binary nos without consecutive 1's
     *
     * TC: O(2^k)
     * SC: O(k)
     */
    public static List<String> generateBinaryNos(int k) {
        List<String> res = new ArrayList<>();
        generate("0", k, res);
        generate("1",k,res);
        return res;
    }

    private static void generate(String s, int k, List<String> res) {
        if(k == s.length()) {
            res.add(s);
            return;
        }
        int sLen = s.length();
        if(s.charAt(sLen-1) == '0') {
            generate(s+'0', k, res);
            generate(s+'1', k, res);
        } else {
            generate(s+'0', k, res);
        }
    }

    /**
     * Generate all possible valid parenthesis
     *
     * TC: O(2^n)
     * SC: O(n)
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis("", 0, 0, n, res);
        return res;
    }

    private static void generateParenthesis(String s, int o, int c, int n, List<String> res) {
        if(c==n) {
            res.add(s);
            return;
        }
        if(o < n) {
            generateParenthesis(s+"(", o+1, c, n,res);
        }
        if(o>c) {
            generateParenthesis(s+")", o, c+1, n, res);
        }
    }

    /**
     * All possible subsets of an array (Recursion) - Power set
     *
     * TC: O(2^n)
     * SC: O(n)
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsets(nums, nums.length, new ArrayList<>(), res);
        return res;
    }

    private static void subsets(int[] a, int i, List<Integer> subSet, List<List<Integer>> res) {
        if(i==0) {
            List<Integer> resList = new ArrayList<>(subSet);
            res.add(resList);
            return;
        }
        subSet.add(a[i-1]);
        subsets(a, i-1, subSet, res);
        subSet.remove(subSet.size()-1);
        subsets(a, i-1, subSet, res);
    }

    /**
     * All possible subsets of an array (Bit manipulation)
     *
     * TC: O(2^n)
     * SC: O(1)
     */
    public static List<List<Integer>> subsetsBitManipulation(int[] a) {
        List<List<Integer>> res = new ArrayList<>();
        int n = a.length;
        for(int i=0; i<(1<<n); i++) {
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<n; j++) {
                if((i & (1<<j)) != 0) {
                    list.add(a[j]);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * Sum of all subsets
     *
     * TC: O(2^n)
     * SC: O(2^n)
     */
    public static ArrayList<Integer> subSetSum(ArrayList<Integer> arr, int n) {
        ArrayList<Integer> res = new ArrayList<>();
        subsetSum(arr,n,0,res);
        return res;
    }

    private  static void subsetSum(ArrayList<Integer> arr, int i, int sum, List<Integer> res) {
        if(i==0) {
            res.add(sum);
            return;
        }
        subsetSum(arr,i-1,sum+arr.get(i-1),res);
        subsetSum(arr,i-1,sum,res);
    }

    /**
     * All possible subsets of an array - duplicate subset not allowed (Using set)
     *
     * TC: O(2^n*k*logx), where x is no of possible combinations.
     * SC: O(2^n*k)
     */
    public static  List<List<Integer>> subsetsWithDupUsingSet(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> subSet = new ArrayList<>();
        Arrays.sort(nums);
        powerSets(nums, 0, nums.length, result, subSet);
        return new ArrayList<>(result);
    }

    private static void powerSets(int[] nums, int index, int n, Set<List<Integer>> result, List<Integer> subSet) {
        if(n==index) {
            result.add(new ArrayList<>(subSet));
            return;
        }
        powerSets(nums, index+1, n, result, subSet);
        subSet.add(nums[index]);
        powerSets(nums, index+1, n, result, subSet);
        subSet.remove(subSet.size()-1);
    }

    /**
     * All possible subsets of an array - duplicate subset not allowed (Efficient)
     *
     * TC: O(2^n*k)
     * SC: O(2^n*k)
     */
    public static List<List<Integer>> subsetsWithDup1(int[] a) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(a);
        subsetWithDup(a,0,res, new ArrayList<>());
        //res.add(new ArrayList<>());
        return res;
    }

    private static void subsetWithDup(int[] a, int i, List<List<Integer>> res, List<Integer> subset) {
        res.add(new ArrayList<>(subset));
        for(int j=i; j<a.length;j++) {
            if(j>i && a[j-1] == a[j]) {
                continue;
            }
            subset.add(a[j]);
            subsetWithDup(a, j+1, res, subset);
            subset.remove(subset.size()-1);
        }
    }

    /**
     * Combination Sum 3
     *
     * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
     * Only numbers 1 through 9 are used.
     * Each number is used at most once.
     * Return a list of all possible valid combinations.
     * The list must not contain the same combination twice, and the combinations may be returned in any order.
     *
     * Input: k = 3, n = 9
     * Output: [[1,2,6],[1,3,5],[2,3,4]]
     *
     * TC: O(2^9*k)
     * SC: O(2^9*k)
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3(1, k, n, res, new ArrayList<>());
        return res;
    }

    private static void combinationSum3(int i, int k, int target, List<List<Integer>> res, List<Integer> combination) {
        if (target == 0 && combination.size() == k) {
            res.add(new ArrayList<>(combination));
            return;
        }
        if (i == 10 || combination.size() == k) {
            return;
        }
        if (i <= target) {
            combination.add(i);
            combinationSum3(i + 1, k, target - i, res, combination);
            combination.remove(combination.size() - 1);
        }
        combinationSum3(i + 1, k, target, res, combination);
    }

    /**
     * Letter Combinations of a Phone Number (Recursion)
     *
     * Given a string containing digits from 2-9 inclusive,
     * return all possible letter combinations that the number could represent.
     * Return the answer in any order.
     *
     * TC: O(4^n)
     * SC: O(4^n)
     */
    public static List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Character, List<Character>> digitMap = new HashMap<>();
        digitMap.put('2', List.of('a','b','c'));
        digitMap.put('3', List.of('d','e','f'));
        digitMap.put('4', List.of('g','h','i'));
        digitMap.put('5', List.of('j','k','l'));
        digitMap.put('6', List.of('m','n','o'));
        digitMap.put('7', List.of('p','q','r','s'));
        digitMap.put('8', List.of('t','u','v'));
        digitMap.put('9', List.of('w','x','y','z'));
        List<String> res = new ArrayList<>();
        lc(digits,0,digitMap,"",res);
        return res;
    }

    private static void lc(String digits, int i, Map<Character, List<Character>> digitMap,String s, List<String> res) {
        if(i==digits.length()) {
            res.add(s);
            return;
        }
        char c = digits.charAt(i);
        for(Character ch : digitMap.get(c)) {
            lc(digits, i+1, digitMap, s+ch, res);
        }
    }

    /**
     * Letter Combinations of a Phone Number (BFS)
     *
     * Given a string containing digits from 2-9 inclusive,
     * return all possible letter combinations that the number could represent.
     * Return the answer in any order.
     *
     * TC: O(4^n)
     * SC: O(4^n)
     */
    public static List<String> letterCombinationsBFS(String a) {
        if(a.isEmpty()) {
            return new ArrayList<>();
        }
        String[] mp = { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        List<String> res = new LinkedList<>();
        Queue<String> q = new LinkedList<>();
        q.add("");
        while (!q.isEmpty()) {
            String s = q.poll();
            if (s.length() == a.length()) {
                res.add(s);
            } else {
                String chars = mp[Character.getNumericValue(a.charAt(s.length()))];
                for (char c : chars.toCharArray()) {
                    q.add(s + c);
                }
            }
        }
        return res;
    }

}
