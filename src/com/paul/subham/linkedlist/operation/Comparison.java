package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * @author subham.paul
 * 
 * 1. Check if two linked lists are identical
 * 2. Check if a linked list is circular linked list
 * 3. Check if a linked list is circular linked list (Using slow fast pointer)
 */
public class Comparison {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.head.next.next = linkedList.head;

        LinkedList linkedList1 = new LinkedList();
        linkedList1.insertAtEnd(1);
        linkedList1.insertAtEnd(2);

        System.out.println(isCircularSlowFast(linkedList));

    }

    /**
     * Check if two linked lists are identical
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isIdentical(LinkedList linkedList1, LinkedList linkedList2) {
        Node temp1 = linkedList1.head;
        Node temp2 = linkedList2.head;
        while(temp1 != null && temp2 != null) {
            if(temp1.data != temp2.data) {
                return false;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return (temp1 == null) && (temp2 == null);
    }

    /**
     * Check if a linked list is circular linked list
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isCircular(LinkedList linkedList) {
        if(linkedList.head == null) {
            return true;
        }
        Node node = linkedList.head.next;
        while(node != linkedList.head && node != null) {
            node = node.next;
        }
        return node == linkedList.head;
    }

    /**
     * Check if a linked list is circular linked list (Using slow fast pointer)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isCircularSlowFast(LinkedList linkedList) {
        Node slow = linkedList.head;
        Node fast = linkedList.head.next;
        while(fast != null && fast.next != null) {
            if(slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
