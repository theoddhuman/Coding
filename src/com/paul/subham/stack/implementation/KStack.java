package com.paul.subham.stack.implementation;

import java.util.Arrays;

/**
 * @author subham.paul
 * 
 * 1. Push an element to kth stack
 * 2. Pop an element from kth stack
 */
public class KStack {
    int[] arr;
    int[] next;
    int[] top;
    int n, k;
    int free;

    public KStack(int n, int k) {
        this.n = n;
        this.k = k;
        arr = new int[n];
        next = new int[n];
        top = new int[k];
        free = 0;
        Arrays.fill(top, -1);
        for(int i=0; i<n-1; i++) {
            next[i] = i+1;
        }
        next[n-1] = -1;
    }

    boolean isFull() {
        return free == -1;
    }

    boolean isEmpty(int stackNumber) {
        return top[stackNumber] == -1;
    }

    /**
     * Push an element to kth stack
     * 
     * TC: O(1)
     * SC: O(1)
     */
    void push(int data, int stackNumber) {
        if(isFull()) {
            System.out.println("Stack overflow");
            return;
        }
        int i = free;
        free = next[i];
        next[i] = top[stackNumber];
        top[stackNumber] = i;
        arr[i] = data;
    }

    /**
     * Pop an element from kth stack
     * 
     * TC: O(1)
     * SC: O(1)
     */
    int pop(int stackNumber) {
        if(isEmpty(stackNumber)) {
            System.out.println("Stack underflow");
            return Integer.MIN_VALUE;
        }
        int i = top[stackNumber];
        top[stackNumber] = next[i];
        next[i] = free;
        free = i;
        return arr[i];
    }
}
