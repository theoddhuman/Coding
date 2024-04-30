package com.paul.subham.linkedlist.implementation.circular;

public class Test {

    public static void main(String[] args) {
        CircularLinkedList cll = new CircularLinkedList();
        cll.head = new CircularNode(1);
        CircularNode second = new CircularNode(2);
        CircularNode third = new CircularNode(3);
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
