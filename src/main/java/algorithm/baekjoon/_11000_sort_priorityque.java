package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class _11000_sort_priorityque {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<int[]> rooms = new ArrayList<>();

        while (N  > 0) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[] room = new int[]{a, b};

            rooms.add(room);

            N--;
        }

        // 시작시간 빠른순 정렬
        rooms.sort(Comparator.comparingInt(a -> a[0]));

        // 종료시간 빠른 순
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.offer(rooms.get(0)[1]);

        // 5) 나머지 수업 처리
        for (int i = 1; i < rooms.size(); i++) {
            int start = rooms.get(i)[0];
            int end = rooms.get(i)[1];

            // 가장 빨리 끝나는 강의가 이미 끝났으면 방 재사용
            if (endTimes.peek() <= start) {
                endTimes.poll();
            }
            // (재사용 했든 새로 잡았든) 지금 수업의 종료 시각을 넣어두고
            endTimes.offer(end);
        }

        //  동시에 사용된 최대 강의실 수 (최소 강의실 개수)
        System.out.println(endTimes.size());
    }
}
