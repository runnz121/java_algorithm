package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * 입려값
 *
 * #O########
 * OOO#######
 * #O########
 * ####OO####
 * ###O##O###
 * ####OO####
 * ##########
 * ########O#
 * #######OOO
 * ########O#
 *
 * 풀이
 * 1. 첫 줄에서 스위치를 조작할 하나를 선택한다.
 * 2. 다음 줄은 윗 줄의 스위치를 모두 끄기 위한 값을 파악
 * 3. 10줄까지 모두 수행 후 마지막 줄이 모두 꺼질때 누른 횟수를 선택
 * 4. 최소값 반환
 *
 */
class _149339_bfs {

    companion object {

        // 4 방향 + 중앙 까지 포함
        val dx = intArrayOf(0, -1, 1, 0, 0)
        val dy = intArrayOf(0, 0, 0, -1, 1)
        var answer = Integer.MAX_VALUE

        @JvmStatic
        fun main(args: Array<String>) {

            var br = BufferedReader(InputStreamReader(System.`in`))

            // 위의 입력값을 한번에 받으면서 맵 초기화 하는 한줄
            val map = Array(10) { i ->
                br.readLine().map { if (it == '#') 0 else 1 }.toIntArray()
            }

            // 전수 조사 하기 (shl : shift left : 왼쪽으로 밀기
            // 1 shl 10 == 1 << 10 == 2^10 == 0 until 1024 와 같다)
            for (mask in 0 until (1 shl 10)) {

                // deppCopy
                val copyMap = Array(10) { i -> map[i].clone() }
                var press = Array(10) {
                    IntArray(10)
                }

                // 1. 첫 줄 스위치 누르기
                for (idx in 0 until 10) {
                    // shr : shift right 오른쪽으로 밀기
                    // 오른쪽으로 밀면서 맨 오른쪽의 비트의 값을 비교한다 (idx = 몇번째)
                    /**
                     * 예시 mask = 13 (1101₂), idx = 2
                     * mask shr 2    : 1101₂ → 0011₂
                     * (0011₂) and 1: 0011₂ & 0001₂ = 0001₂ → 1
                     * → idx=2 비트(원래 1101₂의 '1')가 1 이었음을 확인
                     */
                    if ((mask shr idx) and 1 == 1) {
                        press[0][idx] = 1
                        toggleSwitch(copyMap, 0, idx)
                    }
                }

                // 2. 다음 줄 모두 끄기 (첫줄 제외)
                for (i in 1 until 10) {
                    for (j in 0 until 10) {
                        if (copyMap[i - 1][j] == 1) {
                            press[i][j] = 1
                            toggleSwitch(copyMap, i, j)
                        }
                    }
                }

                // 3. 마지막줄 모두 꺼졌는지 확인
                if ((0 until 10).all { j -> copyMap[9][j] == 0 }) {
                    val cnt = press.sumOf { row -> row.sum() }
                    answer = Math.min(answer, cnt)
                }
            }

            if (answer == Integer.MAX_VALUE) {
                println(-1)
            } else {
                println(answer)
            }

            // 2차원 배열 다보기
//        println(map.contentDeepToString())
        }

//        fun toggleSwitch(map: Array<IntArray>, i: Int, j: Int) {
//
//            for (t in 0 until 5) {
//                var nx = i + dx[t]
//                var ny = j + dy[t]
//
//                if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10) {
//                    continue
//                }
//                if (map[nx][ny] == 0) {
//                    map[nx][ny] = 1
//                } else {
//                    map[nx][ny] = 0
//                }
//            }
//        }

        // 코틀린 스러운 버전
        fun toggleSwitch(board: Array<IntArray>, i: Int, j: Int) {
            dx.indices
                .map { t -> i + dx[t] to j + dy[t] }          // (nx, ny) 쌍 리스트 생성
                .filter { (nx, ny) ->                           // 유효 범위만 필터
                    nx in board.indices && ny in board[nx].indices
                }
                .forEach { (nx, ny) ->
                    board[nx][ny] = 1 - board[nx][ny]          // 0 ↔ 1 반전
                }
        }
    }
}

