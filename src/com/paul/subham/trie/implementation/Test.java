package com.paul.subham.trie.implementation;

public class Test {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("apps");
        System.out.println(trie.startsWith("app"));
    }
}
