package company.pftc

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit

class Ex3 {

    data class RepaymentSchedule(
        val seq: Int,
        val repaymentDate: Date,
        val interestAmount: Int
    )

    fun calculateMonthlyRepaymentSchedule(
        executedDate: Date,
        loanMoth: Int,
        loanAmount: Int,
        interestRate: Double,
        holyDays: List<Date>
    ): List<RepaymentSchedule> {

        var zone = ZoneId.systemDefault()

        var startLocalDate = executedDate.toInstant().atZone(zone).toLocalDate()

        var setHoliyDays = holyDays.map{it.toInstant().atZone(zone).toLocalDate()}.toSet()

        var schdules = mutableListOf<RepaymentSchedule>()

        var prevDate = startLocalDate

        for (i in 1..loanMoth) {

            var targetDate = prevDate.plusMonths(i.toLong())

            while (
                targetDate.dayOfWeek == DayOfWeek.SATURDAY ||
                targetDate.dayOfWeek == DayOfWeek.SUNDAY ||
                setHoliyDays.contains(targetDate)
            ) {
                targetDate = targetDate.plusDays(1)
            }

            var days = ChronoUnit.DAYS.between(prevDate, targetDate).toInt()

            var interest = (loanAmount * (days.toDouble() / 365.0) * (interestRate / 100.0)).toInt()

            schdules += RepaymentSchedule(i, Date.from(targetDate.atStartOfDay(zone).toInstant()), interest)

            prevDate = targetDate
        }

        return schdules
    }
}