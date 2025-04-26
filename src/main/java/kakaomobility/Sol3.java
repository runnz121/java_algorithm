package kakaomobility;

public class Sol3 {

    public int solution(int[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }

        int length = A.length;

        if (length == 1) {
            return 1;
        }

        int answer = 1;
        int start = 0;

        for (int i = 1; i < length; i++) {

            int spanLength = i - start;
            if (spanLength> 1) {

                int target = (spanLength % 2 == 0 ? A[start] : A[start + 1]);

                if (A[i] != target) {
                    start = i - 1;
                }
            }
            int currLen = i - start + 1;
            if (currLen > answer) {
                answer = currLen;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Sol3 s3 = new Sol3();
        int[] A = {3, -7, 3, -7};
        int solution = s3.solution(A);
        System.out.println(solution);
    }
}
