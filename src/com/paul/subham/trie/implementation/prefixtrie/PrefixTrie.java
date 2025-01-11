package com.paul.subham.trie.implementation.prefixtrie;

/**
 * 1. Insert a word
 * 2. Counting word
 * 3. Counting prefix
 * 4. Deleting a word
 */
public class PrefixTrie {
    PrefixNode root;

    public PrefixTrie() {
        root = new PrefixNode();
    }

    /**
     * Insert a word
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void insert(String word) {
        PrefixNode current = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(!current.contains(c)) {
                current.put(c, new PrefixNode());
                current.prefixCount++;
            }
            current = current.get(c);
        }
        current.wordCount++;
    }

    /**
     * Counting word
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int countWords(String word) {
        PrefixNode current = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(!current.contains(c)) {
                return 0;
            }
            current = current.get(c);
        }
        return current.wordCount;
    }

    /**
     * Count prefix
     *
     * TC: O(n)
     * SC: O(1)
     */
    public int countPrefix(String word) {
        PrefixNode current = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(!current.contains(c)) {
                return 0;
            }
            current = current.get(c);
        }
        return current.prefixCount;
    }

    /**
     * Delete a word
     *
     * TC: O(n)
     * SC: O(1)
     */
    public void delete(String word) {
        PrefixNode current = root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(!current.contains(c)) {
                return;
            }
            current.prefixCount--;
            current = current.get(c);
        }
        current.wordCount--;
    }
}
