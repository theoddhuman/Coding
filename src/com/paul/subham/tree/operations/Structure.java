package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. Height of a binary tree (recursive)
 * 2. Height of a binary tree (iterative - level order)
 */

public class Structure {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.insert(4);
        bt.insert(5);
        bt.levelOrder();
        System.out.println();
        System.out.println(heightIterative(bt));
    }

    /**
     * Height of a binary tree (recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static int heightRecursive(BinaryTree binaryTree) {
        Node root = binaryTree.root;
        return heightUtil(root);
    }

    public static int heightUtil(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightUtil(node.left);
        int rightHeight = heightUtil(node.right);
        return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
    }

    /**
     * Height of a binary tree (iterative - level order)
     * TC: O(n)
     * SC: O(n)
     */
    public static int heightIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int height = 0;
        while(true) {
            int nodeCount = queue.size();
            if(nodeCount == 0) {
                return height;
            }
            height++;
            while(nodeCount > 0) {
                Node node = queue.remove();
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
                nodeCount--;
            }
        }
    }
}
