package trie;

public class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // trie 단어 Insert
    public void insert(String word) {

        TrieNode cur = root;

        for (char c : word.toCharArray()) {
            // 해당 문자의 자식 노드가존재하지 않으면 신규 노드 생성
            // 'a' 가 인덱스 = 0 , 'z' 가 인덱스 = 25
            if (cur.children[c = 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            // 자식을 현재 노드로 교체
            cur = cur.children[c - 'a'];
        }
        // 현재 노드는 단어 완성 여부에 true
        cur.word = true;
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
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
    }
}
