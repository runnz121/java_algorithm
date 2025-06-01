package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class _14891 {

}

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val gears = Array(4) { br.readLine() }

    val k = br.readLine().toInt()

    repeat(k) {
        val st = StringTokenizer(br.readLine())
        val tIdx = st.nextToken().toInt()
        val rotate = st.nextToken().toInt()

        // 시계, 반시계 방향을 담을 배열 초기화
        val dir = IntArray(4) { 0 }
        dir[tIdx - 1] = rotate

        // 1번 -> idx : 2
        // 2번 -> idx : 6 (1번 톱니) | 2 (3번 톱니)
        // 3번 -> idx : 6 (3번 톱니) | 2 (4번 톱니)
        // 4번 -> idx : 6 (3번 톱니)

        // 왼쪽으로 전파 검사 (tIdx−1) 인덱스에서 0 방향으로
        for (i in tIdx - 1 downTo 1) {
            // i번 톱니(인덱스 i)와 왼쪽 톱니(i-1)의 맞닿은 극을 비교
            // i번 톱니의 6번(idx 6), 왼쪽 톱니의 2번(idx 2)
            if (gears[i][6] != gears[i - 1][2]) {
                dir[i - 1] = -dir[i]
            } else {
                break
            }
        }
        // 오른쪽으로 전파 검사 (tIdx−1) 인덱스에서 3 방향으로
        for (i in tIdx - 1 until 3) {
            // i번 톱니의 2번(idx 2), 오른쪽 톱니(i+1)의 6번(idx 6)
            if (gears[i][2] != gears[i + 1][6]) {
                dir[i + 1] = -dir[i]
            } else {
                break
            }
        }

        // 계산된 dir[0..3] 만큼 한번에 회전
        for (i in 0..3) {
            gears[i] = when (dir[i]) {
                1  -> clockWise(gears[i])
                -1 -> antiClockWise(gears[i])
                else -> gears[i]
            }
        }
    }

    var answer = 0
    if (gears[0][0] == '1') answer += 1
    if (gears[1][0] == '1') answer += 2
    if (gears[2][0] == '1') answer += 4
    if (gears[3][0] == '1') answer += 8

    println(answer)
}


// 시계방향 이동
fun clockWise(t: String): String {

    val last = t.last()

    val rest = t.substring(0, t.length - 1)

    return "$last$rest"
}

// 반 시계 방향 이동
fun antiClockWise(t: String): String {

    val first = t.first()

    val rest = t.substring(1, t.length)

    return "$rest$first"
}