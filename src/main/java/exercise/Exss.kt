package exercise

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class Exss {

    companion object {

        @JvmStatic
        fun main(args : Array<String>) {

            var br = BufferedReader(InputStreamReader(System.`in`))
            var st = StringTokenizer(br.readLine())

            var N = st.nextToken().toInt()

            var lists = mutableListOf<Pair<String, Int>>()

            repeat(N) {
                st = StringTokenizer(br.readLine())
                var name = st.nextToken()
                var score = st.nextToken().toInt()

                lists += Pair(name, score)
            }

            // 이게
            lists.sortWith(object : Comparator<Pair<String, Int>> {
                override fun compare(o1: Pair<String, Int>, o2: Pair<String, Int>): Int {
                    // score 비교 (내림차순)
                    if (o2.second.compareTo(o1.second) != 0) {
                        return o2.second.compareTo(o1.second)
                    }
                    return o1.first.compareTo(o2.first)
                }
            })

            // 이게 람다 버전
            lists.sortWith { o1, o2 ->
                // 1) score 내림차순
                val scoreCmp = o2.second.compareTo(o1.second)
                if (scoreCmp != 0) scoreCmp
                // 2) score 같으면 name 오름차순
                else o1.first.compareTo(o2.first)
            }

            // 이거랑 같다
            // 점수 내림차순 → 이름 오름차순
            lists.sortWith(
                compareByDescending<Pair<String, Int>> { it.second }
                    .thenBy { it.first }
            )

            println(lists)
        }
    }

}
