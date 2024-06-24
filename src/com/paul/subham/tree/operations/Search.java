package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binarysearch.BSTNode;
import com.paul.subham.tree.implementation.binarysearch.BinarySearchTree;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. kth smallest element in a binary search tree (inorder)
 * 2. kth smallest element in a binary search tree (inorder - iterative)
 * 3. kth smallest element in a binary search tree (Morris traversal)
 * 4. kth smallest element in a binary search tree (Augmented Tree)
 * 5. Ceil of a number in binary search tree
 * 6. Floor of a number in binary search tree
 */
public class Search {
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
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(9);
        bst.insert(2);
        bst.insert(4);
        bst.insert(7);
        System.out.println(floor(bst, 6));
//        int[] a = {20,8,22,4,12,10,14};
//        ATree tree = new ATree();
//        for (int j : a) {
//            tree.insert(j);
//        }
//        System.out.println(kthSmallest(tree, 6));
    }

    /**
     * kth smallest element in a binary search tree (inorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int kthSmallestInorder(BinarySearchTree binaryTree, int k) {
        BSTNode node = kthSmallest(binaryTree.root, k);
        if(node != null) {
            return node.data;
        }
        return Integer.MIN_VALUE;
    }

    private static int count = 0;
    private static BSTNode kthSmallest(BSTNode node, int k) {
        if(node == null) {
            return null;
        }
        BSTNode left = kthSmallest(node.left, k);
        if(left != null) {
            return left;
        }
        count++;
        if(count == k) {
            return node;
        }
        return kthSmallest(node.right, k);
    }

    /**
     * kth smallest element in a binary search tree (inorder - iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int kthSmallestInorderIterative(BinarySearchTree binarySearchTree, int k) {
        Stack<BSTNode> stack = new Stack<>();
        BSTNode current = binarySearchTree.root;
        int count = 0;
        while(true) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }
            if(stack.isEmpty()){
                break;
            }
            current = stack.pop();
            count++;
            if(count == k) {
                return current.data;
            }
            current = current.right;
        }
        return Integer.MIN_VALUE;
    }

    /**
     * kth smallest element in a binary search tree (Morris traversal)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int kthSmallestMorris(BinarySearchTree binarySearchTree, int k) {
        BSTNode current = binarySearchTree.root;
        int count = 0;
        while(current != null) {
            if(current.left == null) {
                count++;
                if(count == k) {
                    return current.data;
                }
                current = current.right;
            } else {
                BSTNode pred = current.left;
                while(pred.right != null && pred.right != current) {
                    pred = pred.right;
                }
                if(pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {
                    pred.right = null;
                    count++;
                    if(count == k) {
                        return current.data;
                    }
                    current = current.right;
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * kth smallest element in a binary search tree (Augmented Tree)
     *
     * TC: O(h), h is height of the tree
     * SC: O(h)
     */
    public static int kthSmallest(ATree tree, int k) {
        ANode node = kthSmallest(tree.root, k);
        if(node != null) {
            return node.data;
        }
        return Integer.MIN_VALUE;
    }

    private static ANode kthSmallest(ANode node, int k) {
        if(node == null) {
            return null;
        }
        int count = node.lCount + 1;
        if(count == k) {
            return node;
        }
        if(count > k) {
            return kthSmallest(node.left, k);
        }
        return kthSmallest(node.right, k-count);
    }

    /**
     * Ceil of a number in binary search tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int ceil(BinarySearchTree binarySearchTree, int data) {
        return ceil(binarySearchTree.root, data);
    }

    private static int ceil(BSTNode node, int data) {
        if(node == null) {
            return Integer.MIN_VALUE;
        }
        if(node.data == data) {
            return data;
        }
        if(node.data < data) {
            return ceil(node.right, data);
        }
        int c = ceil(node.left,data);
        return c>=data ? c : node.data;
    }

    /**
     * Floor of a number in binary search tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int floor(BinarySearchTree binarySearchTree, int data) {
        return floor(binarySearchTree.root, data);
    }

    private static int floor(BSTNode node, int data) {
        if(node == null) {
            return Integer.MAX_VALUE;
        }
        if(node.data == data) {
            return data;
        }
        if(data < node.data) {
            return floor(node.left, data);
        }
        int f = floor(node.right,data);
        return f<=data ? f : node.data;
    }
}

class ATree {
    ANode root;

    void insert(int data) {
        root = insertUtil(root, data);
    }

    ANode insertUtil(ANode node, int data) {
        if(node == null) {
            return new ANode(data);
        }
        if(data < node.data) {
            node.left = insertUtil(node.left, data);
            node.lCount++;
        } else if (data > node.data) {
            node.right = insertUtil(node.right, data);
        }
        return node;
    }
}

class ANode {
    int data;
    int lCount;
    ANode left, right;
    ANode(int data) {
        this.data = data;
        this.lCount = 0;
        this.left = this.right = null;
    }
}
