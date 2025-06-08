package company.pftc

import java.util.Date
import java.time.LocalDate
import java.time.ZoneId
import java.time.DayOfWeek

/**
 * executedDate : 대출 기표일 (Date)
 * loanMonth    : 대출 기간 (개월)
 * holidays     : 2025년 공휴일 리스트 (Date)
 *
 * 1) 기표일을 LocalDate로 변환
 * 2) plusMonths 로 같은 일자(없으면 말일)로 만기일 계산
 * 3) 주말(토·일) 또는 공휴일이면 다음날로 이동(영업일이 될 때까지)
 * 4) 결과 LocalDate → Date 로 변환하여 반환
 */
class Sol2 {

    fun calculateMaturityDate(
        executedDate: Date,
        loanMonth: Int,
        holidays: List<Date>
    ): Date {
        // 1) Zone 세팅 및 Date → LocalDate
        val zone = ZoneId.systemDefault()
        val startLocal = executedDate
            .toInstant()
            .atZone(zone)
            .toLocalDate()

        // 2) 같은 일자(말일 보정 포함)로 월 더하기
        var maturity = startLocal.plusMonths(loanMonth.toLong())

        // 3) 공휴일 리스트도 LocalDate 집합으로 변환
        val holidaySet = holidays
            .map { it.toInstant().atZone(zone).toLocalDate() }
            .toSet()

        // 4) 토/일 또는 공휴일이면 하루씩 뒤로 민다
        while (
            maturity.dayOfWeek == DayOfWeek.SATURDAY ||
            maturity.dayOfWeek == DayOfWeek.SUNDAY   ||
            holidaySet.contains(maturity)
        ) {
            maturity = maturity.plusDays(1)
        }

        // 5) LocalDate → Date 변환 (자정 기준)
        return Date.from(maturity.atStartOfDay(zone).toInstant())
    }
}