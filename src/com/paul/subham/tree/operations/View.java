package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 * 
 * 1. Left view of a binary tree (recursion)
 * 2. Right view of a binary tree (recursion)
 * 3. Left view of a binary tree (level order)
 */
public class View {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(10);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(7);
        tree.root.left.right = new Node(8);
        tree.root.right.right = new Node(15);
        tree.root.right.left = new Node(12);
        tree.root.right.right.left = new Node(14);

        leftViewLevelOrder(tree);
    }

    /**
     * Left view of a binary tree (recursion)
     * 
     * TC: O(n)
     * SC: O(n)
     */
    public static void leftViewRecursion(BinaryTree binaryTree) {
        leftView(binaryTree.root, 0);
    }

    private static int maxLevel = -1;
    private static void leftView(Node node, int level) {
        if(node == null) {
            return;
        }
        if(maxLevel < level) {
            System.out.print(node.data + " ");
            maxLevel = level;
        }
        leftView(node.left, level+1);
        leftView(node.right, level+1);
    }

    /**
     * Right view of a binary tree (recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void rightViewRecursion(BinaryTree binaryTree) {
        rightView(binaryTree.root, 0);
    }
    private static void rightView(Node node, int level) {
        if(node == null) {
            return;
        }
        if(maxLevel < level) {
            System.out.print(node.data + " ");
            maxLevel = level;
        }
        rightView(node.right, level+1);
        rightView(node.left, level+1);
    }

    /**
     * Left view of a binary tree (level order)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void leftViewLevelOrder(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            int i = 0;
            while(nodeCount > 0) {
                Node current = queue.remove();
                if(i == 0) {
                    System.out.print(current.data+" ");
                }
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
                nodeCount--;
                i++;
            }

        }
    }
}
