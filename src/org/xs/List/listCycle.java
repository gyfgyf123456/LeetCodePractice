package org.xs.List;

public class listCycle {

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode index1 = head;
                for (ListNode index2 = fast; index1 != index2; index2 = index2.next)
                    index1 = index1.next;

                return index1;
            }
        }
        return null;
    }
}
