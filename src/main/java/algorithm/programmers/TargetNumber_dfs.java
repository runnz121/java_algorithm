package algorithm.programmers;

public class TargetNumber_dfs {

    static int answers;
    static boolean[] visited;
    static int totalCnt;
    static int targetNum;
    static int[] numberss;

    public int solution(int[] numbers, int target) {
        targetNum = target;
        totalCnt = numbers.length;
        visited = new boolean[totalCnt];
        numberss = numbers;

        dfs(0, 0);
        System.out.println(answers);
        return answers;
    }

    static void dfs(int idx, int result) {

        // 재귀 종료 조건
        if (idx == totalCnt) {
            if (result == targetNum) {
                answers += 1;
            }
        } else {
            // idx 하나씩 증가 + 두가지 연산만 가능 + , -
            dfs(idx + 1, result + numberss[idx]);
            dfs(idx + 1, result - numberss[idx]);
        }
    }

    public static void main(String[] args) {
        TargetNumber_dfs t = new TargetNumber_dfs();
        int[] numbers = {1,1,1,1,1};
        int target = 3;
        t.solution(numbers, target);
    }
}
