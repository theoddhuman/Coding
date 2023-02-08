package com.paul.subham.heap.implementation;

/**
 * 1. parent of a node
 * 2. left child of a node
 * 3. right child of a node
 * 4. maximum element
 * 5. heapify an element
 * 6. delete an element
 * 7. insert an element
 */
public class BinaryHeap {
    private int[] a;
    private int count;
    private int capacity;
    private HeapType heapType;

    public BinaryHeap(int capacity, HeapType heapType) {
        this.capacity = capacity;
        a = new int[capacity];
        this.heapType = heapType;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * parent of a node
     * TC: O(1)
     * SC: O(1)
     */
    public int parent(int i) {
        if (i <= 0 || i >= count) {
            return -1;
        }
        return (i - 1) / 2;
    }

    /**
     * left child of a node
     * TC: O(1)
     * SC: O(1)
     */
    public int leftChild(int i) {
        int left = 2 * i + 1;
        if (left >= count) {
            return -1;
        }
        return left;
    }

    /**
     * right child of a node
     * TC: O(1)
     * SC: O(1)
     */
    public int rightChild(int i) {
        int right = 2 * i + 2;
        if (right >= count) {
            return -1;
        }
        return right;
    }

    /**
     * maximum element
     * TC: O(1)
     * SC: O(1)
     */
    public int max() {
        if (count == 0) {
            return Integer.MIN_VALUE;
        }
        return a[0];
    }

    /**
     * heapify an element
     * TC: O(log n)
     * SC: O(log n)
     */
    public void percolateDown(int i) {
        int max = i;
        int left = leftChild(i);
        int right = rightChild(i);
        if (left != -1 && a[left] > a[i]) {
            max = left;
        }
        if (right != -1 && a[right] > a[max]) {
            max = right;
        }
        if (max != i) {
            int temp = a[i];
            a[i] = a[max];
            a[max] = temp;
            percolateDown(max);
        }
    }

    /**
     * delete an element
     * TC: O(n)
     * SC: O(n)
     */
    public int delete() {
        if (count == 0) {
            return Integer.MIN_VALUE;
        }
        int data = a[0];
        a[0] = a[count - 1];
        count--;
        percolateDown(0);
        return data;
    }

    private void resizeHeap() {
        int[] aOld = a;
        capacity *= 2;
        a = new int[capacity];
        System.arraycopy(aOld, 0, a, 0, aOld.length);
        aOld = null;
    }

    /**
     * insert an element
     * TC: O(n)
     * SC: O(1)
     */
    public void insert(int data) {
        if (count == capacity) {
            resizeHeap();
        }
        int i = count;
        count++;
        if (i == 0) {
            a[i] = data;
            return;
        }
        while (i > 0 && data > a[(i - 1) / 2]) {
            a[i] = a[(i - 1) / 2];
            i = (i - 1) / 2;
        }
        a[i] = data;
    }

    public void destroy() {
        count = 0;
        a = null;
    }
}
