package com.paul.subham.queue.operation;

import java.util.*;

/**
 * 1. Implement LRU Cache (Using Deque)
 * 2. Implement LRU Cache (Using LinkedHashMap)
 * 3. Implement LRU Cache (Using Doubly linked list)
 */
public class LRU {
    public static void main(String[] args) {

    }
}


/**
 * Implement LRU Cache (Using Deque)
 *
 * get, TC: O(n)
 * put, TC: O(n)
 * SC: O(n)
 */
class LRUCacheDeque {
    Map<Integer, Integer> map;
    Deque<Integer> deque;
    int capacity;

    public LRUCacheDeque(int capacity) {
        this.capacity = capacity;
        deque = new LinkedList<>();
        map = new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        deque.remove(key);
        deque.addLast(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            deque.remove(key);
        }
        deque.addLast(key);
        map.put(key, value);
        if(deque.size() > capacity) {
            int x = deque.removeFirst();
            map.remove(x);
        }
    }
}

/**
 * Implement LRU Cache (Using LinkedHashMap)
 *
 * get, TC: O(1)
 * put, TC: O(1)
 * SC: O(n)
 */
class LRUCache {
    Map<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        int value = map.get(key);
        map.remove(key);
        map.put(key, value);
        return value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            map.remove(key);
        }
        map.put(key, value);
        if(map.size() > capacity) {
            int x = map.keySet().iterator().next();
            map.remove(x);
        }
    }
}

/**
 * Implement LRU Cache (Using Doubly linked list)
 *
 * get, TC: O(1)
 * put, TC: O(1)
 * SC: O(n)
 */
class LRUCacheDLL {
    Map<Integer, Node> map;
    int capacity;
    Node start;
    Node end;

    public LRUCacheDLL(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            remove(map.get(key));
        }
        Node node = new Node(key, value);
        add(node);
        map.put(key, node);
        if(map.size() > capacity) {
            int x = start.key;
            remove(start);
            map.remove(x);
        }
    }

    private void add(Node node) {
        if(start == null) {
            start = end = node;
            return;
        }
        end.next = node;
        node.pre = end;
        end = end.next;
    }

    private void remove(Node node) {
        if(node == start) {
            start = start.next;
            return;
        }
        if(node == end) {
            end = end.pre;
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

}

class Node {
    int key;
    int value;
    Node pre;
    Node next;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.pre = this.next = null;
    }

    @Override
    public String toString() {
        return key +" " + value;
    }
}
