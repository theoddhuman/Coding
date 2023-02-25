package com.paul.subham.hashing.implementation;

import java.util.LinkedList;

/**
 * 1. resize hash
 * 2. search an element
 * 3. insert an element
 * 4. delete an element
 */
public class SeparateChainingHash {
    private static final int LOAD_FACTOR = 2;

    LinkedList<Integer>[] listArray;
    int size;
    int tableSize;
    int elementCount;

    SeparateChainingHash(int size) {
        this.size = size;
        this.tableSize = size / LOAD_FACTOR;
        elementCount = 0;
        listArray = new LinkedList[tableSize];
        for(int i=0; i<tableSize; i++) {
            listArray[i] = new LinkedList<>();
        }
    }

    private int hash(int data) {
        return data % tableSize;
    }


    /**
     * resize hash
     */
    private void reSize() {
        size *= 2;
        int oldTableSize = tableSize;
        tableSize = size / LOAD_FACTOR;
        LinkedList<Integer>[] oldListArray = listArray;
        listArray = new LinkedList[tableSize];
        for(int i=0; i<tableSize; i++) {
            listArray[i] = new LinkedList<>();
        }
        for(int i=0; i<oldTableSize; i++) {
            LinkedList<Integer> list = oldListArray[i];
            for(Integer element : list) {
                int index = hash(element);
                listArray[index].add(element);
            }
        }
    }

    /**
     * insert an element
     * TC: O(n)
     * SC: O(1)
     */
    void insert(int data) {
        if(elementCount == size) {
            reSize();
        }
        int index = hash(data);
        if(!contains(data)) {
            listArray[index].add(data);
            elementCount++;
        }
    }

    /**
     * search an element
     * TC: O(n)
     * SC: O(1)
     */
    boolean contains(int data) {
        int index = hash(data);
        LinkedList<Integer> list = listArray[index];
        for(Integer element : list){
            if(element == data) {
                return  true;
            }
        }
        return false;
    }

    /**
     * delete an element
     * TC: O(n)
     * SC: O(1)
     */
    void delete(int data) {
        int index = hash(data);
        LinkedList<Integer> list = listArray[index];
        for(Integer element : list){
            if(element == data) {
                list.remove(element);
                elementCount--;
                break;
            }
        }
    }

    void print() {
        for(int i=0; i<tableSize; i++) {
            LinkedList<Integer> list = listArray[i];
            System.out.print(i + " -> ");
            for(Integer element : list) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
