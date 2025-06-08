package algorithm.baekjoon;

import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1946
/**
 * 풀이
 *
 * 1. 서류 혹은 면접 둘중 하나의 순위 기준으로 오름차순 정렬한다.
 * 2. 정렬 대상값이 아닌 걸 기준으로 나머지 순위를 비교함
 * 3. 이전보다 더 낮은 순위라면 갱신한다 (더 잘한 지원자 임으로)
 *
 */
public class _1946_sort {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        while (T > 0) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            // 객체 생성
            List<Applicants> applicants = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                Applicants ap = new Applicants(a, b);
                applicants.add(ap);
            }

            // 서류 순위 기준으로 오름차 순
            applicants.sort(new Comparator<Applicants>() {
                @Override
                public int compare(Applicants o1, Applicants o2) {
                    return o1.paper - o2.paper;
                }
            });

            System.out.println(applicants);

            // 첫번째는 무조건 선발 (1순위 임으로)
            int answer = 1;

            // 서류 기준 인터뷰 순위
            int maxInterviewCnt = applicants.get(0).interview;
            for (int i = 0; i < applicants.size(); i++) {
                // 해당 서류
                if (maxInterviewCnt > applicants.get(i).interview) {
                    answer ++;
                    maxInterviewCnt = applicants.get(i).interview;
                }
            }
            System.out.println(answer);

            T--;
        }
    }

    public static class Applicants {
        int interview;
        int paper;

        Applicants(int interview, int paper) {
            this.interview = interview;
            this.paper = paper;
        }

        @Override
        public String toString() {
            return "interview : " + interview + " paper : " + paper + "\n";
        }
    }
}
