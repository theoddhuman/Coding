package com.paul.subham.stack.operation;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Sort a stack (by recursion)
 * 2. Sort a stack (by temporary stack)
 */
public class Sort {
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
