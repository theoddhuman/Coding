package com.paul.subham.hashing.implementation;

public class Test {
    public static void main(String[] args) {
        DoubleHash hash = new DoubleHash(10);
        hash.insert(3);
        hash.insert(5);
        hash.insert(2);
        hash.insert(11);
        hash.insert(13);
        hash.insert(7);
        hash.insert(10);
        hash.insert(12);
        hash.insert(14);
        hash.insert(17);
        hash.insert(1);
        hash.print();
        System.out.println(hash.contains(8));
        hash.delete(11);
        hash.delete(5);
        hash.print();
    }
}
