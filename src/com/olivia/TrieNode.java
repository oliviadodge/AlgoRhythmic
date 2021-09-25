package com.olivia;

public class TrieNode {
    public char c;
    public TrieNode[] children;

    TrieNode() {
    }

    TrieNode(char c) {
        this.c = c;
    }

    public TrieNode(char c, TrieNode[] children) {
        this.c = c;
        this.children = children;
    }
}
