package algorithmKotlin.baekjoon

import java.util.StringTokenizer

/*
// 기존 코드
fun main() = with(System.`in`.bufferedReader()) {

    var T = readLine().toInt()

    repeat(T) {

        val N = readLine().toInt()

        var applicatns = mutableListOf<Applicatns>()

        repeat(N) {

            val st = StringTokenizer(readLine())
            val paper = st.nextToken().toInt()
            val interview = st.nextToken().toInt()

            var ap = Applicatns(
                paper, interview
            )

            applicatns.add(ap)
        }

        // 서류기준 오름차순
        applicatns.sortBy { it.paper }

        var answer = 1

        var maxInterviewCnt = applicatns[0].interview
        for (i in 1 until applicatns.size) {

            if (maxInterviewCnt > applicatns[i].interview) {
                answer++
                maxInterviewCnt = applicatns[i].interview
            }
        }
        println(answer)

        T--;
    }
}

data class Applicatns(

    val interview: Int,

    val paper: Int
)
*/

// 코틀린스럽게 리팩토링한 코드
fun main() = with(System.`in`.bufferedReader()) {
    /*
     * [with 스코프 함수]
     * - with(receiver) { block } 형태
     * - receiver 객체를 this로 사용하여 block 내에서 멤버에 직접 접근 가능
     * - 여기서는 BufferedReader의 readLine()을 this.readLine() 없이 바로 호출 가능
     * - 반환값: block의 마지막 표현식 (여기서는 Unit - repeat의 반환값)
     */

    repeat(readLine().toInt()) {
        /*
         * [repeat 함수]
         * - repeat(times) { block } 형태
         * - block을 times번 반복 실행
         * - 람다의 암시적 파라미터 it은 현재 인덱스 (0부터 시작), 여기서는 사용 안 함
         */

        val n = readLine().toInt()

        val applicants = List(n) {
            /*
             * [List 생성자 함수]
             * - List(size) { init } 형태
             * - size 크기의 리스트를 생성하며, init 람다로 각 요소 초기화
             * - init 람다의 it은 현재 인덱스 (여기서는 사용 안 함)
             * - 반환: 불변 List<T>
             */

            readLine().split(" ").map { it.toInt() }.let { (paper, interview) -> paper to interview }
            /*
             * [split 함수]
             * - String.split(delimiter): 문자열을 delimiter 기준으로 나눠 List<String> 반환
             * - "1 2".split(" ") → ["1", "2"]
             *
             * [map 함수]
             * - Collection.map { transform }: 각 요소를 transform 함수로 변환한 새 리스트 반환
             * - it: 람다의 암시적 단일 파라미터 (현재 요소)
             * - ["1", "2"].map { it.toInt() } → [1, 2]
             *
             * [let 스코프 함수]
             * - T.let { block }: 수신 객체를 it(또는 명시적 파라미터)으로 받아 block 실행
             * - 반환값: block의 마지막 표현식
             * - null 체크, 변환, 체이닝에 유용
             *
             * [구조 분해 선언 (Destructuring Declaration)]
             * - { (paper, interview) -> ... } 형태
             * - List나 Pair 등의 요소를 개별 변수로 분해
             * - [1, 2]를 (paper=1, interview=2)로 분해
             * - 내부적으로 component1(), component2() 함수 호출
             *
             * [to 중위 함수 (Infix Function)]
             * - a to b → Pair(a, b) 생성
             * - 중위 표기법: 함수를 연산자처럼 사용 (점과 괄호 생략)
             * - infix fun <A, B> A.to(that: B): Pair<A, B> 로 정의됨
             */

        }.sortedBy { it.first }
        /*
         * [sortedBy 함수]
         * - Collection.sortedBy { selector }: selector 기준으로 오름차순 정렬된 새 리스트 반환
         * - sortBy는 MutableList를 제자리 정렬, sortedBy는 새 리스트 반환 (불변성 유지)
         * - it.first: Pair의 첫 번째 요소 (paper)
         *
         * [Pair의 first, second 프로퍼티]
         * - Pair<A, B>는 first: A, second: B 프로퍼티 보유
         * - (1 to 2).first → 1, (1 to 2).second → 2
         */

        // 서류 1등은 무조건 합격, 나머지는 면접 순위가 현재까지의 최소보다 높아야 합격
        val answer = applicants.drop(1).fold(1 to applicants.first().second) { (count, minInterview), (_, interview) ->
            /*
             * [drop 함수]
             * - Collection.drop(n): 앞에서 n개 요소를 제외한 새 리스트 반환
             * - [1,2,3].drop(1) → [2,3]
             *
             * [first 함수]
             * - Collection.first(): 첫 번째 요소 반환, 비어있으면 예외 발생
             * - firstOrNull()은 비어있으면 null 반환
             *
             * [fold 함수]
             * - Collection.fold(initial) { accumulator, element -> ... }
             * - initial: 초기 누적값
             * - accumulator: 이전 단계의 누적 결과
             * - element: 현재 처리 중인 요소
             * - 모든 요소를 순회하며 누적 연산 수행, 최종 누적값 반환
             *
             * [fold에서의 구조 분해]
             * - { (count, minInterview), (_, interview) -> ... }
             * - 첫 번째 파라미터(accumulator): Pair를 (count, minInterview)로 분해
             * - 두 번째 파라미터(element): Pair를 (_, interview)로 분해
             * - _: 사용하지 않는 값을 나타내는 관례적 표기 (paper는 불필요)
             *
             * [if 표현식 (Expression)]
             * - 코틀린의 if는 표현식으로 값을 반환
             * - if (조건) 값1 else 값2 → 삼항 연산자 대체
             * - 자바: condition ? value1 : value2
             * - 코틀린: if (condition) value1 else value2
             */

            if (interview < minInterview) (count + 1) to interview
            else count to minInterview
        }.first
        /*
         * [.first로 결과 추출]
         * - fold의 결과가 Pair(count, minInterview)
         * - .first로 count만 추출
         */

        println(answer)
    }
}