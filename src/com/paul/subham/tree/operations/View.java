package com.paul.subham.tree.operations;

import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * @author subham.paul
 *
 * 1. Left view of a binary tree (recursion)
 * 2. Right view of a binary tree (recursion)
 * 3. Left view of a binary tree (level order)
 * 4. Bottom view of a binary tree (level order)
 * 5. Bottom view of a binary tree (Preorder)
 * 6. Top view of a binary tree (Preorder)
 * 7. Top view of a binary tree (level order)
 * 8. Top view of a binary tree (level order - optimized)
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
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
        root.left.right.right.right = new Node(6);
        tree.root = root;

        topViewLevelOrderOptimized(tree);
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
     * Bottom view of a binary tree (Preorder)
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

    /**
     * Top view of a binary tree (Preorder)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    static void topView(BinaryTree binaryTree) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        bottomView(binaryTree.root, 0, 0, map);
        for(List<Integer> list : map.values()) {
            System.out.print(list.get(0) + " ");
        }
    }

    private static void topView(Node node, int level, int hd, Map<Integer, List<Integer>> map) {
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
            if(oldList.get(1) > level) {
                oldList.add(0, node.data);
                oldList.add(1, level);
            }
        }
        topView(node.left, level+1, hd-1, map);
        topView(node.right, level+1, hd+1, map);
    }

    /**
     * Top view of a binary tree (level order)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void topViewLevelOrder(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        //it will store the lowest node at horizontal distances
        Map<Integer, Integer> map = new TreeMap<>();
        Node root = binaryTree.root;
        root.horizontalDistance = 0;
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            if(!map.containsKey(current.horizontalDistance)) {
                map.put(current.horizontalDistance, current.data);
            }
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
     * Top view of a binary tree (level order - optimized)
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static void topViewLevelOrderOptimized(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        int l = 0;
        int r = 0;
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        Node root = binaryTree.root;
        root.horizontalDistance = 0;
        queue.add(root);
        Node current;
        while(!queue.isEmpty()) {
            current = queue.remove();
            int hd = current.horizontalDistance;
            if(hd < l) {
                stack.push(current.data);
                l = hd;
            }
            if(hd > r) {
                list.add(current.data);
                r = hd;
            }
            if(current.left != null) {
                current.left.horizontalDistance = current.horizontalDistance - 1;
                queue.add(current.left);
            }
            if(current.right != null) {
                current.right.horizontalDistance = current.horizontalDistance +1;
                queue.add(current.right);
            }
        }
        while(!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.print(binaryTree.root.data + " ");
        for(Integer i : list) {
            System.out.print(i + " ");
        }
    }
}
