package algorithm.hackerank;

// https://www.hackerrank.com/challenges/insert-a-node-at-the-head-of-a-linked-list/problem?utm_source=chatgpt.com
public class InsertANodeAtTheHeadOfALinkedList {

    public static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {

        // 새로운 노드를 생성한다
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        // 새로운 노드의 다음을 기존 head로 연결한다
        newNode.next = llist;

        return newNode;
    }
}
