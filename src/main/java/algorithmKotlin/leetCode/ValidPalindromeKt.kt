package algorithmKotlin.leetCode

import java.util.*

class ValidPalindromeKt {
    fun isPalindrome(s: String): Boolean {

        var modified = s.lowercase(Locale.getDefault())
        // 영어 숫자 아닌거 제외
        val regex = "[0-9a-z]+".toRegex()

        var regexString = ""

        for (k in 0 until modified.length) {
            if (modified[k].toString().matches(regex)) {
                regexString += modified[k]
            }
        }

        var start = 0
        var end = regexString.length - 1

        while(true) {
            if (start > end) {
                return true
            }
            if (regexString[start] != regexString[end]) {
                return false
            }
            start += 1
            end -= 1
        }
    }
}

fun main(args: Array<String>) {
    val sample = "A man, a plan, a canal: Panama"
    val res = ValidPalindromeKt().isPalindrome(sample)
    println(res)
}