package algorithm.programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/12979
public class Set_Tower_Greedy {

    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int idx = 0;
        int i = 1; // 맨 왼쪽 첫번째 부터 (아파트 인덱스)

        while (i <= n) {
            // 만일 현재 인덱스가 전파 범위 이내일 경우
            if (idx < stations.length && i >= stations[idx] - w) {
                // 인덱스 갱신 (단반향 전파범위)
                i = stations[idx] + w + 1;
                idx ++;
                continue;
            }
            // 현재 인덱스가 전파 범위 밖일 경우 -> 인덱스 갱신
            i += 2 * w + 1;
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        Set_Tower_Greedy st = new Set_Tower_Greedy();
        int[] inputs = {4, 11};
        st.solution(11, inputs, 1);
    }
}
