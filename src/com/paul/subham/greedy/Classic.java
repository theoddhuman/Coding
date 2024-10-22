package com.paul.subham.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author subham.paul
 *
 * 1. Maximum meetings possible in one room based on start and end time
 * 2. Maximum no of platform required for given train timings
 * 3. Maximum no of platform required for given train timings (Optimized)
 * 4. Job sequencing problem
 * 5. Fractional Knapsack Problem
 * 6. Assign cookies
 * 7. Minimum no of coins
 * 8. Lemonade change
 * 9. Valid parenthesis string (Memoization)
 * 10. Valid parenthesis string (Efficient)
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
     * Maximum no of platform required for given train timings
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
     * Maximum no of platform required for given train timings (Optimized)
     *
     *
     * TC: O(nlogn)
     * SC: O(1)
     */
    public static int findPlatform(int arr[], int dep[], int n) {
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
