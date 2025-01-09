package com.paul.subham.slidingwindow;

import java.util.*;

/**
 * @author subham.paul
 *
 * 1. Sliding window maximum sum
 * 2. Sliding window maximum sum (efficient)
 * 3. Sliding window maximum
 * 4. Sliding window maximum (Using priority queue)
 * 5. Sliding window maximum (Using priority dequeue)
 * 6. Sliding window maximum (Using stack)
 * 7. Maximum Consecutive 1's III
 * 8. Maximum Consecutive 1's III (Sliding window)
 * 9. Maximum Consecutive 1's III (Sliding window - optimized)
 * 10. Longest subarray with 2 types of numbers
 * 11. Longest subarray with 2 types of numbers (Sliding window)
 * 12. Longest subarray with 2 types of numbers (Sliding window - optimized)
 * 13. Longest Repeating Character Replacement
 * 14. Longest Repeating Character Replacement (Sliding Window)
 * 15. Longest Repeating Character Replacement (Sliding Window - Optimized)
 * 16. Count Binary Sub arrays With Sum
 * 17. Count Number of Nice sub arrays
 */
public class SubArray {
    public static void main(String[] args) {
        int[] a = { 1,1,5,6,7,8,9};
        System.out.println(totalFruitsSW(a));
    }

    /**
     * Sliding window maximum sum
     *
     * TC: O(n*k)
     * SC: O(1)
     */
    public static int slidingWindowMaxSum(int[] a, int n, int k) {
        if(n < k) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        for(int i=0; i<n-k; i++) {
            int sum = 0;
            for(int j = i; j<i+k; j++) {
                sum+=a[j];
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * Sliding window maximum sum (efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int slidingWindowMaxSumEfficient(int[] a, int n, int k) {
        if(n < k) {
            return -1;
        }
        int sum = 0;
        for(int i=0; i<k; i++) {
            sum += a[i];
        }
        int max = sum;
        for(int i=k; i<n; i++) {
            sum  = sum + a[i] - a[i-k];
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * Sliding window maximum
     *
     * TC: O(n*k)
     * SC: O(1)
     */
    public static List<Integer> slidingWindowMax(int[] a, int n, int k) {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<=n-k; i++) {
            int max = Integer.MIN_VALUE;
            for(int j=i; j<i+k; j++) {
                max = Math.max(max, a[j]);
            }
            result.add(max);
        }
        return result;
    }

    /**
     * Sliding window maximum (Using priority queue)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static int[] maxSlidingWindowPQ(int[] a, int k) {
        int n = a.length;
        int[] res = new int[n-k+1];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((i,j)->a[j]-a[i]);
        int i=0;
        for(;i<k;i++) {
            pq.add(i);
        }
        int j=0;
        res[j++] = a[pq.peek()];
        for(i=k;i<n;i++) {
            pq.add(i);
            while(pq.peek() <= i-k) {
                pq.remove();
            }
            res[j++] = a[pq.peek()];
        }
        return res;
    }

    /**
     * Sliding window maximum (Using priority dequeue)
     *
     * TC: O(nlogn)
     * SC: O(k)
     */
    public static List<Integer> slidingWindowMaxUsingDeque(int[] a, int n, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<k; i++) {
            while (!deque.isEmpty() && a[i] > a[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        for(int i=k; i<n; i++) {
            result.add(a[deque.peek()]);
            while(!deque.isEmpty() && deque.peek() <= i-k) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && a[i] > a[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        result.add(a[deque.pop()]);
        return result;
    }

    /**
     * Sliding window maximum (Using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static List<Integer> slidingWindowMaxUsingStack(int[] a, int n, int k) {
        Stack<Integer> stack = new Stack<>();
        int[] maxUpto = new int[n];
        stack.push(0);
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && a[stack.peek()] < a[i]) {
                maxUpto[stack.peek()] = i-1;
                stack.pop();
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            maxUpto[stack.pop()] = n-1;
        }
        List<Integer> result = new ArrayList<>();
        int j=0;
        for(int i=0; i<=n-k; i++) {
            while(j<i || maxUpto[j] < i+k-1){
                j++;
            }
            result.add(a[j]);
        }
        return result;
    }

    /**
     * Maximum Consecutive 1's III
     *
     * Given a binary array a and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int longestOnes(int[] a, int k) {
        int n = a.length;
        int max = 0;
        for(int i=0; i<n; i++) {
            int zeroCount = 0;
            for(int j=i; j<n; j++) {
                if(a[j] == 0) {
                    zeroCount++;
                }
                if(zeroCount <= k) {
                    max = Math.max(max, j-i+1);
                } else {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * Maximum Consecutive 1's III (Sliding window)
     * <p>
     * TC: O(2n)
     * SC: O(1)
     */
    public static int longestOnesSW(int[] a, int k) {
        int n = a.length;
        int max = 0;
        int zeroCount = 0;
        int start = 0;
        for(int i=0; i<n; i++) {
            if(a[i] == 0) {
                zeroCount++;
            }
            if(zeroCount <= k) {
                max = Math.max(max,i-start+1);
            }
            while(zeroCount > k) {
                if(a[start] == 0) {
                    zeroCount--;
                }
                start++;
            }
        }
        return max;
    }

    /**
     * Maximum Consecutive 1's III (Sliding window - optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int longestOnesSWOpt(int[] a, int k) {
        int n = a.length;
        int max = 0;
        int zeroCount = 0;
        int start = 0;
        for(int i=0; i<n; i++) {
            if(a[i] == 0) {
                zeroCount++;
            }
            if(zeroCount <= k) {
                max = Math.max(max,i-start+1);
            }
            if(zeroCount > k) {
                if(a[start] == 0) {
                    zeroCount--;
                }
                start++;
            }
        }
        return max;
    }

    /**
     * Longest subarray with 2 types of numbers
     *
     * a = [1,2,3,4,5,3,2,2,3]
     * output: 4 [3,2,2,3]
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int totalFruits(Integer[] a) {
        int max = 0;
        int n = a.length;
        for(int i=0; i<n; i++) {
            Set<Integer> set = new HashSet<>();
            for(int j=i; j<n; j++) {
                set.add(a[j]);
                if(set.size() <= 2) {
                    max = Math.max(max, j-i+1);
                } else {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * Longest subarray with 2 types of numbers (Sliding window)
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public static int totalFruitsSW(int[] a) {
        int max = 0;
        int n = a.length;
        int start = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            if(map.size() <= 2) {
                max = Math.max(max, i-start+1);
            }
            while(map.size() > 2) {
                map.put(a[start], map.get(a[start])-1);
                if(map.get(a[start])==0) {
                    map.remove(a[start]);
                }
                start++;
            }
        }
        return max;
    }

    /**
     * Longest subarray with 2 types of numbers (Sliding window - optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int totalFruitsSWOptimized(Integer[] a) {
        int max = 0;
        int n = a.length;
        int start = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
            if(map.size() <= 2) {
                max = Math.max(max, i-start+1);
            }
            if(map.size() > 2) {
                map.put(a[start], map.get(a[start])-1);
                if(map.get(a[start])==0) {
                    map.remove(a[start]);
                }
                start++;
            }
        }
        return max;
    }

    /**
     * Longest Repeating Character Replacement
     *
     * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character.
     * You can perform this operation at most k times.
     *
     * Return the length of the longest substring containing the same letter you can get after performing the above operations.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int characterReplacement(String s, int k) {
        int n = s.length();
        int max = 0;
        int maxFreq = 0;
        for(int i=0; i<n; i++) {
            int[] freq = new int[26];
            for(int j=i; j<n; j++) {
                char c = s.charAt(j);
                freq[c-'A']++;
                maxFreq = Math.max(maxFreq, freq[c-'A']);
                int changes = (j-i+1)-maxFreq;
                if(changes <= k) {
                    max = Math.max(max, j-i+1);
                } else {
                    break;
                }
            }
        }
        return max;
    }

    /**
     * Longest Repeating Character Replacement (Sliding Window)
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public int characterReplacementSW(String s, int k) {
        int n = s.length();
        int max = 0;
        int maxFreq = 0;
        int start = 0;
        int[] freq = new int[26];
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            freq[c-'A']++;
            maxFreq = Math.max(maxFreq, freq[c-'A']);
            int changes = (i-start+1)-maxFreq;
            if(changes <= k) {
                max = Math.max(max, i-start+1);
            }
            while(changes > k) {
                int startc = s.charAt(start);
                freq[startc-'A']--;
                maxFreq = Math.max(maxFreq, freq[startc-'A']);
                start++;
                changes = (i-start+1)-maxFreq;
            }
        }
        return max;
    }

    /**
     * Longest Repeating Character Replacement (Sliding Window - Optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int characterReplacementSWOptimized(String s, int k) {
        int n = s.length();
        int max = 0;
        int maxFreq = 0;
        int start = 0;
        int[] freq = new int[26];
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            freq[c-'A']++;
            maxFreq = Math.max(maxFreq, freq[c-'A']);
            int changes = (i-start+1)-maxFreq;
            if(changes <= k) {
                max = Math.max(max, i-start+1);
            }
            if(changes > k) {
                int startc = s.charAt(start);
                freq[startc-'A']--;
                maxFreq = Math.max(maxFreq, freq[startc-'A']);
                start++;
                changes = (i-start+1)-maxFreq;
            }
        }
        return max;
    }

    /**
     * Count Binary Sub arrays With Sum
     *
     * Given a binary array 'a' and an integer goal, return the number of non-empty sub arrays with a sum goal.
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public static int numSubArraysWithSum(int[] a, int goal) {
        return countBinarySubArrayWithSumGteK(a, goal)- countBinarySubArrayWithSumGteK(a, goal-1);
    }

    public static int countBinarySubArrayWithSumGteK(int[] a, int k) {
        if(k < 0) {
            return 0;
        }
        int n = a.length;
        int count = 0;
        int start = 0;
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += a[i];
            while(sum > k) {
                sum -= a[start];
                start++;
            }
            count += (i - start + 1);
        }
        return count;
    }

    /**
     * Count Number of Nice sub arrays
     *
     * Given an array of integers 'a' and an integer k. A continuous sub array is called nice if there are k odd numbers on it.
     *
     * TC: O(2n)
     * SC: O(1)
     */
    public int numberOfSubArrays(int[] a, int k) {
        return countSubArrayWithOddCountGteK(a, k) - countSubArrayWithOddCountGteK(a, k-1);
    }

    public static int countSubArrayWithOddCountGteK(int[] a, int k) {
        if(k < 0) {
            return 0;
        }
        int n = a.length;
        int count = 0;
        int start = 0;
        int oddCount = 0;
        for(int i=0; i<n; i++) {
            oddCount += (a[i]%2);
            while(oddCount > k) {
                oddCount -= (a[start]%2);
                start++;
            }
            count += (i - start + 1);
        }
        return count;
    }
}
