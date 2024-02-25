package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _1202_priority_que_sort {

    static long N; // 보석 수
    static long M; // 가방 수

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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
        // 가방 공간 오름차순
        Collections.sort(bags);
        // 보석 무게 기준 오름차순
        Collections.sort(jewelries);
        // 내림차순 정렬 (보석 무게 큰것부터 출력)
        Queue<Integer> pq = new PriorityQueue<>((o1,o2) -> (o2-o1));

        long answer = 0L;
        int j = 0;
        // 가방 완전 탐색
        for (int x = 0; x < M; x++) {

            // 가방 하나마다 보석이 들어가는 상태를 확인
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

// 오름 차순 정렬되, 무게가 같을 경우 내림차순 정렬
//Arrays.sort(jewelries, new Comparator<Jewelry>() {
//
//@Override
//public int compare(Jewelry o1, Jewelry o2) {
//        if (o1.mass == o2.mass) {
//        return o2.value - o1.value;
//        }
//        return o1.mass - o2.mass;
//        }
//
//        });