package com.paul.subham.greedy;


import java.util.*;

/**
 * @author subham.paul
 *
 * 1. Maximum meetings possible in one room based on start and end time
 * 2. Minimum no of platform required for given train timings
 * 3. Minimum no of platform required for given train timings (Optimized)
 * 4. Job sequencing problem
 * 5. Fractional Knapsack Problem
 * 6. Assign cookies
 * 7. Minimum no of coins
 * 8. Lemonade change
 * 9. Valid parenthesis string (Memoization)
 * 10. Valid parenthesis string (Efficient)
 * 11. Jump game
 * 12. Jump game II (Memoization)
 * 13. Jump game II (Efficient)
 * 14. Candy
 * 15. Candy (Efficient)
 * 16. Candy (Constant space)
 * 17. Shortest job first - CPU Scheduling
 * 18. Merge overlapping intervals (Using stack)
 * 19. Merge overlapping intervals (Efficient)
 * 20. Insert interval and merge overlapping intervals
 * 21. Non-overlapping Intervals
 */
public class Classic {
    public static void main(String[] args) {
        int[] arr ={900,945,955,1100,1500,1800};
        int[] dep={920,1200,1130,1150,1900,2000};
        System.out.println(coinChange(121));
    }

    /**
     * Maximum meetings possible in one room with given start and end time
     *
     * There is one meeting room in a firm. You are given two arrays, start and end each of size N.
     * For an index ‘i’, start[i] denotes the starting time of the ith meeting
     * while end[i] will denote the ending time of the ith meeting.
     * Find the maximum number of meetings that can be accommodated
     * if only one meeting can happen in the room at a  particular time. Print the order in which these meetings will be performed.
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static int maxMeetings(int n, int[] start, int[] end) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> end[a]));
        for(int i=0; i<n; i++) {
            priorityQueue.add(i);
        }
        int x = priorityQueue.poll();
        int count = 1;
        while(!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll();
            if(start[current] > end[x]) {
                count++;
                x = current;
            }
        }
        return count;
    }

    /**
     * Minimum no of platform required for given train timings
     *
     * Given two arrays that represent the arrival and departure times of trains that stop at the platform.
     * Need to find the minimum number of platforms needed at the railway station so that no train has to wait.
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int maxPlatform(int[] arr, int[] dep, int n) {
        int maxPlatform = 1;
        for(int i=0; i<n-1; i++) {
            int platform = 1;
            for(int j=i+1; j<n; j++) {
                if((arr[i] >= arr[j] && arr[i] <= dep[j]) || (arr[j] >= arr[i] && arr[j] <= dep[i])) {
                    platform++;
                    maxPlatform = Math.max(maxPlatform, platform);
                }
            }
        }
        return maxPlatform;
    }

    /**
     * Minimum no of platform required for given train timings (Optimized)
     *
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static int findPlatform(int[] arr, int[] dep, int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int platform = 0;
        int i = 0;
        int j = 0;
        int max = 1;
        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platform++;
                i++;
            } else if (arr[i] > dep[j]) {
                platform--;
                j++;
            }
            max = Math.max(platform, max);
        }
        return max;
    }

    /**
     * Job sequencing problem
     *
     * Given a set of N jobs where each job comes with a deadline and profit.
     * The profit can only be earned upon completing the job within its deadline.
     * Find the number of jobs done and the maximum profit that can be obtained.
     * Each job takes a single unit of time and only one job can be performed at a time.
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void JobScheduling(Job[] arr, int n) {
        Arrays.sort(arr, (job1, job2) -> job2.profit - job1.profit);
        int maxDeadline = 0;
        for (Job job : arr) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }
        int maxProfit = 0;
        int jobCompleted = 0;
        boolean[] visited = new boolean[maxDeadline+1];
        for(int i=0; i<arr.length; i++) {
            for(int j = arr[i].deadline; j>0; j--) {
                if(!visited[j]) {
                    maxProfit += arr[i].profit;
                    jobCompleted++;
                    visited[j] = true;
                    break;
                }
            }
        }
        System.out.println("max profit: " + maxProfit + " Job: " + jobCompleted);
    }

    /**
     * Fractional Knapsack Problem
     *
     * The weight of N items and their corresponding values are given.
     * We have to put these items in a knapsack of weight W such that the total value obtained is maximized.
     * We can either take the item as a whole or break it into smaller units.
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    double fractionalKnapsack(int w, Item[] arr) {
        Arrays.sort(arr, (item1, item2) -> {
            double w2 = item2.value/(1.0*item2.weight);
            double w1 = item1.value/(1.0*item1.weight);
            if(w2 == w1) {
                return 0;
            }
            if(w2 > w1) {
                return 1;
            }
            return -1;
        });
        double max = 0;
        for(int i=0; i<arr.length && w>0; i++) {
            if(arr[i].weight <= w) {
                max += arr[i].value;
                w -= arr[i].weight;
            } else {
                double weight = w/(1.0 * arr[i].weight);
                max += arr[i].value * weight;
                w = 0;
            }
        }
        return max;
    }

    /**
     * Assign cookies
     *
     * Given two arrays representing children’s green factor and cookie sizes, the goal is to maximise the number of content children.
     *
     * Each child i has a greed factor of g[i], which is the minimum size of a cookie that will make the child content.
     * Each cookie j has a size of s[j].
     * If s[j] >= g[j], we can assign cookie j to child i, making the child content.
     * Each child can only receive one cookie.
     *
     * TC: O(nlogn + mlogm + max(m,n))
     * SC: O(1)
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;
        int count = 0;
        int sizeIndex = 0;
        while(sizeIndex < n && count < m) {
            if(g[count] <= s[sizeIndex]) {
                count++;
            }
            sizeIndex++;
        }
        return count;
    }

    /**
     * Minimum no of coins
     *
     * Given a value V, if we want to make a change for V Rs,
     * and we have an infinite supply of each of the denominations in Indian currency,
     * i.e., we have an infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000} valued coins/notes,
     * what is the minimum number of coins and/or notes needed to make the change.
     *
     * TC: O(k)
     * SC: O(1)
     */
    public static int coinChange(int k) {
        int[] a = {1,2,5,10,20,50,100,200,500,2000};
        int n = a.length;
        int count = 0;
        int i = n-1;
        while(i>=0 && k>0) {
            if(a[i] <= k) {
                count++;
                k -= a[i];
            } else {
                i--;
            }
        }
        return count == 0? -1:count;
    }

