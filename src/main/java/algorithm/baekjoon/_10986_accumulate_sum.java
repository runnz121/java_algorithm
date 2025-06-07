package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10986
public class _10986_accumulate_sum {

    public static void main(String[] args) throws IOException {
        // 1) 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 수의 개수
        int M = Integer.parseInt(st.nextToken());  // 나눌 수

        // 2) 나머지 빈도(count) 테이블: 인덱스 0부터 M-1까지 사용
        long[] cnt = new long[M];
        cnt[0] = 1;  // “나머지가 0인 누적합”을 하나 미리 카운팅 (빈 구간을 생각)

        // 3) 배열 A를 읽으면서 바로 누적합 mod M을 계산
        st = new StringTokenizer(br.readLine());
        long prefix = 0L;
        for (int i = 0; i < N; i++) {
            long a = Long.parseLong(st.nextToken());
            prefix = (prefix + a) % M;      // 누적합을 M으로 나눈 나머지
            cnt[(int) prefix]++;
        }

        // 4) 해당 나머지가 등장한 갯수 기준으로 2개를 골라 나올 수 있는 조합의 수를 계산
        // 여기서 사용되는 공식은 다음과 같다
        // 나머지가 같은 i, j 에서 i < j 일 때 j - i 는 항상 나누는 N의 배수이다.
        long answer = 0L;
        for (int r = 0; r < M; r++) {
            long k = cnt[r];
            answer += k * (k - 1) / 2;
        }

        // 5) 결과 출력
        System.out.println(answer);
    }
}
