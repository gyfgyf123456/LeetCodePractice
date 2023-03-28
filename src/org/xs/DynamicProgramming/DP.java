package org.xs.DynamicProgramming;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2022年10月11日 10:12
 */
public class DP {
    /*509. 斐波那契数
    斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
    给定 n ，请计算 F(n) 。*/
    public int fib(int n) {
        int p = 0, q = 0, r = 1;
        if (n < 2) return n;
        while (n > 1) {
            p = q;
            q = r;
            r = p + q;
            n--;
        }
        return r;
    }

    @Test
    public void fibTest() {
        System.out.println(fib(4));
    }

    /*70. 爬楼梯
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？*/
    public int climbStairs(int n) {
        if (n <= 1) return n;
        int[] dp = new int[3];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++){
            int sum = dp[1] + dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[2];
    }

    public int climbStairs_withoutArray(int n) {
        if (n <= 1) return n;
        int p = 1,q = 2,r = 2;
        for (int i = 3; i <= n; i++){
            r = p + q;
            p = q;
            q = r;
        }
        return r;
    }

    @Test
    public void climbStairsTest() {
        System.out.println(climbStairs_withoutArray(2));
    }


    /*746. 使用最小花费爬楼梯
    给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
    你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
    请你计算并返回达到楼梯顶部的最低花费。*/
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }

    /*62. 不同路径
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
    问总共有多少条不同的路径？
    */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*63. 不同路径 II
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
    现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
    网格中的障碍物和空位置分别用 1 和 0 来表示。*/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] != 1; i++) dp[i][0] = 1;
        for (int j = 0; j < n && obstacleGrid[0][j] != 1; j++) dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /*343. 整数拆分
    给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
    返回 你可以获得的最大乘积 。
    2 <= n <= 58*/
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1;j < i; j++) {
                dp[i] = Math.max(Math.max(dp[i],j * (i - j)), j * dp[i - j]);
            }
        }
        return dp[n];
    }

    /*96. 不同的二叉搜索树
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
    https://programmercarl.com/0096.%E4%B8%8D%E5%90%8C%E7%9A%84%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html#%E6%80%9D%E8%B7%AF*/
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1;i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**416. 分割等和子集
    给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。**/
    public boolean canPartition(int[] nums) {
        if (Arrays.stream(nums).sum() % 2 != 0) {
            return false;
        }

        int target = Arrays.stream(nums).sum()/2;
        int[] dp = new int[target + 1];

        for (int i = 0;i < nums.length;i++) {
            for (int j = target;j >= nums[i];j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;
    }

    /***1049. 最后一块石头的重量 II
     有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
     每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     如果 x == y，那么两块石头都会被完全粉碎；
     如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。*/
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        int[] dp = new int[target + 1];

        for (int i = 0;i < stones.length;i++) {
            for (int j = target;j >= stones[i];j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        return sum - dp[target] - dp[target];
    }

    /***494. 目标和
     给你一个整数数组 nums 和一个整数 target 。
     向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。**/
    public int findTargetSumWays(int[] nums, int target) {
        if ((Arrays.stream(nums).sum() + target) % 2 != 0 || Math.abs(target) > Arrays.stream(nums).sum()) {
            return 0;
        }
        int bagSize =  (Arrays.stream(nums).sum() + target) / 2;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i] ; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[bagSize];
    }

    /**474. 一和零
     给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
     如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。**/
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int zeroNums = 0, oneNums = 0;
            for (Character c : s.toCharArray()) {
                if (c == '0') {
                    zeroNums++;
                } else oneNums++;
            }
            for (int i = m; i >= zeroNums ; i--) {
                for (int j = n; j >= oneNums ; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNums][j - oneNums] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /**518. 零钱兑换 II
     给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     假设每一种面额的硬币有无限个。
     题目数据保证结果符合 32 位带符号整数。**/
    public int change(int amount, int[] coins) {
        //递推表达式
        int[] dp = new int[amount + 1];
        //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
            for (int j = 0; j < dp.length; j++) {
                if (j == dp.length - 1) System.out.println(dp[j]);
                else System.out.print(dp[j] + ",");
            }
        }
        return dp[amount];
    }

    @Test
    public void changeTest() {
        change(5, new int[]{1, 2, 5});
    }

    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */
    public int rob(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[nums.length - 1][1], dp[nums.length - 1][0]);
    }

    @Test
    public void robTest() {
        System.out.println(rob(new int[]{2,7,9,3,1}));
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     */
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], (dp[i - 1][1] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][1], (dp[i - 1][0] + prices[i] - fee));
        }
        return dp[prices.length - 1][1];
    }

    @Test
    public void maxProfitTest() {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    /**
     * 300. 最长递增子序列
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * 72. 编辑距离
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * 动态规划：
     * 1.dp数组含义？
     * dp[i][j]表示以word[i-1]结尾的word1子串和以word[j-1]结尾的word2子串完成题目要求的最小操作数
     * 2.状态转移？
     *      if word1[i-1]!=word2[j-1] :
     *          dp[i][j] = ?
     *          (1)考虑插入或者删除（插入或者删除可以看做同一种操作，比如cat和ca既可以由ca增加t也可以由cat删除t）
     *          dp[i][j] = dp[i - 1][j] + 1 或者 dp[i][j - 1] + 1
     *          (2)考虑替换(cat 和 cag)
     *          dp[i][j] = dp[i - 1][j - 1] + 1;
     *      if word1[i-1]==word2[j-1] :
     *          dp[i][j] = dp[i - 1][j - 1]
     * 3.如何初始化？
     *      dp[0][j] = j    dp[i][0] = i
     * 4.遍历顺序？
     *      由dp的状态转移可以看出应该按照从上到下从左到右的顺序
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[word1.length()][word2.length()];
    }

    @Test
    public void minDistanceTest() {
        minDistance("intention", "execution");
    }

    /**
     * 题目内容
     * 游游正在进超市，有 n 个商品摆成一排，第 i 个商品的价格为 ai ，游游对它的喜爱度为 bi 。所有商品的价格都是偶数。
     * 超市开展了一个活动，当游游花费原价买了一件商品时，她可以用半价买下一件右边相邻的商品(也可以用原价购买，这样该商品右边的商品就有次享受半价的机会)。但如果游游半价购买了一件商品，那么下一件右边相邻的商品只能原价购买。
     * 换言之，如果游游想要半价买某一件商品，必须先用原价买下它相邻的左边的那个商品游游
     * 初始的钱为 x ，她想要买的商品的喜爱度总和尽可能大，但总价格不能超过 x 。你能帮帮她计算最大的喜爱度总和吗？
     * 输入描述
     * 第一行输入两个正整数 n 和 x ，分别代表商品的数量，以及游游初始的金额数。
     * 第二行输入 n 个正整数 ai ，分别代表每个商品的价格。
     * 第三行输入 n 个正整数 bi ，分别代表每个商品可以给游游带来的喜爱度。
     * 1≤n,x,ai≤1000
     * 1≤bi≤10^9
     *   保证所有的 ai 都是偶数。
     */
    public static void getMax() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        scanner.nextLine();
        int[] weight = new int[n], value = new int[n];
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            value[i] = scanner.nextInt();
        }
        int[][][] dp = new int[n + 1][x + 1][3];
        //dp[i][j][c]含义：在金额为j，考虑购买第i个商品所能获得的最大满意度，c代表当前的购买状态：不买，半价，全价
        //状态转移：
