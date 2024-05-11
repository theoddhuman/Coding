package com.paul.subham.tree.operations.generic;

import com.paul.subham.tree.implementation.generic.GNode;
import com.paul.subham.tree.implementation.generic.GenericTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 * 
 * 1. Sum of all elements in a generic tree (Recursive)
 * 2. Sum of all elements in a generic tree (Iterative)
 */
public class Sum {
    public static void main(String[] args) {
        GenericTree genericTree = new GenericTree();
        genericTree.root = new GNode(1);
        genericTree.addSibling(genericTree.root, 2);
        genericTree.addChild(genericTree.root, 4);
        genericTree.addChild(genericTree.root, 5);
        genericTree.levelOrderTraversal();
        System.out.println();
        System.out.println(sumIterative(genericTree));
    }

    /**
     * Sum of all elements in a generic tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int sumRecursive(GenericTree genericTree) {
        return sumUtil(genericTree.root);
    }

    private static int sumUtil(GNode node) {
        if(node == null) {
            return 0;
        }
        return node.data + sumUtil(node.child) + sumUtil(node.next);
    }

    /**
     * Sum of all elements in a generic tree (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int sumIterative(GenericTree genericTree) {
        if(genericTree.root == null) {
            return 0;
        }
        Queue<GNode> queue = new LinkedList<>();
        queue.add(genericTree.root);
        int sum =0;
        while (!queue.isEmpty()) {
            GNode current = queue.remove();
            while(current != null) {
                sum += current.data;
                if(current.child != null) {
                    queue.add(current.child);
                }
                current = current.next;
            }
        }
        return sum;
    }
}
