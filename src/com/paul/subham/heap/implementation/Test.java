package com.paul.subham.heap.implementation;

public class Test {
    public static void main(String[] args) {
        BinaryHeap bh = new BinaryHeap(10, HeapType.MAX);
        bh.insert(1);
        bh.insert(2);
        bh.insert(3);
        bh.insert(4);
        bh.print();
    }
}
