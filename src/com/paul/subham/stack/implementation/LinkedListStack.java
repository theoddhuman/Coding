package com.paul.subham.stack.implementation;

/**
 * 1. is empty
 * 2. push an element
 * 3. pop an element
 * 4. top element
 * 5. delete stack
 * 6. print stack
 */
class LinkedListStack {
    Node top = null;

    //is empty
    boolean isEmpty() {
        return top == null;
    }

    //push an element
    void push(int data) {
        Node newNode = new Node(data);
        if(isEmpty()) {
            top = newNode;
            return;
        }
        newNode.next = top;
        top = newNode;
    }

    //pop an element
    int pop() {
        int popped = Integer.MIN_VALUE;
        if(isEmpty()) {
            return popped;
        }
        popped = top.data;
        top = top.next;
        return popped;
    }

    //top element
    int top(){
        return top.data;
    }

    //delete stack
    void delete() {
        top = null;
    }

    //print stack
    void print() {
        Node current = top;
        while(current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
