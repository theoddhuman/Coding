package com.paul.subham.tree.implementation.generic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. Add sibling to a node
 * 2. Add child to a node
 * 3. Traversal of generic tree
 * 4. Level order traversal
 */
public class GenericTree {
    public GNode root;

    /**
     * Add sibling to a node
     *
     * TC: O(n)
     * SC: O(1)
     */
    public GNode addSibling(GNode node, int data) {
        if(node == null) {
            return null;
        }
        while(node.next != null) {
            node = node.next;
        }
        return node.next = new GNode(data);
    }

    /**
     * Add child to a node
     *
     * TC: O(n)
     * SC: O(1)
     */
    public GNode addChild(GNode node, int data) {
        if(node == null) {
            return null;
        }
        if(node.child != null) {
            return addSibling(node.child, data);
        } else {
            return node.child = new GNode(data);
        }
    }

    /**
     * Traversal of generic tree
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void traversal(GNode node) {
        if(node == null) {
            return;
        }
        while(node != null) {
            System.out.print(node.data +" ");
            if(node.child != null) {
                traversal(node.child);
            }
            node = node.next;
        }
    }

    /**
     * Level order traversal
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void levelOrderTraversal() {
        if(root == null) {
            return;
        }
        Queue<GNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            GNode current = queue.remove();
            while(current != null) {
                System.out.print(current.data + " ");
                if(current.child != null) {
                    queue.add(current.child);
                }
                current = current.next;
            }
        }
    }
}
