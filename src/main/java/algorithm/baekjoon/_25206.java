package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class _25206 {

    // 2차원 리스트로 설정
    static List<List<String>> lists = new ArrayList<>();

    // 최대 10개까지는 of로 설정 가능
    static Map<String, Double> grade = Map.of(
        "A+",4.5,
        "A0",4.0,
        "B+",3.5,
        "B0",3.0,
        "C+",2.5,
        "C0",2.0,
        "D+",1.5,
        "D0",1.0,
        "F",0.0
    );

    public static void main(String[] args) throws IOException {
        // 입력값이 존재할 떄까지 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        while (count != 20) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // index 1씩 증가시켜 list로 저장
            String[] stToken = new String[3];
            int idx = 0;
            while (st.hasMoreTokens()) {
                stToken[idx] = st.nextToken();
                idx ++;
            }
            List stringList = new ArrayList(Arrays.asList(stToken));
            lists.add(stringList);
            count += 1;
        }
        Double answer = 0.000000;
        Double totalGrade = 0.0;
        for (List<String> arr : lists) {
            String key = arr.get(2);
            Double gr = Double.valueOf(arr.get(1));
            if (key.equals("P")) {
                continue;
            }
            Double val = 0.000000;
            val = grade.get(key);
            answer += val * gr;
            totalGrade += gr;
        }
        System.out.printf("%.6f", answer/totalGrade);
    }
}
