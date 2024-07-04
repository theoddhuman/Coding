package com.paul.subham.tree.implementation.threadedbinary;

/**
 * @author subham.paul
 */
public class TBTNode {
    public int data;
    public boolean leftBit;
    public boolean rightBit;
    public TBTNode left;
    public TBTNode right;

    public TBTNode(int data) {
        this.data = data;
        this.left = this.right = null;
        this.leftBit = this.rightBit = false;
    }
}
