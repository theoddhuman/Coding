package com.paul.subham.tree.implementation.binarysearch;

public class Test {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(1);
        bst.insert(4);
        bst.insert(2);
        bst.insert(3);
        bst.inOrder(bst.root);
        bst.delete(1);
        System.out.println();
        bst.inOrder(bst.root);
    }
}
