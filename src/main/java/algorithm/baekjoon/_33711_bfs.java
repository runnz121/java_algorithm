package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _33711_bfs {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer n = Integer.parseInt(st.nextToken());
        Integer m = Integer.parseInt(st.nextToken());
        Integer k = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        Map<String, List<Integer>> nameMap = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        Integer w = Integer.parseInt(st.nextToken());

        for (int i = 0; i < w; i++) {

            st = new StringTokenizer(br.readLine());

            Integer num = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if (nameMap.containsKey(name)) {
                List<Integer> integers = nameMap.get(name);
                integers.add(num);
                nameMap.put(name, integers);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(num);
                nameMap.put(name, newList);
            }
        }

        // graph
        for (int x = 0; x <= n; x++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            Integer a = Integer.parseInt(st.nextToken());
            Integer b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // 멀티 소스 bfs
        // 1. 동시에 출발 노드를 모드 큐에 넣는다.
        // 2. dist 배열은 해당 노드가 지나온 거리를 인덱스 기준으로 기록한다
        // 3. 서로다른 노드가 만나는 지점이 존재하는 경우 해당 노드가 지나온 거리를 각각 합산하여 계산한다.

        for (List<Integer> targets : nameMap.values()) {
            if (targets.size() < 2) continue;

            // 1) owner[i]: i번째 노드를 가장 먼저 방문한 출발점(start node)
            // 2) dist[i]: 해당 출발점에서 노드 i까지의 거리
            int[] owner = new int[n + 1];
            int[] dist  = new int[n + 1];
            Arrays.fill(owner, -1);

            Deque<Integer> que = new ArrayDeque<>();

            // 같은 이름을 갖는 노드의 수를 모두 다 큐에 넣는다.
            for (int src : targets) {
                owner[src] = src;
                dist[src] = 0;
                que.addLast(src);
            }

            boolean found = false;
            while (que.isEmpty() == false  && found == false) {
                int u = que.pop();
                // depth 제한
                if (dist[u] == k) continue;

                for (int v : graph.get(u)) {
                    // 아직 해당 노드에 어떤 시작 노드도 방문한 적이 없다면
                    if (owner[v] == -1) {
                        // 아직 방문 안 된 노드 → 같은 출발점 소유권, 거리 기록
                        owner[v] = owner[u];
                        dist[v]  = dist[u] + 1;
                        que.push(v);
                    }
                    // 이미 다른 노드에서 방문했다면 (방문 기록이 있음으로 현재 노드와 값이 같지 않다)
                    else if (owner[v] != owner[u]) {
                        // 다른 출발점이 먼저 방문한 노드와 만남
                        int totalDist = dist[u] + 1 + dist[v];
                        if (totalDist <= k) {
                            found = true;
                            break;
                        }
                    }
                }
            }

            if (found) {
                System.out.println("POWERFUL CODING JungHwan");
                return;
            }
        }
        System.out.println("so sad");


        // 아래 BFS 는 시간초과 발생

//
//        for (Map.Entry<String, List<Integer>> entry : nameMap.entrySet()) {
//
//            List<Integer> targets = entry.getValue();
//            if (targets.size() < 2) continue;
//
//            for (int i = 0; i < targets.size(); i++) {
//
//                int start = targets.get(i);
//                int[] distance = new int[n + 1];
//                boolean[] visited = new boolean[n + 1];
//                Deque<Integer> que = new ArrayDeque<>();
//
//                que.push(start);
//                visited[start] = true;
//                distance[start] = 0;
//
//                while (que.isEmpty() == false) {
//
//                    Integer curr = que.pop();
//
//                    if (distance[curr] > k) break;
//
//                    // 자기 자신 제외
//                    if (curr != start && targets.contains(curr)) {
//                        System.out.println("POWERFUL CODING JungHwan");
//                        return;
//                    }
//
//                    for (int neighbor : graph.get(curr)) {
//
//                        if (!visited[neighbor]) {
//                            visited[neighbor] = true;
//                            distance[neighbor] = distance[curr] + 1;
//                            que.add(neighbor);
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("so sad");
    }
}
