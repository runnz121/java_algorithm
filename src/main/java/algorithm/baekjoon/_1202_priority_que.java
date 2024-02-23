package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1202_priority_que {

    static long N;
    static long M;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 가격 기준으로 우선순위 큐 설정
        List<Jewelry> jewelries = new ArrayList<>();

        // 보석 정보
        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            // 무게
            int m = Integer.parseInt((st1.nextToken()));
            // 가격
            int v = Integer.parseInt((st1.nextToken()));
            // 보석
            Jewelry jw = new Jewelry(m, v);
            // 우선순위 큐 삽입
            jewelries.add(jw);
        }

        List<Long> bags = new ArrayList<>();
        // 가방 정보
        for (int k = 0; k < M; k++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            // 가방
            Long c = Long.parseLong(st2.nextToken());
            // 가방 정보 저장
            bags.add(c);
        }
        // 가방 오름차순
        Collections.sort(bags);
        // 보석 무게 기준 오름차순
        Collections.sort(jewelries);
        // 내림차순 정렬 (보석 무게 큰것부터 출력)
        Queue<Integer> pq = new PriorityQueue<>((o1,o2) -> (o2-o1));

        long answer = 0L;
        int j = 0;
        // 가방 완전 탐색
        for (int x = 0; x < M; x++) {

            while(true) {

                if (j >= N) {
                    break;
                }

                Jewelry jw = jewelries.get(j);

                if (bags.get(x) < jewelries.get(j).weight) {
                    break;
                }

                pq.add(jw.price);
                j++;
            }

            if (!pq.isEmpty()) {
                answer += Math.abs(pq.poll());
            }
        }
        System.out.println(answer);
    }

    public static class Jewelry implements Comparable<Jewelry>{

        private int weight;
        private int price;

        public Jewelry(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewelry o) {
            return this.weight - o.weight;
        }
    }
}
