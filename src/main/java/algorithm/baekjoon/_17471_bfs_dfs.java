package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _17471_bfs_dfs {

    static int N;
    static int[] popul;
    static int[][] dir;
    static int answer = Integer.MAX_VALUE;
    static int total = 0;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        // 인구
        popul = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int pop = Integer.parseInt(st.nextToken());
            popul[i] = pop;
            total += pop;
        }

        // 연관도시 받기
        dir = new int[N][];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int value = Integer.parseInt(line[0]);
            int [] target = new int[value];

            for (int k = 1; k <= value; k++) {
                target[k - 1] = Integer.parseInt(line[k]) - 1;
            }

            dir[i] = target;
        }

        // 모든 경우의 수를 bitMask 로 고려
        // 구역 3인 경우
        // 001, 011....111 로 판단
        // 1부터 (1<<N)-1 까지 모든 부분집합 고려 (양쪽 그룹 모두 비어있지 않아야 하므로, mask가 0이나 full은 제외)
        // mask == 0 (그룹 A가 빈 집합)과 mask == (1<<N)-1 (그룹 B가 빈 집합)은 제외하기 위해 mask 범위를 1부터 (1<<N)-1 미만으로 설정 -> 문제에서 둘중 한구역에는 무조건 속해야 함으로
        for (int bit = 1; bit < (1 << N) - 1; bit ++) {

            // 2개의 선거구가 모두 속해있는지 체크
            if (checkMask(bit)) {
                answer = Math.min(answer, getDiffPeople(bit));
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? - 1 : answer);
    }

    static boolean checkMask(int bitMask) {

        // 그룹 A 연결 확인
        boolean[] visitedA = new boolean[N];
        int startA = -1;
        // 각 i 번째 비트가 1 이면 그룹 A 에 속함
        for (int i = 0; i < N; i++) {
            if ((bitMask & (1 << i)) != 0) {
                startA = i;
                break;
            }
        }

        if (startA == -1) {
            return false;
        }

        // bfs로 도달가능한 부분을 visitedA 에 기록
        bfs(startA, bitMask, visitedA, true);
        // 모두 방문되었는지 확인 > 하나라도 방문 안되면 false
        for (int i = 0; i < N; i++) {
            if ((bitMask & (1 << i)) != 0 && !visitedA[i]) {
                return false;
            }
        }

        // 그룹 B 연결 확인
        boolean[] visitedB = new boolean[N];
        int startB = -1;
        for (int i = 0; i < N; i++) {
            if ((bitMask & (1 << i)) == 0) {
                startB = i;
                break;
            }
        }

        if (startB == -1) {
            return false;
        }

        bfs(startB, bitMask, visitedB, false);
        for (int i = 0; i < N; i++) {
            if ((bitMask & (1 << i)) == 0 && !visitedB[i]) {
                return false;
            }
        }
        return true;
    }

    static void bfs(int start,
                    int mask,
                    boolean[] visited,
                    boolean isA) {

        Queue<Integer> que = new LinkedList<>();
        visited[start] = true;
        que.offer(start);

        while (que.isEmpty() == false) {
            int cur = que.poll();
            for (int next : dir[cur]) {
                if (visited[next] == false) {

                    if (isA && (mask & (1 << next)) != 0) {
                        visited[next] = true;
                        que.offer(next);
                    }

                    if (isA == false && (mask & (1 << next)) == 0) {
                        visited[next] = true;
                        que.offer(next);
                    }
                }
            }
        }
    }

    static int getDiffPeople(int mask) {
        int sumA = 0;
        for (int i = 0; i< N; i++) {
            // i 번 비트가 1 => A 그룹 속함
            // i 번 비트가 0 => B 그룹 속함
            if ((mask & (1 << i)) != 0) {
                sumA += popul[i];
            }
        }
        int sumB = total - sumA;
        return Math.abs(sumA - sumB);
    }
}
