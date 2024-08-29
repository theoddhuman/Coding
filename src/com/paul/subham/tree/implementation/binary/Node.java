package com.paul.subham.tree.implementation.binary;

public class Node {
    public int data;
    public Node left;
    public Node right;

    // not mandatory
    public Node parent;

    //not mandatory
    public int horizontalDistance;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}
