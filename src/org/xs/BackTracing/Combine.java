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
    //设置全局列表存储最后的结果
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

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    //比如digits如果为"23",num 为0，则str表示2对应的 abc
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
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTracing_combinationSum(candidates, target, 0);
        return result;
    }

    private void backTracing_combinationSum(int[] candidates, int target, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }
        for (int i = 0; i < candidates.length; i++) {
            int[] nextCandidates = Arrays.copyOfRange(candidates, i, candidates.length);
            path.add(candidates[i]);
            backTracing_combinationSum(nextCandidates, target, sum + candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}
