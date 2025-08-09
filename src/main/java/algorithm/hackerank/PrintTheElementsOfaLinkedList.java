package algorithm.hackerank;

//https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list/problem?utm_source=chatgpt.com
public class PrintTheElementsOfaLinkedList {

    public static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;
    }


    static void printLinkedList(SinglyLinkedListNode head) {

        SinglyLinkedListNode cur = head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }
}
