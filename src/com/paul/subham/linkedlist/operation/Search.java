package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

import java.util.HashMap;

/**
 * 1. nth node from end (iterative)
 * 2. nth node from end (recursive)
 * 3. Find intersection of two linked lists
 * 4. Middle of a linked list using hashing
 * 5. Middle of a linked list (efficient approach)
 */
public class Search {
    private static int nthNodeCount = 0;
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(5);
        System.out.println(middleEfficient(linkedList));

        /*LinkedList linkedList1 = new LinkedList();
        linkedList1.insertAtEnd(11);
        linkedList1.insertAtEnd(2);
        linkedList1.head.next.next = linkedList.head.next.next;
        linkedList1.insertAtEnd(8);
        System.out.println(intersectingNode(linkedList, linkedList1));*/
    }

    /**
     * nth node from end (iterative)
     * TC: O(n)
     * SC: O(1)
     */
    public static int nthNodeFromEndIterative(LinkedList linkedList, int n) {
        Node temp = linkedList.head;
        Node nthNode = null;
        for(int i=0; i<n-1 && temp != null; i++) {
            temp = temp.next;
        }
        while(temp != null) {
            if(nthNode == null) {
                nthNode = linkedList.head;
            } else {
                nthNode = nthNode.next;
            }
            temp = temp.next;
        }
        if(nthNode != null) {
            return nthNode.data;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * nth node from end (iterative)
     * TC: O(n)
     * SC: O(1)
     */
    public static int nthNodeFromEndRecursive(LinkedList linkedList, int n) {
        Node node = nthNodeFromEndUtil(linkedList.head, n);
        if(node != null) {
            return node.data;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public static Node nthNodeFromEndUtil(Node node, int n) {
        Node result = null;
        if(node != null) {
            result = nthNodeFromEndUtil(node.next, n);
            if(result == null) {
                nthNodeCount++;
                if(nthNodeCount == n) {
                    return node;
                }
            }
        }
        return result;
    }

    /**
     * Find intersection of two linked lists
     * TC: O(n)
     * SC: O(1)
     */
    public static int intersectingNode(LinkedList list1, LinkedList list2) {
        int length1 = 0;
        int length2 = 0;
        int diff;
        Node head1 = list1.head;
        Node head2 = list2.head;
        while(head1 != null) {
            length1++;
            head1 = head1.next;
        }
        while(head2 != null) {
            length2++;
            head2 = head2.next;
        }
        if(length1 > length2) {
            head1 = list1.head;
            head2 = list2.head;
            diff = length1 - length2;
        } else {
            head1 = list2.head;
            head2 = list1.head;
            diff = length2 - length1;
        }
        for(int i=0; i<diff; i++) {
            head1 = head1.next;
        }
        while(head1 != null && head2 != null) {
            if(head1 == head2) {
                return head1.data;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return -1;
    }

    /**
     * Middle of a linked list using hashing
     * TC: O(n)
     * SC: O(n)
     */
    public static int middle(LinkedList linkedList) {
        int count = 0;
        Node current = linkedList.head;
        HashMap<Integer, Node> map = new HashMap<>();
        while(current != null) {
            map.put(count, current);
            count++;
            current = current.next;
        }
        Node node = map.get((count-1)/2);
        return node.data;
    }

    /**
     * Middle of a linked list (efficient approach)
     * TC: O(n)
     * SC: O(1)
     */
    public static int middleEfficient(LinkedList linkedList) {
        Node ptr1 = linkedList.head;
        Node ptr2 = linkedList.head;

        int i = 0;
        while(ptr1 != null) {
            if(i==0) {
                ptr1 = ptr1.next;
                i=1;
            } else if(i==1) {
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
                i=0;
            }
        }
        return ptr2.data;
    }
}
