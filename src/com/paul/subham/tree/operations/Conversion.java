package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. Convert a binary tree to its mirror (Recursive)
 * 2. Convert a binary tree to its mirror (Iterative)
 */
public class Conversion {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.root.left.left = new Node(4);
        bt.root.left.right = new Node(5);
        bt.levelOrder();
        System.out.println();

        BinaryTree bt1 = new BinaryTree();
        bt1.root = new Node(1);
        bt1.root.left = new Node(2);
        bt1.root.right = new Node(3);
        bt1.root.left.left = new Node(4);
        bt1.root.left.right = new Node(5);
        bt1.levelOrder();
        System.out.println();

        BinaryTree b = new BinaryTree();
        b.root = new Node(1);
        b.root.left = new Node(3);
        b.root.right = new Node(2);
        b.root.right.left = new Node(5);
        b.root.right.right = new Node(4);
        b.levelOrder();
        System.out.println();
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
}
