package com.paul.subham.stack.operation;

import com.paul.subham.stack.implementation.LinkedListStack;
import com.paul.subham.stack.implementation.Node;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Reverse a stack (By recursion)
 * 2. Reverse a stack (Efficient Approach)
 */
public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(8);
        stack.push(1);
        stack.push(5);
        System.out.println(stack);
    }

    /**
     * Reverse a stack (By recursion)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void reverseByRecursion(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int top = stack.pop();
        reverseByRecursion(stack);
        insertAtBottom(stack, top);
    }

    private static void insertAtBottom(Stack<Integer> stack, int data) {
        if(stack.isEmpty()) {
            stack.push(data);
            return;
        }
        int top = stack.pop();
        insertAtBottom(stack, data);
        stack.push(top);
    }

    /**
     * Reverse a stack (Efficient Approach)
     *
     * Stack has to be implemented using linked list
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseEfficient(LinkedListStack stack) {
        stack.top = reverse(stack.top);
    }

    public static Node reverse(Node node) {
        Node prev = null;
        Node current = node;
        Node next;
        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
