package com.paul.subham.stack.implementation;

/**
 * 1. is stack empty
 * 2. is stack full
 * 3. push an element
 * 4. pop an element
 * 5. top element
 * 6. print stack
 */
class ArrayStack {
    private final int MAX = 100;
    private int[] a = new int[MAX];
    private int top;

    ArrayStack() {
        top = -1;
    }

    //is stack empty
    boolean isEmpty() {
        return top < 0;
    }

    //is stack full
    boolean isFull() {
        return top >= MAX-1;
    }

    //push an element
    boolean push(int data) {
        if(isFull()) {
            return false;
        }
        a[++top] = data;
        return true;
    }

    //pop an element
    int pop() {
        if(isEmpty()) {
            return -1;
        }
        return a[top--];
    }

    //top element
    int top() {
        return a[top];
    }

    //print stack
    void print() {
        for(int i=top; i>=0; i--) {
            System.out.print(a[i] + " ");
        }
    }
}
