package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;
import com.paul.subham.tree.implementation.binarysearch.BSTNode;
import com.paul.subham.tree.implementation.binarysearch.BinarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
 * 16. Diameter of a binary tree
 * 17. Diameter of a binary tree (Recursive optimized approach)
 * 18. Diameter of a binary tree (Recursive optimized approach - using static variable)
 * 19. Node count of a level in a binary tree (Recursive)
 * 20. Node count of a level in a binary tree (Iterative)
 * 21. Maximum nodes at a level of a binary tree
 * 22. Maximum nodes at a level of a binary tree (Using level order)
 * 23. Maximum nodes at a level of a binary tree (Using preorder)
 * 24. Level of a node in a binary tree (Recursive)
 * 25. Level of a node in a binary tree (Iterative)
 * 26. Check if given binary tree is a BST (Naive approach)
 * 27. Check if given binary tree is a BST (Efficient recursive)
 * 28. Check if given binary tree is a BST (Inorder)
 * 29. Check if given binary tree is a BST (Using nodes)
 * 30. Check if given binary tree is a BST (Using Morris Traversal)
 * 31. Lowest common ancestor of binary search tree (Recursive)
 * 32. Lowest common ancestor of binary search tree (Iterative)
 * 33. Print all possible binary trees from given inorder
 * 34. Fill next sibling (consider all node at same level as sibling)
 * 35. Fill next sibling (consider all node at same level as sibling - Recursive)
 * 36. Leftmost node of a node in binary tree
 * 37. Rightmost node of a node in binary tree
 * 38. Inorder successor of a binary tree
 * 39. Inorder successor of a binary tree (Using reverse inorder)
 * 40. Preorder successor of a binary tree (Using parent pointer)
 * 41. Postorder successor of a binary tree (Using parent pointer)
 * 42. Is a binary tree height balanced
 * 43. Is a binary tree height balanced (Space optimized)
 * 44. Maximum width of a binary tree
 * 45. Count no of nodes in a complete binary tree
 * 46. Inorder successor of a binary search tree
 * 47. Largest BST in a binary tree
 */

