package com.paul.subham.linkedlist.implementation.doubly;

public class Node {
    int data;
    public Node pre;
    public Node next;

    public Node(int data) {
        this.data = data;
        pre = null;
        next = null;
    }
}
