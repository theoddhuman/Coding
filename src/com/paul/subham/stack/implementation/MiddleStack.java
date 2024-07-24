package com.paul.subham.stack.implementation;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;

/**
 * @author subham.paul
 * 
 * 1. Push data
 * 2. Pop data
 * 3. Middle of stack
 * 4. Delete middle element
 */
public class MiddleStack {
    DLNode head;
    DLNode mid;
    int count = 0;
    public MiddleStack() {
        count = 0;
    }

    /**
     * Push data
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void push(int data) {
        DLNode newNode = new DLNode(data);
        newNode.next = head;
        count++;
        if(count == 1) {
            mid = newNode;
        } else {
            head.pre = newNode;
            if(count % 2 != 0) {
                mid = mid.pre;
            }
        }
        head = newNode;
    }

    /**
     * Pop data
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int pop() {
        if(count == 0) {
            return Integer.MIN_VALUE;
        }
        int x = head.data;
        count--;
        head = head.next;
        if(head != null) {
            head.pre = null;
        }
        if(count % 2 == 0) {
            mid = mid.next;
        }
        return x;
    }

    /**
     * Middle of stack
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int middle() {
        if(count == 0) {
            return Integer.MIN_VALUE;
        }
        return mid.data;
    }

    /**
     * Delete middle element
     *
     * TC: O(1)
     * SC: O(1)
     */
    public int deleteMiddle() {
        if(count == 0) {
            return Integer.MIN_VALUE;
        }
        int x = mid.data;
        count--;
        if(count == 1) {
            head = null;
        } else {
            mid.next.pre = mid.pre;
            mid.pre.next = mid.next;
            if(count % 2 == 0) {
                mid = mid.next;
            }
        }
        return x;
    }
}

