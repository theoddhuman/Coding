package com.paul.subham.tree.operations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author subham.paul
 *
 * 1. Print postorder traversal from preorder and inorder
 * 2. Print postorder traversal from preorder and inorder (Efficient)
 * 3. Print postorder traversal from preorder and inorder (Using Hashing)
 * 4. Print postorder traversal from preorder
 */
public class Traversal {
    public static void main(String[] args) {
        int[] pre = {4,2,1,3,6,5,7};
        int[] in = {4,2,5,1,6,3,7};
        printPostFromPre(pre);
    }

    /**
     * Print postorder traversal from preorder and inorder
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void printPostFromPreAndIn(int[] pre, int[] in, int n) {
        int root = search(in, pre[0], n);
        if(root != 0) {
            printPostFromPreAndIn(Arrays.copyOfRange(pre, 1, n), in, root);
        }
        if(root != n-1) {
            printPostFromPreAndIn(Arrays.copyOfRange(pre, root+1, n), Arrays.copyOfRange(in, root+1, n), n-root-1);
        }
        System.out.print(pre[0] + " ");
    }

    private static int search(int[] in, int preData, int n) {
        for(int i =0; i<n; i++) {
            if(in[i] == preData) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Print postorder traversal from preorder and inorder (Efficient)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void printPostFromPreIn(int[] pre, int[] in, int inStart, int inEnd) {
        if(inStart > inEnd) {
            return;
        }
        int root = search(in, inStart, inEnd, pre[pIndex++]);
        printPostFromPreIn(pre, in, inStart, root-1);
        printPostFromPreIn(pre, in, root+1, inEnd);
        System.out.print(in[root] + " ");
    }

    private static int pIndex = 0;
    private static int search(int[] in, int inStart, int inEnd, int preData) {
        for(int i=inStart; i<=inEnd; i++) {
            if(in[i] == preData) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Print postorder traversal from preorder and inorder (Using Hashing)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void printPostFromPreIn(int[] in, int[] pre) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i=0; i<in.length; i++) {
            inMap.put(in[i], i);
        }
        printPostFromPreIn(in, pre, 0, in.length-1, inMap);
    }

    private static void printPostFromPreIn(int[] in, int[] pre, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd) {
            return;
        }
        int root = inMap.get(pre[pIndex++]);
        printPostFromPreIn(pre, in, inStart, root-1);
        printPostFromPreIn(pre, in, root+1, inEnd);
        System.out.print(in[root] + " ");
    }

    /**
     * Print postorder traversal from preorder
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void printPostFromPre(int[] pre) {
        printPostFromPre(pre, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static void printPostFromPre(int[] pre, int min, int max) {
        if (pIndex == pre.length) {
            return;
        }
        if (pre[pIndex] < min || pre[pIndex] > max) {
            return;
        }
        int root = pre[pIndex++];
        printPostFromPre(pre, min, root);
        printPostFromPre(pre, root, max);
        System.out.print(root + " ");
    }
}
