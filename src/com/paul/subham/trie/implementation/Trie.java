package com.paul.subham.trie.implementation;

/**
 * 1. Inserting a word
 * 2. Searching a word
 * 3. Searching a prefix
 */
public class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    /**
     * Inserting a word
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void insert(String word) {
        Node current = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(!current.contains(c)) {
                current.put(c, new Node());
            }
            current = current.get(c);
        }
        current.end = true;
    }

    /**
     * Searching a word
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean search(String word) {
        Node current = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(!current.contains(c)) {
                return false;
            }
            current = current.get(c);
        }
        return current.end;
    }

    /**
     * Searching a prefix
     *
     * TC: O(n)
     * SC: O(1)
     */
    public boolean startsWith(String prefix) {
        Node current = root;
        for(int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!current.contains(c)) {
                return false;
            }
            current = current.get(c);
        }
        return true;
    }
}
