package algorithm.programmers;

public class Fatigue_brute_force {

    static int answer = -1;
    static boolean[] visited;

    public int solution(int k, int[][] dungeons) {

        visited = new boolean[dungeons.length];

        backTracking(0, k, dungeons);

        return answer;
    }

    private void backTracking(int depth, int k, int[][] dungeons) {

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                backTracking(depth + 1, k - dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
        answer  = Math.max(answer, depth);
    }

    public static void main(String[] args) {
        Fatigue_brute_force ft = new Fatigue_brute_force();
        int k = 80;
        int[][] dungenons = {{80,20},{50,40},{30,10}};
        ft.solution(k, dungenons);
    }
}
