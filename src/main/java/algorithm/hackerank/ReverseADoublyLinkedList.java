package algorithm.hackerank;

public class ReverseADoublyLinkedList {

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }
    }

    public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {
        return reverseNode(llist, null);
    }

    public static DoublyLinkedListNode reverseNode(DoublyLinkedListNode node,
                                                   DoublyLinkedListNode prev) {

        if (node == null) {
            return prev;
        }

        // 현재 노드의 다음 노드를 임시로 저장
        DoublyLinkedListNode next = node.next;

        // 현재 노드의 다음 노드를 이전 노드로 변경
        node.next = prev;

        // 현재 노드의 이전 노드를 다음으로 변경
        node.prev = next;

        // 역순임으로 현재 : 다음거, 이전거 : 현재
        return reverseNode(next, node);
    }

    public static void main(String[] args) {
        ReverseADoublyLinkedList a = new ReverseADoublyLinkedList();
    }
}
