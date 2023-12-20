package algorithm.leetCode;

public class ReverseLinkedList {

    public static class ListNode {

        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return " val : " + val + " next : " + next ;
        }
    }

    public ListNode reverse(ListNode node, ListNode prev) {
        // 현재가 null 이면 prev 리턴
        if (node == null) {
            return prev;
        }
        // 현재 노드의 다음 노드 미리 지정
        ListNode next = node.next;
        // 현재 노드의 다음으로 이전 노드 지정
        node.next = prev;
        // 다음 노드와 현재 노드를 파라미터 재귀 호출
        return reverse(next, node);
    }

    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
    }


    public static void main(String[] args) {

        ReverseLinkedList rv = new ReverseLinkedList();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode res = rv.reverseList(head);
        System.out.println(res);
    }
}
