package com.paul.subham.trie.operations;

import com.paul.subham.trie.implementation.trie.Node;
import com.paul.subham.trie.implementation.trie.Trie;

/**
 * 1. Largest String with all prefixes
 * 2. Count distinct substrings
 *
 *
 */
public class Classic {
    public static void main(String[] args) {

    }

    /**
     * Largest String with all prefixes
     *
     * A = [ “ab” , “abc” , “a” , “bp” ] output: "abc"
     * "abc" contains prefixes "a", "bc" are part of the list.
     *
     * TC: O(nk)
     */
    public static String completeString(int n, String[] a) {
        Trie trie = new Trie();
        for(int i=0; i<a.length; i++) {
            trie.insert(a[i]);
        }
        String res = "None";
        int max = 0;
        for(int i=0; i<a.length; i++) {
            if(isAllPrefixExists(trie, a[i])) {
                if((max < a[i].length()) || (max == a[i].length() && a[i].compareTo(res) < 0)) {
                    max = a[i].length();
                    res = a[i];
                }
            }
        }
        return res;
    }

    private static boolean isAllPrefixExists(Trie trie, String word) {
        Node current = trie.root;
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(!current.contains(c)) {
                return false;
            }
            current = current.get(c);
            if(!current.end) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count distinct substrings
     *
     * TC: O(n^2)
     */
    public static int countDistinctSubstrings(String s) {
        Node root = new Node();
        int count = 0;
        for(int i=0; i<s.length(); i++) {
            Node current = root;
            for(int j=i; j<s.length(); j++) {
                char c = s.charAt(j);
                if(!current.contains(c)) {
                    current.put(c, new Node());
                    count++;
                }
                current = current.get(c);
            }
        }
        return count + 1;
    }
}
