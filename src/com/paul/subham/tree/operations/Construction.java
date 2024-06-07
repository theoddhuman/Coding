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
 * 4. Construct binary tree from postorder and inorder traversal
 * 5. Construct binary tree from postorder and inorder traversal (Hashing)
 * 6. Construct binary tree from levelorder and inorder traversal
 * 7. Construct binary tree from levelorder and inorder traversal (Hashing)
 */
public class Construction {
    public static void main(String[] args) {
         //int[] pre = {1,2,4,5,6,7,3};
         //int[] post = {4,5,2,6,7,3,1};
         int[] in = {4,2,5,1,6,3,7};
         int[] level = {1,2,3,4,5,6,7};
         BinaryTree binaryTree = constructBinaryTreeLevelInHashing(level, in);
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

    /**
     * Construct binary tree from postorder and inorder traversal
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreePostIn(int[] in, int[] post) {
        BinaryTree binaryTree = new BinaryTree();
        pIndex = post.length-1;
        binaryTree.root = buildBTPost(post, in, 0, in.length-1);
        return binaryTree;
    }

    private static Node buildBTPost(int[] post, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(post[pIndex--]);
        if(inStart == inEnd) {
            return node;
        }
        int index = search(in, inStart, inEnd, node.data);
        node.right = buildBTPost(post, in, index+1, inEnd);
        node.left = buildBTPost(post, in, inStart, index-1);
        return node;
    }

    /**
     * Construct binary tree from postorder and inorder traversal (Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreePostInHashing(int[] in, int[] post) {
        BinaryTree binaryTree = new BinaryTree();
        pIndex = post.length-1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<in.length; i++) {
            map.put(in[i], i);
        }
        binaryTree.root = buildBTPost(post, in, 0, in.length-1, map);
        return binaryTree;
    }

    private static Node buildBTPost(int[] post, int[] in, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd) {
            return null;
        }
        Node node = new Node(post[pIndex--]);
        if(inStart == inEnd) {
            return node;
        }
        int index = inMap.get(node.data);
        node.right = buildBTPost(post, in, index+1, inEnd, inMap);
        node.left = buildBTPost(post, in, inStart, index-1, inMap);
        return node;
    }

    /**
     * Construct binary tree from levelorder and inorder traversal
     *
     * TC: O(n^3)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeLevelIn(int[] level, int[] in) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBinaryTree(level, in, 0, in.length-1);
        return binaryTree;
    }

    private static Node buildBinaryTree(int[] level, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        if(inStart == inEnd) {
            return new Node(in[inStart]);
        }
        Node node = null;
        int index = 0;
        for(int i=0; i<level.length; i++) {
            boolean found = false;
            for(int j=inStart; j<=inEnd; j++) {
                if(level[i] == in[j]) {
                    node = new Node(in[j]);
                    index = j;
                    found = true;
                    break;
                }
            }
            if(found) {
                break;
            }
        }
        node.left = buildBinaryTree(level, in, inStart, index-1);
        node.right = buildBinaryTree(level, in, index+1, inEnd);
        return node;
    }

    /**
     * Construct binary tree from levelorder and inorder traversal (Hashing)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static BinaryTree constructBinaryTreeLevelInHashing(int[] level, int[] in) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = buildBinaryTreeHashing(level, in, 0, in.length-1);
        return binaryTree;
    }

    private static Node buildBinaryTreeHashing(int[] level, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return null;
        }
        if(inStart == inEnd) {
            return new Node(in[inStart]);
        }
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i=inStart; i<=inEnd; i++) {
            inMap.put(in[i], i);
        }
        Node node = null;
        int index = 0;
        for(int i=0; i<level.length; i++) {
            if(inMap.containsKey(level[i])) {
                node = new Node(level[i]);
                index = inMap.get(level[i]);
                break;
            }
        }
        node.left = buildBinaryTree(level, in, inStart, index-1);
        node.right = buildBinaryTree(level, in, index+1, inEnd);
        return node;
    }

}
