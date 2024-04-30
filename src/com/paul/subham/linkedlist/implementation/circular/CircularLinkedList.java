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
    public CircularNode head;
    private int size;

    public CircularLinkedList() {
        size = 0;
    }
    //insert at start
    public void insertAtStart(int data) {
        CircularNode newCircularNode = new CircularNode(data);
        if(head == null) {
            head = newCircularNode;
            size++;
            return;
        }
        CircularNode last = head;
        while(last.next != head) {
            last = last.next;
        }
        newCircularNode.next = head;
        last.next = newCircularNode;
        head = newCircularNode;
        size++;
    }

    //insert at end
    public void insertAtEnd(int data) {
        CircularNode newCircularNode = new CircularNode(data);
        if(head == null) {
            head = newCircularNode;
            size++;
            return;
        }
        CircularNode last = head;
        while(last.next != head) {
            last = last.next;
        }
        last.next = newCircularNode;
        newCircularNode.next = head;
        size++;
    }

    //delete at start
    public void deleteAtStart() {
        CircularNode last = head;
        while(last.next != head) {
            last = last.next;
        }
        head = head.next;
        last.next = head;
        size--;
    }

    //delete at end
    public void deleteAtEnd() {
        CircularNode current = head;
        CircularNode last = null;
        while(current.next != head) {
            last = current;
            current = current.next;
        }
        last.next = head;
        size--;
    }

    //deletion of an element
    public void deleteKey(int data) {
        CircularNode prev = null;
        CircularNode current = head;
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
        size--;
    }

    //print list
    public void print() {
        CircularNode current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while(current != head);
    }

    public int size() {
        return this.size;
    }
}

