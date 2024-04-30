package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.circular.CircularLinkedList;
import com.paul.subham.linkedlist.implementation.circular.CircularNode;
import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Inserting data in sorted linked list
 * 2. Reverse a linked list iterative
 * 3. Reverse a linked list recursive
 * 4. Merge two sorted linked lists in sorted order (recursive)
 * 5. Merge two sorted linked lists in sorted order (iterative)
 * 6. Reverse a linked list in pair (recursive)
 * 7. Reverse a linked list in pair (iterative)
 * 8. Pairwise swap elements of a linked list (iterative)
 * 9. Pairwise swap elements of a linked list (Recursive)
 * 10. Swap nodes of a linked list (Changing link)
 * 11. Reverse a linked list in group (Iterative)
 * 12. Reverse a linked list in group (Recursive)
 * 13. Rotate a linked list k times right (by changing head)
 * 14. Rotate a linked list k times right (by k rotation)
 * 15. Partition a linked list such that nodes smaller than k at one side and greater than k at the other.
 * 16. Partition a linked list such that odd nodes at one side and even nodes at the other.
 * 17. Remove duplicates in a linked list (Two loop)
 * 18. Remove duplicates in a linked list (Hashing)
 * 19. Remove duplicates in a sorted linked list (Iterative)
 * 20. Remove duplicates in a sorted linked list (Recursive)
 * 21. Create a linked list with common elements of two linked lists
 * 22. Splitting a circular linked list in two halves
 */
public class Manipulation {
    public static void main(String[] args) {
        CircularLinkedList linkedList = new CircularLinkedList();
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(5);
        linkedList.insertAtEnd(7);
        linkedList.insertAtEnd(11);
        linkedList.print();
        System.out.println();
        CircularLinkedList part1 = new CircularLinkedList();
        CircularLinkedList part2 = new CircularLinkedList();
        splitCircularList(linkedList, part1, part2);
        part1.print();
        System.out.println();
        part2.print();
//        removeDuplicatesSortedListRecursive(linkedList);
//        linkedList.print();
//        System.out.println();
//        insertInSortedList(linkedList, 7);
//        reverseRecursive(linkedList);
//        LinkedList linkedList1 = new LinkedList();
//        linkedList1.insertAtEnd(1);
//        linkedList1.insertAtEnd(2);
//        linkedList1.insertAtEnd(3);
//        linkedList1.insertAtEnd(4);
//        linkedList1.insertAtEnd(5);
//        linkedList1.print();
//        System.out.println();
//        reverseInGroupRecursive(linkedList1, 6);
//        System.out.println();
//        linkedList1.print();
//        LinkedList ll = commonElementList(linkedList, linkedList1);
//        ll.print();
    }

    /**
     * Inserting data in sorted linked list
     * TC: O(n)
     * SC: O(1)
     */
    public static void insertInSortedList(LinkedList linkedList, int data) {
        Node newNode = new Node(data);
        if(linkedList.head == null || linkedList.head.data > data) {
            newNode.next = linkedList.head;
            linkedList.head = newNode;
            return;
        }
        Node current = linkedList.head;
        Node temp = null;
        while(current != null && current.data < data) {
            temp = current;
            current = current.next;
        }
        temp.next = newNode;
        newNode.next = current;
    }

