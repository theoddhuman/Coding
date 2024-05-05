package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;

/**
 * @author subham.paul
 * 
 * 1. Print pairs with given sum in doubly linked list
 */
public class Sum {
    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.insertAtEnd(6);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(7);
        linkedList.insertAtEnd(10);
        linkedList.insertAtEnd(7);
        linkedList.insertAtEnd(2);
        printPairSum(linkedList, 9);
    }

    /**
     * Print pairs with given sum in doubly linked list
     * 
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void printPairSum(DoublyLinkedList linkedList, int sum) {
        Sort.quickSort(linkedList);
        DLNode start = linkedList.head;
        DLNode end = linkedList.head;
        while(end.next != null) {
            end = end.next;
        }
        while(start != end && start != end.next) {
            if(start.data + end.data == sum) {
                System.out.println(start.data + " " + end.data);
                start = start.next;
                end = end.pre;
            } else if (start.data + end.data > sum) {
                end = end.pre;
            } else {
                start = start.next;
            }
        }
    }
}
