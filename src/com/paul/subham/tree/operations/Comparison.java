package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 
 * 1. Check if two binary trees are mirrors (Recursive)
 * 2. Check if two binary trees are mirrors (Iterative - Inorder)
 * 3. Check if two binary trees are mirrors (Iterative - level order)
 * 4. Check if two binary trees are identical (Recursive)
 * 5. Check if two binary trees are identical (Iterative - Inorder)
 * 6. Check if two binary trees are identical (Iterative - level order)
 * 7. Check if two binary trees are isomorphic
 * 8. Check if two binary trees are quasi-isomorphic
 */
public class Comparison {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.left.right.left = new Node(7);
        tree.root.left.right.right = new Node(8);

        BinaryTree tree1 = new BinaryTree();
        tree1.root = new Node(1);
        tree1.root.left = new Node(3);
        tree1.root.right = new Node(2);
        tree1.root.right.left = new Node(4);
        tree1.root.right.right = new Node(5);
        tree1.root.left.right = new Node(6);
        tree1.root.right.right.left = new Node(8);
        tree1.root.right.right.right = new Node(11);

        System.out.println(isQuasiIsomorphic(tree, tree1));
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

    /**
     * Check if two binary trees are identical (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isIdenticalRecursive(BinaryTree binaryTree1, BinaryTree binaryTree2) {
        return isIdenticalRecursiveUtil(binaryTree1.root, binaryTree2.root);
    }

    private static boolean isIdenticalRecursiveUtil(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.data != node2.data) {
            return false;
        }
        return isIdenticalRecursiveUtil(node1.left, node2.left) && isIdenticalRecursiveUtil(node1.right, node2.right);
    }

    /**
     * Check if two binary trees are identical (Iterative - Inorder)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isIdenticalIterative(BinaryTree binaryTree1, BinaryTree binaryTree2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node node1 = binaryTree1.root;
        Node node2 = binaryTree2.root;
        while(true) {
            while(node1 != null && node2 != null) {
                stack1.push(node1);
                stack2.push(node2);
                node1 = node1.left;
                node2 = node2.left;
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
            node2 = node2.right;
        }
        return true;
    }

    /**
     * Check if two binary trees are identical (Iterative - level order)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isIdenticalIterativeLevelOrder(BinaryTree binaryTree1, BinaryTree binaryTree2) {
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
            if(current2.left != null) {
                queue2.add(current2.left);
            }
            if(current2.right != null) {
                queue2.add(current2.right);
            }
        }
        if(!(queue1.isEmpty() && queue2.isEmpty())) {
            return false;
        }
        return true;
    }

    /**
     * Check if two binary trees are isomorphic
     *
     * Two trees are called isomorphic if one of them can be obtained from other by a series of flips,
     * i.e. by swapping left and right children of a number of nodes.
     * Any number of nodes at any level can have their children swapped.
     * Two empty trees are isomorphic.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isIsomorphic(BinaryTree binaryTree1, BinaryTree binaryTree2) {
        return isIsomorphic(binaryTree1.root, binaryTree2.root);
    }

    private static boolean isIsomorphic(Node node1, Node node2) {
        if(node1 == null && node2 == null) {
            return true;
        }
        if(node1 == null || node2 == null) {
            return false;
        }
        if(node1.data != node2.data) {
            return false;
        }
        return (isIsomorphic(node1.left, node2.left) && isIsomorphic(node1.right, node2.right))
                || (isIsomorphic(node1.left, node2.right) && isIsomorphic(node1.right, node2.left));
    }

    /**
     * Check if two binary trees are quasi-isomorphic
     *
     * Two trees are called quasi-isomorphic if one of them can be obtained from other by a series of flips,
     * i.e. by swapping left and right children of a number of nodes, values don't matter, only shape matters
     * Any number of nodes at any level can have their children swapped.
     * Two empty trees are quasi-isomorphic.
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isQuasiIsomorphic(BinaryTree binaryTree1, BinaryTree binaryTree2) {
        return isQuasiIsomorphic(binaryTree1.root, binaryTree2.root);
    }

    private static boolean isQuasiIsomorphic(Node node1, Node node2) {
        if(node1 == null && node2 == null) {
            return true;
        }
        if(node1 == null || node2 == null) {
            return false;
        }
        return (isQuasiIsomorphic(node1.left, node2.left) && isQuasiIsomorphic(node1.right, node2.right))
                || (isQuasiIsomorphic(node1.left, node2.right) && isQuasiIsomorphic(node1.right, node2.left));
    }
}
