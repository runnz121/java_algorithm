package algorithm.programmers;

import java.io.IOException;
import java.util.Arrays;

public class Min_Max_Programmers {

    static long []arr;

    public String solution(String s) throws IOException {

        String answer = "";
        //
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String[] strArr = s.split(" ");

        arr = new long[strArr.length];

        for (int i = 0; i < strArr.length; i ++) {
            arr[i] = Long.parseLong(strArr[i]);
        }

        long MAX = Arrays.stream(arr).max().getAsLong();
        long MIN = Arrays.stream(arr).min().getAsLong();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(MIN);
        stringBuilder.append(" ");
        stringBuilder.append(MAX);

        answer = stringBuilder.toString();
        return answer;
    }

    public static void main(String[] args) throws IOException {

        Min_Max_Programmers T = new Min_Max_Programmers();
        System.out.println(T.solution("-1 -2 -3 -4"));
    }
}

