package algorithm.programmers;

public class Network_bfs_dfs {

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[computers.length];

        for (int i = 0; i < computers.length; i++){
            if (!visited[i]) {
                answer++;
                dfs(i, visited, computers);
            }
        }

        System.out.println(answer);
        return answer;
    }

    public void dfs(int node, boolean[] visited, int[][] computers){
        visited[node] = true;

        for (int i = 0; i < computers.length; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                dfs(i, visited, computers);
            }
        }
    }

    public static void main(String[] args) {
        Network_bfs_dfs nbd = new Network_bfs_dfs();
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        nbd.solution(n, computers);
    }
}
