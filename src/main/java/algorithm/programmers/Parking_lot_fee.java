package algorithm.programmers;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Parking_lot_fee {

    public int[] solution(int[] fees, String[] records) {

        List<Integer> answer = new LinkedList<>();

        Map<String, List<LocalTime>> carMap = new LinkedHashMap<>();
        Map<String, Long> carTimeMap = new LinkedHashMap<>();

        for (int i = 0; i < records.length; i++) {

            String[] record = records[i].split(" ");

            LocalTime time = LocalTime.parse(record[0]);
            String carNum = record[1];
            String status = record[2];

            List<LocalTime> times = carMap.computeIfAbsent(carNum, k -> new ArrayList<>());
            times.add(time);

            if (status.equals("OUT")) {
                List<LocalTime> localTimes = carMap.get(carNum);
                LocalTime in = localTimes.get(0);
                LocalTime out = localTimes.get(1);

                Duration duration = Duration.between(in, out);
                long minutes = duration.toMinutes();

                carTimeMap.put(carNum, carTimeMap.getOrDefault(carNum, 0L) + minutes);

                carMap.put(carNum, new ArrayList<>());
            }
        }

        carMap.entrySet()
                .forEach(
                        it -> {
                            if (!it.getValue().isEmpty()) {
                                LocalTime lastTime = LocalTime.parse("23:59");
                                Duration duration = Duration.between(it.getValue().get(0), lastTime);
                                long minutes = duration.toMinutes();
                                carTimeMap.put(it.getKey(), carTimeMap.getOrDefault(it.getKey(), 0L) + minutes);
                                carMap.put(it.getKey(), new ArrayList<>());
                            }
                        }
                );

        Map<String, Long> sortedMap = new TreeMap<>();
        sortedMap.putAll(carTimeMap);

        sortedMap.entrySet().stream().forEach(
                it -> {
                    if (it.getValue() <= fees[0]) {
                        answer.add(fees[1]);
                    } else {
                        int overTime = (int) Math.abs(it.getValue() - fees[0]);
                        int overTimeFee = (int) Math.ceil((double) overTime / fees[2]) * fees[3];

                        int totalFee = fees[1] + overTimeFee;
                        answer.add(totalFee);
                    }
                }
        );

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Parking_lot_fee plf = new Parking_lot_fee();
        int[] fees = {180, 5000, 10, 600};
        String [] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        plf.solution(fees, records);
    }
}
