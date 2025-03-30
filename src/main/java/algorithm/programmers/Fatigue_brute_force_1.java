package algorithm.programmers;

public class Fatigue_brute_force_1 {

    static int answer = 0;
    static int max;
    static int length;
    static int[][] dungeon;
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        max = k;
        length = dungeons.length;
        dungeon = dungeons;
        visited = new boolean[dungeons.length];

        backtracing( 0, k);

        System.out.println(answer);
        return answer;
    }

    void backtracing(int count,
                     int fatigue) {

        for (int i = 0; i < length; i++) {
            if (visited[i] == false && fatigue >= dungeon[i][0]) {
                visited[i] = true;
                backtracing(count + 1, fatigue - dungeon[i][1]);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, count);
    }

    public static void main(String[] args) {
        Fatigue_brute_force_1 ft = new Fatigue_brute_force_1();
        int[][] d = {{80,20},{50,40},{30,10}};
        ft.solution(80, d);
    }
}
