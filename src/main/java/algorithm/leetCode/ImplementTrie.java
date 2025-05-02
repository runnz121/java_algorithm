package algorithm.leetCode;

import trie.Trie;

public class ImplementTrie {

    TrieNode root;

    public ImplementTrie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;

        for (char c : word.toCharArray()) {

            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.word = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;

        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.word;
    }

    public boolean startsWith(String prefix) {

        TrieNode cur = root;

        for (char c : prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a'];
        }
        return true;
    }

    public static class TrieNode {
        boolean word;

        TrieNode[] children;

        TrieNode() {
            children = new TrieNode[26];
            word = false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("bapple");

        boolean res1 = trie.search("apple");
        System.out.println(res1); // true
        boolean res2 = trie.search("kpple");
        System.out.println(res2); // false
        boolean res3 = trie.startsWith("app");
        System.out.println(res3); // true
        boolean res4 = trie.startsWith("bapp");
        System.out.println(res4); // true
    }
}
