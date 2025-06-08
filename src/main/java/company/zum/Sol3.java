package company.zum;

public class Sol3 {
    public int solution(int[] histogram) {
        int answer = -1;
        int size = histogram.length;

        for (int i = 0; i < size; i++) {
            int first = histogram[i];
            for (int k = i + 2; k < size; k++) {
                int second = histogram[k];
                int height = Math.min(first, second);
                int length = k - i - 1;
                int square = height * length;

                answer = Math.max(answer, square);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Sol3 t = new Sol3();
        int[] h = {2,2,2,3};
        int[]h1 = {6,5,7,3,4,2};
        t.solution(h1);
    }
}
