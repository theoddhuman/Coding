package com.paul.subham.stack.operation;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Delete middle element of a stack
 */
public class Manipulation {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        deleteMiddle(stack);
    }

    /**
     * Delete middle element of a stack
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void deleteMiddle(Stack<Integer> stack) {
        deleteMiddle(stack, stack.size(), 0);
    }

    private static void deleteMiddle(Stack<Integer> stack, int n, int count) {
        if(stack.isEmpty() || count == n) {
            return;
        }
        int x = stack.pop();
        deleteMiddle(stack, n, count+1);
        if(n/2 != count) {
            stack.push(x);
        }
    }
}
