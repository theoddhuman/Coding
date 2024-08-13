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
 */
public class Classic {
    public static void main(String[] args) {
        int[] arr ={900,945,955,1100,1500,1800};
        int[] dep={920,1200,1130,1150,1900,2000};
        System.out.println(maxPlatform(arr, dep, 6));
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
        System.out.print(start[x] + " " + end[x]);
        int count = 1;
        while(!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll();
            if(start[current] > end[x]) {
                System.out.print(start[current] + " " + end[current]);
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
}
