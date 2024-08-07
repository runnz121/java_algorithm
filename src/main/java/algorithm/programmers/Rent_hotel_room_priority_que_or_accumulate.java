package algorithm.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/155651
public class Rent_hotel_room_priority_que_or_accumulate {

    public int solution(String[][] book_time) {
        int answer = 0;

        int[][] bookTimeInt = new int[book_time.length][2];

        for (int i = 0; i < book_time.length; i++) {
            int startTime = Integer.parseInt(book_time[i][0].replace(":", ""));
            int endTime = Integer.parseInt(book_time[i][1].replace(":", ""));

            // 끝시간 10분 더함
            endTime += 10;
            if (endTime%100 >= 60) {
                endTime += 40;
            }
            bookTimeInt[i][0] = startTime;
            bookTimeInt[i][1] = endTime;
        }

        Arrays.sort(bookTimeInt, (a, b) -> {
            int firstCompare = Integer.compare(a[0], b[0]);
            // 첫번째 비교
            if (firstCompare != 0) {
                return firstCompare;
            } else {
            // 다음거 비교
                return Integer.compare(a[1], b[1]);
            }
        });

        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (int[] time : bookTimeInt) {
            // 첫번재는 방 배정
            if (rooms.size() == 0) {
                // 방 대여 종료시간
                rooms.offer(time[1]);
                continue;
            }
            int earlyRoom = rooms.peek();
            // 다음 방 시작시간보다 종료시간이 작으면 큐아웃
            if (time[0] >= earlyRoom) {
                rooms.poll();
                rooms.add(time[1]);
            }
            // 새방 배정
            else {
                rooms.add(time[1]);
            }
        }
        answer = rooms.size();
        return answer;
    }

    public static void main(String[] args) {
        Rent_hotel_room_priority_que_or_accumulate rhr = new Rent_hotel_room_priority_que_or_accumulate();
        String[][] inputs = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        rhr.solution(inputs);
    }

    // 누적합
//    class Solution {
//        private static final int MAX_TIME = 1_450; // 24*60 + 10;
//        private static final int HOUR = 60;
//        private static final int CLEAN_TIME = 10; // 청소시간
//
//        public static int solution(String[][] book_time) {
//            int answer = 0;
//
//            int[] rooms = new int[MAX_TIME];
//
//            for (String[] time : book_time) {
//                String inTime = time[0];
//                String outTime = time[1];
//
//                rooms[calTime(inTime)] += 1; // 입실 시간
//            /*
//              끝+1을 하지 않는 것은 seeminglyjs님의 질문에 대한 댓글을 참고해 주세요!
//            */
//                rooms[calTime(outTime) + CLEAN_TIME] += -1; // 퇴실 + 청소 시간
//            }
//
//            // 누적합
//            for (int i = 1; i < MAX_TIME; i++) {
//                rooms[i] += rooms[i-1];
//                answer = Math.max(answer, rooms[i]);
//            }
//
//            return answer;
//        }
}
