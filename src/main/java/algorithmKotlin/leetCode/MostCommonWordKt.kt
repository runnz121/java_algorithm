package algorithmKotlin.leetCode

import java.util.*

class MostCommonWordKt {

    fun mostCommonWord(paragraph: String, banned: Array<String>): String {

        var counts: MutableMap<String, Int> = mutableMapOf()

        var words = paragraph.replace("\\W+".toRegex(), " ").lowercase(Locale.getDefault()).trim().split(" ")

        for (w in words)  {
            if (!banned.contains(w)) {
                counts[w] = counts.getOrDefault(w, 0) + 1
            }
        }
        return counts.maxBy { it.value }!!.key
    }
}

fun main(args: Array<String>) {

    val paragraph = "a, a, a, a, b,b,b,c, c"
    val banned = arrayOf("a")

    val res = MostCommonWordKt().mostCommonWord(paragraph, banned)
    println(res)
}
