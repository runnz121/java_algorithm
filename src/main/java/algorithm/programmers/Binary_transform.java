package algorithm.programmers;

import java.util.Arrays;

public class Binary_transform {

    static int count = 0;
    static int deletedZero = 0;
    public int[] solution(String s) {

        int[] answer = new int[2];
        recursive(s);
        answer[0] = count;
        answer[1] = deletedZero;
        return answer;
    }

    static void recursive(String s) {
        // 길이가 1이면 종료
        if (s.length() == 1) return ;
        // 제거전
        int inputLength = s.length();
        // 제거후
        String output = s.replaceAll("0", "");
        int outputLength = output.length();
        // 0 제거 길이 갱신
        deletedZero += inputLength - outputLength;
        // 2 진수로 변환
        String binary = Integer.toBinaryString(outputLength);
        // count 증가
        count += 1;
        recursive(binary);
    }

    public static void main(String[] args) {
        Binary_transform bf = new Binary_transform();
        String ex = "110010101001";
        bf.solution(ex);
    }
}

