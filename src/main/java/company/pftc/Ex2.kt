package company.pftc

import java.text.SimpleDateFormat
import java.util.*;
import java.time.*;

class Ex2 {

    fun calculateMaturityDate(executedDate: Date, loanMonth: Int, holiDays: List<Date>): Date {

        var zone = ZoneId.systemDefault()

        var startDate = executedDate.toInstant().atZone(zone).toLocalDate()

        var maturityDate = startDate.plusMonths(loanMonth.toLong())

        var setHolidays = holiDays.map { it.toInstant().atZone(zone).toLocalDate() }.toSet()

        while (
            maturityDate.dayOfWeek == DayOfWeek.SATURDAY ||
            maturityDate.dayOfWeek == DayOfWeek.SUNDAY ||
            setHolidays.contains(maturityDate)
        ) {
            maturityDate.plusDays(1)
        }

        return Date.from(maturityDate.atStartOfDay(zone).toInstant())
    }
}

fun main() {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul")

    // 대출 실행일: 2025-06-09
    val executedDate = sdf.parse("2025-06-09")

    // 대출 기간: 1개월
    val loanMonth = 1

    // 예시 휴일 목록
    val holiDays = listOf(
        sdf.parse("2025-07-17"),  // 예: 한국 제헌절
        sdf.parse("2025-07-20")   // 예시 추가 휴일
    )

    var ex2 = Ex2()

    val maturityDate = ex2.calculateMaturityDate(executedDate, loanMonth, holiDays)
    println("조정된 만기일: ${sdf.format(maturityDate)}")
}