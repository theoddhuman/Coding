package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. Sum of a level in a binary tree (Recursive)
 * 2. Sum of a level in a binary tree (Iterative)
 * 3. Maximum level sum of a binary tree
 * 4. Maximum level sum of a binary tree (Using level order)
 * 5. Maximum level sum of a binary tree (Using preorder)
 */
public class Sum {
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
        System.out.println(maxLevelSumPreOrder(bt));
    }

    /**
     * Sum of a level in a binary tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelSumRecursive(BinaryTree binaryTree, int level) {
        return levelSumUtil(binaryTree.root, level);
    }

    private static int levelSumUtil(Node node, int level) {
        if (node == null) {
            return 0;
        }
        if (level == 1) {
            return node.data;
        } else if (level > 1) {
            return levelSumUtil(node.left, level - 1) + levelSumUtil(node.right, level - 1);
        }
        return 0;
    }

    /**
     * Sum of a level in a binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelSumIterative(BinaryTree binaryTree, int level) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int currentLevel = 1;
        int sum = binaryTree.root.data;
        while(true) {
            int nodeCount = queue.size();
            if(currentLevel == level) {
                return sum;
            }
            sum = 0;
            while(--nodeCount >= 0) {
                Node current = queue.remove();
                if(current.left != null) {
                    sum += current.left.data;
                    queue.add(current.left);
                }
                if(current.right != null) {
                    sum += current.right.data;
                    queue.add(current.right);
                }
            }
            currentLevel++;
        }
    }

    /**
     * Maximum level sum of a binary tree
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int maxLevelSum(BinaryTree binaryTree) {
        int max = 0;
        int height = Structure.heightIterative(binaryTree);
        int width;
        for(int i=1; i<=height; i++) {
            width = levelSumIterative(binaryTree, i);
            max = Math.max(max, width);
        }
        return max;
    }

    /**
     * Maximum level sum of a binary tree (Using level order)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxLevelSumLevelOrder(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int maxSum = binaryTree.root.data;
        int sum;
        while(true) {
            int nodeCount = queue.size();
            if(nodeCount == 0) {
                return maxSum;
            }
            sum = 0;
            while(--nodeCount >= 0) {
                Node current = queue.remove();
                sum += current.data;
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
            }
            maxSum = Math.max(sum, maxSum);
        }
    }

    /**
     * Maximum level sum of a binary tree (Using preorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxLevelSumPreOrder(BinaryTree binaryTree) {
        int max = 0;
        int height = Structure.heightIterative(binaryTree);
        int[] sum = new int[height + 1];
        maxLevelSumUtil(binaryTree.root, sum, 1);
        for(int i = 0; i< sum.length; i++) {
            max = Math.max(sum[i], max);
        }
        return max;
    }

    private static void maxLevelSumUtil(Node node, int[] sum, int level) {
        if(node == null) {
            return;
        }
        sum[level] += node.data;
        maxLevelSumUtil(node.left, sum, level+1);
        maxLevelSumUtil(node.right, sum, level+1);
    }
}
