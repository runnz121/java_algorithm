package algorithmKotlin.baekjoon

/**
 * 1. memo = Array(N) { LongArray(21) { -1L } } 는 idx와 현재 값(num) 상태별 경우의 수를 저장하는 DP 테이블을 만든다.
 * 2. 두 번째 차원이 21인 이유는 문제 조건상 중간 계산값이 0~20으로 제한되기 때문이다.
 * 3. 모든 값을 -1로 초기화해 아직 계산되지 않은 상태를 구분한다.
 * 4. 재귀 중 동일한 (idx, num) 상태가 다시 나오면, 저장된 값을 바로 반환해 중복 계산을 방지한다.
 */
class _5557_dp {

    companion object {

        lateinit var dp : IntArray
        lateinit var memo: Array<LongArray>
        var N = 0
        var tt = 0

        @JvmStatic
        fun main(args: Array<String>) {

            var br = System.`in`.bufferedReader()
            N = br.readLine().toInt()
            val lists = br.readLine().split(' ')

            dp = IntArray(N) { lists[it].toInt() }
            tt = dp[N - 1]
            memo = Array(N) { LongArray(21) { -1L } }

            val target = recursive(0, dp[0])

            println(target)
        }

        fun recursive(idx : Int, num : Int) : Long{

            // 마지막 2번째 전거까지 계산
            if (idx == N- 2) {
                if (num == tt) {
                    return 1L
                }
                return 0L
            }

            // 메모이제이션
            val cached = memo[idx][num]
            if (cached != -1L) {
                return cached
            }

            val x = dp[idx + 1]
            var res = 0L

            // 두가지 케이스로 재귀 (덧셈 뺼샘)
            var plus = num + x
            if (plus <= 20) {
                res += recursive(idx + 1, plus)
            }

            val minus = num - x
            if (minus >= 0) {
                res += recursive(idx + 1, minus)
            }

            // 메모이제이션
            memo[idx][num] = res
            return res
        }
    }
}