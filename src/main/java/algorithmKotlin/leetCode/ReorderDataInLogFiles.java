package algorithmKotlin.leetCode;

import java.util.*;

public class ReorderDataInLogFiles {

    public String[] reorderLogFiles(String[] logs) {

        List<String> stringList = new ArrayList<>();
        List<String> digitList = new ArrayList<>();

        // 1. 문자 따로 숫자 따로 로그 분리 넣음
        for (String log : logs) {
            // 빈 공백으로 스플릿 후 첫번째 배열값이 숫자인지 확인
            if (Character.isDigit(log.split(" ")[1].charAt(0))) {
                digitList.add(log);
            } else {
                stringList.add(log);
            }
        }

        // 2. collection list 정렬

        // 기준값.compareTo(비교값)
        // 1 : 비교대상이 앞에
        // -1 : 비교대상이 뒤에
        stringList.sort((o1, o2) -> {
            String[] s1 = o1.split(" ", 2); // 식별자, value값
            String[] s2 = o2.split(" ", 2);

            int compared = s1[1].compareTo(s2[1]);
            // 문자가 동일할 경우 -> 식별자 비교
            if (compared == 0) {
                return s1[0].compareTo(s2[0]);
            } else {
                return compared;
            }
        });

        stringList.addAll(digitList);

        return stringList.toArray(new String[0]);
    }

    public static void main(String[] args) {

        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};

        ReorderDataInLogFiles reorderDataInLogFiles = new ReorderDataInLogFiles();

        String[] res = reorderDataInLogFiles.reorderLogFiles(logs);

        System.out.println(Arrays.toString(res));
    }
}
