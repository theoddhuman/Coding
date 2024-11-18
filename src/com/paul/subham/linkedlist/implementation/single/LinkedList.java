package com.paul.subham.linkedlist.implementation.single;

/**
 * 1. insert data at start
 * 2. insert data at end
 * 3. insert after Node
 * 4. insert at middle position
 * 5. delete at start
 * 6. delete at end
 * 7. delete at middle position
 * 8. delete first occurence
 * 9. delete list
 * 10. length (iterative)
 * 11. length (recursive)
 * 11. print linked list
 */
public class LinkedList {
    public Node head;

    public Node getHead() {
        return head;
    }

    //insert data at start
    public void insertAtStart(int data) {
        Node newNode = new Node(data);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
    }

    //insert data at end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while(current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    //insert after Node
    public void insertAfterNode(Node node, int data) {
        Node newNode = new Node(data);
        newNode.next = node.next;
        node.next = newNode;
    }

    //insert at middle position
    public void insertAtMiddle(int data, int position) {
        Node newNode = new Node(data);
        Node current = head;
        for(int i=1; i<position-1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    //delete at start
    public void deleteAtStart() {
        Node temp = head;
        head = head.next;
        temp.next = null;
    }

    //delete at end
    public void deleteAtEnd() {
        Node temp = head;
        Node pre = null;
        while(temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        pre.next = null;
    }

    //delete at middle position
    public void deleteAtMiddle(int position) {
        if(position == 1) {
            deleteAtStart();
            return;
        }
        Node temp = head;
        Node pre = null;
        for(int i=0; i< position - 1; i++) {
            pre = temp;
            temp = temp.next;
        }
        pre.next = temp.next;
    }

    //delete first occurrence
    public void deleteFirstOccurrence(int data) {
        Node temp = head;
        Node pre = null;
        if(temp != null && temp.data == data) {
            head = temp.next;
            return;
        }

        while(temp != null && temp.data != data) {
            pre =temp;
            temp = temp.next;
        }
        pre.next = temp.next;
    }

    //delete list
    public void delete() {
        head = null;
    }

    //length of list (iterative)
    public int size() {
        Node current = head;
        int length = 0;
        while(current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    //length of list (recursive)
    public int sizeRecursive() {
        return sizeUtil(head);
    }

    private int sizeUtil(Node node) {
        if(node == null) {
            return 0;
        }
        return 1 + sizeUtil(node.next);
    }

    //print linked list
    public void print() {
        Node node = head;
        while(node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}