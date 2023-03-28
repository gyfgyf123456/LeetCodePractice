package org.xs.Series;

import org.junit.Test;

import java.util.*;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年02月10日 15:23
 */
public class Traning {

    /*编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串 ""。*/
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }



    /*现有一台饮水机，可以制备冷水、温水和热水。每秒钟，可以装满 2 杯 不同 类型的水或者 1 杯任意类型的水。
    给你一个下标从 0 开始、长度为 3 的整数数组 amount ，其中 amount[0]、amount[1] 和 amount[2] 分别表示需要装满冷水、温水和热水的杯子数量。返回装满所有杯子所需的 最少 秒数。*/
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[2] > amount[1] + amount[0]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }


    /*1234. 替换子串得到平衡字符串
    有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
    假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
    给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
    你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
    请返回待替换子串的最小可能长度。
    如果原字符串自身就是一个平衡字符串，则返回 0。*/
    // 替换字符串作为一个子串，不能改变剩余子串中其他字母的数量，意味着剩余的子串中每个字母的个数必须小于等于n/4，这也是判断可替换的子串是否符合要求的条件
    // 因此可以通过遍历子串左端点left和右端点right的方式寻找所有满足要求的子串，返回其中最小的子串长度
    public int balancedString(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt[idx(c)]++;
        }

        int partial = s.length() / 4;
        int res = s.length();

        if (check(cnt, partial)) {
            return 0;
        }
        for (int l = 0, r = 0; l < s.length(); l++) {
            while (r < s.length() && !check(cnt, partial)) {
                cnt[idx(s.charAt(r))]--;
                r++;
            }
            if (!check(cnt, partial)) {
                break;
            }
            res = Math.min(res, r - l);
            cnt[idx(s.charAt(l))]++;
        }
        return res;
    }

    public int idx(char c) {
        return c - 'A';
    }

    public boolean check(int[] cnt, int partial) {
        if (cnt[idx('Q')] > partial || cnt[idx('W')] > partial || cnt[idx('E')] > partial || cnt[idx('R')] > partial) {
            return false;
        }
        return true;
    }

    /*2341. 数组能形成多少数对
    给你一个下标从 0 开始的整数数组 nums 。在一步操作中，你可以执行以下步骤：
    从 nums 选出 两个 相等的 整数
    从 nums 中移除这两个整数，形成一个 数对
    请你在 nums 上多次执行此操作直到无法继续执行。
    返回一个下标从 0 开始、长度为 2 的整数数组 answer 作为答案，其中 answer[0] 是形成的数对数目，answer[1] 是对 nums 尽可能执行上述操作后剩下的整数数目。*/
    public int[] numberOfPairs(int[] nums) {
        if (nums.length == 1) {
            return new int[]{0, 1};
        }
        int[] count = new int[101];
        int[] answer = new int[2];
        for (int i : nums) {
            count[i]++;
        }
        for (int j : count) {
            answer[0] += j / 2;
            answer[1] += j % 2;
        }
        return answer;
    }


    @Test
    public void test() {
        //System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        //fillCups(new int[]{5, 4, 4})
        System.out.printf(Arrays.toString(numberOfPairs(new int[]{1,2,3,4,5})));
    }

    /*2363. 合并相似的物品
    给你两个二维整数数组 items1 和 items2 ，表示两个物品集合。每个数组 items 有以下特质：
    items[i] = [valuei, weighti] 其中 valuei 表示第 i 件物品的 价值 ，weighti 表示第 i 件物品的 重量 。
    items 中每件物品的价值都是 唯一的 。
    请你返回一个二维数组 ret，其中 ret[i] = [valuei, weighti]， weighti 是所有价值为 valuei 物品的 重量之和 。
    注意：ret 应该按价值 升序 排序后返回。*/
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        HashMap<Integer, Integer> retMap = new HashMap<>();
        List<List<Integer>> ret = new ArrayList<>();
        for (int[] item : items1) {
            retMap.put(item[0], item[1]);
        }
        for (int[] item : items2) {
            retMap.put(item[0], retMap.getOrDefault(item[0], 0) + item[1]);
        }
        for (Map.Entry<Integer, Integer> set: retMap.entrySet()) {
            ArrayList<Integer> item = new ArrayList<>();
            item.add(set.getKey());
            item.add(set.getValue());
            ret.add(item);
        }
        ret.sort(Comparator.comparingInt(o -> o.get(0)));
        return ret;
    }
    @Test
    public void testMergeSimilarItems() {
        //System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        //fillCups(new int[]{5, 4, 4})
        System.out.println(mergeSimilarItems(new int[][]{{1, 1}, {4, 5}, {3, 8}}, new int[][]{{3, 1}, {1, 5}}));
    }

    /*
    * 50. Pow(x, n)
    实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。*/
    public double myPow(double x, int n) {
        return n >=0 ? myPow_iter(x, n) : 1.0 / myPow_iter(x, -n);
    }
    public double myPow_iter(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        double y = myPow_iter(x, n/2);
        return n % 2 == 0 ? y * y : y * y * x;
    }

    @Test
    public void testmyPow_iter() {
        //System.out.println(myPow(2.0, -3));
        Integer a = 127;
        Integer b = 127;
        Integer a2 = 128;
        Integer b2 = 128;
        Integer c = new Integer(127);
        Integer d = 127 + 127;
        System.out.println(d.equals(a+b));
    }

    /*
    * 2373. 矩阵中的局部最大值
    给你一个大小为 n x n 的整数矩阵 grid 。
    生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：
    maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
    换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
    返回生成的矩阵。
    */
    public int[][] largestLocal(int[][] grid) {
        int[][] maxLocal = new int[grid.length - 2][grid.length - 2];
        for (int i = 0; i < maxLocal.length; i++) {
            for (int j = 0; j < maxLocal[i].length; j++) {
                maxLocal[i][j] = getLocalMax(grid, i + 1, j + 1);
            }
        }
        return maxLocal;
    }

    private int getLocalMax(int[][] grid, int i, int j) {
        int max = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                max = Math.max(max, grid[k][l]);
            }
        }
        return max;
    }


    /**
     * 61. 旋转链表
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode cur;
        cur = head;
        int len = 1;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        cur = head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        k = k % len;
        for (int i = 0; i < len - k; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        if (cur == null) {
            return head;
        }
        ListNode newHead = cur;
        pre.next = null;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        return newHead;
    }

    @Test
    public void rotateRightTest() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        rotateRight(head, 0);
    }

    /**
     * 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int m = nums1.length, n = nums2.length;
        if (m == 0) {
            return n % 2 == 0 ? (double) (nums2[n / 2 - 1] + nums2[n / 2]) / 2 : (double) nums2[n / 2];
        }
        if (n == 0) {
            return m % 2 == 0 ? (double) (nums1[m / 2 - 1] + nums1[m / 2]) / 2 : (double) nums1[m / 2];
        }
        int[] ints = new int[m + n];
        while (i < m || j < n) {
            int cur = 0;
            if (i < m && j < n && nums1[i] < nums2[j]) {
                cur = nums1[i++];
            }else if (i < m && j < n && nums1[i] >= nums2[j]){
                cur = nums2[j++];
            }
            else if (j == n) {
                cur = nums1[i++];
            }
            else if (i == m) {
                cur = nums2[j++];
            }
            ints[i + j - 1] = cur;
        }
        int l = m + n;
        if (l % 2 == 0) {
            return (double) (ints[(m + n) / 2 - 1] + ints[(m + n) / 2]) / 2;
        }else {
            return (double) ints[(m + n) / 2];
        }
    }

    @Test
    public void findMedianSortedArraysTest() {
        findMedianSortedArrays(new int[]{1,3},new int[]{2});
    }

    public int getMaxStableArrayLen(int n, int[] array) {
        if (n <= 1) {
            return 1;
        }
        int result = 1;
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (Math.abs(array[i] - array[i - 1]) <= 1) {
                len++;
            }else {
                len = 1;
            }
            result = Math.max(len, result);
        }

        return result;
    }

    @Test
    public void getMaxStableArrayLenTest() {
        System.out.println(getMaxStableArrayLen(5, new int[]{2, 4, 2, 3, 2}));
    }

    public String doOperation(int n, int q, String s, int[][] lr) {
        String temp = s;
        for (int i = 0; i < q; i++) {
            temp = repeatSubString(temp, lr[i]);
        }
        return temp;
    }

    public String repeatSubString(String s, int[] lr) {
        StringBuffer sb = new StringBuffer();
        int l = lr[0], r = lr[1];
        String repeatString = s.substring(l - 1, r);
        for (int j = 0; j < s.length(); j++) {
            if (j == l - 1) {
                for (int k = 0; k < repeatString.length(); k++) {
                    sb.append(repeatString.charAt(k));
                    sb.append(repeatString.charAt(k));
                }
                j = r - 1;
            }
            else {
                sb.append(s.charAt(j));
            }
        }
        return sb.toString();
    }

    @Test
    public void repeatSubStringTest() {
        System.out.println(doOperation(6, 2, "abcdef", new int[][]{{2, 4}, {3, 6}}));
        System.out.println(getMinTime(2, 2, 4));
        //maxFavor(4, 7, new int[]{2, 2, 6, 2},new int[]{3, 4, 5, 7});
    }

    public double getMinTime(int v0, int x, int y) {
        double t = ((Math.sqrt((double) y/x) - v0) / x);
        return (t + (double) y/(v0 + x*t));
    }

    public static int maxFavor(int n, int x, int[] a, int[] b) {
        int[][] dp = new int[n+1][x+1]; // dp[i][j]表示前i个商品在总价格不超过j的情况下能得到的最大喜爱度
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                dp[i][j] = dp[i-1][j]; // 先赋值为不购买第i个商品的情况
                if (j >= a[i-1]) { // 如果有足够的钱购买第i个商品
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-a[i-1]] + b[i-1]); // 不购买或购买第i个商品两种情况中取最大值
                    if (j >= 2 * a[i-1]) { // 如果有足够的钱购买第i和i+1个商品
                        dp[i][j] = Math.max(dp[i][j], dp[i-2][j-2*a[i-1]] + b[i-1] + b[i-2]); // 不购买或购买第i和i+1个商品两种情况中取最大值
                    }
                }
            }
        }
        return dp[n][x];
    }

    /**
     * 724. 寻找数组的中心下标
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     * 数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
     * 如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     */
    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int leftsum = 0;
        int rightsum = 0;
        for (int i = 0; i < nums.length; i++) {
            leftsum += nums[i];
            rightsum = sum - leftsum + nums[i];
            if (leftsum == rightsum) return i;
        }
        return -1;
    }
    /**
     * 33. 搜索旋转排序数组
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[left] <= nums[middle]) {
                if (nums[left] <= target && nums[middle] > target) {
                    right = middle - 1;
                }else {
                    left = middle + 1;
                }
            }else {
                if (nums[middle] <= target && target < nums[right]) {
                    left = middle + 1;
                }else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 74. 搜索二维矩阵
     * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][n - 1] >= target) {
                int left = 0, right = n - 1;
                while (left <= right) {
                    int middle = left + (right - left) / 2;
                    if (matrix[i][middle] == target) {
                        return true;
                    }
                    if (matrix[i][middle] > target) {
                        right = middle - 1;
                    }
                    if (matrix[i][middle] < target) {
                        left = middle + 1;
                    }
                }
            }
        }
        return false;
    }

    public void getMinPeachNum() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] table = new int[4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                table[j] = scanner.nextInt();
            }
            int a = table[0], b = table[1], c = table[2], d = table[3];
            if (a < c) {
                System.out.println(1);
            } else if (a == c) {
                if (b > d) {
                    System.out.println(b);
                }else {
                    System.out.println(-1);
                }
            } else {
                if (b <= d) {
                    System.out.println(-1);
                }
                else if ((b + d - 1) / d * c > a) {
                    System.out.println(b);
                }
                else System.out.println(-1);
            }
        }
    }



}
