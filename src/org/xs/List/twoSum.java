package org.xs.List;

public class twoSum {

    public twoSum() {
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1 = 0;
        int num2 = 0;
        for (; l1 != null; l1 = l1.next) {
            num1 *= 10;
            num1 += l1.val;
        }

        for (; l2 != null; l2 = l2.next) {
            num2 *= 10;
            num2 += l2.val;
        }

        System.out.println((new StringBuilder()).append(l1).append("...").append(l2).toString());
        return null;
    }
}
