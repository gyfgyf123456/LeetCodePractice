package 剑指offer;

import org.junit.Test;

import java.util.*;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年03月27日 16:16
 */
public class Practice1 {
    /**
     * 剑指 Offer II 001. 整数除法
     * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
     */
    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        boolean positive=true;
        if (a > 0) {
            positive= false;
            a = -a;
        }
        if (b > 0) {
            positive=!positive;
            b = -b;
        }
        int res = div(a, b);
        return positive? res : -res;
    }

    public int div(int a, int b) {
        // 传入的a，b都是负数
        int res = 0;
        while (a <= b) {//a的绝对值更大
            int temp = b;
            int count = 1;
            while (temp >= 0xc0000000 && a <= temp + temp) {
                count += count;
                temp += temp;
            }
            a -= temp;
            res += count;
        }
        return res;
    }

    @Test
    public void divideTest() {
        System.out.println(divide(23, 2));
    }

    /**
     * 剑指 Offer II 002. 二进制加法
     * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
     * 输入为 非空 字符串且只包含数字 1 和 0。
     */
    public String addBinary(String a, String b) {
        long a1 = Long.parseLong(a, 2);
        long b1 = Long.parseLong(b, 2);
        return Long.toBinaryString(a1 + b1);
    }


    /**
     * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
     * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
     */
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }

    /**
     * 剑指 Offer II 004. 只出现一次的数字
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> table = new HashMap<>();
        for (int i : nums) {
            table.put(i, table.getOrDefault(i, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : table.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return 0;
    }

    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num: nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer II 005. 单词长度的最大乘积
     * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
     * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
     *
     * 可以使用位运算预处理每个单词，通过位运算操作判断两个单词是否有公共字母。由于单词只包含小写字母，共有 26 个小写字母，
     * 因此可以使用位掩码的最低 26 位分别表示每个字母是否在这个单词中出现。
     * 将 a到 z分别记为第 0 个字母到第 25 个字母，则位掩码的从低到高的第 i 位是 1 当且仅当第 i 个字母在这个单词中，其中 0≤i≤25。
     * 用数组 masks记录每个单词的位掩码表示。计算数组 masks之后，判断第 i 个单词和第 j 个单词是否有公共字母可以通过判断 masks[i]&masks[j]是否等于 0 实现，当且仅当
     * masks[i]&masks[j]=0时第 i 个单词和第 j 个单词没有公共字母，此时使用这两个单词的长度乘积更新单词长度的最大乘积。
     */
    public int maxProduct(String[] words) {
        int length = words.length;
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int maxProd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        return maxProd;
    }

    /**
     * 剑指 Offer II 006. 排序数组中两个数字之和
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。
     * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if(nums == null || nums.length == 0){
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int temp = target - nums[i];   // 遍历当前元素，并在map中寻找是否有匹配的key
            if(map.containsKey(temp)){
                res[1] = i;
                res[0] = map.get(temp);
                break;
            }
            map.put(nums[i], i);    // 如果没找到匹配对，就把访问过的元素和下标加入到map中
        }
        return res;
    }
    // 双指针
    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            }
            if (sum > target) {
                j--;
            }
            if (sum == target) {
                res[0] = i;
                res[1] = j;
                break;
            }
        }
        return res;
    }

    /**
     * 剑指 Offer II 007. 数组中和为 0 的三个数
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 排序后双指针：题目没有要求给出下标，只需要数值，可以先排序，同时由于存在重复元素，要考虑如何去重
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                }else {
                    Integer[] arr = {nums[i], nums[j], nums[k]};
                    res.add(new ArrayList<>(Arrays.asList(arr)));
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;
                    j++;
                    k--;
                }
            }
        }
        return res;
    }
    @Test
    public void threeSumTest() {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 剑指 Offer II 008. 和大于等于 target 的最短子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的
     * 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */
    public int minSubArrayLen(int target, int[] nums) {
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int min = nums.length + 1;
        for (int j = 0; j < preSum.length; j++) {
            if (preSum[j] >= target) {
                int i = 0;
                while (preSum[j] - preSum[i] >= target) {
                    i++;
                }
                min = Math.min(min, j - i + 1);
            }
        }
        return min == nums.length + 1 ? 0 : min;
    }

    // 滑动窗口，常见写法：
    // for（窗口有边界不断右移）{
    //  while（满足题目条件）{
    //      更新左边界
    //  }
    // }
    // 或者：
    // while(left < right) {
    //    if(满足条件){
    //    }
    // }
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 剑指 Offer II 009. 乘积小于 K 的子数组
     * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums[0] > k) return 0;
        long mul = 1L;
        int ans = 0, i = 0;
        for (int j = 0; j < nums.length; j++) {
            mul *= nums[j];
            while (i <= j && mul >= k) {
                mul /= nums[i];
                i++;
            }
            ans += j - i +1;
        }
        return ans;
    }

    @Test
    public void numSubarrayProductLessThanKTest() {
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }


    /**
     * 剑指 Offer II 010. 和为 k 的子数组
     * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
     */
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 1) return nums[0] == k ? 1 : 0;
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int ans = 0;
        HashMap<Integer, Integer> table = new HashMap<>();
        table.put(k, -1);
        for (int i = 0; i < nums.length; i++) {
            table.put(k + preSum[i], i);
            if (table.containsKey(preSum[i])) {
                ans++;
            }
        }
        return ans;
    }

    @Test
    public void subarraySumTest() {
        System.out.println(subarraySum(new int[]{2}, 0));
    }
}
