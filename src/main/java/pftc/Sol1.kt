package pftc

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date

/**
 * executedDate: 대출 실행일 ("yyyy-MM-dd")
 * maturityDate: 만기일 ("yyyy-MM-dd")
 * loanAmount: 대출 금액 (정수)
 * interestRate: 연 이자율 (%) 예: 3.5 → 3.5%
 *
 * 일수는 “초일산입·말일불산입” (시작일 포함, 종료일 미포함)으로 계산하며,
 * 이자 = 대출액 × (이자율/100) × (일수/365) → 소수점 이하는 버림
 */

class Sol1 {

    fun calculateInterest(
        executedDate: Date,
        maturityDate: Date,
        loanAmount: Int,
        interestRate: Double
    ): Int {
        val msPerDay = 1000L * 60 * 60 * 24

        // 시작일 포함, 종료일 미포함 방식으로 일수 계산
        val days = ((maturityDate.time - executedDate.time) / msPerDay).toInt()

        // 이자 계산: 대출액 × (이자율/100) × (일수/365)
        val interest = loanAmount.toDouble() * (interestRate / 100.0) * (days.toDouble() / 365.0)

        // 소수점 이하는 버림
        return interest.toInt()
    }

    fun cal2 (
        executedDate: Date,
        maturityDate: Date,
        loanAmount: Int,
        interestRate: Double
    ): Int {

        val perDay = 1000L * 60 * 60 * 24
        val days = ((maturityDate.time - executedDate.time) / perDay).toInt()
        val interest = loanAmount.toDouble() * (interestRate / 100.0) * (days.toDouble() / 365.0)
        return interest.toInt()
    }
}