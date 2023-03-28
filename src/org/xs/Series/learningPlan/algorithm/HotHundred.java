package org.xs.Series.learningPlan.algorithm;

import org.junit.Test;
import org.xs.List.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年03月02日 21:12
 */
public class HotHundred {

    /**
     * 141. 环形链表
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
//        ListNode pre = new ListNode(0);
//        pre.next = head;
        List<ListNode> lists = new ArrayList<>();
        while (head.next != null) {
            if (lists.contains(head)) {
                return true;
            }
            lists.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    @Test
    public void maxProfitTest() {
        System.out.println(maxProfit(new int[]{1,2}));
    }


}