    /**
     * Reverse a linked list iterative
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseIterative(LinkedList linkedList) {
        Node prev = null;
        Node current = linkedList.head;
        Node next = null;
        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        linkedList.head = prev;
    }

    public static void reverseRecursive(LinkedList linkedList) {
        reverseUtil(linkedList, linkedList.head, null);
    }

    /**
     * Reverse a linked list recursive
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseUtil(LinkedList linkedList, Node current, Node prev) {
        if (current.next == null) {
            linkedList.head = current;
            current.next = prev;
        } else {
            Node next = current.next;
            current.next = prev;
            reverseUtil(linkedList, next, current);
        }
    }

    /**
     * Merge two sorted linked lists in sorted order (recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static LinkedList mergeListsRecursive(LinkedList linkedList1, LinkedList linkedList2) {
        LinkedList linkedList = new LinkedList();
        linkedList.head = mergeListsUtil(linkedList1.head, linkedList2.head);
        return linkedList;
    }

    public static Node mergeListsUtil(Node node1, Node node2) {
        if(node1 == null) {
            return node2;
        }
        if(node2 == null) {
            return node1;
        }
        Node current = new Node(0);
        if(node1.data <= node2.data) {
            current.data = node1.data;
            current.next = mergeListsUtil(node1.next, node2);
        } else {
            current.data = node2.data;
            current.next = mergeListsUtil(node1, node2.next);
        }
        return current;
    }

    /**
     * Merge two sorted linked lists in sorted order (iterative)
     * TC: O(n)
     * SC: O(1)
     */
    public static LinkedList mergeListsIterative(LinkedList linkedList1, LinkedList linkedList2) {
        Node node1 = linkedList1.head;
        Node node2 = linkedList2.head;
        LinkedList linkedList = new LinkedList();
        linkedList.head = new Node(0);
        Node current = linkedList.head;

        while(true) {
            if(node1 == null && node2 == null) {
                break;
            }
            current.next = new Node(0);
            if(node1 == null) {
                current.next.data = node2.data;
                break;
            }
            if(node2 == null) {
                current.next.data = node1.data;
                break;
            }
            if(node1.data <= node2.data) {
                current.next.data = node1.data;
                node1 = node1.next;
            } else {
                current.next.data = node2.data;
                node2 = node2.next;
            }
            current = current.next;
        }
        linkedList.head = linkedList.head.next;
        return linkedList;
    }

