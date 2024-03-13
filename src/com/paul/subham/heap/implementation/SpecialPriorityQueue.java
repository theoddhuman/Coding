package com.paul.subham.heap.implementation;

/**
 * @author subham.paul
 *
 * 1. parent of a node
 * 2. left child of a node
 * 3. right child of a node
 * 4. maximum priority element
 * 5. heapify an element (Max heap)
 * 6. heapify an element (Min heap)
 * 7. delete an element
 * 8. insert an element with priority
 */
public class SpecialPriorityQueue {
    private int[] a;
    private int[] priorities;
    private int count;
    private int capacity;
    private HeapType heapType;

    public SpecialPriorityQueue(int capacity, HeapType heapType) {
        this.capacity = capacity;
        a = new int[capacity];
        priorities = new int[capacity];
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
     * maximum priority element
     * TC: O(1)
     * SC: O(1)
     */
    public int maxPriorityElement() {
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
        if (left != -1 && priorities[left] > priorities[i]) {
            max = left;
        }
        if (right != -1 && priorities[right] > priorities[max]) {
            max = right;
        }
        if (max != i) {
            int temp = priorities[i];
            priorities[i] = priorities[max];
            priorities[max] = temp;
            temp = a[i];
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
        if (left != -1 && priorities[left] < priorities[i]) {
            min = left;
        }
        if (right != -1 && priorities[right] < priorities[min]) {
            min = right;
        }
        if (min != i) {
            int temp = priorities[i];
            priorities[i] = priorities[min];
            priorities[min] = temp;
            temp = a[i];
            a[i] = a[min];
            a[min] = temp;
            percolateDownMin(min);
        }
    }

    /**
     * delete an element
     * TC: O(logn)
     * SC: O(logn)
     */
    public int delete() {
        if (count == 0) {
            return Integer.MIN_VALUE;
        }
        int data = a[0];
        a[0] = a[count - 1];
        priorities[0] = priorities[count-1];
        count--;
        if(HeapType.MAX.equals(heapType)) {
            percolateDownMax(0);
        } else {
            percolateDownMin(0);
        }
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
     * insert an element with priority
     * TC: O(logn)
     * SC: O(1)
     */
    public void insert(int data, int priority) {
        if (count == capacity) {
            resizeHeap();
        }
        int i = count;
        count++;
        if (i == 0) {
            a[i] = data;
            priorities[i] = priority;
            return;
        }
        if(HeapType.MAX.equals(heapType)) {
            while (i > 0 && priority > priorities[(i - 1) / 2]) {
                a[i] = a[(i - 1) / 2];
                priorities[i] = priorities[(i-1)/2];
                i = (i - 1) / 2;
            }
        } else {
            while (i > 0 && priority < a[(i - 1) / 2]) {
                a[i] = a[(i - 1) / 2];
                priorities[i] = priorities[(i-1)/2];
                i = (i - 1) / 2;
            }
        }
        a[i] = data;
        priorities[i] = priority;
    }

    public boolean isFull() {
        return count == capacity;
    }
}
