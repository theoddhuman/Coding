package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Convert a binary tree to its mirror (Recursive)
 * 2. Convert a binary tree to its mirror (Iterative)
 * 3. Check if two binary trees are mirrors (Recursive)
 * 4. Check if two binary trees are mirrors (Iterative - Inorder)
 * 5. Check if two binary trees are mirrors (Iterative - level order)
 */
public class Comparison {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.root.left.left = new Node(4);
        bt.root.left.right = new Node(5);
        bt.levelOrder();
        System.out.println();
        BinaryTree b = new BinaryTree();
        b.root = new Node(1);
        b.root.left = new Node(3);
        b.root.right = new Node(2);
        b.root.right.left = new Node(5);
        //b.root.right.right = new Node(4);
        b.levelOrder();
        System.out.println();
        System.out.println(isMirrorIterativeLevelOrder(bt, b));
    }

    /**
     * Convert a binary tree to its mirror (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void convertToMirrorRecursive(BinaryTree binaryTree) {
        binaryTree.root = mirror(binaryTree.root);
    }

    private static Node mirror(Node node) {
        if(node == null) {
            return null;
        }
        Node left = mirror(node.left);
        Node right = mirror(node.right);
        node.left = right;
        node.right = left;
        return node;
    }

    /**
     * Convert a binary tree to its mirror (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void convertToMirrorIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            Node temp = current.left;
            current.left = current.right;
            current.right = temp;
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * Check if two binary trees are mirrors (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isMirrorRecursive(BinaryTree binaryTree1, BinaryTree binaryTree2) {
        return isMirrorRecursiveUtil(binaryTree1.root, binaryTree2.root);
    }

    private static boolean isMirrorRecursiveUtil(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.data != node2.data) {
            return false;
        }
        return isMirrorRecursiveUtil(node1.left, node2.right) && isMirrorRecursiveUtil(node1.right, node2.left);
    }

    /**
     * Check if two binary trees are mirrors (Iterative - Inorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isMirrorIterative(BinaryTree binaryTree1, BinaryTree binaryTree2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node node1 = binaryTree1.root;
        Node node2 = binaryTree2.root;
        while(true) {
            while(node1 != null && node2 != null) {
                stack1.push(node1);
                stack2.push(node2);
                node1 = node1.left;
                node2 = node2.right;
            }
            if(!(node1 == null && node2 == null)) {
                return false;
            }
            if(stack1.empty() && stack2.empty()) {
                break;
            }
            node1 = stack1.pop();
            node2 = stack2.pop();
            if(node1.data != node2.data) {
                return false;
            }
            node1 = node1.right;
            node2 = node2.left;
        }
        return true;
    }

    /**
     * Check if two binary trees are mirrors (Iterative - level order)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isMirrorIterativeLevelOrder(BinaryTree binaryTree1, BinaryTree binaryTree2) {
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.add(binaryTree1.root);
        queue2.add(binaryTree2.root);
        while(!queue1.isEmpty() && !queue2.isEmpty()) {
            Node current1 = queue1.remove();
            Node current2 = queue2.remove();
            if(current1.data != current2.data) {
                return false;
            }
            if(current1.left != null) {
                queue1.add(current1.left);
            }
            if(current1.right != null) {
                queue1.add(current1.right);
            }
            if(current2.right != null) {
                queue2.add(current2.right);
            }
            if(current2.left != null) {
                queue2.add(current2.left);
            }
        }
        if(!(queue1.isEmpty() && queue2.isEmpty())) {
            return false;
        }
        return true;
    }
}
