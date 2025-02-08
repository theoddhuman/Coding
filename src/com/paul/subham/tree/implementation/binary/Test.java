package com.paul.subham.tree.implementation.binary;

public class Test {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.insert(4);
        bt.insert(5);
        //bt.delete(1);
        //bt.inOrderRecursive(bt.root);
        //bt.preOrderRecursive(bt.root);
        //bt.postOrderRecursive(bt.root);
        //bt.preOrderIterative();
        //bt.inOrderIterative();
        //bt.postOrderIterative();
        bt.inOrderMorris();
        System.out.println();
    }
}
