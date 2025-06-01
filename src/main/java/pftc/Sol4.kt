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

// 문제 접근법 다음과 같이 격자 기준으르 각 격자마다 I mod j + j mod i 의 값이 들어간다고 할 때 -> 전체 합을 구하면 되지만 이는 O(n^2) 임
/**
 *         j=1   j=2   j=3   j=4   …  j=n
 * i=1      C(1,1) C(1,2) C(1,3) C(1,4) … C(1,n)
 * i=2      C(2,1) C(2,2) C(2,3) C(2,4) … C(2,n)
 * i=3      C(3,1) C(3,2) C(3,3) C(3,4) … C(3,n)
 * i=4      C(4,1) C(4,2) C(4,3) C(4,4) … C(4,n)
 * …
 * i=n      C(n,1) C(n,2) C(n,3) C(n,4) … C(n,n)
 *
 *
 * 따라서 i < j 인 경우만 계산해서 * 2를 하면 같다 ==> i mod j == j mod i 와 같기 때문
 *
 * ** i < j 일 때 고려할 두가지
 * 1. i mod j -> j 가 더 큼으로 목이 0 나머지가 i
 * 2. j mod i -> 완전 주기 + 남는 꼬리 를 더함
 *
 * 완전 주기 : j mod i 에서 나머지가 일정 주기로 순회되는 범위를 말함 예를 들면 3으로 나눈다고 할때 나머지는 0,1,2 가 될 수 있고 이 주기가 반복되는 구간을 말함
 * 남는 꼬리 : 완전주기 블록에 포함되지 않는 부분
 *
 * 1 + 2 번을 더하고 2배를 해주면 된다
 */

class Sol4 {

    fun computeChecksumAggregationAlt(n: Int): Int {
        val MOD = 1_000_000_007L
        var total = 0L

        // i를 하나로 고정하고 반복
        for (i in 1..n) {
            val iLong = i.toLong()

            // 파트 1:
            // i mod j = i 인 것을 계산
            // — (i mod j)=i 기여: i * (n-i) 번 등장 → *2 해서 (i,j),(j,i) 두 방향 반영

            /**
             * 나머지가 i 임으로 해당 구간의 합을 구하고 i 를 곱함 (i < j, i > j 모든 구간 계산해야함 )
             */
            val countI = (n - i).toLong()
            // 전체 = 전체 + 2배 (구간 * i 값)
            total = (total + 2 * (iLong * countI % MOD)) % MOD


            // 파트 2:
            // j mod i 인 것을 계산
            val q = n / i           // ⌊n/i⌋ (몫)
            val r = n % i           // n mod i (나머지)

            // 1. 완전 주기 : 나머지가 완전히 주기를 갖는 부분 == 몫만큼 완전 주기가 돈다
            // 0..(i-1) 합 = i*(i-1)/2 가 q번
            val cycleSum = (q.toLong() % MOD) * (iLong * (iLong - 1) / 2 % MOD) % MOD

            // 2. 남는 꼬리 부분 == 나머지 부분
            // 0..r 합 = r*(r+1)/2
            val tailSum  = (r.toLong() * (r + 1) / 2) % MOD

            // 3. 전체 합 (1 + 2)
            // 전체 j=1..n 구간의 Σ(j mod i)
            val totalModAllJ = (cycleSum + tailSum) % MOD

            // 4. 빼야할 부분 (우리는 i < j 구간만 계산함으로 j = 1 ~ j = i 구간은 빼야한다)
            // 빼야 할 j=1..i 구간의 Σ(j mod i) = i*(i-1)/2
            val diagSum = iLong * (iLong - 1) / 2 % MOD

            // i < j인 구간만 남는다 : (totalModAllJ - diagSum)
            val modSumForI = (totalModAllJ - diagSum + MOD) % MOD

            // *2 해서 (i<j),(j>i) 양 방향 반영
            total = (total + 2 * modSumForI) % MOD
        }

        return total.toInt()
    }
}

fun car(n: Int): Int {

    val MOD = 1_000_000_007L
    var total = 0L

    for (i in 1..n) {

        var iLong = i.toLong()

        // 1 i mod j = i || j mod i = j 인 양방향 구간 모두 계산해야함
        total = (total + 2 * (iLong * (n - i).toLong() % MOD)) % MOD


        // 2.
        val q = n / i
        val r = n % i

        // 1. 완전 주기
        var totalSum = q.toLong() * (iLong * (iLong - 1) / 2) % MOD

        // 2. 꼬리
        var tail = (r.toLong() * (r + 1) / 2) % MOD

        // 3. 앞부분 제거 ( i < j ) 가 아닌 부분
        var delete = (iLong * (iLong - 1) / 2) % MOD

        // 4. 실제 필요한 부분
        var modSum = (totalSum + tail - delete) % MOD

        // total 갱신
        total = (total + 2 * modSum) % MOD
    }

    return total.toInt()
}

fun main() {
    val s = Sol4().computeChecksumAggregationAlt(3)
    println(s)
}