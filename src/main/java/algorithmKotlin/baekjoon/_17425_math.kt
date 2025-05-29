package algorithmKotlin.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader

//https://www.acmicpc.net/problem/17425
//  1부터 N까지 모든 수의 ‘약수의 합’을 구해서 다시 모두 더하라”
class _17425_math {
}

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()
    val allInputs = IntArray(T)
    var maxN = 0
    val sb = StringBuilder()

    // T 만큼 반복
    repeat(T) { i ->
        val N = br.readLine().toInt()
        allInputs[i] = N
        // 배열의 최대값 갱신
        maxN = maxOf(N, maxN)
    }

    // 약수의 합을 담을 배열
    val f = LongArray(maxN + 1)
    // 약수라 가정하고 d를 추출
    for (d in 1..maxN) {
        // d 의 배수만큼 넘기면서 (step) 누적합 진행 및 배열 갱신
        for (m in d..maxN step d) {
            f[m] += d.toLong()
        }
    }

    println(f.contentToString())

    val g = LongArray(maxN + 1)
    for (i in 1..maxN) {
        g[i] = g[i - 1] + f[i]
    }

    println(g.contentToString())

    for (n in allInputs) {
        sb.append(g[n]).append('\n')
    }

    print(sb)
}