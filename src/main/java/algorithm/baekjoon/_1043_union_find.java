package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 접근법
 * - 파티에 참석한 사람들에서 첫번째 사람을 루트로 하여 union 한다 > 누가 루트가 되든 그 파티 집합에는 모든 똑같은 정보가 전파됨으로
 * - 진실을 아는 사람들의 리스트를 만든다 > 이때 find 로 각 파티인원의 루트값을 만든다
 * - 파티를 순회하면서 각 파티 참석인원의 루트 정보가 진실을 아는 루트 리스트에 포함되어 있는지 확인한다
 */
public class _1043_grpah {

    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        // parent 초기화시 주의 I = 1 부터 시작
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // 진실
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int[] truth = new int[T];
        for (int i = 0; i < T; i++) {
            truth[i] = Integer.parseInt(st.nextToken());
        }

        // 파티 참석자 목록 저장
        List<int[]> parties = new ArrayList<>(m);

        // 파티 > 유니온 파인드
        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int[] people = new int[len];

            for (int j = 0; j < len; j++) {
                people[j] = Integer.parseInt(st.nextToken());
            }
            parties.add(people);

            // 해당 파티의 첫번째 참석자를 루트로 나머지 참석자를 union 한다.
            for (int j = 1; j < len; j++) {
                union(people[0], people[j]);
            }
        }

        // 진실 루트 집합 생성 > 진실을 아는 사람들을 기준으로 해당 사람들의 루트를 찾아서 리스트에 넣음
        Set<Integer> truthRoots = new HashSet<>();
        for (int t : truth) {
            truthRoots.add(find(t));
        }

        // 과장 가능한 파티 수 계산
        int answer = 0;
        for (int[] party : parties) {
            boolean canLie = true;
            for (int p : party) {
                if (truthRoots.contains(find(p))) {
                    canLie = false;
                    break;
                }
            }

            if (canLie) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static int find(int a) {

        if (parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {

        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }
}
