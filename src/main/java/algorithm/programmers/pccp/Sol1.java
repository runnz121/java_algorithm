package algorithm.programmers.pccp;

public class Sol1 {

    static int maxDepth;
    static boolean[] visited;
    static int answer;

    public int solution(int[][] ability) {
        answer = 0;
        maxDepth = ability[0].length;
        visited = new boolean[ability.length];

        backTracing(ability, 0,0);

        System.out.println(answer);
        return answer;
    }

    static void backTracing(int[][] ability,
                            int idx,
                            int sums) {

        if (idx == maxDepth) {
            answer = Math.max(answer, sums);
            return;
        }

        for (int i = 0; i < ability.length; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                backTracing(ability, idx + 1, sums + ability[i][idx]);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Sol1 s1 = new Sol1();

        int[][] abil = new int[][]{{40, 10, 10}, {20, 5, 0}, {30, 30, 30}, {70, 0, 70}, {100, 100, 100}};
        s1.solution(abil);
    }
}
