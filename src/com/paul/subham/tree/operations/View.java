package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author subham.paul
 *
 * 1. Left view of a binary tree (recursion)
 * 2. Right view of a binary tree (recursion)
 * 3. Left view of a binary tree (level order)
 * 4. Bottom view of a binary tree (level order)
 * 5. Bottom view of a binary tree (Post order)
 */
public class View {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
//        tree.root = new Node(10);
//        tree.root.left = new Node(2);
//        tree.root.right = new Node(3);
//        tree.root.left.left = new Node(7);
//        tree.root.left.right = new Node(8);
//        tree.root.right.right = new Node(15);
//        tree.root.right.left = new Node(12);
//        tree.root.right.right.left = new Node(14);
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(5);
        root.left.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(25);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        tree.root = root;

        bottomViewPostorder(tree);
    }

    /**
     * Left view of a binary tree (recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void leftViewRecursion(BinaryTree binaryTree) {
        leftView(binaryTree.root, 0);
    }

    private static int maxLevel = -1;
    private static void leftView(Node node, int level) {
        if(node == null) {
            return;
        }
        if(maxLevel < level) {
            System.out.print(node.data + " ");
            maxLevel = level;
        }
        leftView(node.left, level+1);
        leftView(node.right, level+1);
    }

    /**
     * Right view of a binary tree (recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void rightViewRecursion(BinaryTree binaryTree) {
        rightView(binaryTree.root, 0);
    }
    private static void rightView(Node node, int level) {
        if(node == null) {
            return;
        }
        if(maxLevel < level) {
            System.out.print(node.data + " ");
            maxLevel = level;
        }
        rightView(node.right, level+1);
        rightView(node.left, level+1);
    }

    /**
     * Left view of a binary tree (level order)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void leftViewLevelOrder(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            int nodeCount = queue.size();
            int i = 0;
            while(nodeCount > 0) {
                Node current = queue.remove();
                if(i == 0) {
                    System.out.print(current.data+" ");
                }
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
                nodeCount--;
                i++;
            }

        }
    }

    /**
     * Bottom view of a binary tree (level order)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void bottomView(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        //it will store the lowest node at horizontal distances
        Map<Integer, Integer> map = new TreeMap<>();
        Node root = binaryTree.root;
        root.horizontalDistance = 0;
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            map.put(current.horizontalDistance, current.data);
            if(current.left != null) {
                current.left.horizontalDistance = current.horizontalDistance - 1;
                queue.add(current.left);
            }
            if(current.right != null) {
                current.right.horizontalDistance = current.horizontalDistance +1;
                queue.add(current.right);
            }
        }
        for(Integer i : map.values()) {
            System.out.print(i + " ");
        }
    }

    /**
     * Bottom view of a binary tree (Post order)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void bottomViewPostorder(BinaryTree binaryTree) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        bottomView(binaryTree.root, 0, 0, map);
        for(List<Integer> list : map.values()) {
            System.out.print(list.get(0) + " ");
        }
    }

    private static void bottomView(Node node, int level, int hd, Map<Integer, List<Integer>> map) {
        if(node == null) {
            return;
        }
        if(!map.containsKey(hd)) {
            List<Integer> list = new ArrayList<>();
            list.add(node.data);
            list.add(level);
            map.put(hd, new ArrayList<>(list));
        } else {
            List<Integer> oldList = map.get(hd);
            if(oldList.get(1) <= level) {
                oldList.add(0, node.data);
                oldList.add(1, level);
            }
        }
        bottomView(node.left, level+1, hd-1, map);
        bottomView(node.right, level+1, hd+1, map);
    }
}
