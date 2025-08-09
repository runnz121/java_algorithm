package algorithm.hackerank;

// https://www.hackerrank.com/challenges/delete-duplicate-value-nodes-from-a-sorted-linked-list/problem?utm_source=chatgpt.com
public class DeleteDuiplicateValueNodesFromAsortedLinkedList {

    public static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;
    }

    public static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode llist) {

        // 탐색 포인터로 쓰기 위해서 새로 할당함
        SinglyLinkedListNode cur = llist;

        // cur 기준으로 포인터를 이동시켜서 확인
        while (true) {

            if (cur.next == null) {
                break;
            }
            // 현재 노드의 데이터가 다음 노드의 데이터와 같은경우 다음다음 노드로 갱신
            if (cur.data == cur.next.data) {
                cur.next = cur.next.next;
            } else {
                // 그게 아니라면 다음 노드로 갱신
                cur = cur.next;
            }
        }

        // 원본 맨 처음 노드를 반환해야함
        return llist;
    }

}
