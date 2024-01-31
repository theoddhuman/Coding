package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * @author subham.paul
 */
public class Comparison {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);

        LinkedList linkedList1 = new LinkedList();
        linkedList1.insertAtEnd(1);
        linkedList1.insertAtEnd(2);

        System.out.println(isIdentical(linkedList, linkedList1));

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
}
