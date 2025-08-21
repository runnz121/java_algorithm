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
     * 3. 포인터가 같은 총거리 를 가게되고 처음 같은 노드에서 만난다 (합류점)
     *
     */

    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        SinglyLinkedListNode p1 = head1;
        SinglyLinkedListNode p2 = head2;

        while (p1 != p2) {
            p1 = (p1 == null) ? head2 : p1.next;
            p2 = (p2 == null) ? head1 : p2.next;
        }
        return p1.data;

    }
}
