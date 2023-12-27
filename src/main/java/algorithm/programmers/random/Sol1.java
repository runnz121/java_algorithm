package algorithm.programmers.random;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Sol1 {

    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        // 1. 정렬
        Arrays.sort(attacks, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        // 2. 마지막 시간
        int attackIdx = attacks.length;
        int lastTime = attacks[attackIdx-1][0];

        // 2차원을 hashmap으로
        Map<Integer, Integer> attackMap = new HashMap<>();
        for (int i = 0; i < attackIdx; i++) {
            attackMap.put(attacks[i][0], attacks[i][1]);
        }
        // 전체 시간 기준으로 맵 초기화
        for (int k = 0; k < lastTime; k++) {
            attackMap.putIfAbsent(k, 0);
        }
        // 3. 선형 탐색
        int startTime = 0;
        int curHealth = health;
        int continueSuccess = 0;

        while (startTime <= lastTime) {
            // 현재 체력이 0 이하면 죽음
            if (curHealth <= 0) {
                curHealth = -1;
                break;
            }
            // 공격 시점이면 데미지 입히고 패스
            if (attackMap.get(startTime) != 0) {
                curHealth -= attackMap.get(startTime);
                // 연속 체력 회복시 초기화
                continueSuccess = 0;
                startTime ++;
                continue;
            }
            continueSuccess += 1;
            // 연속 성공일 경우 추가 회복
            if (continueSuccess == bandage[0]) {
                curHealth += bandage[2];
                curHealth += bandage[1];
                continueSuccess = 0;
            } else {
            // 아니라면 일반 회복
                curHealth += bandage[1];
            }
            // 현재 체력이 최대 체력보다 넘을 경우 보정 처리
            if (curHealth > health) {
                curHealth = health;
            }
            startTime ++;
        }

        if (curHealth <= 0) {
            curHealth = -1;
        }
        answer = curHealth;

        return answer;
    }

    public static void main(String[] args) {
        Sol1 s1 = new Sol1();
        int[] bandage = {1, 1, 1};
        int health = 5;
        int[][] attacks = {{1,2}, {3,2}};
        int res = s1.solution(bandage, health, attacks);
        System.out.println(res);
    }
}
