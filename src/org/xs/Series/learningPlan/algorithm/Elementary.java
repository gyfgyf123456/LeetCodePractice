package org.xs.Series.learningPlan.algorithm;

import org.junit.Test;
import org.xs.BinaryTree.TreeNode;
import org.xs.List.ListNode;

import java.util.*;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年03月01日 19:53
 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

public class Elementary {
    /**
     * 704. 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] > target) {
                right = middle - 1;
            }
            if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        return -1;
    }

    @Test
    public void searchTest() {
        System.out.println(search(new int[]{7}, 7));
    }

    /**
     278. 第一个错误的版本
     你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     */
    public int firstBadVersion(boolean[] version) {
        if(version[1]) {
            return 1;
        }
        int left = 0;
        int right = version.length - 1;
        while(left <= right) {
            int middle = left + (right - left) / 2;
            if(!version[middle]) {
                left = middle + 1;
            }
            if (version[middle]) {
                right = middle - 1;
            }
        }
        return left;
    }

    @Test
    public void firstBadVersionTest() {
        System.out.println(firstBadVersion(new boolean[]{false, false, false, true, true}));
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] > target) {
                right = middle - 1;
            }
            if (nums[middle] < target) {
                left = middle + 1;
            }
        }
        return left;
    }

    /**977. 有序数组的平方
     给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * */
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0, right = nums.length - 1,n = nums.length - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                res[n--] = nums[left] * nums[left];
                left++;
            }else {
                res[n--] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }

    @Test
    public void sortedSquaresTest() {
        sortedSquares(new int[]{-7,-3,2,3,11});
    }

    /**
     * 189. 轮转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * 实际上最后 (i + k) mod n 位置 即为原来 i 所在元素的位置*/
    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }

    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }


    @Test
    public void rotateTest() {
        rotate(new int[]{1,2}, 2);
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {
        int[] nums2 = new int[nums.length];
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums2[j++] = nums[i];
            }
        }
        System.arraycopy(nums2, 0, nums, 0, nums.length);
    }

    public void moveZeroes2(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     */
    // 利用哈希表没有利用有序性
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (hashMap.containsKey(numbers[i])) {
                return new int[]{hashMap.get(numbers[i]) + 1, i + 1};
            }
            hashMap.put(target - numbers[i], i);
        }

        return null;
    }
    // 双指针
    public int[] twoSum2(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }


    @Test
    public void twoSumTest() {
        System.out.println(Arrays.toString(twoSum(new int[]{-1, 0}, -1)));
    }

    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     */
    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 557. 反转字符串中的单词 III
     * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     */
    public String reverseWords(String s) {
        int j = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                char[] chars = s.substring(j, i).toCharArray();
                reverseString(chars);
                sb.append(chars);
                sb.append(' ');
                j = i + 1;
            }
            else if (i == s.length() - 1) {
                sb.append(' ');
                char[] chars = s.substring(j, i + 1).toCharArray();
                reverseString(chars);
                sb.append(chars);
            }
        }
        return sb.toString().trim();
    }

    /**
     *350. 两个数组的交集 II
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int k : nums1) {
            list.add(k);
        }
        for (int j : nums2) {
            if (list.contains(j)) {
                result.add(j);
                list.remove(Integer.valueOf(j));
            }
        }
        int[] intersect = new int[result.size()];
        for (int i = 0; i < intersect.length; i++) {
            intersect[i] = result.get(i);
        }
        return intersect;
    }

    /**
     * 876. 链表的中间结点
     * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(0);
        pre.next = head;
        int len = 1, middle;
        while (head.next != null) {
            len++;
            head = head.next;
        }
        middle = len / 2 + 1;
        while (middle-- > 0) {
            pre = pre.next;
        }
        return pre;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }
        int len = 1;
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur;
        pre.next = head;
        cur = head;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        int step = len - n;
//        if (step == 0) {
//            return head.next;
//        }
        while (step-- > 0) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }

    @Test
    public void removeNthFromEndTest() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
        removeNthFromEnd(head, 2);
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int result = 0;
        int maxLen = 0;
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i;j < s.length();j++) {
                if (count.containsKey(s.charAt(j)) && count.get(s.charAt(j)) >= 1) {
                    count.clear();
                    maxLen = 0;
                    break;
                }else {
                    count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);
                    maxLen++;
                }
                result = Math.max(result, maxLen);
            }
        }
        return result;
    }

    @Test
    public void lengthOfLongestSubstringTest() {
        lengthOfLongestSubstring("abcabcbb");
    }

    /**
     * 567. 字符串的排列
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        HashMap<Character, Integer> count1 = new HashMap<>();
        HashMap<Character, Integer> count2 = new HashMap<>();
        for (Character c : s1.toCharArray()) {
            count1.put(c, count1.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s2.length(); i++) {
            count2.clear();
            for (int j = i; j < i + s1.length(); j++) {
                if (i + s1.length() > s2.length()) {
                    return false;
                }else {
                    count2.put(s2.charAt(j), count2.getOrDefault(s2.charAt(j), 0) + 1);
                }
            }
            boolean flag = true;
            for (Character c : count1.keySet()) {
                if (!count2.containsKey(c) || !count2.get(c).equals(count1.get(c))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void checkInclusionTest() {
        checkInclusion("ab", "eidbaooo");
    }

    /**
     * 733. 图像渲染
     * 有一幅以 m x n 的二维整数数组表示的图画 image ，其中 image[i][j] 表示该图画的像素值大小。
     * 你也被给予三个整数 sr ,  sc 和 newColor 。你应该从像素 image[sr][sc] 开始对图像进行 上色填充 。
     * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，
     * 接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为 newColor 。
     * 最后返回 经过上色渲染后的图像 。
     */
    // 广度优先搜索
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int value = image[sr][sc];
        if (value == color) {
            return image;
        }
        int m = image.length;
        int n = image[0].length;
        image[sr][sc] = color;
        LinkedList<int[]> queue = new LinkedList<>();
        queue.push(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] pop = queue.pop();
            int x = pop[0], y = pop[1];

            if (x - 1 >= 0 && x - 1 < m && y >= 0 && y < n && image[x - 1][y] == value) {
                queue.push(new int[]{x - 1, y});
                image[x - 1][y] = color;
            }
            if (x + 1 >= 0 && x + 1 < m && y >= 0 && y < n && image[x + 1][y] == value) {
                queue.push(new int[]{x + 1, y});
                image[x + 1][y] = color;
            }
            if (x >= 0 && x < m && y + 1 >= 0 && y + 1 < n&& image[x][y + 1] == value) {
                queue.push(new int[]{x, y + 1});
                image[x][y + 1] = color;
            }
            if (x >= 0 && x < m && y - 1 >= 0 && y - 1 < n && image[x][y - 1] == value) {
                queue.push(new int[]{x, y - 1});
                image[x][y - 1] = color;
            }

        }
        return image;
    }


    @Test
    public void floodFillTest() {
        floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}}, 1, 1, 2);
    }
    /**
     * 695. 岛屿的最大面积
     * 给你一个大小为 m x n 的二进制矩阵 grid 。
     * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;
    }


    public int dfs(int[][] matrix, int x, int y) {
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] != 1) {
            return 0;
        }
        matrix[x][y] = 0;
        int ans = 1;
        for (int i = 0; i < 4; i++) {
            int newx = x + dx[i], newy = y + dy[i];
            if (newx >= 0 && newx < matrix.length && newy >= 0 && newy < matrix[0].length) {
                ans += dfs(matrix, newx, newy);
            }
        }
        return ans;
    }

    /**
     * 617. 合并二叉树
     * 给你两棵二叉树： root1 和 root2 。
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
     * 返回合并后的二叉树。
     * 注意: 合并过程必须从两个树的根节点开始。
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null) return root2;
        if (root2 == null && root1 != null) return root1;
        if (root1 == null && root2 == null) return null;

        TreeNode root = new TreeNode(root1.val + root2.val);

        root.left = mergeTrees(root1.left,root2.left);
        root.right = mergeTrees(root1.right,root2.right);

        return root;
    }

    @Test
    public void mergeTreesTest() {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        TreeNode root2 = new TreeNode(1);
        root1.right = new TreeNode(3);
        mergeTrees(root1, root2);
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            ArrayList<Node> curLayer = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Node curNode = nodeQueue.poll();
                curLayer.add(curNode);
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            for (int i = 0; i < size ; i++) {
                if (i == size - 1) {
                    curLayer.get(i).next = null;
                }else {
                    curLayer.get(i).next = curLayer.get(i + 1);
                }
            }
        }
        return root;
    }

    /**
     * 542. 01 矩阵
     * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
     * 两个相邻元素间的距离为 1 。
     * 广度优先搜索 : <a href="https://leetcode.cn/problems/01-matrix/solutions/202012/01ju-zhen-by-leetcode-solution/">
     * 多源最短路径问题：由于有多个0（源点）的存在，从某个1开始去寻找离其最近的0需要遍历所有的0，时间复杂度达不到题目要求；此时可以把所有的0看为一个整体
     * ，即一个超级0，求这个超级0（超级源点）离所有1的最短距离，即是所有1离最近0的距离
     */
    public int[][] updateMatrix(int[][] mat) {
        int[][] dis = new int[mat.length][mat[0].length];
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 0) {
                    dis[i][j] = Integer.MAX_VALUE;
                }else {
                    queue.offer(new int[]{i, j});
                    dis[i][j] = 0;
                }
            }
        }
        int[][] search = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] index = queue.poll();
            int x = index[0], y = index[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + search[i][0], newY = y + search[i][1];
                if (newX >= 0 && newX < mat.length && newY >= 0 && newY < mat[0].length) {
                    if (dis[newX][newY] > dis[x][y] + 1) {
                        dis[newX][newY] = dis[x][y] + 1;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
        return dis;
    }

    /**
     * 994. 腐烂的橘子
     * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
     * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     */
    public int orangesRotting(int[][] grid) {
        int[][] search = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int time = 0, m = grid.length, n = grid[0].length;
        boolean noFresh = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    noFresh = false;
                }
            }
        }
        if (noFresh) {
            return 0;
        }
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                for (int j = 0; j < 4; j++) {
                    int newX = x + search[j][0], newY = y + search[j][1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                        if (grid[newX][newY] == 1) {
                            grid[newX][newY] = 2;
                            queue.offer(new int[]{newX, newY});
                        }
                    }

                }
            }
            time++;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return time - 1;
    }

    @Test
    public void orangesRottingTest() {
        System.out.println(orangesRotting(new int[][]{{1}}));
    }

    public int change(int amount, int[] coins) {
        //递推表达式
        int[] dp = new int[amount + 1];
        //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;
//        for (int i = 0; i < coins.length; i++) {
//            for (int j = coins[i]; j <= amount; j++) {
//                dp[j] += dp[j - coins[i]];
//            }
//            System.out.println(Arrays.toString(dp));
//        }
        for (int j = 0; j <= amount; j++) {
            for (int i = 0; i < coins.length; i++) {
                if (j - coins[i] >= 0) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];
    }

    @Test
    public void changeTest() {
        change(5, new int[]{1, 2, 5});
    }

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     */
    ArrayList<Integer> path = new ArrayList<>();
    ArrayList<List<Integer>> combines = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
       combineBackTracing(n, k, 1);
       return combines;
    }

    public void combineBackTracing(int n, int k, int startIndex) {
        if (path.size() == k) {
            combines.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            combineBackTracing(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
