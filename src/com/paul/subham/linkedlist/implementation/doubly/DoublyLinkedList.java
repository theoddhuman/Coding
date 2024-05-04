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
    public DLNode head;

    //insert at start
    public void insertAtStart(int data) {
        DLNode newDLNode = new DLNode(data);
        newDLNode.next = head;
        if(head != null) {
            head.pre = newDLNode;
        }
        head = newDLNode;
    }

    //insert at end
    public void insertAtEnd(int data) {
        DLNode newDLNode = new DLNode(data);
        if(head == null) {
            head = newDLNode;
            return;
        }
        DLNode last = head;
        while(last.next != null) {
            last = last.next;
        }
        last.next = newDLNode;
        newDLNode.pre = last;
    }

    //insert at middle position
    public void insertAtMiddle(int data, int position) {
        DLNode newDLNode = new DLNode(data);
        if(head == null && position == 1) {
            head = newDLNode;
            return;
        }
        DLNode current = head;
        for(int i=1; i<position-1; i++) {
            current = current.next;
        }
        current.next.pre = newDLNode;
        newDLNode.next = current.next;
        current.next = newDLNode;
        newDLNode.pre = current;
    }

    //insert after DLNode
    public void insertAfterNode(DLNode DLNode, int data) {
        DLNode newDLNode = new DLNode(data);
        DLNode.next.pre = newDLNode;
        newDLNode.next = DLNode.next;
        DLNode.next = newDLNode;
        newDLNode.pre = DLNode;
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
        DLNode last = head;
        while(last.next != null) {
            last = last.next;
        }
        last.pre.next = null;
    }

    //delete at middle
    public void deleteAtMiddle(int position) {
        DLNode current = head;
        for(int i=1; i<position; i++) {
            current = current.next;
        }
        current.pre.next = current.next;
        current.next.pre = current.pre;
    }

    //print list
    public void print() {
        DLNode current = head;
        DLNode last = null;
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

