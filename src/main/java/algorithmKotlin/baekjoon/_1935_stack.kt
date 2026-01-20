package algorithmKotlin.baekjoon

/**
 * [ fun main() = ... ]
 * - 코틀린은 클래스 없이 top-level function으로 main 작성 가능
 * - '=' 사용: 함수 본문이 단일 표현식일 때 중괄호 {} 대신 사용 (Single Expression Function)
 *
 * [ with(receiver) { ... } ]
 * - 스코프 함수: receiver 객체를 this로 바인딩
 * - 블록 내에서 this.readLine() 대신 readLine()으로 직접 호출 가능
 * - 마지막 표현식이 반환값이 됨
 */
fun main() = with(System.`in`.bufferedReader()) {

    // val: 불변 변수 (Java의 final), var: 가변 변수
    val n = readLine().toInt()
    val expression = readLine()

    /**
     * [ DoubleArray(size) { 람다 } ]
     * - 초기화 람다: 각 인덱스에 대해 람다가 실행되어 값 설정
     * - it: 람다의 암시적 단일 파라미터 (여기서는 인덱스, 사용 안함)
     * - 아래는 n번 readLine()을 호출하여 배열 초기화
     */
    val values = DoubleArray(n) { readLine().toDouble() }

    // Kotlin 표준 라이브러리의 ArrayDeque (Java import 불필요)
    val stack = ArrayDeque<Double>()

    /**
     * [ collection.forEach { } ]
     * - 컬렉션/문자열 순회하는 고차함수
     * - 람다의 파라미터가 하나면 'it'으로 암시적 사용 가능
     * - 여기서는 명시적으로 'ch'로 이름 지정
     */
    expression.forEach { ch ->

        /**
         * [ when (값) { 조건 -> 실행 } ]
         * - Java switch의 강화 버전
         * - 'in A..Z': 범위 연산자, A부터 Z까지 포함 여부 검사
         * - '+', '-', '*', '/': 여러 값을 콤마로 나열하여 OR 조건
         */
        when (ch) {
            in 'A'..'Z' -> stack.addLast(values[ch - 'A'])
            '+', '-', '*', '/' -> {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.addLast(calculate(a, b, ch))
            }
        }
    }

    // String.format() 대신 "패턴".format(값) 확장함수 사용
    println("%.2f".format(stack.removeLast()))
}

/**
 * [ private fun name(): Type = expression ]
 * - private: 같은 파일 내에서만 접근 가능
 * - Single Expression Function: 본문이 단일 표현식이면 '=' 사용
 * - 반환 타입 추론 가능하지만 명시적으로 작성하면 가독성 향상
 *
 * [ when as Expression ]
 * - when을 표현식으로 사용하여 값을 직접 반환
 * - else: 모든 케이스를 커버해야 할 때 필수 (여기서는 '/' 처리)
 */
private fun calculate(a: Double, b: Double, op: Char): Double = when (op) {
    '+' -> a + b
    '-' -> a - b
    '*' -> a * b
    else -> a / b
}

/*
// 기존 코드
import java.util.ArrayDeque
import java.util.Deque

class _1935_stack {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            var br = System.`in`.bufferedReader()

            // 개수
            var n = br.readLine().toInt()

            // 연산
            val operation = br.readLine().toCharArray()
            val size = operation.size

            // 알파뱃 입력 받기
            val alphaArr = DoubleArray(n)
            for (i in 0 until n) {
                alphaArr[i] = br.readLine().toDouble()
            }

            var stack: Deque<Double> = ArrayDeque()

            for (i in 0 until size) {
                var ch = operation[i]
                when {
                    // 숫자일 경우
                    ch in 'A'..'Z' -> {
                        stack.push(alphaArr[ch - 'A'])
                    }
                    // 연산자일 경우
                    ch in setOf('*', '-', '+', '/') -> {

                        val back = stack.pop()
                        val front = stack.pop()

                        val result = operationMethod(front, back, ch)
                        stack.push(result)
                    }
                }
            }

            println("%.2f".format(stack.pop()))
        }

        fun operationMethod(front: Double,
                            back: Double,
                            operation: Char): Double {

            if (operation == '+') {
                return front + back
            }

            if (operation == '-') {
                return front - back
            }

            if (operation == '*') {
                return front * back
            }

            return front / back
        }
    }
}
*/