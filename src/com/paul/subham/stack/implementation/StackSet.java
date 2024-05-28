package com.paul.subham.stack.implementation;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * @author subham.paul
 * 
 * 1. Get the last stack
 * 2. Get nth stack
 * 3. Push data
 * 4. Pop data
 */
public class StackSet<T> {
    int threshold;
    ArrayList<Stack<T>> list = new ArrayList<>();

    StackSet(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Get the last stack
     *
     * TC: O(1)
     * SC: O(1)
     */
    public Stack<T> getLastStack() {
        if(list.size() <= 0) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /**
     * Get nth stack
     *
     * TC: O(1)
     * SC: O(1)
     */
    public Stack<T> getNthStack(int n) {
        if(list.size() <= 0) {
            return null;
        }
        return list.get(n-1);
    }

    /**
     * Push data
     * 
     * TC: O(1)
     * SC: O(1)
     */
    public void push(T data) {
        Stack<T> lastStack = getLastStack();
        if(Objects.isNull(lastStack)) {
            lastStack = new Stack<>();
            lastStack.push(data);
            list.add(lastStack);
        } else {
            if(!lastStack.empty() && lastStack.size() < threshold) {
                lastStack.push(data);
            } else {
                Stack<T> newLastStack = new Stack<>();
                newLastStack.push(data);
                list.add(newLastStack);
            }
        }
    }

    /**
     * Pop data
     *
     * TC: O(1)
     * SC: O(1)
     */
    public T pop() {
        Stack<T> lastStack = getLastStack();
        T data = lastStack.pop();
        if(lastStack.size() == 0) {
            list.remove(list.size() - 1);
        }
        return data;
    }

    public void print() {
        for(Stack<T> stack : list) {
            System.out.println(stack);
        }
    }

}
