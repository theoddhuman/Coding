package com.paul.subham.trie.implementation.trie;

public class Node {
    Node[] links;
    boolean end;

    public Node() {
        links = new Node[26];
        end = false;
    }

    public Node get(char c) {
        return links[c-'a'];
    }

    public void put(char c, Node node) {
        this.links[c-'a'] = node;
    }

    public boolean contains(char c) {
        return this.links[c - 'a'] != null;
    }
}
