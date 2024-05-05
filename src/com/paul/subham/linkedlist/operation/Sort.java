package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;
import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * @author subham.paul
 *
 * 1. Insertion sort of linked list
 * 2. Quick sort of doubly linked list
 * 3. Merge sort of doubly linked list
 */
public class Sort {
    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.insertAtEnd(5);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(2);
        linkedList.print();
        mergeSort(linkedList);
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

    /**
     * Quick sort of doubly linked list
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void quickSort(DoublyLinkedList linkedList) {
        DLNode current = linkedList.head;
        DLNode end = null;
        while(current != null) {
            end = current;
            current = current.next;
        }
        quickSortUtil(linkedList.head, end);
    }

    private static void quickSortUtil(DLNode start, DLNode end) {
        if(end != null && start != end && start != end.next) {
            DLNode partition = partition(start, end);
            quickSortUtil(start, partition.pre);
            quickSortUtil(partition.next, end);
        }
    }

     private static DLNode partition(DLNode start, DLNode end) {
        int pivot = end.data;
        DLNode pIndex = start;
        for(DLNode i = start; i != end; i = i.next) {
            if(i.data <= pivot) {
                int temp = pIndex.data;
                pIndex.data = i.data;
                i.data = temp;
                pIndex = pIndex.next;
            }
        }
        int temp = pIndex.data;
        pIndex.data = end.data;
        end.data = temp;
        return pIndex;
    }

    /**
     * Merge sort of doubly linked list
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void mergeSort(DoublyLinkedList linkedList) {
        linkedList.head = mergeSortUtil(linkedList.head);
    }

    private static DLNode mergeSortUtil(DLNode node) {
        if(node == null || node.next == null) {
            return node;
        }
        DLNode second = split(node);
        node = mergeSortUtil(node);
        second = mergeSortUtil(second);
        return merge(node, second);
    }

    private static DLNode split(DLNode node) {
        DLNode slow = node;
        DLNode fast = node;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        DLNode temp = slow.next;
        slow.next = null;
        return temp;
    }

    private static DLNode merge(DLNode first, DLNode second) {
        if(first == null) {
            return second;
        }
        if(second == null) {
            return first;
        }
        if(first.data <= second.data) {
            first.next = merge(first.next, second);
            first.next.pre = first;
            first.pre = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            second.next.pre = second;
            second.pre = null;
            return second;
        }
    }
}
