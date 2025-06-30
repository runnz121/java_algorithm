package algorithmKotlin.baekjoon

class _2636_bfs {

    companion object {

        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)
        var visited = emptyArray<BooleanArray>()
        var N: Int = 0
        var M: Int = 0
        var graph = emptyArray<IntArray>()
        var time: Int = 0
        var last: Int = 0

        @JvmStatic
        fun main(args: Array<String>) {

            val br = System.`in`.bufferedReader()
            val n = br.readLine().split(' ')

            N = n[0].toInt()
            M = n[1].toInt()

            graph = Array(N) {IntArray(M)}

            repeat(N) { i ->
                val k = br.readLine().split(' ')
                repeat(M) {
                    j ->
                    graph[i][j] = k[j].toInt()
                }
            }

            while (true) {

                // 외부 공기
                visited = Array(N) { BooleanArray(M) }

                // 외부 공기 처리
                bfs(0, 0)

                // 삭제할 것 마킹 처리
                val doMarkedLists = doMark()

                if (doMelt(doMarkedLists)) {
                    break
                }

                time += 1
                last = doMarkedLists.size
            }

            println(time)
            println(last)
        }


        fun bfs(i: Int, j: Int) {

            visited[i][j] = true

            val que = ArrayDeque<Pair<Int, Int>>()
            que.addLast(Pair(i, j))

            while (que.isEmpty() == false) {

                val out = que.removeFirst();
                var idy = out.first
                var idx = out.second

                for (i in 0..3) {
                    var yy = idy + dy[i]
                    var xx = idx + dx[i]

                    if (yy < 0 || xx < 0 || yy >= N || xx >= M) {
                        continue
                    }

                    // 치즈가 아니고 아직 방문하지 않은 것 그리고 0,0 부터 시작함으로 치즈로 둘러쌓인곳은 침투하지 못한다.
                    if (visited[yy][xx] == false && graph[yy][xx] == 0) {
                        visited[yy][xx] = true
                        que.addLast(yy to xx)
                    }
                }
            }
        }

        fun doMark(): List<Pair<Int, Int>> {

            var toMelt = mutableListOf<Pair<Int, Int>>()

            for (i in 0 until N) {
                for (j in 0 until M) {

                    if (graph[i][j] == 1) {

                        for (x in 0..3) {
                            var newY = i + dy[x]
                            var newX = j + dx[x]

                            if (newY < 0 || newX < 0 || newY >= N || newX >= M) {
                                continue
                            }

                            // 공기와 맞닿아있다면 (한면이라도)
                            if (graph[newY][newX] == 0 && visited[newY][newX]) {
                                toMelt.add(i to j)
                                // 중복으로 들어가는 경우를 방지
                                break
                            }
                        }
                    }
                }
            }

            return toMelt
        }

        fun doMelt(toMeltLists: List<Pair<Int, Int>>): Boolean {

            if (toMeltLists.isEmpty()) {
                return true;
            }

            for ((i, j) in toMeltLists) {
                graph[i][j] = 0
            }

            return false;
        }
    }
}