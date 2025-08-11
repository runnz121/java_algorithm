package algorithm.hackerank;

//https://www.hackerrank.com/challenges/insert-a-node-at-the-tail-of-a-linked-list/problem?utm_source=chatgpt.com
public class InsertANodeAtTheTailOfALinkedList {
    public static class SinglyLinkedListNode {

        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        // null 이면 신규 생성 노드 반환
        if (head == null) {
            return newNode;
        }

        // 현재 노드를 head 부터 시작
        SinglyLinkedListNode current = head;

        // 다음 노드가 있는 경우 -> 마지막 노드가 될 때까지 계속 이동한다 (이 구문이 없으면 마지막 노드로 도달이 불가 )
        while (current.next != null) {

            // 다음 노드로 이동 (현재 노드를 다음 노드로 갱신한다)
            current = current.next;
        }

        // 마지막 노드의 next 에 새로운 노드로 이동
        current.next = newNode;

        return head;
    }
}
