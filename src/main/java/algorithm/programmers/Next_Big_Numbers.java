package algorithm.programmers;

public class Next_Big_Numbers {

    public int solution(int n) {
        int answer = 0;
        int bitCount = Integer.bitCount(n);

        for (int i = n + 1; i < 10000000; i++) {
            int nextBitCount = Integer.bitCount(i);
            if (bitCount == nextBitCount) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Next_Big_Numbers nbn = new Next_Big_Numbers();
        nbn.solution(78);
    }
}
