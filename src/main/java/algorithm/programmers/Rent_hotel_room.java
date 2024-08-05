package algorithm.programmers;

import java.time.LocalTime;
import java.util.Arrays;

public class Rent_hotel_room {

    public int solution(String[][] book_time) {
        int answer = 1;
        for (int i = 0; i < book_time.length; i++) {
            book_time[i][0] = book_time[i][0].replace(":", "");
            book_time[i][1] = book_time[i][1].replace(":", "");
        }

        Arrays.sort(book_time, (a, b) -> {
            int firstCompare = a[0].compareTo(b[0]);
            // 첫번째 비교
            if (firstCompare != 0) {
                return firstCompare;
            } else {
            // 다음거 비교
                return a[1].compareTo(b[1]);
            }
        });

        for (int i = 0; i < book_time.length; i++) {
            book_time[i][0] = book_time[i][0].substring(0, 2) + ":" + book_time[i][0].substring(2);
            book_time[i][1] = book_time[i][1].substring(0, 2) + ":" + book_time[i][1].substring(2);
        }

        for (int i = 1; i < book_time.length; i++) {
            LocalTime currentStartTime = LocalTime.parse(book_time[i][0]);
            for (int x = 0; x < i; x++) {
                LocalTime existLastTime = LocalTime.parse(book_time[x][1]);
                if (!currentStartTime.minusMinutes(10).isAfter(existLastTime)) {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Rent_hotel_room rhr = new Rent_hotel_room();
        String[][] inputs = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        rhr.solution(inputs);
    }
}
