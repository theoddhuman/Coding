package com.paul.subham.stack.implementation;


import java.util.TreeSet;

/**
 * Author: the_odd_human
 * Date: 30/05/25
 *
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 *
 * Implement the MaxStack class:
 *
 * MaxStack() Initializes the stack object.
 * void push(int x) Pushes element x onto the stack.
 * int pop() Removes the element on top of the stack and returns it.
 * int top() Gets the element on the top of the stack without removing it.
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 * int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
 */
public class MaxStack {
    private SNode top;
    private TreeSet<SNode> set;
    private SNode max;

    public MaxStack() {
        set = new TreeSet<>((a, b) -> {
            if(a == b) {
                return 0;
            } else if (a.data >= b.data) {
                return 1;
            }
            return -1;
        });
    }

    public void push(int x) {
        SNode node = new SNode(x);
        if(top != null) {
            node.next = top;
            top.pre = node;
        }
        top = node;
        set.add(node);
    }

    public int pop() {
        SNode node = top;
        set.remove(top);
        top = top.next;
        if(node.pre != null) {
            node.pre.next = node.next;
        }
        if(node.next != null) {
            node.next.pre = node.pre;
        }
        return node.data;
    }

    public int top() {
        return top.data;
    }

    public int peekMax() {
        return set.last().data;
    }

    public int popMax() {
        SNode node = set.pollLast();
        if(node == top) {
            top = top.next;
        }
        if(node.pre != null) {
            node.pre.next = node.next;
        }
        if(node.next != null) {
            node.next.pre = node.pre;
        }
        return node.data;
    }
}

