package com.paul.subham.searching;

/**
 * 1. linear search
 * 2. linear search sorted array
 */
public class LinearSearch {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        System.out.println(search(a, 4));
    }

    /**
     * linear search
     * TC: O(n)
     * SC: O(1)
     */
    static int search(int[] a, int data) {
        for(int i=0; i<a.length; i++) {
            if(a[i] == data) {
                return i;
            }
        }
        return -1;
    }

    /**
     * linear search sorted array
     * TC: O(n)
     * SC: O(1)
     */
    static int searchSortedArray(int[] a, int data) {
        for(int i=0; i<a.length; i++) {
            if(a[i] == data){
                return i;
            } else if(a[i] > data) {
                return -1;
            }
        }
        return -1;
    }
}
