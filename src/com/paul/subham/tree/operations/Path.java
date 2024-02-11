package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Print all root to leaf paths of a binary tree
 * 2. Print ancestors of a node in binary tree (Using Recursion)
 * 3. Print ancestors of a node in binary tree (Iterative)
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
        printAncestorsIterative(bt, 3);
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

    /**
     * Print ancestors of a node in binary tree (Using Recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printAncestorsByRecursion(BinaryTree binaryTree, int child) {
        printAncestorsUtil(binaryTree.root, child);
    }

    private static boolean printAncestorsUtil(Node node, int child) {
        if(node == null) {
            return false;
        }
        if(node.data == child) {
            return true;
        }
        if(printAncestorsUtil(node.left, child) || printAncestorsUtil(node.right, child)) {
            System.out.print(node.data + " ");
            return true;
        }
        return false;
    }

    /**
     * Print ancestors of a node in binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printAncestorsIterative(BinaryTree binaryTree, int child) {
        Stack<Node> stack = new Stack<>();
        Node current = binaryTree.root;
        while(!stack.empty() || current != null) {
            while(current != null && current.data != child) {
                stack.push(current);
                current = current.left;
            }
            if(current != null) {
                break;
            }
            if(!stack.isEmpty() && stack.peek().right == null) {
                current = stack.pop();
                while(!stack.isEmpty() && stack.peek().right == current) {
                    current = stack.pop();
                }
            }
            current = !stack.isEmpty() ? stack.peek().right : null;
        }
        if(!stack.isEmpty()) {
            while(!stack.isEmpty()) {
                System.out.print(stack.pop().data + " ");
            }
        }
    }
}
