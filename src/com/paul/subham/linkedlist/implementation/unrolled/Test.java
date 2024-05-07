package com.paul.subham.linkedlist.implementation.unrolled;

/**
 * @author subham.paul
 */
public class Test {
    public static void main(String[] args) {
        UnrolledLinkedList linkedList = new UnrolledLinkedList(5);
        linkedList.insert(1);
        linkedList.insert(2);
        linkedList.insert(3);
        linkedList.insert(4);
        linkedList.insert(5);
        linkedList.insert(6);
        linkedList.insert(7);
        linkedList.insert(8);
        linkedList.insert(9);
        linkedList.insert(10);
        linkedList.display();
        System.out.println(linkedList.get(4));
    }
}
