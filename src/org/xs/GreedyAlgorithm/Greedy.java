package org.xs.GreedyAlgorithm;

import org.junit.Test;

import java.util.*;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2022年11月17日 10:08
 */
public class Greedy {

    /**455.分发饼干
    假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
    对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
    如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。**/
    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int j = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if (j >= 0 && s[j] >= g[i]){
                count++;
                j--;
            }
        }
        return count;
    }

    /*45. 跳跃游戏 II
   给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
   数组中的每个元素代表你在该位置可以跳跃的最大长度。
   你的目标是使用最少的跳跃次数到达数组的最后一个位置。
   假设你总是可以到达数组的最后一个位置。*/
    public int jump(int[] nums) {
        int curDistance = 0, nextDistance = 0;
        int step = 0;
        for (int i = 0; i < nums.length; i++) {
            curDistance = Math.max(curDistance, i + nums[i]);
            if (i == curDistance) {
                step++;
            }
        }
        return step;
    }

    /*1005. K 次取反后最大化的数组和
    给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
    选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
    重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
    以这种方式修改数组后，返回数组 可能的最大和 。*/
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        Arrays.sort(nums);
        //k有剩余，反复反转数值最小的数（最后结果只与k的奇偶有关）
        if (k % 2 == 1) nums[0] = -nums[0];
        return Arrays.stream(nums).sum();
    }

    @Test
    public void largestSumAfterKNegationsTest() {
        System.out.println(largestSumAfterKNegations(new int[]{3,-1,0,2}, 3));
    }

    /*134. 加油站
    在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
    你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
    给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。*/
    /*首先如果总油量减去总消耗大于等于零那么一定可以跑完一圈，说明 各个站点的加油站 剩油量rest[i]相加一定是大于等于零的。
    每个加油站的剩余量rest[i]为gas[i] - cost[i]。
    i从0开始累加rest[i]，和记为curSum，一旦curSum小于零，说明[0, i]区间都不能作为起始位置，起始位置从i+1算起，再从0计算curSum。*/
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalSum += gas[i] - cost[i];
            curSum += gas[i] - cost[i];
            if (curSum < 0) {
                start = i + 1;
                curSum = 0;
            }
        }
        if (totalSum < 0) return -1;
        return start;
    }


    /*135. 分发糖果
    n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
    你需要按照以下要求，给这些孩子分发糖果：
    每个孩子至少分配到 1 个糖果。
    相邻两个孩子评分更高的孩子会获得更多的糖果。
    请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。*/
    /**
     分两个阶段
     1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
     2、起点下标 ratings.length - 2 从右往左， 只要左边 比 右边 大，此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，这样才符合 它比它左边的大，也比它右边大
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candyVec = new int[len];
        candyVec[0] = 1;
        for (int i = 1; i < len; i++) {
            candyVec[i] = (ratings[i] > ratings[i - 1]) ? candyVec[i - 1] + 1 : 1;
        }

        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
            }
        }

        int ans = 0;
        for (int num : candyVec) {
            ans += num;
        }
        return ans;
    }

    /*860. 柠檬水找零
    在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
    每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
    注意，一开始你手头没有任何零钱。
    给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。*/
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            }
            if (bills[i] == 10) {
                ten++;
                five--;
            }
            if (bills[i] == 20) {
                if (ten > 0) {
                    ten--;
                    five--;
                }else {
                    five -= 3;
                }
            }
            if (five < 0 || ten < 0) return false;
        }
        return true;
    }

    /*406. 根据身高重建队列
    假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
    每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
    请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
    其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。*/
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[people.length][]);
    }

    /**452. 用最少数量的箭引爆气球
    有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，
    其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
    一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
    且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
    给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。**/
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i-1][1]) {
                count++;
            }else {
                points[i][1] = Math.min(points[i-1][1],points[i][1]);
            }
        }
        return count;
    }

    @Test
    public void findMinArrowShotsTest(){
        System.out.println(findMinArrowShots(new int[][]{{3,9}, {7, 12}, {3, 8}, {6, 8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}}));
    }

    /**435. 无重叠区间
    给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。**/
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int count = 1, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {
                count++;
                end = intervals[i][1];
            }
        }
        return intervals.length - count;
    }

    @Test
    public void eraseOverlapIntervalsTest(){
        System.out.println(eraseOverlapIntervals(new int[][]{{1,2}, {2, 3}, {3, 4}, {1, 3}}));
    }

    /**763. 划分字母区间
     给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
     返回一个表示每个字符串片段的长度的列表。**/
    public List<Integer> partitionLabels(String s) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] charEdge = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charEdge[s.charAt(i) - 'a'] = i;
        }

        int start = 0;
        int maxEdge = charEdge[s.charAt(0) - 'a'];

        for (int i = 0; i < s.length(); i++) {
            maxEdge = Math.max(maxEdge, charEdge[s.charAt(i) - 'a']);
            if (i == maxEdge) {
                result.add(maxEdge - start + 1);
                start = i + 1;
            }
        }

        return result;
    }

    @Test
    public void partitionLabelsTest() {
        partitionLabels("ababcbacadefegdehijhklij");
    }

    /**56. 合并区间
     以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。**/
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return null;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];
        ArrayList<int[]> resultList = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                resultList.add(new int[]{start, end});
                start = intervals[i][0];
                end = Math.max(end, intervals[i][1]);
            }
        }
        resultList.add(new int[]{start, end});
        int[][] result = new int[resultList.size()][];
        for (int i = 0;i < resultList.size();i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    @Test
    public void mergeTest() {
        merge(new int[][]{{1,4},{4,4}});
    }
}
