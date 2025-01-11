package com.paul.subham.trie.implementation;

import com.paul.subham.trie.implementation.prefixtrie.PrefixTrie;

public class Test {
    public static void main(String[] args) {
        PrefixTrie trie = new PrefixTrie();
        trie.insert("apple");
        trie.insert("apps");
        trie.insert("apps");
        System.out.println(trie.countWords("apple"));
        trie.delete("apple");
        System.out.println(trie.countWords("apple"));
    }
}
