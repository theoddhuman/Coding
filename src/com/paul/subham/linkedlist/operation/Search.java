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
 * 5. Intersection point of two linked lists (2 loop)
 * 6. Intersection point of two linked lists (Marking visiting node)
 * 7. Intersection point of two linked lists (Using length)
 * 8. Intersection point of two linked lists (Making first list circular)
 * 9. Intersection point of two linked lists (Reversing first list)
 * 10. Intersection point of two linked lists (By hashing)
 * 11. Intersection point of two linked lists (By two pointer)
 * 12. Search an element in a linked list (extra space)
 * 13. Search an element in a linked list (Iterative)
 * 14. Search an element in a linked list (Recursive)
 * 15. Number of occurrences of an element in a linked list (Iterative)
 * 16. Number of occurrences of an element in a linked list (Recursive)
 * 17. Find modular node of a linked list
 * 18. Find fractional node of a linked list
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
     * Intersection point of two linked lists (2 loop)
     * TC: O(n^2)
     * SC: O(1)
     */
    public static Node intersectionPoint(LinkedList linkedList1, LinkedList linkedList2) {
        Node head1 = linkedList1.head;
        Node head2 = linkedList2.head;
        Node temp1 = head1;
        while(temp1 != null) {
            Node temp2 = head2;
            while(temp2 != null) {
                if(temp1 == temp2) {
                    return temp1;
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        return null;
    }

    /**
     * Intersection point of two linked lists (Marking visiting node)
     * TC: O(n)
     * SC: O(max(m,n))
     */
    public static VisitingLinkedList.Node intersectionPointMarkingVisitedNode(VisitingLinkedList linkedList1, VisitingLinkedList linkedList2) {
        VisitingLinkedList.Node head1 = linkedList1.head;
        VisitingLinkedList.Node head2 = linkedList2.head;
        VisitingLinkedList.Node temp1 = head1;
        VisitingLinkedList.Node temp2 = head2;
        while(temp1 != null) {
            temp1.visited = true;
            temp1 = temp1.next;
        }
        while(temp2 != null) {
            if(temp2.visited) {
                return temp2;
            } else {
                temp2.visited = true;
            }
            temp2 = temp2.next;
        }
        return null;
    }

    /**
     * Intersection point of two linked lists (Using length)
     * TC: O(n)
     * SC: O(1)
     */
    public static int intersectionPointUsingLength(LinkedList list1, LinkedList list2) {
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
     * Intersection point of two linked lists (Making first list circular)
     * TC: O(n)
     * SC: O(1)
     */
    public static Node intersectionPointMakingCircleInFirst(LinkedList linkedList1, LinkedList linkedList2) {
        Node head1 = linkedList1.head;
        Node head2 = linkedList2.head;
        Node temp1 = head1;
        int size1 = 0;
        while(temp1.next != null) {
            size1 ++;
            temp1 = temp1.next;
        }
        size1++;
        temp1.next = head1;
        Node front = head2;
        for(int i=0; i<size1; i++) {
            front = front.next;
        }
        Node rear = head2;
        //check the circle
        while(front != rear) {
            front = front.next;
            rear = rear.next;
        }
        temp1.next = null;
        return front;
    }


    /**
     * Intersection point of two linked lists (Reversing first list)
     *
     * 1) Let X be the length of the first linked list until intersection point.
     *    Let Y be the length of the second linked list until the intersection point.
     *    Let Z be the length of the linked list from the intersection point to End of
     *    the linked list including the intersection node.
     *    We Have
     *            X + Z = C1;
     *            Y + Z = C2;
     * 2) Reverse first linked list.
     * 3) Traverse Second linked list. Let C3 be the length of second list - 1.
     *      Now we have
     *         X + Y = C3
     *      We have 3 linear equations. By solving them, we get
     *        X = (C1 + C3 – C2)/2;
     *        Y = (C2 + C3 – C1)/2;
     *        Z = (C1 + C2 – C3)/2;
     *       WE GOT THE INTERSECTION POINT.
     * 4)  Reverse first linked list.
     *
     * TC: O(n)
     * SC: O(1)
     */

    public static Node intersectionPointReversingFirstList(LinkedList linkedList1, LinkedList linkedList2) {
        int c1 = linkedList1.size();
        int c2 = linkedList2.size();
        Manipulation.reverseIterative(linkedList1);
        int c3 = linkedList2.size() + 1;
        int x = (c2 + c3 - c1) / 2;
        Node current = linkedList2.head;
        for(int i=0; i<x-1; i++) {
            current = current.next;
        }
        Manipulation.reverseIterative(linkedList1);
        return current;
    }

    /**
     * Intersection point of two linked lists (By hashing)
     * TC: O(n)
     * SC: O(n)
     */
    public static Node intersectionPointByHashing(LinkedList linkedList1, LinkedList linkedList2) {
        Set<Node> set = new HashSet<>();
        Node current1 = linkedList1.head;
        while(current1 != null) {
            set.add(current1);
            current1 = current1.next;
        }
        Node current2 = linkedList2.head;
        while(current2 != null) {
            if(set.contains(current2)) {
                return current2;
            }
            current2 = current2.next;
        }
        return null;
    }

    /**
     * Intersection point of two linked lists (By two pointer)
     * TC: O(n)
     * SC: O(n)
     */
    public static Node intersectionPointByTwoPointer(LinkedList linkedList1, LinkedList linkedList2) {
        Node p1 = linkedList1.head;
        Node p2 = linkedList2.head;
        do {
            if(p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
            if(p1 == null) {
                p1 = linkedList1.head;
                break;
            }
            if(p2 == null) {
                p2 = linkedList2.head;
                break;
            }
        } while(p1 != p2);

        do {
            if(p1 == p2) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        } while(p1 != p2);

        return null;
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
