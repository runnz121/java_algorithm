package algorithm.programmers;

public class NPowArraySlice {

    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) ((right - left) + 1)];

        for (int i = 0; i < answer.length; i++) {
            int row = (int)((i + left) / n) + 1;
            int col = (int)((i + left) % n) + 1;
            // 행 열 중 큰 값을 갖는다
            answer[i] = Math.max(row, col);
        }

        return answer;
    }

    public static void main(String[] args) {

        NPowArraySlice ns = new NPowArraySlice();
        int[] inputs = {3,2,2,3};
        ns.solution(3,2, 5);
    }
}