    /**
     * Lemonade change
     *
     * Given an array representing a queue of customers and the value of bills they hold,
     * determine if it is possible to provide correct change to each customer.
     * Customers can only pay with 5$, 10$ or 20$ bills, and we initially do not have any change at hand.
     * Return true, if it is possible to provide correct change for each customer otherwise return false.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for(int i=0; i<bills.length;i++) {
            if(bills[i] == 5) {
                five++;
            } else if(bills[i]==10) {
                if(five > 0) {
                    five--;
                    ten++;
                } else {
                    return false;
                }
            } else {
                if(five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if(five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Valid parenthesis string (Memoization)
     *
     * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
     *
     * The following rules define a valid string:
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
     *
     * (*)) -> T, ())( -> F
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public static boolean checkValidString(String s) {
        int n = s.length();
        Boolean[][] dp = new Boolean[n][n+1];
        return valid(s,0,0, dp);
    }

    private static boolean valid(String s, int i, int count, Boolean[][] dp) {
        if(count < 0) {
            return false;
        }
        if(i == s.length()) {
            return count == 0;
        }
        if(dp[i][count] != null) {
            return dp[i][count];
        }
        if(s.charAt(i) == '(') {
            return valid(s, i+1, count+1, dp);
        }
        if(s.charAt(i) == ')') {
            return valid(s, i+1, count -1, dp);
        }
        return valid(s, i+1, count, dp) || valid(s, i+1, count+1, dp) || valid(s, i+1, count-1, dp);
    }

    /**
     * Valid parenthesis string (Efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean checkValidStringEfficient(String s) {
        int min = 0;
        int max = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                min++;
                max++;
            } else if (s.charAt(i) == ')') {
                min--;
                max--;
            } else {
                min--;
                max++;
            }
            if(min < 0) {
                min = 0;
            }
            if(max < 0) {
                return false;
            }
        }
        return min==0;
    }

    /**
     * Jump game
     *
     * Given an array where each element represents the maximum number of steps you can jump forward from that element,
     * return true if we can reach the last index starting from the first index. Otherwise, return false.
     * a = [3,2,1,0,4] -> false
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean canJump(int[] a) {
        int maxIndex = 0;
        for(int i=0; i<a.length;i++){
            if(i>maxIndex) {
                return false;
            }
            maxIndex = Math.max(maxIndex, i+a[i]);
        }
        return true;
    }

    /**
     * Jump game II (Memoization)
     *
     * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
     * Each element nums[i] represents the maximum length of a forward jump from index i.
     * Return min no of jumps to reach n-1;
     *
     * TC: O(n^2)
     * SC: O(n^2)
     */
    public int jump(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];
        for(int i=0; i<n;i++){
            Arrays.fill(dp[i], -1);
        }
        return jump(a,0,0, dp);
    }

    private static int jump(int[] a, int i, int jump, int[][] dp) {
        if(i>=a.length-1){
            return jump;
        }
        if(dp[i][jump] != -1) {
            return dp[i][jump];
        }
        int min = Integer.MAX_VALUE;
        for(int k=1;k<=a[i];k++) {
            min = Math.min(min, jump(a, i+k,jump+1, dp));
        }
        return dp[i][jump] = min;
    }

    /**
     * Jump game II (Efficient)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int jump2(int[] a) {
        int n = a.length;
        int jump = 0;
        int l=0;
        int r=0;
        while(r < n-1) {
            int farthest = 0;
            for(int i=l;i<=r;i++) {
                farthest = Math.max(farthest, i+a[i]);
            }
            l = r+1;
            r = farthest;
            jump++;
        }
        return jump;
    }

    /**
     * Candy
     *
     * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
     *
     * You are giving candies to these children subjected to the following requirements:
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * Return the minimum number of candies you need to have to distribute the candies to the children.
     * [1,0,2] -> 2+1+2 = 5
     * [1 2 3] -> 1+2+3 = 6
     *
     *
     * TC: O(3n)
     * SC: O(2n)
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0]=1;
        right[n-1]=1;
        for(int i=1;i<n;i++) {
            if(ratings[i] > ratings[i-1]) {
                left[i] = left[i-1]+1;
            } else {
                left[i] = 1;
            }
        }
        for(int i=n-2;i>=0;i--) {
            if(ratings[i] > ratings[i+1]) {
                right[i] = right[i+1]+1;
            } else {
                right[i]=1;
            }
        }
        int sum = 0;
        for(int i=0; i<n; i++) {
            System.out.println(left[i] +" "+right[i]);
            sum += Math.max(left[i],right[i]);
        }
        return sum;
    }

    /**
     * Candy (Efficient)
     *
     * TC: O(2n)
     * SC: O(n)
     */
    public int candy1(int[] ratings) {
        int n = ratings.length;

        int[] left = new int[n];
        left[0]=1;
        for(int i=1;i<n;i++) {
            if(ratings[i] > ratings[i-1]) {
                left[i] = left[i-1]+1;
            } else {
                left[i] = 1;
            }
        }

        int right=1;
        int current;
        int sum = Math.max(left[n-1], 1);
        for(int i=n-2;i>=0;i--) {
            if(ratings[i] > ratings[i+1]) {
                current = right+1;
            } else {
                current=1;
            }
            right = current;
            sum += Math.max(current, left[i]);
        }
        return sum;
    }

    /**
     * Candy (Constant space)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int sum = 1;
        int i = 1;
        while(i<n) {
            if(ratings[i] == ratings[i-1]){
                sum++;
                i++;
            }
            int peak = 1;
            while(i<n && ratings[i] > ratings[i-1]) {
                peak++;
                sum += peak;
                i++;
            }
            int down = 1;
            while(i<n && ratings[i] < ratings[i-1]) {
                sum += down;
                i++;
                down++;
            }
            if(down > peak) {
                sum += (down-peak);
            }
        }
        return sum;
    }

    /**
     * Shortest job first - CPU Scheduling
     *
     * Given a list of job durations representing the time it takes to complete each job.
     * Implement the Shortest Job First algorithm to find the average waiting time for these jobs.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int sjf(int bt[] ) {
        int n = bt.length;
        Arrays.sort(bt);
        int totalTime = bt[0];
        int waitTime = 0;
        for(int i=1;i<n;i++) {
            waitTime += totalTime;
            totalTime += bt[i];
        }
        return waitTime/n;
    }

    /**
     * Page Faults in LRU
     *
     * Given a sequence of pages in an array pages[] of length N and memory capacity C,
     * find the number of page faults using Least Recently Used (LRU) Algorithm
     *
     * TC: O(nc)
     * SC: O(n)
     */
    public static int pageFaults(int n, int c, int[] pages){
        Set<Integer> set = new HashSet<>();
        Deque<Integer> deque = new ArrayDeque<>();
        int pageFault = 0;
        for(int i=0; i<n; i++) {
            if(set.contains(pages[i])) {
                deque.remove(pages[i]);
                deque.addLast(pages[i]);
            } else {
                if(deque.size() == c) {
                    int x = deque.removeFirst();
                    set.remove(x);
                    deque.addLast(pages[i]);
                    set.add(pages[i]);
                } else {
                    deque.addLast(pages[i]);
                    set.add(pages[i]);
                }
                pageFault++;
            }
        }
        return pageFault;
    }

    /**
     * Merge overlapping intervals (Using stack)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void mergeIntervalsStack(Interval[] intervals) {
        Stack<Interval> stack = new Stack<>();
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval.start));
        stack.push(intervals[0]);
        for(int i=1; i<intervals.length; i++) {
            Interval top = stack.peek();
            Interval current = intervals[i];
            if(top.end < current.start) {
                stack.push(current);
            } else if (top.end < current.end) {
                top.end = current.end;
            }
        }
        while(!stack.isEmpty()) {
            Interval top = stack.pop();
            System.out.println(top.start + " " + top.end);
        }
    }

    /**
     * Merge overlapping intervals
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void mergeIntervals(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval.start));
        int index = 0;
        for(int i=1; i<intervals.length; i++) {
            Interval top = intervals[index];
            Interval current = intervals[i];
            if(top.end < current.start) {
                index++;
                intervals[index] = intervals[i];
            } else {
                top.end = Math.max(top.end, current.end);
            }
        }
        for(int i=0; i<=index; i++) {
            System.out.println(intervals[i].start + " " + intervals[i].end);
        }
    }

    /**
     * Insert interval and merge overlapping intervals
     *
     * You are given an array of non-overlapping intervals where
     * intervals[i] = [starti, endi] represent the start and the end of the ith interval
     * and intervals is sorted in ascending order by starting.
     * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
     *
     * Insert newInterval into intervals such that intervals is still sorted in ascending order by starting
     * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     *
     * Return intervals after the insertion.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int[][] newIntervals = new int[n+1][2];
        int index = 0;
        boolean insert = false;
        for(int i=0; i<n; i++) {
            if(newInterval[0] < intervals[i][0] && !insert) {
                newIntervals[index][0] = newInterval[0];
                newIntervals[index][1] = newInterval[1];
                insert = true;
                index++;
            }
            newIntervals[index++] = intervals[i];
        }
        if(!insert) {
            newIntervals[index][0] = newInterval[0];
            newIntervals[index][1] = newInterval[1];
        }
        return merge(newIntervals);
    }

    private static int[][] merge(int[][] intervals) {
        int index = 0;
        for(int i=1; i<intervals.length;i++) {
            int[] top = intervals[index];
            int[] current = intervals[i];
            if(top[1] < current[0]) {
                index++;
                intervals[index] = current;
            } else {
                intervals[index][1] = Math.max(top[1], current[1]);
            }
        }
        int[][] res = new int[index+1][2];
        for(int i=0; i<=index; i++) {
            res[i] = intervals[i];
        }
        return res;
    }

    /**
     * Non-overlapping Intervals
     *
     * Given an array of intervals where intervals[i] = [start, end],
     * return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 1;
        int[] top = intervals[0];
        for(int i=1; i<n; i++) {
            int[] current = intervals[i];
            if(current[0] >= top[1]) {
                count++;
                top = current;
            }
        }
        return n-count;
    }
}

class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z;
    }
}

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}

class Interval {
    int start;
    int end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