    /**
     * Reverse a linked list in pair (recursive)
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseInPairRecursive(LinkedList linkedList) {
        linkedList.head = reverseInPairUtil(linkedList.head);
    }

    public static Node reverseInPairUtil(Node node) {
        if(node == null || node.next == null) {
            return node;
        }
        Node temp = node.next;
        node.next = temp.next;
        temp.next = node;
        node.next = reverseInPairUtil(node.next);
        return temp;
    }

    /**
     * Reverse a linked list in pair (iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseInPairIterative(LinkedList linkedList) {
        Node current = linkedList.head;
        Node node = null;
        Node temp;
        while(current != null && current.next != null) {
            temp = current.next;
            current.next = temp.next;
            temp.next = current;
            if(current == linkedList.head) {
                linkedList.head = temp;
            }
            if(node != null) {
                node.next = temp;
            }
            node = current;
            current = current.next;
        }
    }

    /**
     * Pairwise swap elements of a linked list (iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void pairwiseSwapIterative(LinkedList linkedList) {
        Node current = linkedList.head;
        while(current != null && current.next != null) {
            int temp = current.data;
            current.data = current.next.data;
            current.next.data = temp;
            current = current.next.next;
        }
    }

    /**
     * Pairwise swap elements of a linked list (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void pairwiseSwapRecursive(LinkedList linkedList) {
        pairwiseSwapRecursiveUtil(linkedList.head);
    }

    public static void pairwiseSwapRecursiveUtil(Node node) {
        if(node != null && node.next != null) {
            int temp = node.data;
            node.data = node.next.data;
            node.next.data = temp;
            pairwiseSwapRecursiveUtil(node.next.next);
        }
    }

    /**
     * Swap nodes of a linked list (Changing link)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void swapNode(LinkedList linkedList, int x, int y) {
        if(x==y) {
            return;
        }
        Node prevX = null, currX = linkedList.head, prevY = null, currY = linkedList.head;
        while(currX != null && currX.data != x) {
            prevX = currX;
            currX = currX.next;
        }
        while(currY != null && currY.data != y) {
            prevY = currY;
            currY = currY.next;
        }
        if(prevX != null) {
            prevX.next = currY;
        } else {
            linkedList.head = currY;
        }
        if(prevY != null) {
            prevY.next = currX;
        } else {
            linkedList.head = currX;
        }
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }

    /**
     * Pairwise swap elements of a linked list (Changing link - Iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void pairwiseSwapChangingLinkIterative(LinkedList linkedList) {
        Node current = linkedList.head;
        Node temp1 = null, temp2 = null;
        while(current != null && current.next != null) {
            if(temp1 != null) {
                temp1.next.next = current.next;
            }
            temp1 = current.next;
            current.next = temp1.next;
            temp1.next = current;
            if(temp2 == null) {
                temp2 = temp1;
            }
            current = current.next;
        }
        linkedList.head = temp2;
    }

    /**
     * Pairwise swap elements of a linked list (Changing link - Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void pairwiseSwapChangingLinkRecursive(LinkedList linkedList) {
        linkedList.head = pairwiseSwapChangingLinkRecursiveUtil(linkedList.head, null);
    }

    public static Node pairwiseSwapChangingLinkRecursiveUtil(Node current, Node temp1) {
        if(current != null && current.next != null) {
            if(temp1 != null) {
                temp1.next.next = current.next;
            }
            temp1 = current.next;
            current.next = temp1.next;
            temp1.next = current;
            pairwiseSwapChangingLinkRecursiveUtil(current.next, temp1);
        }
        return temp1;
    }

    /**
     * Reverse a linked list in group (Iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void reverseInGroupIterative(LinkedList linkedList, int K) {
        Node current = linkedList.head;
        Node prevCurrent = current;
        Node prevTail = null;
        while(current != null) {
            Node tail = null;
            int count = K;
            while(current != null && count-- > 0 ) {
                Node next = current.next;
                current.next = tail;
                tail = current;
                current = next;
            }
            if(prevTail != null) {
                prevTail.next = tail;
            } else {
                linkedList.head = tail;
            }
            prevTail = prevCurrent;
            prevCurrent = current;
        }
    }

    /**
     * Reverse a linked list in group (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void reverseInGroupRecursive(LinkedList linkedList, int K) {
        linkedList.head = reverseInGroupRecursiveUtil(linkedList.head, K);
    }

    public static Node reverseInGroupRecursiveUtil(Node current, int K) {
        if(current == null) {
            return null;
        }
        Node tail = null;
        Node prevCurrent = current;
        int count = K;
        while (current != null && count-- > 0) {
            Node next = current.next;
            current.next = tail;
            tail = current;
            current = next;
        }
        prevCurrent.next = reverseInGroupRecursiveUtil(current, K);
        return tail;
    }

    /**
     * Rotate a linked list k times right (by changing head)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void rotateByChangingHead(LinkedList linkedList, int k) {
        Node current = linkedList.head;
        int count = 1;
        while(count < k && current != null) {
            current = current.next;
            count ++ ;
        }
        if(current == null) {
            return;
        }
        Node kthNode = current;
        while(current.next != null) {
            current = current.next;
        }
        current.next = linkedList.head;
        linkedList.head = kthNode.next;
        kthNode.next = null;
    }

    /**
     * Rotate a linked list k times right (by k rotation)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void rotateByKRotation(LinkedList linkedList, int k) {
        Node temp = linkedList.head;
        Node last = linkedList.head;
        while(last.next != null) {
            last = last.next;
        }
        while(k > 0) {
            linkedList.head = linkedList.head.next;
            temp.next = null;
            last.next = temp;
            last = temp;
            temp = linkedList.head;
            k--;
        }
    }

    /**
     * Partition a linked list such that nodes smaller than k at one side and greater than k at the other.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void partitionSmallerGreater(LinkedList linkedList, int k) {
        Node smaller = new Node(0);
        Node greater = new Node(k);
        Node smallerLast = smaller;
        Node greaterLast = greater;
        Node current = linkedList.head;
        while(current != null) {
            Node next = current.next;
            if(current.data < k ) {
                smallerLast.next = current;
                smallerLast = current;
            } else {
                greaterLast.next = current;
                greaterLast = current;
                greaterLast.next = null;
            }
            current = next;
        }
        smallerLast.next = greater.next;
        linkedList.head = smaller.next;
    }

    /**
     * Partition a linked list such that odd nodes at one side and even nodes at the other.
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void partitionOddEven(LinkedList linkedList) {
        Node odd = new Node(0);
        Node even = new Node(0);
        Node oddLast = odd;
        Node evenLast = even;
        Node current = linkedList.head;
        while(current != null) {
            Node next = current.next;
            if(current.data%2 != 0 ) {
                oddLast.next = current;
                oddLast = current;
            } else {
                evenLast.next = current;
                evenLast = current;
                evenLast.next = null;
            }
            current = next;
        }
        oddLast.next = even.next;
        linkedList.head = odd.next;
    }

    /**
     * Remove duplicates in a linked list (Two loop)
     *
     * TC: O(n^2)
     * SC: O(1)
     */
    public static void removeDuplicates(LinkedList linkedList) {
        Node ptr1 = linkedList.head;
        Node ptr2;
        while (ptr1 != null && ptr1.next != null) {
            ptr2 = ptr1;
            while (ptr2.next != null) {
                if (ptr1.data == ptr2.next.data) {
                    ptr2.next = ptr2.next.next;
                } else {
                    ptr2 = ptr2.next;
                }
            }
            ptr1 = ptr1.next;
        }
    }

