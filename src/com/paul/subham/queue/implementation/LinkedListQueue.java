package com.paul.subham.queue.implementation;

/**
 * 1. is empty
 * 2. enqueue an element
 * 3. dequeue an element
 * 4. print queue
 */
public class LinkedListQueue {
    Node front;
    Node rear;

    LinkedListQueue() {
        front = null;
        rear = null;
    }

    //is empty
    boolean isEmpty() {
        return front == null;
    }

    //enqueue an element
    void enqueue(int data) {
        Node newNode = new Node(data);
        if(rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = rear.next;
        }
    }

    //dequeue an element
    int dequeue() {
        if(isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int data = front.data;
        if(front == rear) {
            front = rear = null;
        } else {
            front = front.next;
        }
        return data;
    }

    //print queue
    void print() {
        Node current = front;
        while(current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}
