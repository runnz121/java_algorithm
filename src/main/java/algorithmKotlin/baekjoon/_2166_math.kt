package algorithmKotlin.baekjoon

import kotlin.math.abs
import kotlin.math.atan2

/**
 * 풀이 방법
 *
 * 1. 다격헝 구하기 위한 공식을 사용 (신발끈 공식)
 *
 * 공식은 다음과 같다.
 * 1. 각 점의 합을 통해 중심점을 구한다
 * 2. atan2 각도로 계산후 해당 좌표를 정렬한다 (반시계, 혹은 시계 방향) -> 해당 방향으로 정렬해야지 shoelace 공식을 적용할 수 있다.
 *
 *
 * 해당 코드는 꼭지점이 시계 혹은 반시계 방향으로 정렬되어 내려오지 않을 수 있는 케이스를 고려함
 */
class _2166_math {

    data class Point(val x : Double, val y : Double)

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            // 정렬까지 적용됨 -> 문제에서는 이미 정렬된 상태로 꼭지점 정보가 들어옴으로 재정렬시 인접정렬 순서가 보장되지 않음

//            val br = System.`in`.bufferedReader()
//            val t = br.readLine().toInt()
//
//            val points = mutableListOf<Point>()
//
//            repeat(t) {
//
//               val split = br.readLine().split(' ')
//
//                points += Point(split[0].toDouble(), split[1].toDouble())
//            }
//
//            // 중심점 계산
//            val centroid = points
//                // 리스트 좌표 전체합
//                .fold(0.0 to 0.0) { (sx, sy), p -> (sx + p.x) to (sy + p.y) }
//                // 위의 누적합 값을 점 개수로 나눔
//                .let { (sx, sy) -> Point(sx / points.size, sy / points.size) }
//
//            // atan2 각도 계산 후 정렬 (반시계 방향)
//            val sorted = points.sortedBy { p -> atan2(p.y - centroid.y, p.x - centroid.x) }
//
//            // shoelace 공식 적용
//            var sum = 0.0
//            for (i in sorted.indices) {
//                val j = (i + 1 ) % sorted.size
//                sum += sorted[i].x * sorted[j].y - sorted[j].x * sorted[i].y
//            }
//            val d = abs(sum) / 2.0
//
//            println(String.format("%.1f", d))

            // 정렬 제거 로직
            val br = System.`in`.bufferedReader()
            val N = br.readLine().toInt()
            val pts = List(N) {
                br.readLine().split(' ').let { (x, y) -> x.toLong() to y.toLong() }
            }
            // 첫 점을 뒤에 붙여서 순환 계산
            val points = pts + pts.first()

            var sum1 = 0L
            var sum2 = 0L
            for (i in 0 until N) {
                val (x1, y1) = points[i]
                val (x2, y2) = points[i + 1]
                sum1 += x1 * y2
                sum2 += y1 * x2
            }

            val area = kotlin.math.abs(sum1 - sum2) / 2.0
            // 소수 첫째 자리까지 반올림 출력
            println(String.format("%.1f", area))
        }
    }
}