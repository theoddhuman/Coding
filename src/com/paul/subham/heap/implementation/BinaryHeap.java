package com.paul.subham.heap.implementation;

/**
 * 1. parent of a node
 * 2. left child of a node
 * 3. right child of a node
 * 4. maximum element
 * 5. Heapify an element (Max heap)
 * 6. Heapify an element (Min heap)
 * 7. delete max element in max heap
 * 8. Delete an element
 * 9. Delete an element at ith position
 * 10. insert an element
 * 11. Heapify an array (Max heap)
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getA() {
        return a;
    }

    public void setA(int[] a) {
        this.a = a;
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
     * heapify an element (Max heap)
     * TC: O(log n)
     * SC: O(log n)
     */
    public void percolateDownMax(int i) {
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
            percolateDownMax(max);
        }
    }

    /**
     * Heapify an element (Min heap)
     * TC: O(log n)
     * SC: O(log n)
     */
    public void percolateDownMin(int i) {
        int min = i;
        int left = leftChild(i);
        int right = rightChild(i);
        if (left != -1 && a[left] < a[i]) {
            min = left;
        }
        if (right != -1 && a[right] < a[min]) {
            min = right;
        }
        if (min != i) {
            int temp = a[i];
            a[i] = a[min];
            a[min] = temp;
            percolateDownMin(min);
        }
    }

    /**
     * delete max element in max heap
     * TC: O(logn)
     * SC: O(logn)
     */
    public int delete() {
        if (count == 0) {
            return Integer.MIN_VALUE;
        }
        int data = a[0];
        a[0] = a[count - 1];
        count--;
        if(HeapType.MAX.equals(heapType)) {
            percolateDownMax(0);
        } else {
            percolateDownMin(0);
        }
        return data;
    }

    /**
     * Delete an element
     * TC: O(logn)
     * SC: O(logn)
     */
    public void delete(int data) {
        for(int i=0; i<count; i++) {
            if(a[i] == data) {
                a[i] = a[count - 1];
                count--;
                break;
            }
        }
        if(HeapType.MAX.equals(heapType)) {
            percolateDownMax(0);
        } else {
            percolateDownMin(0);
        }
    }

    /**
     * Delete an element at ith position
     * TC: O(logn)
     * SC: O(logn)
     */
    public void deleteAtIndex(int i) {
        a[i] = a[count - 1];
        count--;
        if (HeapType.MAX.equals(heapType)) {
            percolateDownMax(0);
        } else {
            percolateDownMin(0);
        }
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
     * TC: O(logn)
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
        if(HeapType.MAX.equals(heapType)) {
            while (i > 0 && data > a[(i - 1) / 2]) {
                a[i] = a[(i - 1) / 2];
                i = (i - 1) / 2;
            }
        } else {
            while (i > 0 && data < a[(i - 1) / 2]) {
                a[i] = a[(i - 1) / 2];
                i = (i - 1) / 2;
            }
        }
        a[i] = data;
    }

    public void destroy() {
        count = 0;
        a = null;
    }


    /**
     * Heapify an array (Max heap)
     * TC: O(n)
     * SC: O(n)
     */
    public void buildHeap(int arr[]) {
        if(arr.length > capacity) {
            resizeHeap();
        }
        System.arraycopy(arr, 0, a, 0, arr.length);
        count = arr.length;
        for(int i=(arr.length/2)-1; i>=0; i--){
            percolateDownMax(i);
        }
    }
}
