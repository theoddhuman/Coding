package com.paul.subham.trie.implementation.prefixtrie;

public class PrefixNode {
    PrefixNode[] links;
    public int wordCount;
    public int prefixCount;

    public PrefixNode() {
        links = new PrefixNode[26];
        wordCount = 0;
        prefixCount = 0;
    }

    public PrefixNode get(char c) {
        return this.links[c-'a'];
    }

    public boolean contains(char c) {
        return this.links[c-'a'] != null;
    }

    public void put(char c, PrefixNode node) {
        this.links[c-'a'] = node;
    }
}
