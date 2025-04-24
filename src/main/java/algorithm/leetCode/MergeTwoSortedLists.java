package algorithm.leetCode;

import Array.ListNode;

public class MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1,
                                  ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;

        while (list1 != null && list2 != null) {

            // l2가 더 큰 경우
            if (list1.val <= list2.val) {
                // 다음 노드는 l1 으로 지정 (더 작으니깐)
                node.next = list1;
                list1 = list1.next;
            } else {
                //
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }

        node.next = (list1 != null ? list1 : list2);
        return dummy.next;
    }
}
