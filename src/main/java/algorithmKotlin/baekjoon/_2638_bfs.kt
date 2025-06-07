package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque

class _2638_dfs {

    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    var N: Int = 0
    var M: Int = 0
    var graph = emptyArray<IntArray>()
    var answer: Int = 0
    var visited = emptyArray<BooleanArray>()

    fun main() {
        var br = BufferedReader(InputStreamReader(System.`in`))
        var st = StringTokenizer(br.readLine())

        N = st.nextToken().toInt()
        M = st.nextToken().toInt()

        // graph 초기화
        graph = Array(N) { IntArray(M) }

        repeat(N) { i ->
            st = StringTokenizer(br.readLine())
            repeat(M) { j ->
                graph[i][j] = st.nextToken().toInt()
            }
        }

        while (true) {

            visited = Array(N) { BooleanArray(M) }

            // 1. 외부 공기 표시 -> visited로 처리
            bfs(0, 0)

            // 2. 마킹
            val markedLists = doMark()

            // 3. 녹이기 -> 더이상 녹일게 없는 경우 true 반환 -> 전체 반복 종료
            if (doMelt(markedLists)) {
                break
            }
            // 4. 그게 아니면 시간 증가
            answer += 1
        }

        println(answer)
// graph 출력 메서드
//    println(graph.contentDeepToString())
    }

    fun bfs(i: Int, j: Int) {

        visited[i][j] = true

        val que = ArrayDeque<Pair<Int, Int>>()
        que.addLast(Pair(i, j))

        // que 남아있을때까지 반복
        while(que.isEmpty() == false) {

            val out = que.removeFirst()
            var idy = out.first
            var idx = out.second

            // 4 방향 탐색
            for (i in 0..3) {

                var yy = idy + dy[i]
                var xx = idx + dx[i]

                // 범위 밖이면
                if (yy < 0 || xx < 0 || yy >= N || xx >= M) {
                    continue
                }

                if (!visited[yy][xx] && graph[yy][xx] == 0) {
                    visited[yy][xx] = true
                    que.addLast(yy to xx)
                }
            }
        }
    }

    fun doMark(): List<Pair<Int, Int>>  {

        val toMelt = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until N) {
            for (j in 0 until M) {

                // 치즈인것들 중에서 해당 좌표 기준 4방향 탐색해서 주변 2개가 0인 것들을 마킹함
                if (graph[i][j] == 1) {
                    var expose = 0

                    for (x in 0..3) {
                        var newY = i + dy[x]
                        var newX = j + dx[x]

                        if (newY < 0 || newX < 0 || newY >= N || newX >= M) {
                            continue
                        }

                        // 해당칸이 공기와 맞닿아있는 부분이라면 (치즈가 아니고)
                        if (graph[newY][newX] == 0 && visited[newY][newX]) {
                            expose += 1
                        }
                    }

                    // 치즈 기준으로 2개의 변이 노출되었다면 마킹함
                    if (expose >= 2) {
                        toMelt.add(i to j)
                    }
                }
            }
        }

        return toMelt
    }

    fun doMelt(toMelt: List<Pair<Int, Int>>): Boolean {

        // 녹일게 없으면 종료
        if (toMelt.isEmpty()) {
            return true
        }

        // 치즈 녹이기
        for ((i, j) in toMelt) {
            graph[i][j] = 0
        }

        return false;
    }
}


