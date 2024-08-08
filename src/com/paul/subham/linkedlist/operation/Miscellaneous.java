package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * @author subham.paul
 *
 * 1. Multiply two numbers using doubly linked list
 * 2. Add two numbers using linked list
 */
public class Miscellaneous {
    public static void main(String[] args) {
        System.out.println(multiply(123, 24));
    }

    /**
     * Multiply two numbers using doubly linked list
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int multiply(int a, int b) {
        DoublyLinkedList la = new DoublyLinkedList();
        DoublyLinkedList lb = new DoublyLinkedList();
        while(a > 0) {
            la.insertAtEnd(a%10);
            a/=10;
        }
        while(b > 0) {
            lb.insertAtEnd(b%10);
            b/=10;
        }

        DLNode currentA = la.head;
        int i = 1;
        int finalMul = 0;
        while(currentA != null) {
            int mul = 0;
            int j = 1;
            DLNode currentB = lb.head;
            while(currentB != null) {
                mul += currentA.data * currentB.data * j;
                j *= 10;
                currentB = currentB.next;
            }
            finalMul += mul * i;
            i*=10;
            currentA = currentA.next;
        }
        return finalMul;
    }

    /**
     * Add two numbers using linked list
     *
     * TC: O(n)
     * SC: O(n)
     */
    public Node addTwoNumbers(Node l1, Node l2) {
        Node current = new Node(0);
        Node head = current;
        int rem=0;
        int sum = 0;
        while(l1 != null || l2 != null || rem ==1) {
            sum=0;
            if(l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            sum += rem;
            rem = sum /10;
            current.next = new Node(sum%10);
            current = current.next;
        }
        return head.next;
    }
}
