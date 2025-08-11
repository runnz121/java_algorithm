package algorithm.hackerank;

// https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list/problem?utm_source=chatgpt.com
public class InsertANodeAtASpecificPositionInALinkedList {

    public static class SinglyLinkedListNode {

        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        // 0번째 위치일 경우 -> 최초 삽입 후 다음 노드 연결
        if (position == 0) {
            newNode.next = llist;
            return newNode;
        }

        SinglyLinkedListNode current = llist;

        // n-1 까지 이동
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        // 연결: (current) -> (newNode) -> (원래 current.next)

        // 신규 생성 노드의 다음을 현재의 다음노드로 지정
        newNode.next = current.next;

        // 현재의 다음 노드를 새로운 노드로 갱신
        current.next = newNode;

        return llist;
    }
}
