package pftc


/**
 * In a distributed network system, data packets with unique
 * identifiers and checksum values are sent between nodes to maintain communication and data integrity.
 * Develop a function to aggregate the checksum values for all possible pairs of packets within the network.
 *
 * For any two distinct packets with identifiers i and j, the checksum is calculated as:
 *
 * C(1,1) = 1 mod 1 + 1 mod 1 = 0 + 0 = 0
 * C(1,2) = 1 mod 2 + 2 mod 1 = 1 + 0 = 1
 * C(1,3) = 1 mod 3 + 3 mod 1 = 1 + 0 = 1
 * C(2,1) = 2 mod 1 + 1 mod 2 = 0 + 1 = 1
 * C(2,2) = 2 mod 2 + 2 mod 2 = 0 + 0 = 0
 * C(2,3) = 2 mod 3 + 3 mod 2 = 2 + 1 = 3
 * C(3,1) = 3 mod 1 + 1 mod 3 = 0 + 1 = 1
 * C(3,2) = 3 mod 2 + 2 mod 3 = 1 + 2 = 3
 * C(3,3) = 3 mod 3 + 3 mod 3 = 0 + 0 = 0
 *
 * total each sum -> 10
 */

class Sol4 {

    fun computeChecksumAggregationAlt(n: Int): Int {
        val MOD = 1_000_000_007L
        var total = 0L

        // i<j인 구간에서 (i mod j)=i, (j mod i)는 modSumForI로 구함
        for (i in 1..n) {
            val iLong = i.toLong()

            // — (i mod j)=i 기여: i * (n-i) 번 등장 → *2 해서 (i,j),(j,i) 두 방향 반영
            val countI = (n - i).toLong()
            total = (total + 2 * (iLong * countI % MOD)) % MOD

            // — (j mod i) 기여
            val q = n / i           // ⌊n/i⌋
            val r = n % i           // n mod i

            // 0..(i-1) 합 = i*(i-1)/2 가 q번
            val cycleSum = (q.toLong() % MOD) * (iLong * (iLong - 1) / 2 % MOD) % MOD
            // 0..r 합 = r*(r+1)/2
            val tailSum  = (r.toLong() * (r + 1) / 2) % MOD

            // 전체 j=1..n 구간의 Σ(j mod i)
            val totalModAllJ = (cycleSum + tailSum) % MOD
            // 빼야 할 j=1..i 구간의 Σ(j mod i) = i*(i-1)/2
            val diagSum = iLong * (iLong - 1) / 2 % MOD

            // j>i인 구간만: (totalModAllJ - diagSum)
            val modSumForI = (totalModAllJ - diagSum + MOD) % MOD

            // *2 해서 (i<j),(j>i) 양 방향 반영
            total = (total + 2 * modSumForI) % MOD
        }

        return total.toInt()
    }

}