package org.xs.BackTracing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2022年09月22日 9:44
 */
public class Combine {

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    /*77. 组合
    给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
    你可以按 任何顺序 返回答案。*/
    public List<List<Integer>> combine(int n, int k) {
        int startIndex = 1;
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> allCombine = new ArrayList<>();
        backTracing(n, k, path, allCombine, startIndex);
        return allCombine;
    }

    private void backTracing(int n, int k, List<Integer> path, List<List<Integer>> allCombine, int startIndex) {
        if (path.size() == k) {
            // 注意不能直接add（path），这样做allCombine只记录了一个path的地址
            allCombine.add(new ArrayList<>(path));
            return;
        }
        // 组合问题中可以进行剪枝操作，例如k=4，n=4情况下，第一层循环从2开始没有意义，第二层循环从3开始没有意义
        // 可以通过限制i的起始位置进行剪枝
        // k - path.size() 还需要寻找的元素个数 ；n - (k - path.size()) + 1 当次循环i的结束位置（注意+1操作，因为i从startIndex开始）
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backTracing(n, k, path, allCombine, i + 1);
            path.remove(path.size() - 1);
        }
//        for (int i = startIndex; i <= n; i++) {
//            path.add(i);
//            backTracing(n, k, path, allCombine, i + 1);
//            path.remove(path.size() - 1);
//        }
    }

    @Test
    public void combineTest(){
        System.out.println(combine(2, 2));
    }

    /*216. 组合总和 III
    找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
    只使用数字1到9
    每个数字 最多使用一次
    返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。*/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> allCombine = new ArrayList<>();
        backTracing_combinationSum3(k, n, path, allCombine, 1);
        return allCombine;
    }

    private void backTracing_combinationSum3(int k, int n, List<Integer> path, List<List<Integer>> allCombine, int startIndex) {
        if (path.size() == k) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += path.get(i);
            }
            if (sum == n) {
                allCombine.add(new ArrayList<>(path));
                return;
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            backTracing_combinationSum3(k, n, path, allCombine, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void combinationSum3Test(){
        System.out.println(combinationSum3(9, 45));
    }

    /*17. 电话号码的字母组合
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。*/
    // 设置全局列表存储最后的结果
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代处理
        backTracking(digits, numString, 0);
        return list;

    }

    // 每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    // 比如digits如果为"23",num 为0，则str表示2对应的 abc
    public void backTracking(String digits, String[] numString, int num) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //c
            backTracking(digits, numString, num + 1);
            //剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    /*39. 组合总和
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
    找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
    candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
    对于给定的输入，保证和为 target 的不同组合数少于 150 个。*/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracing_combinationSum(candidates, target, 0);
        return result;
    }

    // 直接传递给下一层循环新的数组
    private void backTracing_combinationSum(int[] candidates, int target, int sum) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }
        // sum + candidates[i] < target 剪枝
        for (int i = 0; i < candidates.length && sum + candidates[i] < target; i++) {
            int[] nextCandidates = Arrays.copyOfRange(candidates, i, candidates.length);
            path.add(candidates[i]);
            backTracing_combinationSum(nextCandidates, target, sum + candidates[i]);
            path.remove(path.size() - 1);
        }
    }

    // 利用startIndex控制循环
    private void backTracing_combinationSum(int[] candidates, int target, int sum, int stratIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }
        for (int i = stratIndex; i < candidates.length && sum + candidates[i] < target; i++) {
            path.add(candidates[i]);
            backTracing_combinationSum(candidates, target, sum + candidates[i], i);
            path.remove(path.size() - 1);
        }
    }

    /*40. 组合总和 II
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    candidates 中的每个数字在每个组合中只能使用 一次 。（集合中有重复元素）
    注意：解集不能包含重复的组合。 */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] used = new int[candidates.length];
        Arrays.sort(candidates);
        backTracing_combinationSum2(candidates, target, used, 0, 0);
        return result;
    }

    /**
     * @description 由于有重复元素，需要有一个标志位来记录树形结构上树层出现相邻两元素相同并且已经查找过的情况，也就是说假如集合中有3个1，在第一层遍历
     * 时不需要考虑后面两个一，因为后两个1查找的结果一定会包含在第一个1之中；类似的有第二层遍历时不需要考虑第三个1，以此类推这样就避免了重复元素导致的重复结果。
     * 在树枝上重复元素则任意选取。
     * @author xs
     * @param candidates 有重复元素的集合（已排序）
     * @param target
     * @param used 已取元素标志位
     * @param sum
     * @param startIndex
     * @return void
     */
    private void backTracing_combinationSum2(int[] candidates, int target, int[] used, int sum, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }
        for (int i = startIndex; i < candidates.length && sum < target; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == 0) {
                continue;
            }
            used[i] = 1;
            path.add(candidates[i]);
            backTracing_combinationSum2(candidates, target, used, sum + candidates[i], i + 1);
            path.remove(path.size() - 1);
            used[i] = 0;
        }

    }
    /*131. 分割回文串
    给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
    回文串 是正着读和反着读都一样的字符串。*/
    List<String> partition = new ArrayList<>();
    List<List<String>> allPartition = new ArrayList<>();
    public List<List<String>> partition(String s) {
        backTracing_partition(s, 0);
        return allPartition;
    }

    private void backTracing_partition(String s, int startIndex) {
        if (startIndex >= s.length()) {
            allPartition.add(new ArrayList<>(partition));
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i + 1)) {
                partition.add(s.substring(startIndex, i + 1));
            }else continue;
            backTracing_partition(s, i + 1);
            partition.remove(partition.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int startIndex, int end) {
        if (s.length() == 0 || startIndex > end) return false;
        for (int i = startIndex,j = end - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void isPalindromeTest(){
        System.out.println(partition("aaaa"));
    }

    /*93. 复原 IP 地址
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
    给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
    你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。*/
    List<String> resultIP = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        if (s.length() < 4 || s.length() > 12) return resultIP;
        backTracing_restoreIpAddresses(stringBuilder, 0, 0);
        return resultIP;
    }

    private void backTracing_restoreIpAddresses(StringBuilder s, int startIndex, int pointNum) {
        if (pointNum == 3) {
            if (isValid(s, startIndex, s.length() - 1)) {
                resultIP.add(s.toString());
            }

        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s.insert(i + 1, ".");
                pointNum++;
                backTracing_restoreIpAddresses(s, i + 2, pointNum);
                pointNum--;
                s.deleteCharAt(i + 1);
            } else break;
        }
    }

    private boolean isValid(StringBuilder s, int start, int end) {
        if (start > end) return false;
        if (s.charAt(start) == '0' && start != end) return false;
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到非数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果大于255了不合法
                return false;
            }
        }
        return true;
    }

    /*78. 子集
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。*/
    public List<List<Integer>> subsets(int[] nums) {
        backTracing_subsets(nums, 0);
        return result;
    }

    /**
     * @description
     * 可以不写终止条件，因为本来我们就要遍历整棵树。
     * 有的同学可能担心不写终止条件会不会无限递归？
     * 并不会，因为每次递归的下一层就是从i+1开始的。也就是说函数最终总会执行完毕。
     * @author xs
     * @param nums
     * @param startIndex
     * @return void
     */
    private void backTracing_subsets(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backTracing_subsets(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /*90. 子集 II
    给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。*/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int[] usedNum = new int[nums.length];
        Arrays.sort(nums);
        backTracing_subsetsWithDup(nums, 0, usedNum);
        return result;
    }

    private void backTracing_subsetsWithDup(int[] nums, int startIndex, int[] usedNum) {
        result.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > 0 && usedNum[i - 1] == 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            usedNum[i] = 1;
            path.add(nums[i]);
            backTracing_subsetsWithDup(nums, i + 1, usedNum);
            path.remove(path.size() - 1);
            usedNum[i] = 0;
        }
    }

    @Test
    public void subsetsWithDupTest(){
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
    }


    /*491. 递增子序列
    给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
    数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。*/
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums,0);
        return res;
    }

    private void backtracking (int[] nums, int start) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }

        int[] used = new int[201];
        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) ||
                    (used[nums[i] + 100] == 1)) continue;
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
