package com.paul.subham.tree.operations.generic;

import com.paul.subham.tree.implementation.generic.GNode;
import com.paul.subham.tree.implementation.generic.GenericTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. Depth or height of array representation of generic tree
 * 2. Depth or height of array representation of generic tree (Dynamic Programming)
 * 3. Count siblings of a node in generic tree (Recursive)
 * 4. Count siblings of a node in generic tree (iterative)
 * 5. Count children of a node in generic tree
 */
public class Structure {
    public static void main(String[] args) {
//        int[] a = {-1, 0, 1, 6, 6, 0, 0, 2, 7};
//        System.out.println(depthFromGenericArrayDP(a));
        GenericTree genericTree = new GenericTree();
        genericTree.root = new GNode(1);
        GNode gNode = genericTree.addChild(genericTree.root, 2);
        GNode gNode1 = genericTree.addSibling(gNode, 3);
        genericTree.addSibling(gNode, 4);
        GNode gNode2 = genericTree.addChild(gNode, 7);
        genericTree.addSibling(gNode2, 8);
        GNode gNode3 = genericTree.addChild(gNode1, 9);
        GNode gNode4 = genericTree.addSibling(gNode3, 10);
        genericTree.addSibling(gNode3, 11);
        GNode gNode5 = genericTree.addChild(gNode4, 12);
        genericTree.addSibling(gNode5, 14);
        genericTree.addSibling(gNode5, 15);
        genericTree.traversal(genericTree.root);
        System.out.println();
        System.out.println(countSiblingIterative(genericTree, gNode4));
    }

    /**
     * Depth or height of array representation of generic tree
     * {-1, 0, 1, 6, 6, 0, 0, 2, 7}
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int depthFromGenericArray(int[] a) {
        int max = Integer.MIN_VALUE;
        int count = 0;
        int j;
        for(int i=0; i<a.length; i++) {
            count = 0;
            j = i;
            while(a[j] != -1) {
                count++;
                j = a[j];
            }
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * Depth or height of array representation of generic tree (Dynamic Programming)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int depthFromGenericArrayDP(int[] a) {
        int max = Integer.MIN_VALUE;
        int j;
        int[] c = new int[a.length];
        for(int i=0; i<a.length; i++) {
            j = i;
            while(a[j] != -1) {
                if(c[j] > 0) {
                    c[i] += c[j];
                    break;
                }
                c[i]++;
                j = a[j];
            }
            max = Math.max(c[i], max);
        }
        return max;
    }

    /**
     * Count siblings of a node in generic tree (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int countSiblingRecursive(GenericTree genericTree, GNode sibling) {
        return countSiblingUtil(genericTree.root, sibling);
    }

    private static int countSiblingUtil(GNode node, GNode bro) {
        if(node == null) {
            return 0;
        }
        int count = 0;
        boolean found = false;
        int childCount = 0;
        while(node != null) {
            count++;
            if(node == bro) {
                found = true;
            }
            if(node.child != null && !found) {
                childCount = countSiblingUtil(node.child, bro);
            }
            if(childCount > 0) {
                return childCount;
            }
            node = node.next;
        }
        if(found) {
            return count;
        }
        return 0;
    }

    /**
     * Count siblings of a node in generic tree (iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static int countSiblingIterative(GenericTree genericTree, GNode bro) {
        if(genericTree.root == null) {
            return 0;
        }
        Queue<GNode> queue = new LinkedList<>();
        GNode current = null;
        queue.add(genericTree.root);
        while(!queue.isEmpty()) {
            current = queue.remove();
            int count=0;
            boolean found = false;
            while(current != null) {
                count++;
                if(current == bro) {
                    found = true;
                }
                if(current.child != null) {
                    queue.add(current.child);
                }
                current = current.next;
            }
            if(found) {
                return count;
            }
        }
        return 0;
    }

    /**
     * Count children of a node in generic tree
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static int countChild(GNode node) {
        int count = 0;
        GNode current = node.child;
        while(current != null) {
            count ++ ;
            current = current.next;
        }
        return count;
    }
}
