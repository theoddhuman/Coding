package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1. nth node from end (iterative)
 * 2. nth node from end (recursive)
 * 3. Middle of a linked list using hashing
 * 4. Middle of a linked list (efficient approach)
 * 5. Search an element in a linked list (extra space)
 * 6. Search an element in a linked list (Iterative)
 * 7. Search an element in a linked list (Recursive)
 * 8. Number of occurrences of an element in a linked list (Iterative)
 * 9. Number of occurrences of an element in a linked list (Recursive)
 * 10. Find modular node of a linked list
 * 11. Find fractional node of a linked list
 */
public class Search {
    private static int nthNodeCount = 0;
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(4);
        linkedList.insertAtEnd(5);

        System.out.println(fractionalNode(linkedList, 3).data);

//        LinkedList linkedList1 = new LinkedList();
//        linkedList1.insertAtEnd(5);
//        linkedList1.insertAtEnd(6);
//        linkedList1.head.next.next = linkedList.head.next.next;
//        linkedList.print();
//        System.out.println();
//        linkedList1.print();
//        System.out.println();
//        System.out.println(intersectionPointByTwoPointer(linkedList1, linkedList).data);
//        linkedList.print();

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

    /**
     * Search an element in a linked list (extra space)
     * TC: O(n)
     * SC: O(n)
     */
    public static int searchExtraSpace(LinkedList linkedList, int data) {
        List<Integer> list = new ArrayList<>();
        Node current = linkedList.head;
        while(current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list.indexOf(data);
    }

    /**
     * Search an element in a linked list (Iterative)
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean searchIterative(LinkedList linkedList, int data) {
        Node current = linkedList.head;
        while(current != null) {
            if(current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Search an element in a linked list (Recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean searchRecursive(LinkedList linkedList, int data) {
        Node current = linkedList.head;
        return searchUtil(current, data);
    }

    public static boolean searchUtil(Node node, int data) {
        if(node == null) {
            return false;
        }
        if(node.data == data) {
            return true;
        }
        return searchUtil(node.next, data);
    }

    /**
     * Number of occurrences of an element in a linked list (Iterative)
     * TC: O(n)
     * SC: O(1)
     */
    public static int countOccurrenceIterative(LinkedList linkedList, int data) {
        Node current = linkedList.head;
        int count = 0;
        while(current != null) {
            if(current.data == data) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

    /**
     * Number of occurrences of an element in a linked list (Recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static int countOccurrenceRecursive(LinkedList linkedList, int data) {
        return countUtil(linkedList.head, data);
    }

    private static int countUtil(Node node, int data) {
        if(node == null) {
            return 0;
        }
        int freq = 0;
        if(node.data == data) {
            freq++;
        }
        return freq + countUtil(node.next, data);
    }

    /**
     * Find modular node of a linked list
     *
     * Given a singly linked list and a number k, find the last node whose n%k == 0, where n is the number of nodes in the list.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node modularNode(LinkedList linkedList, int k) {
        if(k < 0) {
            return null;
        }
        Node modularNode = null;
        Node current = linkedList.head;
        int i = 1;
        while(current != null) {
            if(i%k == 0) {
                modularNode = current;
            }
            i++;
            current = current.next;
        }
        return modularNode;
    }

    /**
     * Find fractional node of a linked list
     *
     * Given a singly linked list and a number k, find the (n/k)-th element, where n is the number of elements in the list.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static Node fractionalNode(LinkedList linkedList, int k) {
        if(k < 0) {
            return null;
        }
        Node fractionalNode = null;
        Node current = linkedList.head;
        int i = 0;
        while(current != null) {
            if(i%k == 0) {
                if(fractionalNode == null) {
                    fractionalNode = linkedList.head;
                } else {
                    fractionalNode = fractionalNode.next;
                }
            }
            i++;
            current = current.next;
        }
        return fractionalNode;
    }


}


class VisitingLinkedList {
    Node head;

    static class Node {
        int data;
        Node next;
        boolean visited;
        Node(int data) {
            this.data = data;
            this.next = null;
            this.visited = false;
        }
    }
}
