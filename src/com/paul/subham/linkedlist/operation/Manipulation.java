package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * 1. Inserting data in sorted linked list
 * 2. Reverse a linked list iterative
 * 3. Reverse a linked list recursive
 *
 */
public class Manipulation {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(5);
        linkedList.insertAtEnd(11);
        insertInSortedList(linkedList, 7);
        reverseRecursive(linkedList);
        linkedList.print();

    }

    /**
     * Inserting data in sorted linked list
     * TC: O(n)
     * SC: O(n)
     */
    public static void insertInSortedList(LinkedList linkedList, int data) {
        Node newNode = new Node(data);
        if(linkedList.head == null) {
            linkedList.head = newNode;
            return;
        }
        Node current = linkedList.head;
        Node temp = null;
        while(current != null && current.data < data) {
            temp = current;
            current = current.next;
        }
        temp.next = newNode;
        newNode.next = current;
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

    public static void reverseRecursive(LinkedList linkedList) {
        reverseUtil(linkedList, linkedList.head, null);
    }

    /**
     * Reverse a linked list recursive
     * TC: O(n)
     * SC: O(n)
     */
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
}
