package org.xs.BackTracing;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2022年10月10日 11:21
 */
public class Permute {
    /*46. 全排列
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。*/

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return result;
        int[] used = new int[nums.length];
        backTracing_permute(nums, used);
        return result;
    }

    private void backTracing_permute(int[] nums, int[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) {
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backTracing_permute(nums, used);
            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void permuteTest() {
        System.out.println(permute(new int[]{1}));
    }

    /*47. 全排列 II
    给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。*/

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return result;
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        backTracing_permuteUnique(nums, used);
        return result;
    }

    private void backTracing_permuteUnique(int[] nums, int[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] & used[i - 1] == 0) {
                continue;
            }
            if (used[i] == 0) {
                path.add(nums[i]);
                used[i] = 1;
                backTracing_permuteUnique(nums, used);
                used[i] = 0;
                path.remove(path.size() - 1);
            }
        }
    }
}
