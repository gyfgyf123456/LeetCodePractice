package org.xs.Series;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年02月09日 15:58
 */
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


public class TwoNumsPlus {

    /*给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
    请你将两个数相加，并以相同形式返回一个表示和的链表。
    你可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/
    //注意逆序，可以从低到高进位 直接转成数字相加会有溢出问题
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {

            int i1 = l1 != null ? l1.val:0;
            int i2 = l2 != null ? l2.val:0;
            int sum = i1 + i2 + carry;

            carry = (sum) / 10;
            int val = (sum) % 10;

            cur.next = new ListNode(val);
            cur = cur.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) cur.next = new ListNode(1);

        return pre.next;
    }

    /*将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ArrayList<Integer> mergeList = new ArrayList<>();
        while (list1 != null) {
            mergeList.add(list1.val);
            list1 = list1.next;
        }
        while (list2 != null) {
            mergeList.add(list2.val);
            list2 = list2.next;
        }
        mergeList.sort(Comparator.comparingInt(o -> o));
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        for (int i = 0; i < mergeList.size(); i++) {
            cur.next = new ListNode(mergeList.get(i));
            cur = cur.next;
        }
        return pre.next;
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        mergeTwoLists(l1, l2);
    }
}
