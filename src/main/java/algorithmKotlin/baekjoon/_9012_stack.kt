package algorithmKotlin.baekjoon

fun main() = with(System.`in`.bufferedReader()) {

    repeat(readLine().toInt()) {

        val result = readLine().fold(0) { count, c ->
            if (count < 0) return@repeat println("NO")
            if (c == '(') count + 1 else count - 1
        }

        println(if (result == 0) "YES" else "NO")
    }
}

/*
// 리펙토링 전 코드
fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()

    repeat(n) {

        val bracket = readLine().toCharArray()

        var stack = 0

        for (i in 0 until bracket.size) {

            if (bracket[i] == '(') {
                stack += 1
            }

            else {
                if (stack - 1 < 0) {
                    println("NO")
                    return@repeat // repeat만 종료
                }
                stack -= 1
            }
        }

        if (stack == 0) {
            println("YES")
        }
        else {
            println("NO")
        }
    }
}
*/
