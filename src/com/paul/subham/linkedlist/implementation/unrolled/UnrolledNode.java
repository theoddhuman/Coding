package com.paul.subham.linkedlist.implementation.unrolled;

/**
 * @author subham.paul
 */

public class UnrolledNode {
    public int numElements;
    public int[] arr;
    public UnrolledNode next;
    UnrolledNode(int capacity) {
        this.next = null;
        this.numElements = 0;
        this.arr = new int[capacity];
    }
}
