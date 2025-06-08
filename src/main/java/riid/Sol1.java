package riid;

import java.util.*;

/**
 * 문제 설명
 * ○○피자집은 손님이 피자를 한 번 살 때마다 쿠폰 1장을 줍니다. 이 쿠폰을 3장 모으면 다음 주문 때 스파게티를 무료로 받습니다. (쿠폰을 2개 갖고 있을 때 피자를 주문하면 새 쿠폰을 받아 쿠폰이 3개이지만, 이 상황에서는 쿠폰을 쓸 수 없습니다.) 무료로 스파게티를 받았을 때는 피자를 사도 쿠폰을 주지 않으며, 손님은 쿠폰을 4개 이상 모을 수 없습니다.
 *
 * 예를 들어, [1,1,3,3,2,4,3,3,2,4,2,4] 순으로 주문했다고 가정해봅시다. 괄호 안의 숫자는 손님 고유의 ID를 나타내며, 리스트의 원소는 주문한 순서를 나타냅니다.
 * 이를 표로 나타내어 첫 번째 행은 손님 고유의 ID, 두 번째 행은 해당 손님의 쿠폰 개수를 나타냅니다.
 *
 * 표:
 *
 * rust
 * 복사
 * 편집
 * Customer's ID  1 1 3 3 2 4 3 3 2 4 2 4
 * The number of   1 2 1 2 1 1 2 3 2 2 3 🍝
 *  coupons
 * 이 경우엔 3번 손님, 2번 손님은 스파게티를 무료로 받습니다. 4번 손님은 쿠폰 3장을 모았지만, 이 쿠폰은 다음번 피자를 주문할 때 쓸 수 있습니다.
 *
 * 피자를 산 손님들의 ID가 매개변수 people로 주어졌을 때, 스파게티를 무료로 받은 손님의 ID를 return 하는 solution 함수를 완성해 주세요. 예를 들어, 위의 예시에서는 3번 손님, 2번 손님 순으로 스파게티를 무료로 받았기 때문에 답은 [3,2]입니다. 단, 어떠한 손님도 스파게티를 무료로 받지 못한 경우에는 1차원 배열에 -1을 넣어서 return 해주세요.
 *
 * 제약사항
 * people의 길이: 100,000 이하의 자연수
 *
 * 손님 ID: 1,000,000,000 이하의 자연수
 *
 * 입력 예
 * ini
 * 복사
 * 편집
 * people = [1,3,3,2,4,3,3,2,4,2,4]
 * answer = [3,2]
 * ini
 * 복사
 * 편집
 * people = [1,1,3,3,3,3,1,3,3,3,3,2]
 * answer = [3,3]
 * ini
 * 복사
 * 편집
 * people = [1,2,3,4]
 * answer = [-1]
 * 입력 예 설명
 * 입력 예 #1
 * 문제의 예시와 같습니다.
 *
 * 입력 예 #2
 *
 * 복사
 * 편집
 * 손님ID       1 1 3 3 3 3 1 3 3 3 3 2
 * 쿠폰 개수    1 2 1 2 3 🍝 1 2 3 🍝 1 1
 * 3번 손님이 두 번 연속으로 스파게티를 받았습니다.
 *
 * 입력 예 #3
 * 어떤 손님도 스파게티를 무료로 받지 못한 경우입니다.
 *
 */

public class Sol1 {
    public int[] solution(int[] people) {

        Map<Integer, Integer> couponMap = new HashMap<>();
        List<Integer> freeList = new ArrayList<>();

        for (int id : people) {
            int cnt = couponMap.getOrDefault(id, 0);

            if (cnt == 3) {
                freeList.add(id);
                couponMap.put(id, 0);
            }
            else {
                couponMap.put(id, Math.min(cnt + 1, 3));
            }
        }

        if (freeList.isEmpty()) {
            return new int[]{ -1 };
        }

        int[] answer = new int[freeList.size()];
        for (int i = 0; i < freeList.size(); i++) {
            answer[i] = freeList.get(i);
        }
        return answer;
    }
}
