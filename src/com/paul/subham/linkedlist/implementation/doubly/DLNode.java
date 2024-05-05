package com.paul.subham.linkedlist.implementation.doubly;

/**
 * @author subham.paul
 */
public class DLNode {
    public int data;
    public DLNode pre;
    public DLNode next;

    public DLNode(int data) {
        this.data = data;
        pre = null;
        next = null;
    }
}
