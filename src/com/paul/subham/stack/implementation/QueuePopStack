package com.paul.subham.stack.implementation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * Implementation of stack using queue (pop costly)
 */
public class QueuePopStack {
    Queue<Integer> mainQueue = new LinkedList<>();
    Queue<Integer> tempQueue = new LinkedList<>();
    int size = 0;

    /**
     * TC: O(1)
     * SC: O(1)
     */
    void push(int x) {
        size ++;
        mainQueue.add(x);
    }

    /**
     * TC: O(n)
     * SC: O(1)
     */
    int pop() {
        if(mainQueue.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        while(mainQueue.size() > 1) {
            tempQueue.add(mainQueue.remove());
        }
        int x = mainQueue.remove();
        size--;
        Queue<Integer> dummyQueue = mainQueue;
        mainQueue = tempQueue;
        tempQueue = dummyQueue;
        return x;
    }

    void print() {
        System.out.println(mainQueue);
    }
}
