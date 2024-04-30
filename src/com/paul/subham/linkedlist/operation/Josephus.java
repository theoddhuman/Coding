package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.circular.CircularLinkedList;
import com.paul.subham.linkedlist.implementation.circular.CircularNode;

/**
 * @author subham.paul
 */
public class Josephus {
    public static void main(String[] args) {
        CircularLinkedList linkedList = new CircularLinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(5);
        linkedList.insertAtEnd(6);
        linkedList.insertAtEnd(7);
        linkedList.insertAtEnd(8);
        linkedList.insertAtEnd(9);
        linkedList.insertAtEnd(10);
        linkedList.insertAtEnd(11);
        linkedList.insertAtEnd(12);
        linkedList.insertAtEnd(13);
        //linkedList.insertAtEnd(14);
        System.out.println(josephusIterative(linkedList, 2));
    }

    public static int josephusRecursive(CircularLinkedList linkedList, int skip) {
        size = linkedList.size();
        return josephusRecurUtil(linkedList.head, skip).data;
    }

    private static int size;
    private static CircularNode josephusRecurUtil(CircularNode circularNode, int skip) {
        if(size == 1) {
            return circularNode;
        }
        CircularNode prev = null;
        CircularNode current = circularNode;
        for(int i=0; i<skip; i++) {
            prev = current;
            current = current.next;
        }
        prev.next = current.next;
        size--;
        return josephusRecurUtil(prev.next, skip);
    }

    private static int josephusIterative(CircularLinkedList linkedList, int skip) {
        CircularNode circularNode = linkedList.head;
        CircularNode prev = null;
        int size = linkedList.size();
        while(circularNode.next != null && size > 1) {
            for(int i=0; i<skip; i++) {
                prev = circularNode;
                circularNode = circularNode.next;
            }
            prev.next = circularNode.next;
            circularNode = prev.next;
            size--;
        }
        return circularNode.data;
    }
}
