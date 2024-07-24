package com.paul.subham.stack.operation;

import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Sort a stack (by recursion)
 * 2. Sort a stack (by temporary stack)
 * 3. Sort array using stack
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
        int[] a = {5,2,3,1,4};
        sortArray(a, 5);


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

    /**
     * Sort array using stack
     * 
     * TC: O(n)
     * SC: O(1)
     */
   public static void sortArray(int[] a, int n) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            stack.push(a[i]);
        }
        Stack<Integer> sortedStack = sortByTemporaryStack(stack);
        for(int i=n-1; i>=0; i--) {
            a[i] = sortedStack.pop();
        }
   }
}
