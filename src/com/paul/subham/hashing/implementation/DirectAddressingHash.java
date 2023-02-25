package com.paul.subham.hashing.implementation;

/**
 * 1. insert an element
 * 2. delete an element
 * 3. search an element
 */
public class DirectAddressingHash {
    boolean[] hash;
    int size;

    DirectAddressingHash(int size) {
        this.size = size;
        hash = new boolean[size];
    }

    /**
     * insert an element
     */
    void insert(int data) {
        hash[data] = true;
    }

    /**
     * delete an element
     */
    void delete(int data) {
        hash[data] = false;
    }

    /**
     * search an element
     */
    boolean search(int data) {
        return hash[data];
    }
}
