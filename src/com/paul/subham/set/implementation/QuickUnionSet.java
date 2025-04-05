package com.paul.subham.set.implementation;

/**
 * This is a QUICK UNION set where parent is used as set.
 * 0 1 2 3 4 5 6 7 8 9
 * 0 1 2 3 7 5 6 7 8 9 //union(4,7)
 * 0 1 2 3 7 9 6 7 8 9 //union(5,9)
 * 0 1 2 7 7 9 6 7 8 9 //union(3,4)
 * 0 1 2 7 7 9 6 9 8 9 //union(3,9)
 *
 *
 * 1. make set
 * 2. find set
 * 3. union of sets
 */
public class QuickUnionSet {
    int[] s;
    int size;

    /**
     * make set
     */
    void makeSet(int size) {
        s = new int[size];
        this.size = size;
        for(int i=0; i<size; i++) {
            s[i] = i;
        }
    }

    /**
     * find set
     */
    int find(int x) {
        if(!(x>=0 && x<size)) {
            return -1;
        }
        if(s[x] == x) {
            return x;
        }
        return find(s[x]);
    }

    /**
     * union of sets
     */
    void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) {
            return;
        }
        s[rootA] = rootB;
    }

    void print() {
        for(int i=0; i<size; i++) {
            System.out.print(s[i] + " ");
        }
    }
}
