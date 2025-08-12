package algorithm.hackerank;

//https://www.hackerrank.com/challenges/delete-a-node-from-a-linked-list/problem?utm_source=chatgpt.com
public class DeleteANode {

    public static class SinglyLinkedListNode {

        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    public static SinglyLinkedListNode deleteNode(SinglyLinkedListNode llist, int position) {

        // 첫번재 포지션은 다름 노드 반환
        if (position == 0) {
            return llist.next;
        }

        // n - 1 까지 이동
        SinglyLinkedListNode current = llist;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        // 현재의 다음 노드는 다음다음 노드로 지정
        current.next = current.next.next;

        return llist;
    }
}