public class Structure {
    /**
     *          7
     *        /   \
     *      2      8
     *    /   \
     *  1      5
     *       /   \
     *      3     6
     *        \
     *         4
     */
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(7);
        bt.root.left = new Node(2);
        bt.root.right = new Node(8);
        bt.root.left.left = new Node(1);
        bt.root.left.right = new Node(5);
        bt.root.left.right.left = new Node(3);
        bt.root.left.right.right = new Node(6);
        bt.root.left.right.left.right = new Node(4);
//        bt.insertWithParentPointer(1);
//        bt.insertWithParentPointer(2);
//        bt.insertWithParentPointer(3);
//        bt.insertWithParentPointer(4);
//        bt.insertWithParentPointer(5);
//        bt.insertWithParentPointer(6);
        System.out.println(isHeightBalancedSpaceOptimized(bt));

//        bt.insert(4);
//        bt.insert(5);
//        bt.insert(6);
//        bt.levelOrder();
//        System.out.println();
//        System.out.println(isValidBSTMorris(bt));
//        BinarySearchTree bst = new BinarySearchTree();
//        bst.insert(5);
//        bst.insert(3);
//        bst.insert(9);
//        bst.insert(2);
//        bst.insert(4);
//        bst.insert(6);
//        System.out.println(lcaBSTRecursive(bst, 2, 4));
//        System.out.println(preorderSuccessor(bt, bt.root.left).data);
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
        return Math.max(lHeight.value + rHeight.value, Math.max(lDiameter, rDiameter));
    }

    static class Height{
        int value;
    }

    /**
     * Diameter of a binary tree (Recursive optimized approach - using static variable)
     *
     * TC: O(n)
     * SC: O(n)
     */
    private static int diameterX(BinaryTree binaryTree) {
        return diameterUtil(binaryTree.root);
    }

    private static int diameter = 0;
    private static int diameterUtil(Node node) {
        if(node == null) {
            return 0;
        }
        int lHeight = diameterUtil(node.left);
        int rHeight = diameterUtil(node.right);
        diameter = Math.max(lHeight + rHeight, diameter);
        return Math.max(lHeight, rHeight) + 1;
    }

    /**
     * Node count of a level in a binary tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelWidthRecursive(BinaryTree binaryTree, int level) {
        return levelWidthUtil(binaryTree.root, level);
    }

    private static int levelWidthUtil(Node node, int level) {
        if (node == null) {
            return 0;
        }
        if (level == 1) {
            return 1;
        } else if (level > 1) {
            return levelWidthUtil(node.left, level - 1) + levelWidthUtil(node.right, level - 1);
        }
        return 0;
    }

    /**
     * Node count of a level in a binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelWidthIterative(BinaryTree binaryTree, int level) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int currentLevel = 1;
        while(true) {
            int nodeCount = queue.size();
            if(currentLevel == level) {
                return nodeCount;
            }
            while(--nodeCount >= 0) {
                Node current = queue.remove();
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
            }
            currentLevel++;
        }
    }

    /**
     * Maximum nodes at a level of a binary tree
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int maxWidth(BinaryTree binaryTree) {
        int max = 0;
        int height = heightIterative(binaryTree);
        int width;
        for(int i=1; i<=height; i++) {
            width = levelWidthIterative(binaryTree, i);
            max = Math.max(max, width);
        }
        return max;
    }

    /**
     * Maximum nodes at a level of a binary tree (Using level order)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxWidthLevelOrder(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int max = 0;
        while(true) {
            int nodeCount = queue.size();
            if(nodeCount == 0) {
                return max;
            }
            max = Math.max(max, nodeCount);
            while(--nodeCount >= 0) {
                Node current = queue.remove();
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
            }
        }
    }

    /**
     * Maximum nodes at a level of a binary tree (Using preorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int maxWidthPreOrder(BinaryTree binaryTree) {
        int max = 0;
        int height = heightIterative(binaryTree);
        int[] count = new int[height + 1];
        maxWidthUtil(binaryTree.root, count, 1);
        for(int i=0; i<count.length; i++) {
            max = Math.max(count[i], max);
        }
        return max;
    }

    private static void maxWidthUtil(Node node, int[] count, int level) {
        if(node == null) {
            return;
        }
        count[level]++;
        maxWidthUtil(node.left, count, level+1);
        maxWidthUtil(node.right, count, level+1);
    }

    /**
     * Level of a node in a binary tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelOfNodeRecursive(BinaryTree binaryTree, int data) {
        return levelOfNodeRecursiveUtil(binaryTree.root, data, 1);
    }

    public static int levelOfNodeRecursiveUtil(Node node, int data, int level) {
        if(node ==null) {
            return 0;
        }
        if(node.data == data) {
            return level;
        }
        int downLevel = levelOfNodeRecursiveUtil(node.left, data, level+1);
        if(downLevel != 0) {
            return downLevel;
        }
        return  levelOfNodeRecursiveUtil(node.right, data, level+1);
    }

    /**
     * Level of a node in a binary tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int levelOfNodeIterative(BinaryTree binaryTree, int data) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int level = 0;
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            level++;
            while(nodeCount > 0) {
                Node current = queue.remove();
                if(current.data == data) {
                    return level;
                }
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
                nodeCount--;
            }
        }
        return 0;
    }

    /**
     * Check if given binary tree is a BST (Naive approach)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static boolean isBSTNaive(BinaryTree binaryTree) {
        return isBSTNaive(binaryTree.root, binaryTree);
    }

    private static boolean isBSTNaive(Node node, BinaryTree binaryTree) {
        if(node == null) {
            return true;
        }
        if(node.left != null && binaryTree.maxRecursive(node.left) > node.data) {
            return false;
        }
        if(node.right != null && binaryTree.minRecursive(node.right) < node.data) {
            return false;
        }
        return isBSTNaive(node.left, binaryTree) && isBSTNaive(node.right, binaryTree);
    }

    /**
     * Check if given binary tree is a BST (Efficient recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public boolean isValidBST(Node root) {
        return isBST(root, null, null);
    }

    private boolean isBST(Node node, Integer min, Integer max) {
        if(node == null) {
            return true;
        }
        if((min != null && node.data <= min) || (max != null && node.data >= max)) {
            return false;
        }
        return isBST(node.left, min, node.data) && isBST(node.right, node.data, max);
    }

    /**
     * Check if given binary tree is a BST (Inorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isBSTInorder(BinaryTree binaryTree) {
        return isBSTInorder(binaryTree.root);
    }
    private static Integer prev = Integer.MIN_VALUE;
    private static boolean isBSTInorder(Node node) {
        if(node == null) {
            return true;
        }
        if(!isBSTInorder(node.left)) {
            return false;
        }
        if(prev != null && node.data <= prev) {
            return false;
        }
        prev = node.data;
        return isBSTInorder(node.right);
    }

    /**
     * Check if given binary tree is a BST (Using nodes)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isBSTUsingNode(BinaryTree binaryTree) {
        return isBSTUsingNode(binaryTree.root, null, null);
    }

    private static boolean isBSTUsingNode(Node node, Node left, Node right) {
        if(node == null) {
            return true;
        }
        if(left != null && node.data < left.data) {
            return false;
        }
        if(right != null && node.data > right.data) {
            return false;
        }
        return isBSTUsingNode(node.left, left, node) && isBSTUsingNode(node.right, node, right);
    }

    /**
     * Check if given binary tree is a BST (Using Morris Traversal)
     *
     * TC: O(n)
     * SC: O(n)
     */
    private static boolean isValidBSTMorris(BinaryTree binaryTree) {
        Node current = binaryTree.root;
        Node prev = null;
        while(current != null) {
            if(current.left == null) {
                if(prev != null && current.data < prev.data) {
                    return false;
                }
                prev = current;
                current = current.right;
            } else {
                Node pred = current.left;
                while(pred.right != null && pred.right != current ) {
                    pred = pred.right;
                }
                if(pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {
                    pred.right = null;
                    if(prev != null && current.data < prev.data) {
                        return false;
                    }
                    prev = current;
                    current = current.right;
                }
            }
        }
        return true;
    }

    /**
     * Lowest common ancestor of binary search tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int lcaBSTRecursive(BinarySearchTree binaryTree, int a, int b) {
        BSTNode lca = lcaBSTRecursive(binaryTree.getRoot(), a , b);
        if(lca != null) {
            return lca.data;
        }
        return Integer.MIN_VALUE;
    }

    private static BSTNode lcaBSTRecursive(BSTNode node, int a, int b) {
        if(node == null) {
            return null;
        }
        if(node.data > a && node.data > b) {
            return lcaBSTRecursive(node.left, a , b);
        }
        if(node.data < a && node.data < b) {
            return lcaBSTRecursive(node.right, a , b);
        }
        return node;
    }

    /**
     * Lowest common ancestor of binary search tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int lcaBSTIterative(BinarySearchTree binarySearchTree, int a, int b) {
        BSTNode current = binarySearchTree.getRoot();
        while(current != null) {
            if(current.data > a && current.data > b) {
                current = current.left;
            }
            else if(current.data < a && current.data < b) {
                current = current.right;
            } else {
                return current.data;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * Print all possible binary trees from given inorder
     *
     * TC: O(n^3)
     * SC: O(n^2)
     */
    public static void printPossibleTrees(int[] inOrder) {
        List<Node> trees = getTrees(inOrder, 0, inOrder.length-1);
        BinaryTree binaryTree = new BinaryTree();
        for(int i=0; i<trees.size(); i++) {
            binaryTree.preOrderRecursive(trees.get(i));
            System.out.println();
        }
    }

    private static List<Node> getTrees(int[] in, int start, int end) {
        List<Node> trees = new ArrayList<>();
        if(start > end) {
            trees.add(null);
            return trees;
        }
        for(int i=start; i<=end; i++) {
            List<Node> leftTrees = getTrees(in, start, i-1);
            List<Node> rightTrees = getTrees(in, i+1, end);
            for(int j=0; j<leftTrees.size(); j++) {
                for(int k = 0; k<rightTrees.size(); k++) {
                    Node node = new Node(in[i]);
                    node.left = leftTrees.get(j);
                    node.right = rightTrees.get(k);
                    trees.add(node);
                }
            }
        }
        return trees;
    }

    /**
     * Fill next sibling (consider all node at same level as sibling)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void fillNextSibling(BNSNode node) {
        if(node == null) {
            return;
        }
        Queue<BNSNode> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);
        while(!queue.isEmpty()) {
            BNSNode temp = queue.remove();
            if(temp != null) {
                temp.nextSibling = queue.peek();
                if(temp.left != null) {
                    queue.add(temp.left);
                }
                if(temp.right != null) {
                    queue.add(temp.right);
                }
            } else {
                if(!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }
    }

    /**
     * Fill next sibling (consider all node at same level as sibling - Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void fillNextSiblingRecursive(BNSNode node) {
        if(node == null) {
            return;
        }
        if(node.left != null) {
            node.left.nextSibling = node.right;
        }
        if(node.right != null) {
            if(node.nextSibling != null) {
                node.right.nextSibling = node.nextSibling.left;
            }
        }
        fillNextSiblingRecursive(node.left);
        fillNextSiblingRecursive(node.right);
    }

    public static void largestAtEachLevel(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        int nodeCount;
        int max;
        while (!queue.isEmpty()) {
            nodeCount = queue.size();
            max = Integer.MIN_VALUE;
            while(nodeCount > 0) {
                Node current = queue.remove();
                max = Math.max(current.data, max);
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
                nodeCount--;
            }
            System.out.println(max + " ");
        }
    }

    /**
     * Leftmost node of a node in binary tree
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node leftMostNode(Node node) {
        while(node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Rightmost node of a node in binary tree
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node rightMostNode(Node node) {
        while(node != null && node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * Inorder successor of a binary tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void inorderSuccessor(BinaryTree binaryTree, Node node) {
        if(node.right != null) {
            Node leftMost = leftMostNode(node.right);
            System.out.println(leftMost.data);
        } else {
            Node rightMost = rightMostNode(binaryTree.root);
            if(node == rightMost) {
                System.out.println("No successor");
            }
            inorderSuccessorRecursive(binaryTree.root, node);
        }
    }

    private static Node inorderSuccessorRecursive(Node root, Node node) {
        if (root == null) {
            return null;
        }
        Node temp = null;
        if (root == node || (temp = inorderSuccessorRecursive(root.left, node)) != null
                || (temp = inorderSuccessorRecursive(root.right, node)) != null) {
            if (temp != null) {
                if (root.left == temp) {
                    System.out.println(root.data);
                    return null;
                }
            }
            return root;
        }
        return null;
    }

    /**
     * Inorder successor of a binary tree (Using reverse inorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static Node inorderSuccessorInorder(BinaryTree binaryTree, Node searchNode) {
        return inorderSuccessorRecur(binaryTree.root, searchNode);
    }

    private static Node pre = null;

    private static Node inorderSuccessorRecur(Node node, Node searchNode) {
        if(node == null) {
            return null;
        }
        Node successor = inorderSuccessorRecur(node.right, searchNode);
        if(successor != null) {
            return successor;
        }
        if(node == searchNode) {
            return pre;
        }
        pre = node;
        return inorderSuccessorRecur(node.left, searchNode);
    }

    /**
     * Preorder successor of a binary tree (Using parent pointer)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node preorderSuccessor(BinaryTree binaryTree, Node node) {
        if(node.left != null) {
            return node.left;
        }
        if(node.right != null) {
            return node.right;
        }
        Node parent = node.parent;
        Node current = node;
        while(parent != null && parent.right == current) {
            current = current.parent;
            parent = parent.parent;
        }
        return parent != null ? parent.right : null;
    }

    /**
     * Postorder successor of a binary tree (Using parent pointer)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node postorderSuccessor(BinaryTree binaryTree, Node node) {
        if(node == binaryTree.root) {
            return null;
        }
        Node parent = node.parent;
        // when node is left of parent and right of parent is null, or when node is right of parent.
        if(parent.right == null || parent.right == node) {
            return parent;
        }
        // when right of parent is not null
        Node current = parent.right;
        while(current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Is a binary tree height balanced
     *
     * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isHeightBalanced(BinaryTree binaryTree) {
        if(binaryTree.root == null) {
            return true;
        }
        return isBalanced(binaryTree.root, new Height());
    }

    private static boolean isBalanced(Node node, Height height) {
        if(node == null) {
            return true;
        }
        Height lHeight = new Height();
        Height rHeight = new Height();
        boolean l = isBalanced(node.left, lHeight);
        boolean r = isBalanced(node.right, rHeight);
        height.value = Math.max(lHeight.value, rHeight.value) + 1;
        if(Math.abs(lHeight.value - rHeight.value) > 1) {
            return false;
        }
        if(!l || !r) {
            return false;
        }
        return true;
    }

    /**
     * Is a binary tree height balanced (Space optimized)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isHeightBalancedSpaceOptimized(BinaryTree binaryTree) {
        if(binaryTree.root == null) {
            return true;
        }
        return isBalanced(binaryTree.root) != -1;
    }

    private static int isBalanced(Node node) {
        if(node == null) {
            return 0;
        }
        int lHeight = isBalanced(node.left);
        if(lHeight == -1) {
            return -1;
        }
        int rHeight = isBalanced(node.right);
        if(rHeight == -1) {
            return -1;
        }
        if(Math.abs(lHeight - rHeight) > 1) {
            return -1;
        }
        return Math.max(lHeight, rHeight) + 1;
    }

    /**
     * Maximum width of a binary tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int widthOfBinaryTree(Node root) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 1));
        int maxWidth = 0;
        while(!queue.isEmpty()) {
            System.out.println(true);
            int nodeCount = queue.size();
            int min = queue.peek().index;
            int first = 0;
            int last = 0;
            for(int i=0; i<nodeCount; i++) {
                Pair current = queue.remove();
                Node node = current.node;
                int index = current.index-min;
                if(i==0) {
                    first = index;
                }
                if(i==nodeCount-1) {
                    last = index;
                }
                if(node.left != null) {
                    queue.add(new Pair(node.left, 2*index+1));
                }
                if(node.right != null) {
                    queue.add(new Pair(node.right, 2*index+2));
                }
            }
            maxWidth = Math.max(maxWidth, last-first+1);
        }
        return maxWidth;
    }

    /**
     * Count no of nodes in a complete binary tree
     *
     * TC: O((logn)^2)
     * SC: O(1)
     */
    public static int countNodes(Node root) {
        if(root == null) {
            return 0;
        }
        int lHeight = leftHeight(root);
        int rHeight = rightHeight(root);
        if(lHeight == rHeight) {
            return (1<<lHeight) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int leftHeight(Node node) {
        int height = 0;
        while(node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    private static int rightHeight(Node node) {
        int height = 0;
        while(node != null) {
            height++;
            node = node.right;
        }
        return height;
    }

    /**
     * Inorder successor of a binary search tree
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node inorderSuccessorBST(Node root, Node p) {
        if(root == null) {
            return null;
        }
        Node successor = null;
        while(root != null) {
            if(p.data >= root.data) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }

    /**
     * Largest BST in a binary tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int largestBst(Node root) {
        return validBST(root).size;
    }

    private static NodeValue validBST(Node node) {
        if (node == null) {
            return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        NodeValue left = validBST(node.left);
        NodeValue right = validBST(node.right);
        if (left.max < node.data && node.data < right.min) {
            return new NodeValue(Math.min(left.min, node.data), Math.max(right.max, node.data), left.size + right.size + 1);
        }
        return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.size, right.size));
    }

}

class Height {
    int value;
}

class BNSNode {
    int data;
    BNSNode left, right, nextSibling;
    BNSNode(int data) {
        this.data = data;
        left = right = nextSibling = null;
    }
}

class Pair {
    Node node;
    int index;
    Pair(Node node, int index) {
        this.node = node;
        this.index = index;
    }
}

class NodeValue {
    int min;
    int max;
    int size;
    NodeValue(int min, int max, int size) {
        this.min = min;
        this.max = max;
        this.size = size;
    }
}
