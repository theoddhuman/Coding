package com.paul.subham.set.implementation;

public class Test {
    public static void main(String[] args) {
        QuickUnionSet set = new QuickUnionSet();
        set.makeSet(10);
        set.union(4,7);
        set.union(5,9);
        set.union(3,4);
        set.union(3,9);
        set.print();
        System.out.println();
        //System.out.println(set.findPathCompression(3));
        set.print();
    }
}
