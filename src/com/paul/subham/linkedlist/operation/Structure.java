package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author subham.paul
 *
 * 1. Check if linked list is palindrome (using stack)
 * 2. Check if linked list is palindrome (By reversing the list)
 * 3. Check if linked list is palindrome (Using recursion)
 * 4. Intersection point of two linked lists (2 loop)
 * 5. Intersection point of two linked lists (Marking visiting node)
 * 6. Intersection point of two linked lists (Using length)
 * 7. Intersection point of two linked lists (Making first list circular)
 * 8. Intersection point of two linked lists (Reversing first list)
 * 9. Intersection point of two linked lists (By hashing)
 * 10. Intersection point of two linked lists (By two pointer)
 */

public class Structure {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.insertAtEnd(1);
        linkedList.print();
        System.out.println();
        System.out.println(isPalindromeByRecursion(linkedList));
    }

    /**
     * Check if linked list is palindrome (using stack)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isPalindrome(LinkedList linkedList) {
        Node current = linkedList.head;
        Node temp = current;
        Stack<Integer> stack = new Stack<>();
        while(temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }
        while(current != null) {
            if(current.data != stack.pop()) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    /**
     * Check if linked list is palindrome (By reversing the list)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean isPalindromeByReversing(LinkedList linkedList) {
        Node slow = linkedList.head;
        Node fast = linkedList.head;
        Node prev = linkedList.head;
        Node midNode = null;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null) {
            midNode = slow;
            slow = slow.next;
        }

        //creating second half of the list
        LinkedList secondList = new LinkedList();
        secondList.head = slow;
        //creating first half of the list
        prev.next = null;

        Manipulation.reverseIterative(secondList);
        boolean result = Comparison.isIdentical(linkedList, secondList);
        Manipulation.reverseIterative(secondList);
        if(midNode != null) {
            prev.next = midNode;
        } else {
            prev.next = slow;
        }
        return result;
    }


    /**
     * Check if linked list is palindrome (Using recursion)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean isPalindromeByRecursion(LinkedList linkedList) {
        left = linkedList.head;
        return isPalindromeUtil(linkedList.head);
    }

    private static Node left;
    private static boolean isPalindromeUtil(Node right) {
        if (right == null) {
            return true;
        }
        if(!isPalindromeUtil(right.next)) {
            return false;
        }
        if(left.data != right.data) {
            return false;
        }
        left = left.next;
        return true;
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
}
