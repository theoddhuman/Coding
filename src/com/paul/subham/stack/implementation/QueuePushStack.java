package com.paul.subham.stack.implementation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * Implementation of stack using queue (push costly)
 */
public class QueuePushStack {
    Queue<Integer> mainQueue = new LinkedList<>();
    Queue<Integer> tempQueue = new LinkedList<>();
    int size = 0;

    /**
     * TC: O(n)
     * SC: O(1)
     */
    void push(int x) {
        size ++;
        tempQueue.add(x);
        while(!mainQueue.isEmpty()) {
            tempQueue.add(mainQueue.remove());
        }
        Queue<Integer> dummyQueue = mainQueue;
        mainQueue = tempQueue;
        tempQueue = dummyQueue;
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    int pop() {
        if(mainQueue.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        size--;
        return mainQueue.remove();
    }

    /**
     * TC: O(1)
     * SC: O(1)
     */
    int top() {
        if(mainQueue.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return mainQueue.peek();
    }

    void print() {
        System.out.println(mainQueue);
    }
}


