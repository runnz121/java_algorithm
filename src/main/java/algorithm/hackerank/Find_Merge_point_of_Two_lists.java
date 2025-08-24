package algorithm.hackerank;

// https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=linked-lists
public class Find_Merge_point_of_Two_lists {

    public static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    /**
     * 접근 방법
     * 1. 각 포인터는 각 해드에서 시작
     * 2. 포인터가 한칸씩 이동하다가 Null 에 닿으면 반대 리스트 head로 점프
     * 3. 포인터가 같은 총거리 를 가게되고 처음 같은 노드에서 만난다 (합류)
     */
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        SinglyLinkedListNode p1 = head1;
        SinglyLinkedListNode p2 = head2;

        while (p1 != p2) {
            // null == 현재 리스트에서 끝에 도달했다 -> 반대편 리스트 head로 점프
            p1 = (p1 == null) ? head2 : p1.next;
            p2 = (p2 == null) ? head1 : p2.next;
        }
        return p1.data;

    }

    /**
     * 문제 조건상 반드시 공통의 노드가 존재함
     *
     * A: 1 → 2 → 3 → 4 → 5 → 6 → null
     * B: 9 → 8 ───────────────┘
     * (merge point = 노드 5)
     *
     *
     * 스텝-by-스텝 표
     * “시작 위치”는 해당 반복의 while 조건 비교 시점의 값이야.
     * 스위칭이 일어나면 다음 줄 “이동 후”에 반영됨.
     *
     * 반복(시작 시점)	p1	    p2	    같음?	이동 후 p1	    이동 후 p2
     * 1 시작	    1	    9	    ×	    2	            8
     * 2 시작	    2	    8	    ×	    3	            5
     * 3 시작	    3	    5	    ×	    4	            6
     * 4 시작	    4	    6	    ×	    5	            null→headA(1)
     * 5 시작	    5	    null→1	×	    6	            2
     * 6 시작	    6	    2	    ×	    null→headB(9)	3
     * 7 시작	    null→9	3	    ×	    8	            4
     * 8 시작	    8	    4	    ×	    5	            5
     * 9 시작	    5	    5	    ✓	    ( 루프 종료)	    (루프 종료)
     */
}
