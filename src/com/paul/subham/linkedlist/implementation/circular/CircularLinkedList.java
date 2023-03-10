package com.paul.subham.linkedlist.implementation.circular;

/**
 * 1. insert at start
 * 2. insert at end
 * 3. delete at start
 * 4. delete at end
 * 5. deletion of an element
 * 6. print list
 */
public class CircularLinkedList {
    public Node head;

    //insert at start
    public void insertAtStart(int data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            return;
        }
        Node last = head;
        while(last.next != head) {
            last = last.next;
        }
        newNode.next = head;
        last.next = newNode;
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
        while(last.next != head) {
            last = last.next;
        }
        last.next = newNode;
        newNode.next = head;
    }

    //delete at start
    public void deleteAtStart() {
        Node last = head;
        while(last.next != head) {
            last = last.next;
        }
        head = head.next;
        last.next = head;
    }

    //delete at end
    public void deleteAtEnd() {
        Node current = head;
        Node last = null;
        while(current.next != head) {
            last = current;
            current = current.next;
        }
        last.next = head;
    }

    //deletion of an element
    public void deleteKey(int data) {
        Node prev = null;
        Node current = head;
        while(current.data != data) {
            if(current.next == head) {
                System.out.print("No data present");
                return;
            }
            prev = current;
            current = current.next;
        }
        if(current == head) {
            if(current.next == head) {
                head = null;
            } else {
                prev = head;
                while(prev.next != head) {
                    prev = prev.next;
                }
                head = head.next;
                prev.next = head;
            }
        } else {
            prev.next = current.next;
        }
    }

    //print list
    public void print() {
        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while(current != head);
    }
}

class Node {
    int data;
    public Node next;
    public Node(int data) {
        this.data = data;
        this.next = this;
    }
}
