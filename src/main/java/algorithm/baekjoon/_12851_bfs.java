package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 방문할 수 있는 거리의 가지수를 모든 조합 조건으로 탐색해야한다. 따라서
 * 타겟 거리 초과 미만으로 나누는 것이 아닌 모든 조건을 큐에 넣고 돌려야 한다.
 *
 * 방문거리 배열이 필요하다. 같은 거리를 다양한 방법으로 방문 할 수 있고, 여기서는 시간 기준으로 몇가지 방법으로 해당 거리에 도달할 수 있는지
 * 확인이 필요하기 때문에 중복으로 처리되는 경우도 고려해야한다.
 *
 */
public class _12851_bfs {

    public static class Node {

        int time;
        int src;

        public Node(int time,
                    int src) {

            this.time = time;
            this.src = src;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int MAX = 100_000;

        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        Map<Integer, Integer> answer = new HashMap<>();
        Deque<Node> que = new ArrayDeque<>();
        int [] visited = new int[MAX + 1];
        Arrays.fill(visited, -1);

        // 시작
        que.add(new Node(0, N));

        while (que.isEmpty() == false) {
            Node pop = que.pop();

            int currentTime = pop.time;
            int currentSrc = pop.src;

            // 도착하면 anser 맵에 넣고 건너뛰기 -> 다른 조건도 봐야함
            if (pop.src == K) {

                answer.put(pop.time, answer.getOrDefault(pop.time, 0) + 1);

                continue;
            }

            // 범위 벗어난 경우 건너뛰기
            if (currentSrc < 0 || currentSrc > 100_000) {

                continue;
            }

            // 이미 방문한곳이면 건너 뛰기 && 현재 시간보다 이전인 경우 건너 뛰기
            // 현재 시간보다 이전인 경우 건너 뛰기 -> 이 조건을 넣지 않으면 같은 시간 다른 방법으로 들어오는 경우에는 이미 방문 처리가되어 버려서 카운트가 되지 암ㅎ음
            if (visited[currentSrc] != -1  && visited[currentSrc] < currentTime) {

                continue;
            }

            visited[currentSrc] = currentTime;

            // 갈 수 있는 모든 조건을 탐색하기 위해 큐에 넣는다.
            // +1
            que.add(new Node(currentTime + 1, currentSrc + 1));
            // *2
            que.add(new Node(currentTime + 1, currentSrc * 2));
            // -1
            que.add(new Node(currentTime + 1, currentSrc - 1));
        }

        // 시간 기준 정렬
//        LinkedHashMap<Integer, Integer> sortedMap = answer.entrySet().stream()
//                .sorted(Map.Entry.<Integer, Integer>comparingByKey())
//                .collect(Collectors.toMap(
//                                Map.Entry::getKey,
//                                Map.Entry::getValue,
//                                (e1, e2) -> e1,
//                                LinkedHashMap::new
//                        )
//                );
//
//        Integer firstKey = sortedMap.keySet().iterator().next();

        // 시간 기준 정렬 -> 위의 로직을 아래 한줄로 리펙토링 할 수 있다 -> 가장 작은 키 꺼내기
        Integer firstKey = answer.keySet().stream().min(Integer::compareTo).get();

        System.out.println(firstKey);
        System.out.println(answer.get(firstKey));
    }
}
