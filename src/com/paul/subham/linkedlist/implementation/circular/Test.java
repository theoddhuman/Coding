package com.paul.subham.linkedlist.implementation.circular;

public class Test {

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        cll.head.next = second;
        second.next = third;
        third.next = cll.head;
        cll.insertAtEnd(4);
        //cll.deleteAtEnd();
        //cll.deleteAtStart();
        cll.deleteKey(2);
        cll.print();
    }
}
