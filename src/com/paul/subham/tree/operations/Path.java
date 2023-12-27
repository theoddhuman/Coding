package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

/**
 * @author subham.paul
 *
 * 1. Print all root to leaf paths of a binary tree
 */
public class Path {
    /**
     *          1
     *        /   \
     *      2      3
     *    /   \
     *  4      5
     *   \   /   \
     *   11 6     8
     *        \
     *         7
     */
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.root.left.left = new Node(4);
        bt.root.left.left.right = new Node(11);
        bt.root.left.right = new Node(5);
        bt.root.left.right.left = new Node(6);
        bt.root.left.right.right = new Node(8);
        bt.root.left.right.left.right = new Node(7);
        bt.levelOrder();
        System.out.println();
        printPathRecursive(bt);
    }


    /**
     * Print all root to leaf paths of a binary tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printPathRecursive(BinaryTree binaryTree) {
        printPathRecursiveUtil(binaryTree.root, new int[100], 0);
    }

    private static void printPathRecursiveUtil(Node node, int[] path, int pathLen) {
        if(node == null) {
            return;
        }
        path[pathLen] = node.data;
        pathLen++;
        if(node.left == null && node.right == null) {
            for(int i=0; i<pathLen; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
        } else {
            printPathRecursiveUtil(node.left, path, pathLen);
            printPathRecursiveUtil(node.right, path, pathLen);
        }
    }
}
