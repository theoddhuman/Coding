package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

/**
 * @author subham.paul
 *
 * 1. Multiply two numbers using doubly linked list
 * 2. Add two numbers using linked list
 * 3. Add 1 to a linked list number (Reversing list)
 * 4. Add 1 to a linked list number (One run)
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

    /**
     * Add 1 to a linked list number (Reversing list)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node addOne(Node head) {
        head = reverse(head);
        Node current = head;
        int carry = 1;
        while(carry != 0) {
            current.data += 1;
            if(current.data < 10) {
                return reverse(head);
            } else {
                current.data = 0;
            }
            if(current.next == null) {
                break;
            } else {
                current = current.next;
            }
        }
        current.next = new Node(1);
        return reverse(head);
    }

    private static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;
        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     * Add 1 to a linked list number (One run)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node addOneOpt(Node head) {
        int carry = addCarry(head);

        if(carry == 1) {
            Node newNode = new Node(1);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    public static int addCarry(Node node) {
        if(node == null) {
            return 1;
        }
        int carry = addCarry(node.next);
        if(carry == 1) {
            int x = node.data + carry;
            node.data = x%10;
            return x/10;
        }
        return 0;
    }
}
