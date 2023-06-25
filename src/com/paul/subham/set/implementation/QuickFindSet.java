package com.paul.subham.set.implementation;

/**
 * This is a QUICK FIND set where root is used as set name.
 * 0 1 2 3 4 5 6 7 8 9
 * 0 1 2 3 7 5 6 7 8 9 //union(4,7)
 * 0 1 2 3 7 9 6 7 8 9 //union(5,9)
 * 0 1 2 7 7 9 6 7 8 9 //union(3,4)
 * 0 1 2 9 9 9 6 9 8 9 //union(3,9)
 *
 * 1. make set
 * 2. find set
 * 3. union of sets
 */
public class QuickFindSet {
    int[] s;
    int size;

    /**
     * make set
     * TC: O(n)
     * SC: O(1)
     */
    public void makeSet(int size) {
        s = new int[size];
        this.size = size;
        for(int i=0; i<size; i++) {
            s[i] = i;
        }
    }


    /**
     * find set
     * TC: O(1)
     * SC: O(1)
     */
    public int find(int a) {
        return s[a];
    }

    /**
     * union of sets
     * TC: O(n)
     * SC: O(1)
     */
    public void union(int a, int b) {
        //belong to same set
        if(find(a) == find(b)) {
            return;
        }
        int temp = s[a];
        for(int i=0; i<size; i++){
            if(s[i] == temp) {
                s[i] = s[b];
            }
        }
    }

    void print() {
        for(int i=0; i<size; i++) {
            System.out.print(s[i] + " ");
        }
    }

}
