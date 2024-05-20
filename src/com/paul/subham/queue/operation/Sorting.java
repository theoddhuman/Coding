package com.paul.subham.queue.operation;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. Sorting a queue (Using recursion)
 * 2. Sorting a queue (Without extra space)
 */

public class Sorting {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(5);
        queue.add(2);
        queue.add(4);
        queue.add(3);

        System.out.println(queue);
        sortSpaceOptimized(queue);
        System.out.println(queue);
    }

    /**
     * Sorting a queue (Using recursion)
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void sort(Queue<Integer> queue) {
        if(queue.isEmpty()) {
            return;
        }
        int data = queue.remove();
        sort(queue);
        push(queue, data, queue.size());
    }

    private static void push(Queue<Integer> queue, int data, int size) {
        if(queue.isEmpty() || size == 0) {
            queue.add(data);
            return;
        }
        if(data <= queue.peek()) {
            queue.add(data);
            frontToLast(queue, size);
        } else {
            queue.add(queue.remove());
            push(queue, data, size-1);
        }

    }

    private static void frontToLast(Queue<Integer> queue, int size) {
        if(queue.isEmpty() || size == 0) {
            return;
        }
        queue.add(queue.remove());
        frontToLast(queue, size-1);
    }

    /**
     * Sorting a queue (Without extra space)
     * TC: O(n^2)
     * SC: O(n)
     */
    private static void sortSpaceOptimized(Queue<Integer> queue) {
        for(int i=1; i<= queue.size(); i++) {
            int minIndex = minIndex(queue, queue.size() - i);
            insertMinToRear(queue, minIndex);
        }
    }

    private static void insertMinToRear(Queue<Integer> queue, int minIndex) {
        int minValue = 0;
        int size = queue.size();
        for(int i=0; i< size; i++) {
            int current = queue.remove();
            if(i != minIndex) {
                queue.add(current);
            } else {
                minValue = current;
            }
        }
        queue.add(minValue);
    }

    private static int minIndex(Queue<Integer> queue, int sortIndex) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        for(int i=0; i< queue.size(); i++) {
            int current = queue.remove();
            if(current <= minValue && i <= sortIndex) {
                minIndex = i;
                minValue = current;
            }
            queue.add(current);
        }
        return minIndex;
    }
}
