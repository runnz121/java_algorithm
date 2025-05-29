package algorithmKotlin.leetCode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

//  투 포인터 (Floyd's Cycle Detection) → 공간 O(1), 시간 O(n)

class LinkedListCycle {
    fun hasCycle(head: ListNode?): Boolean {

        var slow = head
        var fast = head

        while (fast != null && fast.next != null) {

            // 한번에 한칸 이동
            slow = slow?.next

            // 한번에 두칸이동
            fast = fast.next?.next

            // 사이클이 존재하면 반드시 만남
            if (slow == fast) {
                return true
            }
        }

        return false
    }
}

fun main() {
    // Example: head = [3,2,0,-4], pos = 1
    val node1 = ListNode(3)
    val node2 = ListNode(2)
    val node3 = ListNode(0)
    val node4 = ListNode(-4)

    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node2 // create cycle here (pos = 1)

    val solution = LinkedListCycle()
    val result = solution.hasCycle(node1)

    println("Has cycle: $result")  // Expected output: true
}