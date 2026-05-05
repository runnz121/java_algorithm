package algorithmKotlin.baekjoon

/*
// 기존 컨버팅 코드
import java.io.BufferedReader
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {

    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()

    val stack = ArrayDeque<Int>()

    run {
        repeat(n) {

            val s = readLine() ?: return@run // break

            if (s.length > 1) {

                val splits = s.split(" ")
                val s1 = splits[1]
                stack.add(s1.toInt())
                return@repeat
            }

            val x = s.toInt()

            if (x == 2) {
                if (stack.isEmpty()) {
                    println(-1)
                }
                else {
                    println(stack.removeLast())
                }
                return@repeat
            }

            if (x == 3) {
                if (stack.isEmpty()) {
                    println(0)
                }
                else {
                    println(stack.size)
                }
                return@repeat
            }

            if (x == 4) {
                if (stack.isEmpty()) {
                    println(1)
                }
                else {
                    println(0)
                }
                return@repeat
            }

            if (x == 5) {
                if (stack.isEmpty()) {
                    println(-1)
                }
                else {
                    println(stack.last())
                }
            }
        }
    }

}
*/

// 코틀린스럽게 리팩토링한 코드
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().trim().toInt()
    val stack = ArrayDeque<Int>()
    val sb = StringBuilder()

    repeat(n) {
        val line = readLine() ?: return@with

        val parts = line.split(" ")
        when (parts[0].toInt()) {
            1 -> stack.addLast(parts[1].toInt())
            2 -> sb.appendLine(if (stack.isEmpty()) -1 else stack.removeLast())
            3 -> sb.appendLine(stack.size)
            4 -> sb.appendLine(if (stack.isEmpty()) 1 else 0)
            5 -> sb.appendLine(if (stack.isEmpty()) -1 else stack.last())
        }
    }
    print(sb)
}
