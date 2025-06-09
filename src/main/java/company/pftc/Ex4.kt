package company.pftc

class Ex4 {

    // i < j 기준으로만 따지고
    // 격자는 고정
    // 첫번째는 나머지 를 전체 합산하고
    // 두번째는 전체 순환 + 꼬리 + 전체에서 앞부분 빼기

    fun computeChecksumAggregationAlt(n: Int): Int {

        val MOD = 1_000_000_007L
        var total = 0L

        for (i in 1..n) {

            var iLong = i.toLong()

            var countI = (n - i).toLong()
            total = (total + 2 * (iLong) * countI % MOD) % MOD

            var q = n / i
            var r = n % i

            var cycleSum = q.toLong() * ((iLong) * (iLong - 1) / 2 % MOD) % MOD

            var tailSum = (r.toLong() * (r + 1) / 2) % MOD

            var delete = iLong * (iLong - 1) / 2 % MOD

            var jModSum = (cycleSum + tailSum - delete + MOD) % MOD

            total = (total + 2 * jModSum) % MOD
        }

        return total.toInt()
    }
}


//fun computeChecksumAggregationAlt(n: Int): Int {
//
//    val MOD = 1_000_000_007L
//    var total = 0L
//
//    for (i in 1..n) {
//        var iLong = i.toLong()
//
//        // i < j 에서 나머지가 그대로 나오는 전체합
//        var totalSum = (iLong * (iLong - i) / 2) % MOD
//        total = (total + 2 * totalSum) % MOD
//
//        // i < j 인데
//        var q = n / i
//        var r = n % i
//
//        // 완전 주기
//        var cycleSum = (q.toLong()) * (iLong * (n - 1) / 2) % MOD
//
//        // 꼬리 주기
//        var tailSum = (r.toLong() * (r + 1) / 2) % MOD
//
//        // 전체
//        var modTotal = (cycleSum + tailSum) % MOD
//
//        // 빼기 앞부분
//        var delete = (iLong * (iLong - 1) / 2) % MOD
//
//        var modSum = (modTotal - delete + MOD) % MOD
//
//        total = (total + 2 * modSum) % MOD
//    }
//
//    return total.toInt()
//}