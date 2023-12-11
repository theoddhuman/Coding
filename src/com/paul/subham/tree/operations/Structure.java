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
 * 3. Minimum depth of a binary tree (Recursive)
 * 4. Minimum depth of a binary tree (Iterative - level order)
 * 5. Deepest node of a binary tree (Using inorder)
 * 6. Deepest node of a binary tree (Using height)
 * 7. Deepest node of a binary tree (Using level order)
 * 8. No of leaves in a binary tree (Recursive)
 * 9. No of leaves in a binary tree (Iterative)
 * 10. No of full nodes in a binary tree (Recursive)
 * 11. No of full nodes in a binary tree (Iterative)
 * 12. No of half nodes in a binary tree (Recursive)
 * 13. No of half nodes in a binary tree (Iterative)
 * 14. No of non-leaf nodes in a binary tree (Recursive)
 * 15. No of non-leaf nodes in a binary tree (Iterative)
 */

public class Structure {
    public static void main(String[] args) {
//        BinaryTree bt = new BinaryTree();
//        bt.root = new Node(1);
//        bt.root.left = new Node(2);
//        bt.root.right = new Node(3);
//        bt.insert(4);
//        bt.insert(5);
//        bt.insert(6);
//        bt.levelOrder();
//        System.out.println();
//        System.out.println(diameterOpt(bt));
        double a = 347.89;
        double base = (int)(a/10)*10;
        double rem = a%10 ;
        double res = 0;
        if(rem > 9) {
            res = base + 10;
        } else if (rem > 5) {
            res = base + 9;
        } else {
            res = base + 5;
        }
        System.out.println(res);
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
        while (true) {
            int nodeCount = queue.size();
            if (nodeCount == 0) {
                return height;
            }
            height++;
            while (nodeCount > 0) {
                Node node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                nodeCount--;
            }
        }
    }

    /**
     * Minimum depth of a binary tree (Recursive)
     * TC: O(n)
     * SC: O(h), where h is the height of the binary tree
     */
    public static int minDepthRecursion(BinaryTree binaryTree) {
        return minDepthUtil(binaryTree.root);
    }

