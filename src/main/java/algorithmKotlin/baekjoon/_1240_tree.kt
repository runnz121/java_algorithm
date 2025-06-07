package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

// https://www.acmicpc.net/problem/1240
class _1240_tree {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            var br = BufferedReader(InputStreamReader(System.`in`))
            var st = StringTokenizer(br.readLine())

            var M = st.nextToken().toInt()
            var N = st.nextToken().toInt()

            // 트리 선언
            /**
             * 자바는 Pair 가 없음으로 객체로 생성해야된다
             *  List<Pair>[] tree = new ArrayList[M + 1];
             *  for (int i = 0; i <= M; i++) {
             *      tree[i] = new ArrayList<>();
             *   }
             *
             */
            val tree = Array(M + 1) {
                mutableListOf<Pair<Int, Int>>()
            }

            // 입력
            repeat(M - 1) {
                var st1 = StringTokenizer(br.readLine())

                var a = st1.nextToken().toInt()
                var b = st1.nextToken().toInt()
                var c = st1.nextToken().toInt()

                tree[a].add(Pair(b, c))
                tree[b].add(Pair(a, c))
            }

            // 거리를 알고 싶은 두 노드
            repeat(N) {
                var st2 = StringTokenizer(br.readLine())

                var a = st2.nextToken().toInt()
                var b = st2.nextToken().toInt()

                val visited = BooleanArray(M + 1)

                // dfs 로 탐색
                val answer = dfs(0, a, b, visited, tree)

                println(answer)
            }
        }

        fun dfs(value: Int, start: Int, end: Int, visited: BooleanArray, tree: Array<MutableList<Pair<Int, Int>>>): Int {

            // 방문처리
            visited[start] = true

            // 트리 정보 갖고옴
            val pairs = tree[start]

            if (start == end) {
                return value
            }

            // 전체 순회
            for (pair in pairs) {
                // 첫번재는 인덱스
                var idx = pair.first
                var dist = pair.second

                // 방문을 했으면 넘어감
                if (visited[idx]) {
                    continue
                }

                // 하위 호출을 상위로 반환 -> 하위호출이 -1 이 아닌 것을 반환했따면 바로 반환
                val results = dfs(value + dist, idx, end, visited, tree)

                if (results != -1) {
                    return results
                }
            }

            // 목표를 몾찾았을경우 -1 반환함
            return -1
        }
    }
}