    /**
     * Remove duplicates in a linked list (Hashing)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void removeDuplicatesByHashing(LinkedList linkedList) {
        Node current = linkedList.head;
        Set<Integer> set = new HashSet<>();
        set.add(current.data);
        while(current.next != null) {
            if(set.contains(current.next.data)) {
                current.next = current.next.next;
            } else {
                set.add(current.next.data);
                current = current.next;
            }
        }
    }

    /**
     * Remove duplicates in a sorted linked list (Iterative)
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void removeDuplicatesSortedListIterative(LinkedList linkedList) {
        Node current = linkedList.head;
        while(current != null) {
            Node temp = current;
            while(temp != null && temp.data == current.data) {
                temp = temp.next;
            }
            current.next = temp;
            current = current.next;
        }
    }

    /**
     * Remove duplicates in a sorted linked list (Recursive)
     *
     * TC: O(n)
     * SC: O(n)
     */
    public static void removeDuplicatesSortedListRecursive(LinkedList linkedList) {
        removeDupRecUtil(linkedList.head);
    }

    private static void removeDupRecUtil(Node node) {
        if(node == null) {
            return;
        }
        if(node.next != null) {
            if(node.data == node.next.data) {
                node.next = node.next.next;
                removeDupRecUtil(node);
            } else {
                removeDupRecUtil(node.next);
            }
        }
    }

    /**
     * Create a linked list with common elements of two linked lists
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static LinkedList commonElementList(LinkedList linkedList1, LinkedList linkedList2) {
        Node common = new Node(0);
        Node temp = common;
        Node node1 = linkedList1.head;
        Node node2 = linkedList2.head;
        while(node1 != null && node2 != null) {
            if(node1.data == node2.data) {
                temp.next = new Node(node1.data);
                temp = temp.next;
                node1 = node1.next;
                node2 = node2.next;
            } else if(node1.data > node2.data) {
                node2 = node2.next;
            } else {
                node1 = node1.next;
            }
        }
        LinkedList linkedList = new LinkedList();
        linkedList.head = common.next;
        return linkedList;
    }

    /**
     * Splitting a circular linked list in two halves
     *
     * TC: O(n)
     * SC: O(1)
     */
    public static void splitCircularList(CircularLinkedList linkedList, CircularLinkedList part1, CircularLinkedList part2) {
        CircularNode slow = linkedList.head;
        CircularNode fast = linkedList.head;
        while(fast.next != linkedList.head && fast.next.next != linkedList.head) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast.next.next == linkedList.head) {
            fast = fast.next;
        }
        if(linkedList.head.next != linkedList.head) {
            part2.head = slow.next;
        }
        fast.next = part2.head;
        part1.head = linkedList.head;
        slow.next = part1.head;
    }

}
