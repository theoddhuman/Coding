package com.paul.subham.linkedlist.operation;


import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * @author subham.paul
 *
 * 1. Length of a linked list even or odd
 */
public class Miscellaneous {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(2);
        System.out.println(isLengthEven(linkedList));
    }


    /**
     * Length of a linked list even or odd
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isLengthEven(LinkedList linkedList) {
        Node current = linkedList.head;
        while(current != null && current.next != null) {
            current = current.next.next;
            if(current == null) {
                return true;
            }
        }
        return false;
    }
}
