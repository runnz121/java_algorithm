package linkedList;

import java.util.Objects;

public class LinkedList {

    private Node head; // 첫 번째 노드를 지정하는 "참조값"
    private Node tail; // 마지막 노드
    private int size = 0;

    private static class Node {
        private Object data; // 데이터가 저장될 필드
        private Node next; // 다음 노드를 가르키는 필드

        public Node(Object input) {
            this.data = input;
            this.next = null;
        }

        public String toString() {
            return String.valueOf(this.data);
        }
    }

    // 추가 -> 원하는 노드를 만들고 그 노드와 tail 노드의 next point를 연결 (마지막 노드가 가르키고 있는 null 위치에 추가)
    public void add(Object input) {

        // 헤드 노드 부터 시작
        Node curr = this.head;

        // null 이전까지 순회 -> tail 쪽으로 순회
        while (curr != null) {
            curr = curr.next;
        }
        // 새로운 노드 생성
        Node node = new Node(input);
        // curr.next는 현재 tail 부분임으로 이 다음 노드를 새로 생성한 노드로 할당
        curr.next = node;
        // 사이즈 증가
        this.size++;
    }

    // 시작 부분에 추가 (맨 앞쪽)
    public void addFirst(Object input) {
        // 노드 생성
        Node newNode = new Node(input);
        // 새로운 노드의 다음 노드를 해드로 지정
        newNode.next = head;

        head = newNode;
        size ++;

        if (head.next == null) {
            tail = head;
        }
    }

    // 마지막에 추가
    public void addLast(Object input) {
        // 노드 생성
        Node newNode = new Node(input);
        // 리스트의 노드가 없을 경우 첫번째 노드에 추가
        if (size == 0) {
            addFirst(input);
        } else {
            // 마지막 노드에 다음 노드로 새로운 노드 지정
            tail.next = newNode;
            // 마지막 노드를 새로운 노드로 갱신
            tail = newNode;
            size++;
        }
    }
}
