package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class _1946_sort {

//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//
//            var br = BufferedReader(InputStreamReader(System.`in`))
//            var st = StringTokenizer(br.readLine())
//
//            var T = st.nextToken().toInt()
//
//            repeat(T) {
//
//                st = StringTokenizer(br.readLine())
//                var N = st.nextToken().toInt()
//
//                var lists = mutableListOf<Pair<Int, Int>>()
//                for (i in 0 until N) {
//
//                    st = StringTokenizer(br.readLine())
//
//                    var a = st.nextToken().toInt()
//                    var b = st.nextToken().toInt()
//
//                    lists.add(Pair(a, b))
//                }
//
//                // 최초 한명은 반드시 뽑힌다.
//                var answer = 1
//
//                // 서류 순위로 오름차순 정렬
//                lists.sortBy { it.first }
//                var maxInterview = lists.get(0).second
//
//                for ((a, interviewRank) in lists) {
//                    if (maxInterview > interviewRank) {
//                        maxInterview = interviewRank
//                        answer ++
//                    }
//                }
//
//                println(answer)
//            }
//        }
//    }

    // 코틀린스러운 버전
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val br = System.`in`.bufferedReader()
            val sb = StringBuilder()

            // 테스트 케이스 수
            val t = br.readLine()!!.toInt()
            repeat(t) {
                // 지원자 수
                val n = br.readLine()!!.toInt()

                // 지원자 리스트를 생성하고 서류 순위 기준으로 정렬
                val applicants = List(n) {
                    br.readLine()!!.split(' ').let { (doc, interview) ->
                        doc.toInt() to interview.toInt()
                    }
                }.sortedBy { it.first }

                // 첫 지원자는 무조건 선발
                var count = 1
                var bestInterview = applicants.first().second

                // 나머지 지원자 중 면접 순위가 더 좋은 경우만 카운트
                for ((_, interview) in applicants.drop(1)) {
                    if (interview < bestInterview) {
                        count++
                        bestInterview = interview
                    }
                }

                sb.appendLine(count)
            }

            print(sb)
        }
    }
}