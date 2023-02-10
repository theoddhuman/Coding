package com.paul.subham.queue.implementation;

/**
 * 1. is empty
 * 2. is full
 * 3. enqueue an element
 * 4. dequeue an element
 * 5. front element
 * 6. rear element
 * 7. resize queue
 * 8. print queue
 */
public class CircularArrayQueue {
    private int rear;
    private int front;
    private int size;
    private int capacity;
    private int a[];

    CircularArrayQueue(int capacity) {
        this.capacity = capacity;
        size = front = 0;
        rear = capacity - 1;
        a = new int[capacity];
    }

    //is empty
    boolean isEmpty() {
        return size == 0;
    }

    //is full
    boolean isFull() {
        return size == capacity;
    }

    //enqueue an element
    void enqueue(int data) {
        if(isFull()) {
            resize();
        }
        rear = (rear + 1) % capacity;
        a[rear] = data;
        size++;
    }

    //dequeue an element
    int dequeue() {
        if(isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int data = a[front];
        front = (front+1) % capacity;
        size --;
        return data;
    }

    //front element
    int front() {
        if(isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return a[front];
    }

    //rear element
    int rear() {
        if(isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return a[rear];
    }

    //resize queue
    void resize() {
        int b[] = a;
        capacity *= 2;
        a = new int[capacity];
        for(int i=front; i<b.length; i++) {
            a[i] = b[i];
        }
        if(front > rear) {
            for(int i=0; i<front; i++) {
                a[b.length+i] = b[i];
            }
            rear += b.length;
        }
    }

    //print queue
    void print() {
        if(isEmpty()) {
            return;
        }
        if(front > rear) {
            for(int i=front; i<capacity; i++) {
                System.out.print(a[i] + " ");
            }
            for(int i=0; i<=rear; i++) {
                System.out.print(a[i] + " ");
            }
        } else {
            for(int i=front; i<=rear; i++) {
                System.out.print(a[i] + " ");
            }
        }
        System.out.println();
    }
}