    private static int minDepthUtil(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.right == null) {
            return minDepthUtil(node.left);
        }
        if (node.left == null) {
            return minDepthUtil(node.right);
        }
        return Math.min(minDepthUtil(node.left), minDepthUtil(node.right)) + 1;
    }

    /**
     * Minimum depth of a binary tree (Iterative - level order)
     * TC: O(n), better than recursion as once we get the first leaf node, no need to traverse further.
     * SC: O(n)
     */
    public static int minDepthIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int height = 0;
        while (true) {
            int nodeCount = queue.size();
            height++;
            while (nodeCount > 0) {
                Node node = queue.remove();
                if (node.left == null && node.right == null) {
                    return height;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                nodeCount--;
            }
        }
    }


    /**
     * Deepest node of a binary tree (Using inorder)
     * TC: O(n)
     * SC: O(n)
     */
    private static int maxLevel = -1;
    private static int res = Integer.MIN_VALUE;

    public static int deepestNodeInOrder(BinaryTree binaryTree) {
        Node node = binaryTree.root;
        deepestNodeUtil(node, 0);
        return res;
    }

    private static void deepestNodeUtil(Node node, int level) {
        if (node == null) {
            return;
        }
        deepestNodeUtil(node.left, level + 1);
        if (level >= maxLevel) {
            maxLevel = level;
            res = node.data;
        }
        deepestNodeUtil(node.right, level + 1);
    }

    /**
     * Deepest node of a binary tree (Using height)
     * TC: O(n)
     * SC: O(n)
     */
    public static void deepestNodeByHeight(BinaryTree binaryTree) {
        int height = heightRecursive(binaryTree);
        deepestNodeByHeightUtil(binaryTree.root, height);
    }

    private static void deepestNodeByHeightUtil(Node node, int height) {
        if (node == null) {
            return;
        }
        if (height == 1) {
            System.out.println(node.data);
        }
        if (height > 1) {
            deepestNodeByHeightUtil(node.left, height - 1);
            deepestNodeByHeightUtil(node.right, height - 1);
        }
    }

    /**
     * Deepest node of a binary tree (Using level order)
     * TC: O(n)
     * SC: O(n)
     */
    public static int deepestNodeLevelOrder(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        Node current = null;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return current.data;
    }

    /**
     * No of leaves in a binary tree (Recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countLeavesRecursive(BinaryTree binaryTree) {
        return countLeaveRecursiveUtil(binaryTree.root);
    }

    private static int countLeaveRecursiveUtil(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeaveRecursiveUtil(node.left) + countLeaveRecursiveUtil(node.right);
    }

    /**
     * No of leaves in a binary tree (Iterative)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countLeavesIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int count = 0;
        Node current = null;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.left == null && current.right == null) {
                count++;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return count;
    }

    /**
     * No of full nodes in a binary tree (Recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countFullNodesRecursive(BinaryTree binaryTree) {
        return countFullNodesRecursiveUtil(binaryTree.root);
    }

    private static int countFullNodesRecursiveUtil(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.left != null && node.right != null) {
            count++;
        }
        return count + countFullNodesRecursiveUtil(node.left) + countFullNodesRecursiveUtil(node.right);
    }

    /**
     * No of full nodes in a binary tree (Iterative)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countFullNodesIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int count = 0;
        Node current = null;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.left != null && current.right != null) {
                count++;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return count;
    }

    /**
     * No of half nodes in a binary tree (Recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countHalfNodesRecursive(BinaryTree binaryTree) {
        return countHalfNodesRecursiveUtil(binaryTree.root);
    }

    private static int countHalfNodesRecursiveUtil(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
            count++;
        }
        return count + countHalfNodesRecursiveUtil(node.left) + countHalfNodesRecursiveUtil(node.right);
    }

    /**
     * No of half nodes in a binary tree (Iterative)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countHalfNodesIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int count = 0;
        Node current = null;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if ((current.left != null && current.right == null) || (current.left == null && current.right != null)) {
                count++;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return count;
    }

    /**
     * No of non-leaf nodes in a binary tree (Recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countNonLeafNodesRecursive(BinaryTree binaryTree) {
        return countNonLeafNodesRecursiveUtil(binaryTree.root);
    }

    private static int countNonLeafNodesRecursiveUtil(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.left != null || node.right != null) {
            count++;
        }
        return count + countNonLeafNodesRecursiveUtil(node.left) + countNonLeafNodesRecursiveUtil(node.right);
    }

    /**
     * No of non-leaf nodes in a binary tree (Iterative)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countNonLeafNodesIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int count = 0;
        Node current = null;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.left != null || current.right != null) {
                count++;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return count;
    }

    /**
     * Diameter of a binary tree (Recursive)
     *
     * The diameter of a tree is the largest of the following quantities
     * 1. Diameter of left subtree
     * 2. Diameter of right subtree
     * 3. The longest path between leaves that go through the root of tree.
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int diameterRecursive(BinaryTree binaryTree) {
        return diameterRecursiveUtil(binaryTree.root);
    }

    private static int diameterRecursiveUtil(Node node) {
        if(node == null) {
            return 0;
        }
        int lHeight = heightUtil(node.left);
        int rHeight = heightUtil(node.left);
        int lDiameter = diameterRecursiveUtil(node.left);
        int rDiameter = diameterRecursiveUtil(node.right);
        return Math.max(lHeight + rHeight + 1, Math.max(lDiameter, rDiameter));
    }

    /**
     * Diameter of a binary tree (Recursive optimized approach)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int diameterOpt(BinaryTree binaryTree) {
        Height height = new Height();
        return diameterOptUtil(binaryTree.root, height);
    }

    private static int diameterOptUtil(Node node, Height height) {
        if(node == null) {
            height.value = 0;
            return 0;
        }
        Height lHeight = new Height();
        Height rHeight = new Height();
        int lDiameter = diameterOptUtil(node.left, lHeight);
        int rDiameter = diameterOptUtil(node.right, rHeight);
        height.value = Math.max(lHeight.value, rHeight.value) + 1;
        return Math.max(lHeight.value + rHeight.value + 1, Math.max(lDiameter, rDiameter));
    }

    static class Height{
        int value;
    }
}
