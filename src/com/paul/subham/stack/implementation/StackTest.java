package com.paul.subham.stack.implementation;

public class StackTest {
    public static void main(String[] args) {
        MiddleStack stack = new MiddleStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack.middle());
        stack.pop();
        System.out.println(stack.middle());

    }
}
