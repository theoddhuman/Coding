package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.doubly.DLNode;
import com.paul.subham.linkedlist.implementation.doubly.DoublyLinkedList;

import java.util.HashMap;

/**
 * @author subham.paul
 *
 * 1. Print pairs with given sum in doubly linked list
 * 2. Count triplets in a sorted doubly linked list whose sum is equal to k
 * 3. Count triplets in a sorted doubly linked list whose sum is equal to k (using hashing)
 * 4. Count triplets in a sorted doubly linked list whose sum is equal to k (Two pointer)
 */
public class Sum {
    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(7);
        linkedList.insertAtEnd(10);
        linkedList.insertAtEnd(12);
        linkedList.insertAtEnd(14);
        System.out.println(countTripletsTwoPointer(linkedList, 20));
    }

    /**
     * Print pairs with given sum in doubly linked list
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static void printPairSum(DoublyLinkedList linkedList, int sum) {
        Sort.quickSort(linkedList);
        DLNode start = linkedList.head;
        DLNode end = linkedList.head;
        while(end.next != null) {
            end = end.next;
        }
        while(start != end && start != end.next) {
            if(start.data + end.data == sum) {
                System.out.println(start.data + " " + end.data);
                start = start.next;
                end = end.pre;
            } else if (start.data + end.data > sum) {
                end = end.pre;
            } else {
                start = start.next;
            }
        }
    }

    /**
     * Count triplets in a sorted doubly linked list whose sum is equal to k
     *
     * TC: O(n^3)
     * SC: O(1)
     */
    public static int countTriplets(DoublyLinkedList linkedList, int sum) {
        int count = 0;
        for(DLNode ptr1 = linkedList.head; ptr1 != null; ptr1 = ptr1.next) {
            for(DLNode ptr2 = ptr1.next; ptr2 != null; ptr2 = ptr2.next) {
                for(DLNode ptr3 = ptr2.next; ptr3 != null; ptr3 = ptr3.next) {
                    if(ptr1.data + ptr2.data + ptr3.data == sum) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Count triplets in a sorted doubly linked list whose sum is equal to k (using hashing)
     *
     * TC: O(n^2)
     * SC: O(n)
     */
    public static int countTripletsHashing(DoublyLinkedList linkedList, int k) {
        HashMap<Integer, DLNode> hashMap = new HashMap<>();
        for(DLNode ptr = linkedList.head; ptr != null; ptr = ptr.next) {
            hashMap.put(ptr.data, ptr);
        }
        int count = 0;
        for(DLNode ptr1 = linkedList.head; ptr1 != null; ptr1 = ptr1.next) {
            for(DLNode ptr2 = ptr1.next; ptr2 != null; ptr2 = ptr2.next) {
                int sum = ptr1.data + ptr2.data;
                if(hashMap.containsKey(k-sum) && hashMap.get(k-sum) != ptr1 && hashMap.get(k-sum) != ptr2) {
                    count++;
                }
            }
        }
        return count/3;
    }

    /**
     * Count triplets in a sorted doubly linked list whose sum is equal to k (Two pointer)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static int countTripletsTwoPointer(DoublyLinkedList linkedList, int k) {
        DLNode last = linkedList.head;
        while(last.next != null) {
            last =last.next;
        }
        int count = 0;
        for(DLNode ptr = linkedList.head; ptr != last; ptr = ptr.next) {
            count += countPair(ptr.next, last, k-ptr.data);
        }
        return count;
    }

    private static int countPair(DLNode start, DLNode end, int sum) {
        int count = 0;
        while(start != null && end != null && start != end && end.next != start) {
            int pairSum = start.data + end.data;
            if(pairSum == sum) {
                count ++;
                start = start.next;
                end = end.pre;
            } else if (pairSum > sum) {
                end = end.pre;
            } else {
                start = start.next;
            }
        }
        return count;
    }
}
