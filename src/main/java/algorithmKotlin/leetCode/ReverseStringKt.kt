package algorithmKotlin.leetCode

class ReverseStringKt {

    fun reverString(origin : CharArray): Unit {

        var start = 0
        var end = origin.size - 1

        while (start < end) {

            var tmp = origin[start]
            origin[start] = origin[end]
            origin[end] = tmp

            start ++
            end --
        }
    }

}

fun main(args: Array<String>) {
    val sample = charArrayOf('h','e','l','l','o')
    ReverseStringKt().reverString(sample)
    println(sample)
}