package com.paul.subham.classic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Sliding window maximum sum
 * 2. Sliding window maximum sum (efficient)
 * 3. Sliding window maximum
 * 4. Sliding window maximum (Using priority queue)
 * 5. Sliding window maximum (Using priority dequeue)
 * 6. Sliding window maximum (Using stack)
 * 7. Largest Sum Contiguous Sub array
 * 8. Largest Sum Contiguous Sub array (Improved)
 * 9. Largest Sum Contiguous Sub array (Dynamic Programming)
 * 10. Largest Sum Contiguous Sub array (Dynamic Programming - Space optimized)
 * 11. Largest Sum Contiguous Sub array (Kadane's algorithm)
 */
public class SubArray {
    public static void main(String[] args) {
        int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
        System.out.println(maxContiguousSumKadane(a, a.length));
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
    public static List<Integer> slidingWindowMaxUsingPQ(int[] a, int n, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> a[o2] - a[o1]);
        for(int i = 0; i<k; i++) {
            priorityQueue.add(i);
        }
        result.add(a[priorityQueue.peek()]);
        for(int i=k; i<n; i++) {
            priorityQueue.add(i);
            while(priorityQueue.peek() <= i-k) {
                priorityQueue.poll();
            }
            result.add(a[priorityQueue.peek()]);
        }
        return result;
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
     * Largest Sum Contiguous Sub array
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public static int maxContiguousSum(int[] a, int n) {
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            for(int j=i; j<n; j++) {
                int sum = 0;
                for(int k = i; k<=j; k++) {
                    sum += a[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    /**
     * Largest Sum Contiguous Sub array (Improved)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int maxContiguousSumImproved(int[] a, int n) {
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            int sum = 0;
            for(int j=i; j<n; j++) {
                sum += a[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    /**
     * Largest Sum Contiguous Sub array (Dynamic Programming)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxContiguousSumDP(int[] a, int n) {
        int[] dp = new int[n];
        dp[0] = a[0];
        int maxSum = dp[0];
        for(int i=1; i<n; i++) {
            dp[i] = Math.max(dp[i-1] + a[i], a[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    /**
     * Largest Sum Contiguous Sub array (Dynamic Programming - Space optimized)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxContiguousSumDPSpaceOptimized(int[] a, int n) {
        int sum = a[0];
        int maxSum = sum;
        for(int i=1; i<n; i++) {
            sum = Math.max(sum + a[i], a[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    /**
     * Largest Sum Contiguous Sub array (Kadane's algorithm)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int maxContiguousSumKadane(int[] a, int n) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += a[i];
            maxSum = Math.max(sum, maxSum);
            if(sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }
}
