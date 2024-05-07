package com.paul.subham.linkedlist.implementation.unrolled;

/**
 * @author subham.paul
 *
 * 1. Insert an element
 * 2. Remove an element
 * 3. Search an element
 * 4. Get element at an index
 */
public class UnrolledLinkedList {
    UnrolledNode start;
    UnrolledNode end;
    int nodeCapacity;
    int size;
    UnrolledLinkedList(int nodeCapacity) {
        this.nodeCapacity = nodeCapacity;
        this.size = 0;
        start = end = null;
    }

    /**
     * Insert an element
     *
     * TC: O(sqrt(n))
     * SC: O(1)
     */
    public void insert(int data) {
        if(start == null) {
            start = new UnrolledNode(nodeCapacity);
            start.arr[0] = data;
            start.numElements++;
            end = start;
        } else if (end.numElements < nodeCapacity) {
            end.arr[end.numElements] = data;
            end.numElements++;
        } else {
            UnrolledNode newNode = new UnrolledNode(nodeCapacity);
            for(int i = end.numElements/2 + 1; i<end.numElements; i++) {
                newNode.arr[newNode.numElements] = end.arr[i];
                newNode.numElements++;
            }
            newNode.arr[newNode.numElements] = data;
            newNode.numElements++;
            end.numElements = end.numElements/2 + 1;
            end.next = newNode;
            end = newNode;
        }
        size++;
    }

    public void display() {
        for(UnrolledNode ptr = start; ptr!= null; ptr = ptr.next) {
            for(int i=0; i<ptr.numElements; i++) {
                System.out.print(ptr.arr[i] + " ");
            }
            System.out.println();
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Remove an element
     *
     * TC: O(sqrt(n))
     * SC: O(1)
     */
    public void delete() {
        if(isEmpty()) {
            return;
        }
        size--;
        end.numElements--;
        if(end.numElements == 0) {
            UnrolledNode current = start;
            while(current.next != end) {
                current = current.next;
            }
            end = current;
        }
    }

    /**
     * Search an element
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean search(int data) {
        if(isEmpty()) {
            return false;
        }
        for(UnrolledNode ptr = start; ptr != null; ptr = ptr.next) {
            for(int i=0; i<ptr.numElements; i++) {
                if(ptr.arr[i] == data) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get element at an index
     *
     * TC: O(sqrt(n))
     * SC: O(1)
     */
    public int get(int index) {
        if(isEmpty() || index >= size) {
            return Integer.MIN_VALUE;
        }
        int rest = size - end.numElements;
        if(index+1 > rest) {
            return end.arr[index - rest];
        } else {
            int requiredNode = (index+1)/(nodeCapacity/2 + 1);
            UnrolledNode current = start;
            for(int i=1; i<requiredNode; i++) {
                current = current.next;
            }
            int res = (index+1)%(nodeCapacity/2 + 1);
            if(res == 0) {
                return current.arr[current.numElements-1];
            } else {
                return current.next.arr[res-1];
            }
        }
    }
}
