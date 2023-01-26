package com.paul.subham.linkedlist.implementation.doubly;

public class Test {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.head = new Node(1);
        dll.head.next = new Node(2);
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
