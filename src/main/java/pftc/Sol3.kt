package pftc

import java.util.Date
import java.time.LocalDate
import java.time.ZoneId
import java.time.DayOfWeek
import java.time.temporal.ChronoUnit

class Sol3 {

    data class RepaymentSchedule(
        val seq: Int,
        val repaymentDate: Date,
        val interestAmount: Int
    )

    fun calculateMonthlyRepaymentSchedule(
        executedDate: Date,
        loanMonth: Int,
        loanAmount: Int,
        interestRate: Double,
        holidays: List<Date>
    ): List<RepaymentSchedule> {
        val zone       = ZoneId.systemDefault()
        // 1) 기표일 → LocalDate
        val startLocal = executedDate
            .toInstant().atZone(zone).toLocalDate()
        // 2) 공휴일도 LocalDate 셋으로
        val holidaySet = holidays
            .map { it.toInstant().atZone(zone).toLocalDate() }
            .toSet()

        val schedules = mutableListOf<RepaymentSchedule>()
        // 다음 이자 계산의 시작 기준이 될 “이전 상환일”
        var prevLocal = startLocal

        for (seq in 1..loanMonth) {
            // ────────────── 핵심 변경 ──────────────
            // 매회차마다 '기표일 + seq 개월' 을 기준으로 잡는다
            var candidate = startLocal.plusMonths(seq.toLong())
            // ──────────────────────────────────────

            // 주말/공휴일이면 다음 영업일까지 민다
            while (
                candidate.dayOfWeek == DayOfWeek.SATURDAY ||
                candidate.dayOfWeek == DayOfWeek.SUNDAY   ||
                candidate in holidaySet
            ) {
                candidate = candidate.plusDays(1)
            }

            // 이자 계산용 일수: 초일산입·말일불산입
            val days = ChronoUnit.DAYS.between(prevLocal, candidate).toInt()

            val interest = (loanAmount.toDouble()
                    * (interestRate / 100.0)
                    * (days / 365.0)
                    ).toInt() // 소수점 이하 버림

            // 결과로 담기
            schedules += RepaymentSchedule(
                seq,
                Date.from(candidate.atStartOfDay(zone).toInstant()),
                interest
            )

            // 다음 회차 계산을 위해 prevLocal 갱신
            prevLocal = candidate
        }

        return schedules
    }
}