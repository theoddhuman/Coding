package com.paul.subham.stack.implementation;


public class SNode {
    int data;
    public SNode next;
    SNode pre;
    SNode(int data){
        this.data = data;
        this.next = this.pre = null;
    }
}
