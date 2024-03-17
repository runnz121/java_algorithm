package segmentTree;

import java.util.Map;

/**
 * 세그먼트 트리를 배열로 구현
 * 구간내 연산 계산이 목적
 *
 * 특징
 * 1. 기본적으로 이진 트리
 * 2. 부모노드가 자식 노드들의 합을 저장
 * 3. 루트 노드 번호는 1로 시작
 * 4. 왼쪽 자식 노드 번호는 2 * X, 오른쪽 자식 노드번호는 2 * X + 1
 * 5. 전체 구간의 크기는 리프 노드의 개수와 같다.
 *
 * 연관문제 :
 *  2042, 11505
 *
 */
public class SegmentTree {

    long[] tree;
    int treeSize;

    // 1. 생성자
    // 세그먼트 트리를 배열로 구현하기 위한 배열 크기 설정
    public SegmentTree(int arrSize) {
        // Tree 높이 : 전체 배열 개수 log화
        // leaf : n 개, 전체 갯수는 leaf를 더한 개수 포함함으로 log(n)으로 높이 계산
        int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

        // Tree 에 들어가는 Node의 개수는 2의 높이 + 1 제곱 미만 갯수
        this.treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
    }

    // 2. init 메소드 설정 (최초 세그먼트 트리 구성)
    // 1.리프 노드에 배열로 전달된 값을 넣고, 부모 노드들에 각각의 구간합을 삽입하는 방식으로 진행
    // 2.재귀로 구현
    // 3. node_idx 는 1로 전달 (트리를 배열로 구현했기 때문) -> ex) start = 1, end = 100
    public long init(int[] nums, int node_idx, int start, int end) {
        // start == end 이면 leaf 노드
        // 배열 값을 그대로 저장
        if (start == end) {
            return tree[node_idx] = nums[start];
        }
        // 위의 조건이 아니면 좌측 노드와 우측 노드의 합으로 구해진다
        return tree[node_idx] =
                // 왼쯕 자식 노드
                init(nums, node_idx * 2, start, (start + end) / 2) +
                // 오른쪽 자식 노드
                init(nums, node_idx * 2 + 1, (start + end) / 2 + 1, end);
    }

    // 3. update 메소드
    // 1. 중간에 값이 변경되어을 경우, 재귀로 기존과 현재 값의 차이만큼 영향 받는 모든 노드에 연산하여 변경해줌
    // 2. 전달시 기존값과의 차이를 전달
    public void update(int node_idx, int start, int end, int idx, long diff) {
        // 변경할 idx 값이 범위 밖이면 해당 tree는 확인 불필요
        if (idx < start || end < idx) return;

        // 변경된 차이만큼 영향 받는 각 Node 더해줌
        tree[node_idx] += diff;

        // leaf 노드에 도달할때까지 모든 노드 진행
        if (start != end) {
            // 좌측 노드 진행
            update(node_idx * 2, start, (start + end) / 2, idx, diff);
            // 우측 노드 진행
            update(node_idx * 2 + 1, start, (start + end) / 2 + 1, idx, diff);
        }
    }

    // 4. sum 메소드
    // 구간합을 위한 메소드
    public long sum(int node_idx, int start, int end, int left, int right) {
        // 범위 벗어나는 경우 구하지 않음
        if (left > end || right < start) {
            return 0;
        }
        // 범위 내 완전 포함시 더 내려가지 않고 리턴
        if (left <= start && end <= right) {
            return tree[node_idx];
        }
        // 그 외의 경우 좌/우로 지속 탐색 수행
        return sum(node_idx * 2, start, (start + end) / 2, left, right) +
                sum(node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }
}
