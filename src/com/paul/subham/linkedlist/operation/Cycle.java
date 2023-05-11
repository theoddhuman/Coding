package com.paul.subham.linkedlist.operation;

import com.paul.subham.linkedlist.implementation.single.LinkedList;
import com.paul.subham.linkedlist.implementation.single.Node;

import java.util.HashSet;

/**
 * 1. detect cycle in a linked list
 * 2. detect cycle in a linked list (Floyd Cycle Detection)
 * 3. start node of cycle in a linked list
 * 4. length of cycle in a linked list
 */
public class Cycle {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertAtEnd(1);
        linkedList.insertAtEnd(2);
        linkedList.insertAtEnd(3);
        linkedList.head.next.next.next = linkedList.head.next;
        System.out.println(lengthOfCycle(linkedList));
    }

    /**
     * detect cycle in a linked list
     * TC: O(n)
     * SC: O(n)
     */
    public static boolean detectCycle(LinkedList linkedList) {
        HashSet<Node> hashSet = new HashSet<>();
        Node current = linkedList.head;
        while (current != null) {
            if (hashSet.contains(current)) {
                return true;
            }
            hashSet.add(current);
            current = current.next;
        }
        return false;
    }

    /**
     * detect cycle in a linked list (Floyd Cycle Detection)
     * TC: O(n)
     * SC: O(1)
     */
    public static boolean detectCycleFloyd(LinkedList linkedList) {
        Node fast = linkedList.head;
        Node slow = linkedList.head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast.equals(slow)) {
                return true;
            }
        }
        return false;
    }

    /**
     * start node of cycle in a linked list
     * TC: O(n)
     * SC: O(1)
     */
    public static Node startOfCycle(LinkedList linkedList) {
        Node slow = linkedList.head;
        Node fast = linkedList.head;
        boolean loopExists = false;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                loopExists = true;
                break;
            }
        }
        if(loopExists) {
            slow = linkedList.head;
            while(fast != slow) {
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
        return null;
    }

    /**
     * length of cycle in a linked list
     * TC: O(n)
     * SC: O(1)
     */
    public static int lengthOfCycle(LinkedList linkedList) {
        Node slow = linkedList.head;
        Node fast = linkedList.head;
        boolean loopExists = false;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                loopExists = true;
                break;
            }
        }
        int length = 0;
        if(loopExists) {
            do {
                slow = slow.next;
                length++;
            } while (slow != fast);
        }
        return length;
    }

}
