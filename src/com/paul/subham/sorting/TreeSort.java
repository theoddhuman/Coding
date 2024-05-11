package com.paul.subham.sorting;

import com.paul.subham.tree.implementation.binarysearch.BinarySearchTree;
import com.paul.subham.tree.implementation.binarysearch.BSTNode;

import java.util.Arrays;

public class TreeSort {
    private static int index = 0;
    public static void main(String[] args) {
        int[] a = {4,3,2,7, 11, 5,1};
        sort(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + " "));
    }

    /**
     * TC: Worst - O(n^2)
     *     Average - O(nlogn)
     *     Best - O(n)
     * SC: O(n)
     */
    static void sort(int a[]) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for(int i=0; i<a.length; i++) {
            binarySearchTree.insert(a[i]);
        }
        inOrder(binarySearchTree.getRoot(), a);
    }

    public static void inOrder(BSTNode node, int[] a) {
        if(node != null) {
            inOrder(node.left, a);
            a[index] = node.data;
            index++;
            inOrder(node.right, a);
        }
    }
}
