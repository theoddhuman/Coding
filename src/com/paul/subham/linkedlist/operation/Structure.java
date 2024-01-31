package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Check if linked list is palindrome (using stack)
 * 2. Check if linked list is palindrome (By reversing the list)
 * 3. Check if linked list is palindrome (Using recursion)
 */

public class Structure {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(1);
        linkedList.print();
        System.out.println();
        System.out.println(isPalindromeByRecursion(linkedList));
    }

    /**
     * Check if linked list is palindrome (using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isPalindrome(LinkedList linkedList) {
        Node current = linkedList.head;
        Node temp = current;
        Stack<Integer> stack = new Stack<>();
        while(temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }
        while(current != null) {
            if(current.data != stack.pop()) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * Check if linked list is palindrome (By reversing the list)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isPalindromeByReversing(LinkedList linkedList) {
        Node slow = linkedList.head;
        Node fast = linkedList.head;
        Node prev = linkedList.head;
        Node midNode = null;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null) {
            midNode = slow;
            slow = slow.next;
        }

        //creating second half of the list
        LinkedList secondList = new LinkedList();
        secondList.head = slow;
        //creating first half of the list
        prev.next = null;

        Manipulation.reverseIterative(secondList);
        boolean result = Comparison.isIdentical(linkedList, secondList);
        Manipulation.reverseIterative(secondList);
        if(midNode != null) {
            prev.next = midNode;
        } else {
            prev.next = slow;
        }
        return result;
    }


    /**
     * Check if linked list is palindrome (Using recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isPalindromeByRecursion(LinkedList linkedList) {
        left = linkedList.head;
        return isPalindromeUtil(linkedList.head);
    }

    private static Node left;
    private static boolean isPalindromeUtil(Node right) {
        if (right == null) {
            return true;
        }
        if(!isPalindromeUtil(right.next)) {
            return false;
        }
        if(left.data != right.data) {
            return false;
        }
        left = left.next;
        return true;
    }
}
