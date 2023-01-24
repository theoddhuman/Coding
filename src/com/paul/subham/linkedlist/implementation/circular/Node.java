package com.paul.subham.linkedlist.implementation.circular;

public class Node {
    int data;
    public Node next;
    public Node(int data) {
        this.data = data;
        this.next = this;
    }
}