//        dp[i][j][0] = max(dp[i - 1][j]);
//        dp[i][j][1] = dp[i - 1][j - weight[i] / 2][2] + value[i];
//        dp[i][j][2] = dp[i - 1][j - weight[i]][2] + value[i] or dp[i - 1][j - weight[i]][0] + value[i] or
//                        dp[i-1][j - weight[i]][1] + value[i];
//        //初始化：
//        dp[0][j] = 0;
//        dp[i][0] = 0;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= x; j++) {
                dp[i][j][0] = Math.max(Math.max(dp[i - 1][j][0], dp[i - 1][j][1]), dp[i - 1][j][2]);
                if (j >= weight[i - 1] / 2 && dp[i - 1][j - weight[i - 1] / 2][2] != 0) {
                    dp[i][j][1] = dp[i - 1][j - weight[i - 1] / 2][2] + value[i - 1];
                }
                if (j >= weight[i - 1]) {
                    dp[i][j][2] = Math.max(Math.max(dp[i - 1][j - weight[i - 1]][2],dp[i - 1][j - weight[i - 1]][0]),
                            dp[i-1][j - weight[i - 1]][1]) + value[i - 1];
                }
                ans = Math.max(ans, Arrays.stream(dp[i][j]).max().getAsInt());
            }

        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        getMax();
    }



}


