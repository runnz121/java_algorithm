package trie;

import java.util.Arrays;

public class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // trie 단어 Insert
    public void insert(String word) {
        // 루트 부터 시작
        TrieNode cur = root;
        // 단어 문자 차례대로 반복하며 자식 노드 구성
        for (char c : word.toCharArray()) {
            // 해당 문자의 자식 노드가존재하지 않으면 신규 노드 생성
            // 'a' 가 인덱스 = 0 , 'z' 가 인덱스 = 25
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            // 자식을 현재 노드로 교체
            cur = cur.children[c - 'a'];
        }
        // 현재 노드는 단어 완성 여부에 true
        cur.word = true;
    }

    // 단어 존재 여부 판별
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

    // 문자열로 시작 단어 존재 여부 판별
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


    @Override
    public String toString() {
        return "Trie{" +
                "root=" + root +
                '}';
    }

    // trie node
    public static class TrieNode {
        boolean word;
        TrieNode[] children;

        // 자식 노드는 알파벳 26자리까지 설정
        TrieNode() {
            children = new TrieNode[26];
            word = false;
        }

        @Override
        public String toString() {
            return "TrieNode{" +
                    "word=" + word +
                    ", children=" + Arrays.toString(children) +
                    '}';
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
