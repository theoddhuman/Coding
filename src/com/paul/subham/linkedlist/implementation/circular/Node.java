package com.paul.subham.linkedlist.implementation.circular;

/**
 * @author subham.paul
 */
public class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = this;
    }
}
