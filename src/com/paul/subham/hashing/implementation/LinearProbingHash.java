package com.paul.subham.hashing.implementation;

/**
 * 1. insert an element
 * 2. search an element
 * 3. delete an element
 */
public class LinearProbingHash {
    private static final int LOAD_FACTOR = 1;

    private int size;
    private int tableSize;
    int[] tableArray;
    int elementCount;

    LinearProbingHash(int size) {
        this.size = size;
        tableSize = size / LOAD_FACTOR;
        elementCount = 0;
        tableArray = new int[tableSize];
    }

    private int hash(int key) {
        return key % tableSize;
    }

    private void reSize() {
        size *= 2;
        int oldTableSize = tableSize;
        tableSize = size / LOAD_FACTOR;
        int[] oldTableArray = tableArray;
        tableArray = new int[tableSize];
        for(int i=0; i<oldTableSize; i++) {
            int index = hash(oldTableArray[i]);
            int k = index;
            int j = 1;
            while(tableArray[index] != 0) {
                //Quadratic Probing
                //index = hash(k + j*j);
                index = hash(k + j);
                j++;
            }
            tableArray[index] = oldTableArray[i];
        }
    }

    /**
     * insert an element
     * TC: O(n)
     * TC: O(1)
     */
    void insert(int data) {
        if(elementCount == size) {
            reSize();
        }
        int index = hash(data);
        int k = index;
        int i = 1;
        while(tableArray[index] != 0) {
            if(tableArray[index] == data) {
                return;
            }
            //Quadratic Probing
            //index = hash(k + i*i);
            index = hash(k+i);
            i++;
        }
        tableArray[index] = data;
        elementCount++;
    }

    /**
     * search an element
     * TC: O(n)
     * SC: O(1)
     */
    boolean contains(int data) {
        int index = hash(data);
        int k = 1;
        int i = 1;
        do {
            if(tableArray[index] == data) {
                return true;
            }
            //Quadratic Probing
            //index = hash(k + i*i);
            index = hash(k+i);
            i++;
        } while(index != k);
        return false;
    }

    /**
     * delete an element
     * TC: O(n)
     * SC: O(1)
     */
    void delete(int data) {
        int index = hash(data);
        int k = 1;
        int i = 1;
        do {
            if(tableArray[index] == data) {
                tableArray[index] = 0;
                elementCount--;
                break;
            }
            //Quadratic Probing
            //index = hash(k + i*i);
            index = hash(k+i);
            i++;
        } while(index != k);
    }

    void print() {
        for(int i=0; i<tableSize; i++) {
            System.out.print(tableArray[i] + " ");
        }
    }
}
