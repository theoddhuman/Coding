package com.paul.subham.stack.operation;

import com.paul.subham.stack.implementation.LinkedListStack;
import com.paul.subham.stack.implementation.Node;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Reverse a stack (By recursion)
 * 2. Reverse a stack (Efficient Approach)
 * 3. Sort a stack (by recursion)
 * 4. Sort a stack (by temporary stack)
 */
public class Manipulation {
    public static void main(String[] args) {
//        LinkedListStack stack = new LinkedListStack();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.print();
//        reverseEfficient(stack);
//        stack.print();

        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(8);
        stack.push(1);
        stack.push(5);
        System.out.println(stack);
        stack = sortByTemporaryStack(stack);
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

    /**
     * Sort a stack (by recursion)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void sortByRecursion(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int x = stack.pop();
        sortByRecursion(stack);
        sortedInsert(stack, x);
    }

    private static void sortedInsert(Stack<Integer> stack, int data) {
        if(stack.isEmpty() || data > stack.peek()) {
            stack.push(data);
            return;
        }
        int x = stack.pop();
        sortedInsert(stack, data);
        stack.push(x);
    }

    /**
     * Sort a stack (by temporary stack)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static Stack<Integer> sortByTemporaryStack(Stack<Integer> stack) {
        Stack<Integer> sort = new Stack<>();
        while(!stack.isEmpty()) {
            int current = stack.pop();
            while(!sort.isEmpty() && current < sort.peek()) {
                stack.push(sort.pop());
            }
            sort.push(current);
        }
        return sort;
    }
}
