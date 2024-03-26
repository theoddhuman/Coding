package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * @author subham.paul
 */
public class Sort {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(5);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(2);
        linkedList.print();
        insertionSort(linkedList);
        System.out.println();
        linkedList.print();
    }


    /**
     * Insertion sort of linked list
     * 
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void insertionSort(LinkedList linkedList) {
        Node current = linkedList.head;
        while(current != null) {
            Node next = current.next;
            sortedInsert(current);
            current = next;
        }
        linkedList.head = head;
    }
    private static Node head = null;
    private static void sortedInsert(Node newNode) {
        if(head == null || head.data > newNode.data) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node current = head;
        Node temp = null;
        while(current != null && current.data < newNode.data) {
            temp = current;
            current = current.next;
        }
        temp.next = newNode;
        newNode.next = current;
    }
}
