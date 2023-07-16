package algorithm;

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

    static List<String> lists = new ArrayList<>();
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
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (!st.hasMoreElements()) {
                break;
            }
            String[] stToken = new String[3];
            int idx = 0;
            while (st.hasMoreTokens()) {
                stToken[idx] = st.nextToken();
                idx ++;
            }
            lists.add(Arrays.toString(stToken));
        }
        //

    }
}
