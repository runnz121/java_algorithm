package algorithm.hackerank;


// https://www.hackerrank.com/challenges/reverse-a-linked-list/problem?utm_source=chatgpt.com
public class ReverseALinkedList {

    public static class SinglyLinkedListNode {

        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }

        /**
         *
         * 초기 상태:
         * 1 -> 2 -> 3 -> 4 -> 5 -> null
         *
         * current=1, next=2, 1.next = null → prev=1
         * current=2, next=3, 2.next = 1 → prev=2
         * current=3, next=4, 3.next = 2 → prev=3
         * current=4, next=5, 4.next = 3 → prev=4
         * current=5, next=null, 5.next = 4 → prev=5
         *
         * 결과:
         * 5 -> 4 -> 3 -> 2 -> 1 -> null
         *
         * prev는 이미 뒤집힌 앞부분의 head를 가리킴
         * current는 아직 뒤집지 않은 나머지의 head를 가리킴
         */

        public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {

            SinglyLinkedListNode prev = null;
            SinglyLinkedListNode current = llist;
            SinglyLinkedListNode next = null;

            while (current != null) {

                // 다음 노드 저장
                next = current.next;

                // 방향 반전
                current.next = prev;

                // prev, current 이동
                prev = current;
                current = next;
            }

            // prev 가 새로운 head
            return prev;
        }

        static void printList(SinglyLinkedListNode head) {
            SinglyLinkedListNode current = head;
            while (current != null) {
                System.out.print(current.data + (current.next != null ? " -> " : ""));
                current = current.next;
            }
            System.out.println();
        }

        static SinglyLinkedListNode buildList(int... values) {
            if (values.length == 0) return null;
            SinglyLinkedListNode head = new SinglyLinkedListNode(values[0]);
            SinglyLinkedListNode current = head;
            for (int i = 1; i < values.length; i++) {
                current.next = new SinglyLinkedListNode(values[i]);
                current = current.next;
            }
            return head;
        }

        // 테스트
        public static void main(String[] args) {
            SinglyLinkedListNode head = buildList(1, 2, 3, 4, 5);
            System.out.println("Before:");
            printList(head);

            head = reverse(head);

            System.out.println("After:");
            printList(head);
        }







        // 재귀버전

        /**
         *
         * reverseRecursive(1)
         *   → reverseRecursive(2)
         *     → reverseRecursive(3)
         *       → reverseRecursive(4)
         *         → reverseRecursive(5)  (base case)
         */
        public static SinglyLinkedListNode reverseRecursive(SinglyLinkedListNode llist) {

            // base case: 빈 리스트거나 노드 1개
            if (llist == null || llist.next == null) {
                return llist;
            }

            // 나머지 리스트 뒤집기
            SinglyLinkedListNode newHead = reverseRecursive(llist.next);

            // 방향 반전
            llist.next.next = llist;
            llist.next = null;

            return newHead;
        }
    }
}
