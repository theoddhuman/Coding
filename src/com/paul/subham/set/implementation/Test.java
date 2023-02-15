package com.paul.subham.set.implementation;

public class Test {
    public static void main(String[] args) {
        QuickUnionWeightedSet set = new QuickUnionWeightedSet();
        set.makeSet(10);
        set.unionByRank(4,7);
        set.unionByRank(5,9);
        set.unionByRank(3,4);
        set.unionByRank(3,9);
        set.print();
        System.out.println();
        System.out.println(set.findPathCompression(3));
        set.print();
    }
}
