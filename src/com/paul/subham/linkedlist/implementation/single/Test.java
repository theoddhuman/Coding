package com.paul.subham.linkedlist.implementation.single;

public class Test {
    public static void main(String[] args) {
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
}
