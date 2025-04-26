package kakaomobility;

public class Sol2 {

    public int solution(int[] T, int[] A) {

        boolean[] visited = new boolean[T.length];
        int count = 0;

        for (int skill : A) {
            count += dfs(skill, T, visited);;
        }

        return count;
    }

    private int dfs(int skill,
                    int[] roots,
                    boolean[] visited) {

        if (visited[skill]) {
            return 0;
        }

        visited[skill] = true;
        int count = 1;

        if (skill != 0) {
            count += dfs(roots[skill], roots, visited);
        }

        return count;
    }

    public static void main(String[] args) {
        Sol2 s2 = new Sol2();

        int[] T = {0,0,0,0,2,3,3};
        int[] A = {2,5,6};
        int solution = s2.solution(T, A);
        System.out.println(solution);
    }
}
