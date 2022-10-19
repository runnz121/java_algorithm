package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 3
 * 1 5
 * 3 10 11 12
 * 5 1 2 3 4 5
 * 형태의 데이터를 입력 받는 경우
 */

public class InputOutPutStdBaekJoon {

    public void solution() throws Exception {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력하기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++){
            // 엔터를 기준으로 문자열 분리
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());

            for (int j = 0; j < s; j++) {
                // 다음 문자열을 받아 입력 받음
                int data = Integer.parseInt(st.nextToken());

                // 출력(스트링 형태)
                bw.write(String.valueOf(data));
                // 한칸 띄기(엔터)
                bw.newLine();
            }
        }
        // 모두 출력되면 버퍼 비워줌
        bw.flush();
    }

    public static void main(String[] args) throws Exception{
        new InputOutPutStdBaekJoon().solution();
    }
}
