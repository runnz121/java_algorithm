package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
                target[k - 1] = Integer.parseInt(line[k]);
            }

            dir[i] = target;
        }

        // 전체 구역 다 돌아 -> 하나씩 특정해서 해당 번호를 제외하고 나머지 구역이 모두 이어져 있는지 확인
        for (int d = 0; d < N; d++) {

            boolean[] visited = new boolean[N];

            visited[d] = true;

            dfs(d, visited, popul[d]);
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
    }

    // 선거구 하나에 대해서 진행
    static void dfs(int idx,
                    boolean[] visited,
                    int sumPeo) {


        for (int i = 0; i < dir[idx].length; i++) {

            int next = dir[idx][i] - 1;

            if (visited[next] == false) {
                visited[next] = true;

                // 갱신되었을 때만 체크하는걸로
                int targetPeo = popul[next];
                // 현재 구역 인원
                int sectionA = sumPeo + targetPeo;
                // 나머지 구역
                int sectionB = total - sectionA;
                // 두 인구의 절대값이 답보다 작은 경우 갱신 및 다 이어져있는지 확인
                if (Math.abs(sectionA - sectionB) < answer) {
                    // 다 이어져있으면 답 갱신
                    if (checkAllLinked(visited) && hasBothGroups(visited)) {
                        answer = Math.abs(sectionA - sectionB);
                    }
                }

                dfs(next, visited, sumPeo + targetPeo);
                visited[next] = false;
            }
        }
    }

    // 두 선거구가 모두 존재하는지 체크
    static boolean hasBothGroups(boolean[] visited) {
        boolean groupA = false;
        boolean groupB = false;

        for (boolean b : visited) {
            if (b) groupA = true;
            else groupB = true;
        }
        return groupA && groupB;
    }

    // 두 선거구 연결여부 확인
    static boolean checkAllLinked(boolean[] visited) {
        // 그룹 A 체크
        boolean[] checkVisit = new boolean[N];
        int startA = -1;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                startA = i;
                break;
            }
        }
        if (startA != -1) {
            dfsCheck(startA, visited, checkVisit);
            for (int i = 0; i < N; i++) {
                if (visited[i] && !checkVisit[i])
                    return false;
            }
        }
        // 그룹 B 체크
        boolean[] checkVisitB = new boolean[N];
        int startB = -1;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                startB = i;
                break;
            }
        }
        if (startB != -1) {
            dfsCheck(startB, invertVisited(visited), checkVisitB);
            for (int i = 0; i < N; i++) {
                if (!visited[i] && !checkVisitB[i])
                    return false;
            }
        }
        return true;
    }

    static void dfsCheck(int idx, boolean[] group, boolean[] toCheckVisit) {

        toCheckVisit[idx] = true;

        for (int i = 0; i < dir[idx].length; i++) {
            int next = dir[idx][i] - 1;

            if (toCheckVisit[next] == false) {
                toCheckVisit[next] = true;
                dfsCheck(next, group, toCheckVisit);
            }
        }
    }

    // visited 배열의 반대(나머지 그룹) 생성
    static boolean[] invertVisited(boolean[] visited) {
        boolean[] inv = new boolean[N];
        for (int i = 0; i < N; i++) {
            inv[i] = !visited[i];
        }
        return inv;
    }
}
