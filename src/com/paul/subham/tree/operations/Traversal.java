package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.*;

/**
 * @author subham.paul
 *
 * 1. Print postorder traversal from preorder and inorder
 * 2. Print postorder traversal from preorder and inorder (Efficient)
 * 3. Print postorder traversal from preorder and inorder (Using Hashing)
 * 4. Print postorder traversal from preorder
 * 5. kth node of postorder traversal of binary tree
 * 6. kth node of inorder traversal of binary tree
 * 7. Vertical order traversal
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

    /**
     * kth node of postorder traversal of binary tree
     *
     * TC: O(n)
     * SC: O(k)
     */
    public static void kthPostorder(BinaryTree binaryTree, int k) {
        kthPostorder(binaryTree.root, k);
    }
    
    private static int count = 0;
    private static void kthPostorder(Node node, int k) {
        if(node == null) {
            return;
        }
        if(count < k) {
            kthPostorder(node.left, k);
            kthPostorder(node.right, k);
            count++;
            if(count == k) {
                System.out.println(node.data);
            }
        }
    }

    /**
     * kth node of inorder traversal of binary tree
     *
     * TC: O(n)
     * SC: O(k)
     */
    public static void kthInorder(BinaryTree binaryTree, int k) {
        kthInorder(binaryTree.root, k);
    }
    private static void kthInorder(Node node, int k) {
        if(node == null) {
            return;
        }
        if(count < k) {
            kthInorder(node.left, k);
            count++;
            if(count == k) {
                System.out.println(node.data);
            }
            kthInorder(node.right, k);
        }
    }

    /**
     * Vertical order traversal
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static List<List<Integer>> verticalTraversal(Node node) {
        Queue<Tuple> queue = new LinkedList<>();
        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        queue.add(new Tuple(node, 0, 0));
        while(!queue.isEmpty()) {
            Tuple current = queue.remove();
            Node cNode = current.node;
            int h = current.hLevel;
            int v = current.vLevel;
            if(!map.containsKey(v)) {
                map.put(v, new TreeMap<>());
            }
            if(!map.get(v).containsKey(h)) {
                map.get(v).put(h, new PriorityQueue<>());
            }
            map.get(v).get(h).add(cNode.data);
            if(cNode.left != null) {
                queue.add(new Tuple(cNode.left, v-1, h+1));
            }
            if(cNode.right != null) {
                queue.add(new Tuple(cNode.right, v+1, h+1));
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(Map<Integer, PriorityQueue<Integer>> hMap : map.values()) {
            List<Integer> list = new ArrayList<>();
            for(PriorityQueue<Integer> pq : hMap.values()) {
                while(!pq.isEmpty()) {
                    list.add(pq.remove());
                }
            }
            res.add(list);
        }
        return res;
    }
}

class Tuple {
    Node node;
    int vLevel;
    int hLevel;
    Tuple(Node node, int vLevel, int hLevel) {
        this.node = node;
        this.vLevel = vLevel;
        this.hLevel = hLevel;
    }
}
