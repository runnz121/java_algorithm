package company.oHousePay;

import java.util.Arrays;

public class Sol4 {

    public int solution(int[] references) {
        int answer = 0;
        Arrays.sort(references);

        for (int i = 0; i < references.length; i++) {
            int hIndex = references[i];
            if (hIndex >= references.length - i) {
                answer = references.length - i;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Sol4 sol4 = new Sol4();
        int[] refer = new int[]{3, 0, 4, 2, 8};
        sol4.solution(refer);
    }
}
