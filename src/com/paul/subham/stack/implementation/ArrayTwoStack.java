package com.paul.subham.stack.implementation;

/**
 * @author subham.paul
 *
 * Implementation of TwoStack using array
 */
public class ArrayTwoStack {
    int size;
    int top1, top2;
    int[] arr;

    ArrayTwoStack(int size) {
        this.size = size;
        top1 = -1;
        top2 = size;
        arr = new int[size];
    }

    void push1(int x) {
        if(top1 < top2-1) {
            top1++;
            arr[top1] = x;
        } else {
            System.out.println("Overflow");
        }
    }

    void push2(int x) {
        if(top1 < top2-1) {
            top2--;
            arr[top2] = x;
        } else {
            System.out.println("Overflow");
        }
    }

    int pop1() {
        if(top1 >= 0) {
            int x = arr[top1];
            top1--;
            return x;
        } else {
            System.out.println("Underflow");
        }
        return Integer.MIN_VALUE;
    }

    int pop2() {
        if(top2 < size) {
            int x = arr[top2];
            top2++;
            return x;
        } else {
            System.out.println("Underflow");
        }
        return Integer.MIN_VALUE;
    }

    void print() {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
