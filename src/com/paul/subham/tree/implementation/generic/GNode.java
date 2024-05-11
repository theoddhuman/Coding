package com.paul.subham.tree.implementation.generic;

/**
 * @author subham.paul
 */
public class GNode {
    public int data;
    public GNode next, child;
    GNode(int data) {
        this.data = data;
        this.next = this.child = null;
    }
}
