package algorithmKotlin.baekjoon

/*
// ==================== 기존 코드 (Java 스타일) ====================
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class _2133_dp {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val br = BufferedReader(InputStreamReader(System.`in`))
            val st = StringTokenizer(br.readLine())

            val N = st.nextToken().toInt()

            if (N % 2 == 1) {
                print(0)
                return;
            }

            val dp = IntArray(N + 1) { 0 }

            dp[0] = 1
            dp[1] = 1
            dp[2] = 3

            for (i in 4 .. N step 2) {

                var sum = 0

                // 첫항
                sum += 3 * dp[i - 2]

                for (k in i - 4 downTo 0 step 2) {
                    sum += 2 * dp[k]
                }

                dp[i] = sum.toInt()
            }

            print(dp[N])
        }
    }
}
*/

// ==================== 코틀린스럽게 리팩토링한 코드 ====================

/**
 * [사용된 코틀린 문법 정리]
 *
 * 1. readln() - 코틀린 1.6+에서 제공하는 표준 입력 함수. BufferedReader 대신 간단히 사용
 *
 * 2. toIntOrNull() ?: default - 안전한 형변환 + 엘비스 연산자
 *    - toIntOrNull(): 변환 실패시 null 반환 (예외 발생 X)
 *    - ?: (엘비스 연산자): null이면 우측 값 반환
 *
 * 3. 최상위 함수 (Top-level function)
 *    - class나 companion object 없이 바로 fun main() 선언 가능
 *    - 코틀린에서는 모든 것이 클래스 안에 있을 필요 없음
 *
 * 4. when 표현식 (Expression)
 *    - if-else 체인 대신 사용, 값을 반환할 수 있음
 *    - else -> 는 default 케이스
 *
 * 5. IntArray(size) { 초기화 람다 }
 *    - 인덱스를 받아 초기값을 결정하는 람다식
 *    - it은 현재 인덱스를 나타냄
 *
 * 6. also { } - 스코프 함수
 *    - 객체를 수정하고 그 객체 자체를 반환
 *    - it으로 객체 참조, 초기화 로직에 유용
 *
 * 7. (start..end step n) - Range with step
 *    - start부터 end까지 n씩 증가하며 반복
 *
 * 8. downTo - 역방향 Range
 *    - (4 downTo 0 step 2) = 4, 2, 0
 *
 * 9. sumOf { } - 컬렉션 합계를 람다로 계산
 *    - for문 + sum 대신 함수형으로 간결하게 표현
 *
 * 10. println() vs print()
 *     - println: 줄바꿈 포함
 *     - print: 줄바꿈 없음
 */

fun main() {
    val n = readln().toIntOrNull() ?: 0

    // 홀수면 타일을 채울 수 없음
    if (n % 2 == 1) {
        print(0)
        return
    }

    // dp 배열 초기화 (also를 사용해 초기값 설정)
    val dp = IntArray(n + 1).also {
        it[0] = 1   // 빈 타일 = 1가지
        if (n >= 2) it[2] = 3  // 3xN에서 N=2일 때 3가지
    }

    // 짝수 인덱스만 순회 (step 2 사용)
    (4..n step 2).forEach { i ->
        // 기본 케이스: 직전 2칸에서 3가지 패턴
        // 특수 케이스: 4칸 이상 떨어진 곳에서 오는 2가지 특수 패턴
        dp[i] = 3 * dp[i - 2] + (i - 4 downTo 0 step 2).sumOf { k -> 2 * dp[k] }
    }

    print(dp[n])
}