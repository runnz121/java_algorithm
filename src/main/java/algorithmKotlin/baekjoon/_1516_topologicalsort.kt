package algorithmKotlin.baekjoon

import java.util.*

class _1516_topologicalsort {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            var br = System.`in`.bufferedReader()
            var N = br.readLine().toInt()

            // 그래프(인접 리스트), 진입 차수, 건물별 순수 소요 시간, 결과 누적 시간 배열 초기화
            val graph = Array(N + 1) { mutableListOf<Int>() }
            val inDegree = IntArray(N + 1)
            val buildTime = IntArray(N + 1)
            val result = IntArray(N + 1)

            for (i in 1..N) {

                val input = br.readLine().split(" ").map { it.toInt()}

                buildTime[i] = input[0]

                // 선행 건물 정보 등록: “pre → i” 간선 추가, inDegree[i] 증가
                for (j in 1 until input.size - 1) {
                    val pre = input[j]
                    graph[pre].add(i)
                    inDegree[i]++
                }
            }

            val que : Queue<Int> = LinkedList()

            //  진입 차수 0인 노드를 큐에 넣고, result 초기값(buildTime) 설정
            for (i in 1..N) {
                if (inDegree[i] == 0) {
                    que.add(i)
                    result[i] = buildTime[i]
                }
            }

            // 위상정렬 + DP 누적
            while (que.isNotEmpty()) {
                val now = que.poll()
                for (next in graph[now]) {

                    //“now”까지 걸린 최장 시간 + “next” 건축 시간 비교하여 갱신
                    result[next] = maxOf(result[next], result[now] + buildTime[next])
                    inDegree[next]--

                    // 진입 차수 감소 후 0이면 큐에 추가
                    if (inDegree[next] == 0) {
                        que.add(next)
                    }
                }
            }

            for (i in 1..N) {
                println(result[i])
            }
        }
    }
}