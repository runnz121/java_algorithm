package algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Palindrome_Pairs_Trie {

    static class TrieNode {

        int wordId;

        TrieNode[] children;

        List<Integer> palindromeWords;

        public TrieNode() {
            wordId = -1;
            children = new TrieNode[26];
            palindromeWords = new ArrayList<>();
        }
    }

    static class Trie {
        TrieNode root;


    }

    public List<List<Integer>> palindromePairs(String[] words) {

        return null;


    }

    public static void main(String[] args) {
        Palindrome_Pairs_Trie ppt = new Palindrome_Pairs_Trie();
        String[] inputs = {"abcd","dcba","lls","s","sssll"};
        ppt.palindromePairs(inputs);
    }
}
