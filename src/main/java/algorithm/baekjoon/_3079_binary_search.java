package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _3079_binary_search {

    static long N;
    static long M;
    static long answer;
    static List<Integer> examtimes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            examtimes.add(Integer.parseInt(st2.nextToken()));
        }

        Collections.sort(examtimes);

        // 2. 최소, 최대값 구분
        long left = 1;
        long right = (long) Collections.max(examtimes) * M;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            // 소요 시간을 각 심사관의 시간으로 나누고 합산한값 계산
            for (long examTime : examtimes) {
                long calTime = (mid / examTime);
                // sum 이 M 보다 크면 overFlow 발생함으로 중간에 break 해줘야한다.
                if (sum >= M) {
                    break;
                }
                sum += calTime;
            }

            // 합산값이 기다리는 사람 수보다 크거나 일치하는 경우 중앙값을 정답으로 설정
            if (sum >= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}

