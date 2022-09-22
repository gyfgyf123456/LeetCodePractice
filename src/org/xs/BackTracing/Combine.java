package org.xs.BackTracing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backTracing(n, k, path, allCombine, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void combineTest(){
        System.out.println(combine(2, 2));
    }
}
