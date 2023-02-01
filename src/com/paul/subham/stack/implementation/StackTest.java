package com.paul.subham.stack.implementation;

public class StackTest {
    public static void main(String[] args) {
        //ArrayStack stack = new ArrayStack();
        LinkedListStack stack = new LinkedListStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.pop();
        stack.print();
    }
}
