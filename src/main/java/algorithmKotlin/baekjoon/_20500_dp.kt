package algorithmKotlin.baekjoon

/**
 * 1. “1 또는 5” 두 값만 반복해서 붙이기 때문에, 합을 3으로 나눈 나머지(0,1,2)만 추적해도 충분
 * 2. 마지막 자리 고정 15의 배수 판별을 위해 마지막 자리는 ‘5’로 고정하고, 앞만 DP 처리
 *
 */

class _20500_dp {

    companion object {


        @JvmStatic
        fun main(args: Array<String>) {

            val MOD = 1000000007
            val br = System.`in`.bufferedReader()
            val input = br.readLine().toInt()

            // dp 배열 초기화
            // dp[r]: 현재까지 합의 3으로 나눈 나머지가 r인 경우의 수
            val dp = LongArray(3) { 0L }
            dp[0] = 1L

            // 앞의 N-1 자리만 1~5를 붙인다.
            for (i in 1 until input) {
                val next = LongArray(3) { 0L }

                for (r in 0..2) {
                    val cnt = dp[r]

                    if (cnt == 0L) {
                        continue
                    }

                    /**
                     * 이전까지 합을 3으로 나눈 나머지별로 세어 놓은 경우의 수”를 기반으로,
                     * 다음 자리에 ‘1’ 또는 ‘5’를 붙였을 때 새로 생기는 합의 나머지를 계산해 next 배열에 누적함
                     */

                    // 1을 붙인 경우
                    next[(r + 1) % 3] = (next[(r + 1) % 3] + cnt) % MOD
                    // 5를 붙인 경우
                    next[(r + 2) % 3] = (next[(r + 2) % 3] + cnt) % MOD
                }

                for (r in 0..2) {
                    dp[r] = next[r]
                }
            }

            // 1 자리는 15가 나올 수 없음
            if (input < 2) {
                println(0)
                return
            }

            // 2 자리는 1개
            if (input == 2) {
                println(1)
                return
            }

            // 마지막 N번째 자리는 항상 5를 붙인다 ⇒ 5%3 = 2
            // (앞까지 합%3 == 1) 이어야 (합 + 2)%3 == 0 ⇒ 15의 배수
            println(dp[1])
        }
    }
}