package com.paul.subham.tree.implementation.binary;

public class Node {
    int data;
    public Node left;
    public Node right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}
