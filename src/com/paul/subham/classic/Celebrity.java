package com.paul.subham.classic;

import java.util.Stack;

/**
 * 1. Find celebrity (Recursion)
 * 2. Find celebrity (stack)
 * 3. Find celebrity (Two pointer)
 */
public class Celebrity {
    public static void main(String[] args) {
        int[][] a = {{0, 0, 1, 0},
                     {0, 0, 1, 0},
                     {0, 0, 0, 0},
                     {0, 0, 1, 0}};
        System.out.println(findCelebrityTwoPointer(a));
    }

    /**
     * Find celebrity (Recursion)
     *
     * Everyone knows him, he doesn't know anyone
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int findCelebrityRec(int[][] a) {
        int n = a.length;
        int celebrity = potentialCelebrity(a, n);
        if(celebrity == -1) {
            return -1;
        }
        int known = 0;
        int knows = 0;
        for(int i=0; i<n; i++) {
            if(i != celebrity) {
                known += a[i][celebrity];
                knows += a[celebrity][i];
            }
        }
        if(known == n-1 && knows == 0) {
            return celebrity;
        }
        return -1;
    }

    private static int potentialCelebrity(int[][] a, int i) {
        if(i==0) {
            return -1;
        }
        int celebrity = potentialCelebrity(a, i-1);
        if(celebrity == -1) {
            return i-1;
        }
        if(a[celebrity][i-1]==1) {
            return i-1;
        }
        if(a[i-1][celebrity] == 1) {
            return celebrity;
        }
        return -1;
    }

    /**
     * Find celebrity (Stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int findCelebrityStack(int[][] a) {
        int n = a.length;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            stack.push(i);
        }
        while(stack.size() > 1) {
            int x = stack.pop();
            int y = stack.pop();
            if(a[x][y]==1) {
                stack.push(y);
            } else {
                stack.push(x);
            }
        }
        if(stack.isEmpty()) {
            return -1;
        }
        int celebrity = stack.pop();
        int known = 0;
        int knows = 0;
        for(int i=0; i<n; i++) {
            if(i != celebrity) {
                known += a[i][celebrity];
                knows += a[celebrity][i];
            }
        }
        if(known == n-1 && knows == 0) {
            return celebrity;
        }
        return -1;
    }

    /**
     * Find celebrity (Two pointer)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int findCelebrityTwoPointer(int[][] a) {
        int n = a.length;
        int start = 0;
        int end = n-1;
        while(start < end) {
            if(a[start][end] == 1) {
                start++;
            } else {
                end--;
            }
        }
        int celebrity = start;
        for(int i=0; i<n; i++) {
            if(i != celebrity) {
                if(a[celebrity][i] == 1 || a[i][celebrity] == 0) {
                    return -1;
                }
            }
        }
        return celebrity;
    }
}
