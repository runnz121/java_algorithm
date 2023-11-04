package algorithmKotlin.sample

import java.time.DayOfWeek

fun main() {
    println("kotlin start")

    println(varFunc())

    println(valFunc())

    println(forFun())

    println(forFunStep())
}

fun varFunc() : Int {
    var res : Int = 5

    res = 7

    return res
}

fun valFunc() : Int {

    val res : Int = 6

    return res
}

fun forFun() : Int {
    var sum = 0
    for (i in 1..10) {
        sum += i
    }
    return sum
}

fun forFunStep() : Int {
    var step = 0

    for (i in 10 downTo 5 step 2) {
        step += i
    }
    return step
}

fun whenFun(dayOfWeek: DayOfWeek) {


}