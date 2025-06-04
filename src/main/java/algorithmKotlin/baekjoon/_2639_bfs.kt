package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayDeque

class _2638_dfs {
}

val dx = intArrayOf(-1, 1, 0, 0)
val dy = intArrayOf(0, 0, -1, 1)

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    var N = st.nextToken().toInt()
    var M = st.nextToken().toInt()

    // graph 초기화
    var graph = Array(N) { IntArray(M) }

    for (i in 0 until N) {
        var st1 = StringTokenizer(br.readLine())
        for (j in 0 until M) {
            graph[i][j] = st1.nextToken().toInt()
            // 1인 경우 치즈임으로 이때 bfs 수행

            // bfs 수행하여 mark and delete 한다.
            bfs(i, j)
        }
    }
// graph 출력 메서드
//    println(graph.contentDeepToString())
}

fun bfs(i: Int, j: Int) {

    // TODO visited 체크 해야할까..?

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
        }
    }

}

// 두변이 노출되어있어서 다음 시간에 삭제 대상이 되는 것
fun isMark(i: Int, j: Int) {


}
