package org.xs.Series.learningPlan.datastructure;

import org.junit.Test;
import org.xs.List.ListNode;

import java.util.*;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年03月02日 21:13
 */
public class Elementary {


    /**
     * 217. 存在重复元素
     * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (Integer i : nums) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() >= 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * 注意到题目要求只求出最大和，而不需要求出具体的子数组，可以考虑动态规划
     * 子问题：以num[i]结尾的连续子数组的最大和
     * 关系：以num[i]结尾的连续子数组的最大和与以nums[i - 1]结尾的连续子数组的最大和之间的关系是什么？
     *      1.如果以nums[i - 1]结尾的连续子数组的最大和是负数，那么以nums[i]结尾的连续子数组的最大和就是nums[i]
     *      2.否则就是以nums[i - 1]结尾的连续子数组的最大和+nums[i]
     * 总结：dp[0] = nums[0],dp[i] = max(dp[i - 1] + nums[i], nums[i])
     * 可以认为是最大前缀和问题？
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
    }

    @Test
    public void mergeTest() {
        merge(new int[]{1,2,3,0,0},3,new int[]{2,5,6},3);
    }

    /**
     * 566. 重塑矩阵
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
     * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length, n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        List<Integer> list = new ArrayList<>();
        int[][] newMatrix = new int[r][c];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                list.add(mat[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newMatrix[i][j] = list.get(i * c + j);
            }
        }
        return newMatrix;
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>(5);
        ArrayList<Integer> list1 = new ArrayList<>(1);
        list1.add(1);
        lists.add(list1);
        for (int i = 1; i < numRows; i++) {
            ArrayList<Integer> listN = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    listN.add(1);
                }else {
                    listN.add(lists.get(i - 1).get(j - 1) + lists.get(i - 1).get(j));
                }
            }
            lists.add(listN);
        }
        return lists;
    }

    @Test
    public void generateTest() {
        generate(5);
    }

    /**
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     */
    public void setZeroes(int[][] matrix) {
        int[][] temp = new int[matrix.length][matrix[0].length];
        for (int i = 0;i < matrix.length;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < matrix[0].length; k++) {
                        temp[i][k] = 0;
                    }
                    for (int k = 0; k < matrix.length; k++) {
                        temp[k][j] = 0;
                    }
                }
            }
        }
        for (int i = 0;i < matrix.length;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }


    /**
     * 387. 字符串中的第一个唯一字符
     * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
     */
    public int firstUniqChar(String s) {
        int[] record = new int[26];
        for (int i = 0;i < s.length();i++) {
            record[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (record[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 383. 赎金信
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * 如果可以，返回 true ；否则返回 false 。
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            table[ransomNote.charAt(i) - 'a'] -= 1;
        }
        for (int i = 0; i < magazine.length(); i++) {
            table[magazine.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void canConstruct() {
        canConstruct("aa", "aab");
    }

    /**
     * 242. 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a'] -= 1;
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 206. 反转链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ArrayList<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (i == 0) {
                list.get(i).next = null;
            }else {
                list.get(i).next = list.get(i - 1);
            }
        }
        return list.get(list.size() - 1);
    }

    /**
     *83. 删除排序链表中的重复元素
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ret = deleteDuplicates(head.next);
        if (head.val == ret.val) {
            head.next = ret.next;
        }else {
            head.next = ret;
        }
        return head;
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 每个右括号都有一个对应的相同类型的左括号。
     */
    public boolean isValid(String s) {
        if (s.length() < 2 || s.length() % 2 == 1) {
            return false;
        }
        HashMap<Character, Character> table = new HashMap<>();
        table.put(')', '(');
        table.put('}', '{');
        table.put(']', '[');
        table.put('?', '?');
        LinkedList<Character> stack = new LinkedList<>();
        stack.push('?');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            }else {
                Character pop = stack.pop();
                if (table.get(s.charAt(i)) != pop) {
                    return false;
                }
            }
        }
        return stack.size() == 1;
    }

    @Test
    public void isValidTest() {
        System.out.println(isValid("()"));
    }



}
/**
 * 232. 用栈实现队列
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * 说明：
 * 你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 */
class MyQueue {

    private LinkedList<Integer> stack1 = new LinkedList<>();

    private LinkedList<Integer> stack2 = new LinkedList<>();

    public MyQueue() {

    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        while (!stack1.isEmpty()) {
            Integer pop = stack1.pop();
            stack2.push(pop);
        }
        return stack2.pop();
    }

    public int peek() {
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
