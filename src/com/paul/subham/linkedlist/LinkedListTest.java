package com.paul.subham.linkedlist;

import com.paul.subham.linkedlist.implementation.circular.CircularLinkedList;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;
import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

public class LinkedListTest {
    public static void main(String[] args) {
        //testLinkedList();
        //testDoublyLinkedList();
        //testCircularLinkedList();
    }

    static void testCircularLinkedList() {
        CircularLinkedList cll = new CircularLinkedList();
        cll.head = new com.paul.subham.linkedlist.implementation.circular.Node(1);
        com.paul.subham.linkedlist.implementation.circular.Node second = new com.paul.subham.linkedlist.implementation.circular.Node(2);
        com.paul.subham.linkedlist.implementation.circular.Node third = new com.paul.subham.linkedlist.implementation.circular.Node(3);
        cll.head.next = second;
        second.next = third;
        third.next = cll.head;
        cll.insertAtEnd(4);
        //cll.deleteAtEnd();
        //cll.deleteAtStart();
        cll.deleteKey(2);
        cll.print();
    }

    static void testLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.head = new Node(1);
        linkedList.head.next = new Node(2);
        linkedList.head.next.next = new Node(3);
        //linkedList.insertAfterNode(linkedList.head, 4);
        //linkedList.insertAtMiddle(7,3);
        //linkedList.insertAtStart(4);
        //linkedList.insertAtEnd(5);
        //linkedList.deleteAtStart();
        //linkedList.deleteAtEnd();
        //linkedList.deleteAtMiddle(2);
        //linkedList.deleteFirstOccurence(3);
        linkedList.delete();
        linkedList.print();
    }

    static void testDoublyLinkedList() {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.head = new com.paul.subham.linkedlist.implementation.doubly.Node(1);
        dll.head.next = new com.paul.subham.linkedlist.implementation.doubly.Node(2);
        dll.head.next.pre = dll.head;
        //dll.insertAtStart(4);
        dll.insertAtEnd(3);
        dll.insertAtEnd(4);
        //dll.insertAtMiddle(4, 3);
        //dll.deleteAtStart();
        //dll.deleteAtEnd();
        dll.deleteAtMiddle(3);
        dll.print();
    }
}
