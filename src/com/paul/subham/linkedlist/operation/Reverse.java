package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;
import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Reverse a linked list iterative
 * 2. Reverse a linked list recursive
 * 3. Reverse a linked list in pair (recursive)
 * 4. Reverse a linked list in pair (iterative)
 * 5. Reverse a linked list in group (Iterative)
 * 6. Reverse a linked list in group (Recursive)
 * 7. Reverse a doubly linked list
 * 8. Reverse a doubly linked list (Using stack)
 */
public class Reverse {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(5);
        linkedList.insertAtEnd(7);
        linkedList.insertAtEnd(11);
        linkedList.print();
        System.out.println();
    }

    /**
     * Reverse a linked list iterative
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseIterative(LinkedList linkedList) {
        Node prev = null;
        Node current = linkedList.head;
        Node next = null;
        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        linkedList.head = prev;
    }

    /**
     * Reverse a linked list recursive
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseRecursive(LinkedList linkedList) {
        reverseUtil(linkedList, linkedList.head, null);
    }

    public static void reverseUtil(LinkedList linkedList, Node current, Node prev) {
        if (current.next == null) {
            linkedList.head = current;
            current.next = prev;
        } else {
            Node next = current.next;
            current.next = prev;
            reverseUtil(linkedList, next, current);
        }
    }

    /**
     * Reverse a linked list in pair (recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseInPairRecursive(LinkedList linkedList) {
        linkedList.head = reverseInPairUtil(linkedList.head);
    }

    public static Node reverseInPairUtil(Node node) {
        if(node == null || node.next == null) {
            return node;
        }
        Node temp = node.next;
        node.next = temp.next;
        temp.next = node;
        node.next = reverseInPairUtil(node.next);
        return temp;
    }

    /**
     * Reverse a linked list in pair (iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseInPairIterative(LinkedList linkedList) {
        Node current = linkedList.head;
        Node node = null;
        Node temp;
        while(current != null && current.next != null) {
            temp = current.next;
            current.next = temp.next;
            temp.next = current;
            if(current == linkedList.head) {
                linkedList.head = temp;
            }
            if(node != null) {
                node.next = temp;
            }
            node = current;
            current = current.next;
        }
    }

    /**
     * Reverse a linked list in group (Iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseInGroupIterative(LinkedList linkedList, int K) {
        Node current = linkedList.head;
        Node prevCurrent = current;
        Node prevTail = null;
        while(current != null) {
            Node tail = null;
            int count = K;
            while(current != null && count-- > 0 ) {
                Node next = current.next;
                current.next = tail;
                tail = current;
                current = next;
            }
            if(prevTail != null) {
                prevTail.next = tail;
            } else {
                linkedList.head = tail;
            }
            prevTail = prevCurrent;
            prevCurrent = current;
        }
    }

    /**
     * Reverse a linked list in group (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseInGroupRecursive(LinkedList linkedList, int K) {
        linkedList.head = reverseInGroupRecursiveUtil(linkedList.head, K);
    }

    public static Node reverseInGroupRecursiveUtil(Node current, int K) {
        if(current == null) {
            return null;
        }
        Node tail = null;
        Node prevCurrent = current;
        int count = K;
        while (current != null && count-- > 0) {
            Node next = current.next;
            current.next = tail;
            tail = current;
            current = next;
        }
        prevCurrent.next = reverseInGroupRecursiveUtil(current, K);
        return tail;
    }

    /**
     * Reverse a doubly linked list
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverse(DoublyLinkedList linkedList) {
        DLNode temp = null;
        DLNode current = linkedList.head;
        while(current != null) {
            current.pre = current.next;
            current.next = temp;
            temp = current;
            current = current.pre;
        }
        if(temp != null) {
            linkedList.head = temp;
        }
    }

    /**
     * Reverse a doubly linked list (Using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseStack(DoublyLinkedList linkedList) {
        Stack<Integer> stack = new Stack<>();
        DLNode current = linkedList.head;
        while(current != null) {
            stack.push(current.data);
            current = current.next;
        }
        current = linkedList.head;
        while(current != null) {
            current.data = stack.pop();
            current = current.next;
        }
    }
}
