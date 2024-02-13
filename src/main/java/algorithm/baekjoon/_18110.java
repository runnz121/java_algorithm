package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class _18110 {

    public Integer solution() throws Exception {

        List<Integer> levels = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 처음 갯수
        int N = Integer.parseInt(br.readLine());

        // 의견 엾다면 난인도 0 평균 리턴
        if (N == 0) {
            return 0;
        }

        // 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            levels.add(s);
        }
        // 정렬
        levels.sort(Comparator.comparingInt(Integer::intValue));
        // 절사 평균 구하기
        int removeAverage = (int) Math.round(N * 0.15);
        // 최소, 최대 제외
        IntSummaryStatistics avearge = IntStream.range(removeAverage, levels.size() - removeAverage)
                .filter(idx -> idx >= removeAverage && idx <= N - removeAverage)
                .mapToObj(levels::get)
                        .mapToInt(num -> num)
                                .summaryStatistics();

        return (int) Math.round(avearge.getAverage());
    }

    public static void main(String[] args) throws Exception {
        new _18110().solution();
    }
}
