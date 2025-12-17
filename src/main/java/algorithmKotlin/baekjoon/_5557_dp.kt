package algorithmKotlin.baekjoon

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

            memo[idx][num] = res
            return res
        }
    }
}