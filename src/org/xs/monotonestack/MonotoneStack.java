package org.xs.monotonestack;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年03月15日 15:00
 */
public class MonotoneStack {
    /**
     * 739. 每日温度
     * 相关企业
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
     * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // 单调栈存放的是对应元素的下标
        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[temperatures.length];
        stack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            }else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    Integer pop = stack.pop();
                    result[pop] = i - pop;
                }
                stack.push(i);
            }
        }
        return result;
    }

    @Test
    public void test() {
        dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
    }
}
