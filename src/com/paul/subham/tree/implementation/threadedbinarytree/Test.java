package com.paul.subham.tree.implementation.threadedbinary;

/**
 * @author subham.paul
 */
public class Test {
    public static void main(String[] args) {
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.insert(1);
        threadedBinaryTree.insert(5);
        threadedBinaryTree.insert(2);
        threadedBinaryTree.insert(16);
        threadedBinaryTree.insert(11);
        threadedBinaryTree.insert(14);
        threadedBinaryTree.inorder();
        System.out.println();
        threadedBinaryTree.insertRight(threadedBinaryTree.root.left, 9);
        threadedBinaryTree.insertLeft(threadedBinaryTree.root.left, 20);
        threadedBinaryTree.preorder();
    }
}
