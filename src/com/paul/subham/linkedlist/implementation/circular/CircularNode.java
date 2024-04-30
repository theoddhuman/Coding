package com.paul.subham.linkedlist.implementation.circular;

/**
 * @author subham.paul
 */
public class CircularNode {
    public int data;
    public CircularNode next;

    public CircularNode(int data) {
        this.data = data;
        this.next = this;
    }
}
