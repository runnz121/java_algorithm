package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

class _16934_trie {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val br = BufferedReader(InputStreamReader(System.`in`))
            var bw = BufferedWriter(OutputStreamWriter(System.`out`))

            var N = br.readLine().toInt()

            var trie = TrieNickName()

            var nickNameNumMap = mutableMapOf<String, Int>()

            while(N > 0) {

                var st = StringTokenizer(br.readLine())

                var nickName = st.nextToken()

                // 1. 해당 단어가 트리에 있는지 확인
                val isExists = trie.search(nickName)

                // 2. 존재하면 더해서 반환 미존재면 map insert -> 출력 후 종료
                if (isExists) {
                    val prev = (nickNameNumMap[nickName] ?: 0)
                    val newCount = prev + 1
                    nickNameNumMap[nickName] = newCount
                    println("$nickName$newCount")
                    N -= 1
                    continue
                }
                // 2-1. 그게 아니면 새로 생성해서 맵에 넣음
                else {
                    nickNameNumMap.put(nickName, 1)
                }

                // 3. 접두사가 존재하는지를 파악
                var prefix: String = ""
                var printed = false
                for (c in  nickName.toCharArray()) {

                    prefix += c

                    // 접두사 false 이라면 바로 출력하고 트리에 삽입
                    val isNotExists = trie.startsWith(prefix)

                    if (isNotExists == false) {
                        trie.insert(nickName)
                        println(prefix)
                        printed = true
                        break
                    }
                }

                if (printed) {
                    N -= 1
                    continue
                }

                // 4. 전체 글자라면 해당글자 insert 하고 출력 하고종료
                trie.insert(nickName)
                println(nickName)
                N -= 1
            }
        }
    }
}

class TrieNickName {

    var root: TrieNode = TrieNode()

    fun insert(word: String) {

        var cur = root

        for (c in  word.toCharArray()) {
            // 해당 char가 null 이면 char 로 노드 생성
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = TrieNode()
            }
            // 위에서 이미 초기화 됨으로 not null 단언
            cur = cur.children[c - 'a']!!
        }
        // 단어 완성 여부 true
        cur.word = true
    }

    fun search(word: String) : Boolean {

        var cur = root

        for (c in word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false;
            }
            cur = cur.children[c - 'a']!!
        }
        return cur.word
    }

    fun startsWith(prefix: String) : Boolean {
        var cur = root

        for (c in prefix.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return false
            }
            cur = cur.children[c - 'a']!!
        }
        return true
    }
}


class TrieNode {

    var word: Boolean = false

    var children: Array<TrieNode?> = Array(26) {null}

//    override fun toString(): String {
//
//        return "TrieNode (word = $word, children= ${children.contentToString()}"
//    }
}