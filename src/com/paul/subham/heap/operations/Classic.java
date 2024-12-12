package com.paul.subham.heap.operations;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1. Replace elements by its rank in the array
 */
public class Classic {
    public static void main(String[] args) {

    }

    /**
     * Replace elements by its rank in the array
     *
     * Input:
     * arr = [20, 15, 26, 2, 98, 6]
     * Output:
     * 4, 3, 5, 1, 6, 2
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int[] replaceWithRank(int a[], int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(x -> a[x]));
        for(int i=0; i<n; i++) {
            pq.add(i);
        }
        int[] res = new int[n];
        int rank = 0;
        int last = 0;
        while(!pq.isEmpty()) {
            int current = pq.remove();
            if(a[current] > last) {
                res[current] = ++rank;
                last = a[current];
            } else {
                res[current] = rank;
            }
        }
        return res;
    }
}
