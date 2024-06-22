package com.paul.subham.tree.operations;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;
import com.paul.subham.tree.implementation.binary.BinaryTree;
import com.paul.subham.tree.implementation.binary.Node;
import com.paul.subham.tree.implementation.binarysearch.BSTNode;
import com.paul.subham.tree.implementation.binarysearch.BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author subham.paul
 *
 * 1. Convert a binary tree to its mirror (Recursive)
 * 2. Convert a binary tree to its mirror (Iterative)
 * 3. Convert a binary tree to circular doubly linked list
 * 4. Convert a binary tree to circular doubly linked list (converting DLL then to CDLL)
 * 5. Convert a binary tree to doubly linked list
 * 6. Convert a sorted doubly linked list to binary search tree
 * 7. Convert a sorted doubly linked list to binary search tree (optimized)
 * 8. Convert a sorted array to binary search tree (optimized)
 */
public class Conversion {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(1);
        bt.root.left = new Node(2);
        bt.root.right = new Node(3);
        bt.root.left.left = new Node(4);
        bt.root.left.right = new Node(5);

        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(5);
        linkedList.insertAtEnd(6);
        BinarySearchTree binaryTree = sortedArrayToBST(new int[]{1,2,3,4,5});
        binaryTree.inOrder();



//        BinaryTree bt1 = new BinaryTree();
//        bt1.root = new Node(1);
//        bt1.root.left = new Node(2);
//        bt1.root.right = new Node(3);
//        bt1.root.left.left = new Node(4);
//        bt1.root.left.right = new Node(5);
//        bt1.levelOrder();
//        System.out.println();
//
//        BinaryTree b = new BinaryTree();
//        b.root = new Node(1);
//        b.root.left = new Node(3);
//        b.root.right = new Node(2);
//        b.root.right.left = new Node(5);
//        b.root.right.right = new Node(4);
//        b.levelOrder();
//        System.out.println();
    }

    /**
     * Convert a binary tree to its mirror (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void convertToMirrorRecursive(BinaryTree binaryTree) {
        binaryTree.root = mirror(binaryTree.root);
    }

    private static Node mirror(Node node) {
        if(node == null) {
            return null;
        }
        Node left = mirror(node.left);
        Node right = mirror(node.right);
        node.left = right;
        node.right = left;
        return node;
    }

    /**
     * Convert a binary tree to its mirror (Iterative)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void convertToMirrorIterative(BinaryTree binaryTree) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(binaryTree.root);
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            Node temp = current.left;
            current.left = current.right;
            current.right = temp;
            if(current.left != null) {
                queue.add(current.left);
            }
            if(current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * Convert a binary tree to circular doubly linked list
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void binaryTreeToCircularLL(BinaryTree binaryTree){
        Node head = binaryTreeToCircularLL(binaryTree.root);
        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.right;
        } while(current != head);
    }

    private static Node binaryTreeToCircularLL(Node node) {
        if(node == null) {
            return null;
        }
        Node left = binaryTreeToCircularLL(node.left);
        Node right = binaryTreeToCircularLL(node.right);
        node.left = node.right = node;
        return concatenate(concatenate(left, node), right);
    }

    private static Node concatenate(Node left, Node right) {
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        Node leftLast = left.left;
        Node rightLast = right.left;
        leftLast.right = right;
        right.left = leftLast;
        left.left = rightLast;
        rightLast.right = left;
        return left;
    }

    /**
     * Convert a binary tree to circular doubly linked list (converting DLL then to CDLL)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void binaryTreeToCircularDLLUsingDLL(BinaryTree binaryTree) {
        binaryTreeToDoublyLL(binaryTree.root);
        head.left = prev;
        prev.right = head;
        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.right;
        } while(current != head);
    }

    /**
     * Convert a binary tree to doubly linked list
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void binaryTreeToDoublyLL(BinaryTree binaryTree) {
        binaryTreeToDoublyLL(binaryTree.root);
        Node current = head;
        while(current != null) {
            System.out.print(current.data + " ");
            current = current.right;
        }
    }

    private static Node prev = null;
    private static Node head;
    private static void binaryTreeToDoublyLL(Node node) {
        if(node == null) {
            return;
        }
        binaryTreeToDoublyLL(node.left);
        if(prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        binaryTreeToDoublyLL(node.right);
    }

    /**
     * Convert a sorted doubly linked list to binary search tree
     *
     * TC: O(nlogn)
     * SC: O(n)
     */
    public static BinarySearchTree sortedDLLToBST(DoublyLinkedList linkedList) {
        DLNode first = linkedList.head;
        DLNode last = linkedList.head;
        while(last.next != null) {
            last = last.next;
        }
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.root = convertDLLToBT(first, last);
        return binarySearchTree;
    }

    private static BSTNode convertDLLToBT(DLNode first, DLNode last) {
        if(first == null || last == null || last.next == first) {
            return null;
        }
        DLNode fast = first;
        DLNode mid = first;
        while(fast != last && fast.next != last) {
            mid = mid.next;
            fast = fast.next.next;
        }
        BSTNode left = convertDLLToBT(first, mid.pre);
        BSTNode right = convertDLLToBT(mid.next, last);
        BSTNode root = new BSTNode(mid.data);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * Convert a sorted doubly linked list to binary search tree (optimized)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinarySearchTree sortedDLLToBSTOptimized(DoublyLinkedList linkedList) {
        DLNode temp = linkedList.head;
        int length = 0;
        while(temp != null) {
            length++;
            temp = temp.next;
        }
        current = linkedList.head;
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.root = convertDLLToBST(0, length-1);
        return binarySearchTree;
    }

    private static DLNode current;
    private static BSTNode convertDLLToBST(int start, int end) {
        if(start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BSTNode left = convertDLLToBST(start, mid-1);
        BSTNode root = new BSTNode(current.data);
        root.left = left;
        current = current.next;
        root.right = convertDLLToBST(mid+1, end);
        return root;
    }

    /**
     * Convert a sorted array to binary search tree
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static BinarySearchTree sortedArrayToBST(int[] a) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.root = convertArrayToBST(a, 0, a.length-1);
        return binarySearchTree;
    }

    private static BSTNode convertArrayToBST(int[] a, int start, int end) {
        if(start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BSTNode left = convertArrayToBST(a, start, mid-1);
        BSTNode root = new BSTNode(a[mid]);
        root.left = left;
        root.right = convertArrayToBST(a, mid+1, end);
        return root;
    }


}
