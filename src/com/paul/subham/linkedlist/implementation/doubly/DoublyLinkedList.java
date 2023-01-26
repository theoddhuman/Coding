package com.paul.subham.linkedlist.implementation.doubly;

/**
 * 1. insert at start
 * 2. insert at end
 * 3. insert at middle position
 * 4. insert after node
 * 5. delete at start
 * 6. delete at end
 * 7. delete at middle
 * 8. print list
 */
public class DoublyLinkedList {
    public Node head;

    //insert at start
    public void insertAtStart(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        if(head != null) {
            head.pre = newNode;
        }
        head = newNode;
    }

    //insert at end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            return;
        }
        Node last = head;
        while(last.next != null) {
            last = last.next;
        }
        last.next = newNode;
        newNode.pre = last;
    }

    //insert at middle position
    public void insertAtMiddle(int data, int position) {
        Node newNode = new Node(data);
        if(head == null && position == 1) {
            head = newNode;
            return;
        }
        Node current = head;
        for(int i=1; i<position-1; i++) {
            current = current.next;
        }
        current.next.pre = newNode;
        newNode.next = current.next;
        current.next = newNode;
        newNode.pre = current;
    }

    //insert after node
    public void insertAfterNode(Node node, int data) {
        Node newNode = new Node(data);
        node.next.pre = newNode;
        newNode.next = node.next;
        node.next = newNode;
        newNode.pre = node;
    }

    //delete at start
    public void deleteAtStart() {
        if(head == null) {
            return;
        }
        head = head.next;
        head.pre = null;
    }

    //delete at end
    public void deleteAtEnd() {
        Node last = head;
        while(last.next != null) {
            last = last.next;
        }
        last.pre.next = null;
    }

    //delete at middle
    public void deleteAtMiddle(int position) {
        Node current = head;
        for(int i=1; i<position; i++) {
            current = current.next;
        }
        current.pre.next = current.next;
        current.next.pre = current.pre;
    }

    //print list
    public void print() {
        Node current = head;
        Node last = null;
        System.out.println("Forward direction:");
        while(current != null) {
            System.out.print(current.data + " ");
            last = current;
            current = current.next;
        }
        System.out.println("\nReverse direction:");
        while(last != null) {
            System.out.print(last.data + " ");
            last = last.pre;
        }

    }
}

class Node {
    int data;
    public Node pre;
    public Node next;

    public Node(int data) {
        this.data = data;
        pre = null;
        next = null;
    }
}
