package com.paul.subham.queue.implementation;

/**
 * @author subham.paul
 * 
 * 
 * 1. Is queue full
 * 2. Is queue empty
 * 3. Insert at front
 * 4. Insert at rear
 * 5. Delete at front
 * 6. Delete at rear
 */
public class ArrayDeque {
    int[] arr;
    int front;
    int rear;
    int size;
    int capacity;

    public ArrayDeque(int capacity) {
        arr = new int[capacity];
        this.capacity = capacity;
        front = -1;
        rear = -1;
        size = 0;
    }

    /**
     * Is queue full
     * TC: O(1)
     * SC: O(1)
     */
    boolean isFull() {
        return size == capacity;
    }

    /**
     * Is queue empty
     * TC: O(1)
     * SC: O(1)
     */
    boolean isEmpty() {
        return size == 0;
    }

    /**
     * Insert at front
     * TC: O(1)
     * SC: O(1)
     */
    void insertFront(int data) {
        if(isFull()) {
            System.out.println("Overflow");
            return;
        }
        if (front == -1) {
            front = 0;
            rear = 0;
        } else {
            front = (capacity + front - 1) % capacity;
        }
        arr[front] = data;
        size++;
    }

    /**
     * Insert at rear
     * TC: O(1)
     * SC: O(1)
     */
    void insertRear(int data) {
        if(isFull()) {
            System.out.println("Overflow");
            return;
        }
        if(rear == -1) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear+1) % capacity;
        }
        arr[rear] = data;
        size++;
    }

    /**
     * Delete at front
     * TC: O(1)
     * SC: O(1)
     */
    int deleteFront() {
        if(isEmpty()) {
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int data = arr[front];
        if(front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return data;
    }

    /**
     * Delete at rear
     * TC: O(1)
     * SC: O(1)
     */
    int deleteRear() {
        if(isEmpty()) {
            System.out.println("Underflow");
            return Integer.MIN_VALUE;
        }
        int data = arr[rear];
        if(front == rear) {
            front = rear = -1;
        } else {
            rear = (capacity + rear - 1) % capacity;
        }
        size--;
        return data;
    }

}
