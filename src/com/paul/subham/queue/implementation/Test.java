package com.paul.subham.queue.implementation;

public class Test {
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print();
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        queue.enqueue(5);
        queue.print();
    }
}
