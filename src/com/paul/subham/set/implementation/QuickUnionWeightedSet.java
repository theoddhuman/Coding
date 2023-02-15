package com.paul.subham.set.implementation;

/**
 * This is a QUICK UNION weighted set where parent is root name.
 *
 * 1. make set
 * 2. find set
 * 3. union of sets by weight
 * 4. union of sets by height or rank
 * 5. find a set by path compression
 */
public class QuickUnionWeightedSet {
    int[] s;
    int[] weight;
    int size;

    /**
     * make set
     */
    void makeSet(int size) {
        s = new int[size];
        weight = new int[size];
        this.size = size;
        for(int i=0; i<size; i++) {
            s[i] = i;
            weight[i] = 1;
        }
    }

    /**
     * find set
     * TC: O(log n)
     * SC: O(1)
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
     * union of sets by weight
     * TC: O(log n)
     * SC: O(1)
     */
    void unionByWeight(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) {
            return;
        }
        if(weight[rootA] > weight[rootB]) {
            s[rootB] = rootA;
            weight[rootA] += weight[rootB];
        } else {
            s[rootA] = rootB;
            weight[rootB] += weight[rootA];
        }
    }

    /**
     * union of sets by height or rank
     * TC: O(log n)
     * SC: O(1)
     */
    void unionByRank(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) {
            return;
        }
        if(weight[rootA] > weight[rootB]) {
            s[rootB] = rootA;
        } else if(weight[rootA] < weight[rootB]){
            s[rootA] = rootB;
        } else {
            s[rootA] = rootB;
            weight[rootB] ++;
        }
    }

    /**
     * find set by path compression
     * TC: O(log n)
     * SC: O(1)
     */
    int findPathCompression(int x) {
        if(!(x>=0 && x<size)) {
            return -1;
        }
        if(s[x] == x){
            return x;
        }
        return s[x] = findPathCompression(s[x]);
    }

    void print() {
        for(int i=0; i<size; i++) {
            System.out.print(s[i] + " ");
        }
        System.out.println();
        for(int i=0; i<size; i++) {
            System.out.print(weight[i] + " ");
        }
    }
}
