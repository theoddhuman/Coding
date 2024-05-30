package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Construct binary tree from preorder and inorder traversal
 * 2. Construct binary tree from preorder and inorder traversal (Using hashing)
 * 3. Construct binary tree from preorder and inorder traversal (using stack and set)
 */
public class Construction {
    public static void main(String[] args) {
         int[] pre = {1,2,4,5,6,7,3};
         int[] in = {4,2,6,5,7,1,3};
         BinaryTree binaryTree = constructBinaryTreeStackAndSet(pre, in);
         binaryTree.levelOrder();
    }

    /**
     * Construct binary tree from preorder and inorder traversal
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeFromPreIn(int[] pre, int[] in) {
        if(pre.length == 0 || in.length==0) {
            return null;
        }
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBT(pre, in, 0, in.length-1);
        return binaryTree;
    }

    private static int pIndex = 0;

    private static Node buildBT(int[] pre, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if(inStart == inEnd) {
            return node;
        }
        int index = search(in, inStart, inEnd, node.data);
        node.left = buildBT(pre, in, inStart, index-1);
        node.right = buildBT(pre, in, index+1, inEnd);
        return node;
    }

    private static int search(int[] a, int left, int right, int data) {
        for(int i=left; i<=right; i++) {
            if(a[i] == data) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Construct binary tree from preorder and inorder traversal (using Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreePreInHashing(int[] pre, int[] in) {
        if(pre.length == 0 || in.length==0) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i=0; i<in.length; i++) {
            inMap.put(in[i], i);
        }
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBT(pre, in, 0, in.length-1, inMap);
        return binaryTree;
    }

    private static Node buildBT(int[] pre, int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(pre[pIndex++]);
        if(inStart == inEnd) {
            return node;
        }
        int index = inMap.get(node.data);
        node.left = buildBT(pre, in, inStart, index-1, inMap);
        node.right = buildBT(pre, in, index+1, inEnd, inMap);
        return node;
    }

    /**
     * Construct binary tree from preorder and inorder traversal (using stack and set)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeStackAndSet(int[] pre, int[] in) {
        // To start the path we visited while traversing preorder array
        Stack<Node> stack = new Stack<>();
        //To maintain the node in which right subtree expected
        Set<Node> set = new HashSet<>();
        BinaryTree binaryTree = new BinaryTree();
        for(int p=0, i=0; p<pre.length;) {
            Node node = null;
            do {
                node = new Node(pre[p]);
                if(binaryTree.root == null) {
                    binaryTree.root = node;
                }
                if(!stack.isEmpty()) {
                    if(set.contains(stack.peek())) {
                        set.remove(stack.peek());
                        stack.pop().right = node;
                    } else {
                        stack.peek().left = node;
                    }
                }
                stack.push(node);
            } while (pre[p++] != in[i] && p < pre.length);

            // To find the node with right subtree
            while(i < in.length && !stack.isEmpty() && in[i] == stack.peek().data) {
                node = stack.pop();
                i++;
            }
            if(node != null) {
                set.add(node);
                stack.push(node);
            }
        }
        return binaryTree;
    }

}
