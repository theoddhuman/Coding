package com.paul.subham.tree.implementation.tournament;

/**
 * @author subham.paul
 */
public class TNode {
    public int index;
    public TNode left, right;
    TNode(int index) {
        this.index = index;
        this.left = this.right = null;
    }
}
