package exercise

import java.io.BufferedReader
import java.io.InputStreamReader

//7
//1 6
//6 3
//3 5
//4 1
//2 4
//4 7
class Ex1 {


    // 1. 주어진 문자열이 반대로 뒤집어도 같은 글자인지 찾는 알고리즘을 구현하라.
    fun isPalindrome(s: String): Boolean {
        return s == s.reversed()
    }

    // 2. 숫자가 들어있는 배열이 있을 때, 중복되는 숫자가 있는지 찾는 알고리즘을 구현하라.
    fun hasDuplicates(arr: IntArray): Boolean {
        val seen = mutableSetOf<Int>()
        for (num in arr) {
            if (!seen.add(num)) return true
        }
        return false
    }

    // 3. Factorial을 구하는 함수를 재귀로 짜보세요.
    fun factorial(n: Int): Long {
        if (n <= 1) return 1
        return n * factorial(n - 1)
    }

    // 4. 대각선이 고정인 행렬인 pivotal 3x3, 4x4를 뒤집는 로직을 재귀로 짜보세요.
    fun flipDiagonalRecursive(matrix: Array<IntArray>, start: Int = 0) {
        val n = matrix.size
        if (start >= n / 2) return

        val tmp = matrix[start][start]
        matrix[start][start] = matrix[n - 1 - start][n - 1 - start]
        matrix[n - 1 - start][n - 1 - start] = tmp

        flipDiagonalRecursive(matrix, start + 1)
    }

    // 5.atoi 함수를 구현하세요.
    fun myAtoi(str: String): Int {
        val s = str.trim()
        if (s.isEmpty()) return 0

        var sign = 1
        var index = 0
        var result = 0L

        if (s[index] == '+' || s[index] == '-') {
            sign = if (s[index] == '-') -1 else 1
            index++
        }

        while (index < s.length && s[index].isDigit()) {
            result = result * 10 + (s[index] - '0')
            if (result * sign > Int.MAX_VALUE) return Int.MAX_VALUE
            if (result * sign < Int.MIN_VALUE) return Int.MIN_VALUE
            index++
        }

        return (result * sign).toInt()
    }

    // 6. String으로 있는 출근 시간/퇴근 시간의 나열에서 내림차순으로 정렬하는 함수를 구현하세요.
    fun sortTimesDescending(times: List<String>): List<String> {
        return times.sortedDescending()
    }

    // 7. 10진수로 입력받은 수를 입력받은 진수로 변환하는 함수를 구현하세요.
    fun convertToBase(num: Int, base: Int): String {
        if (num == 0) return "0"
        val digits = "0123456789ABCDEF"
        var n = num
        var result = ""

        while (n > 0) {
            result = digits[n % base] + result
            n /= base
        }
        return result
    }

    // 8. Binary Search(이진 탐색)를 구현해보세요.
    fun binarySearch(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1

        while (left <= right) {
            val mid = (left + right) / 2
            when {
                arr[mid] == target -> return mid
                arr[mid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return -1
    }

    // 9.(트리 형태의 숫자를 주고) 주어진 모양의 트리를 정의하고 DFS 알고리즘을 구현하라.
    fun treeDFSExample() {
        val tree = mutableMapOf<Int, MutableList<Int>>()
        val visited = mutableSetOf<Int>()

        fun addEdge(parent: Int, child: Int) {
            tree.computeIfAbsent(parent) { mutableListOf() }.add(child)
            tree.computeIfAbsent(child) { mutableListOf() }.add(parent)
        }

        fun dfs(node: Int) {
            if (!visited.add(node)) return
            println("Visit: $node")
            for (neighbor in tree[node] ?: emptyList()) {
                dfs(neighbor)
            }
        }

        // Example: Tree edges
        addEdge(1, 2)
        addEdge(1, 3)
        addEdge(2, 4)
        addEdge(2, 5)
        addEdge(3, 6)
        addEdge(3, 7)

        dfs(1)
    }

    // 10.(1이상 100이하 숫자 N개가 주어 지고) 이 N개의 수의 최소 공배수를 구하라.
    fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    fun lcm(a : Int, b: Int): Int {
        return a * b / gcd(a, b)
    }

    fun findLCMOfList(numbers: List<Int>): Int {
        return numbers.reduce { acc, num -> lcm(acc, num) }
    }

    // 11. 에리스토테네스의 채
    /**
     * 1. 2부터 n까지 숫자를 나열하고, 초기에 모두 ‘소수(prime)’로 표시한다.
     * 2. 가장 작은 표시된 소수 p(처음엔 2)를 선택해, p의 제곱(p²)부터 시작해 p의 배수들을 모두 ‘합성수(composite)’로 지운다.
     * 3. 그다음 남은 가장 작은 소수로 넘어가 같은 과정을 반복(p → 다음 소수 → 배수 지우기)하며, p²이 n보다 커질 때까지 진행한다.
     * 4. 최종적으로 지워지지 않은 숫자들이 모두 2~n 구간의 소수가 된다.
     */
    fun sieve(n: Int): List<Int> {
        // 1) 0..n 범위의 Boolean 배열을 만들어, 모두 'true'(소수로 가정)로 초기화
        val isPrime = BooleanArray(n + 1) { true }

        // 2) 결과를 담을 가변 리스트 선언
        val primes = mutableListOf<Int>()

        // 3) 2부터 n까지 차례로 검사
        for (i in 2..n) {
            // 4) 아직 소수로 남아 있다면
            if (isPrime[i]) {
                // 5) i는 소수이므로 결과 리스트에 추가
                primes += i

                // 6) i의 제곱(i*i)부터 시작해서 i만큼 건너뛰며 (p 이전 배수(2p, 3p …)는 이미 더 작은 소수에서 걸러냈으므로)
                //    그 배수들은 모두 합성수이므로 'false'로 표시
                for (j in i * i..n step i) {
                    isPrime[j] = false
                }
            }
        }

        // 7) 최종적으로 모은 소수 리스트를 반환
        return primes
    }

    fun main() {
        println(sieve(100))  // 2, 3, 5, 7, 11, …
    }
}