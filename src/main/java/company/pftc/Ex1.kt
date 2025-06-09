package company.pftc

import java.text.SimpleDateFormat
import java.util.*

class Ex1 {

    fun calculateInterest(executedDate: Date, maturityDate: Date, loanAmount: Int, interestRate: Double): Int {

        var msPerDay = 1000 * 60 * 60 * 24

        var days = ((maturityDate.time - executedDate.time) / msPerDay).toInt()

        var interest = loanAmount.toDouble() * (interestRate / 100.0) * (days.toDouble() / 365.0)

        return interest.toInt()
    }
}

fun main() {

    val ex1 = Ex1()

    val sdf = SimpleDateFormat("yyyy-MM-dd")

    val executedDate: Date = sdf.parse("2024-06-01")
    val maturityDate: Date = sdf.parse("2025-06-01")

    val loanAmount = 1_000_000   // 100만원
    val interestRate = 5.5       // 연이율 5.5%

    val interest = ex1.calculateInterest(executedDate, maturityDate, loanAmount, interestRate)

    println("계산된 이자: $interest 원")
}