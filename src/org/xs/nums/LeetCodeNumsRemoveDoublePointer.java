// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LeetCodeNumsRemoveDoublePointer.java

package org.xs.nums;

public class LeetCodeNumsRemoveDoublePointer {

    public LeetCodeNumsRemoveDoublePointer() {
    }

    public static void main(String args[]) {
        (new LeetCodeNumsRemoveDoublePointer()).sortedSquares(new int[]{
                -4, -1, 0, 3, 10
        });
    }

    public int removeDuplicates(int nums[]) {
        int slowIndex = 0;
        if (nums.length == 0)
            return 0;
        int flag = 0;
        for (int fastIndex = 0; fastIndex < nums.length - 1; fastIndex++)
            if (nums[fastIndex + 1] != nums[fastIndex])
                nums[slowIndex++] = nums[fastIndex];

        nums[slowIndex] = nums[nums.length - 1];
        return slowIndex + 1;
    }

    public int removeElement(int nums[], int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++)
            if (nums[fastIndex] != val)
                nums[slowIndex++] = nums[fastIndex];

        return slowIndex;
    }

    public void moveZeroes(int nums[]) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++)
            if (nums[fastIndex] != 0)
                nums[slowIndex++] = nums[fastIndex];

        for (int i = slowIndex; i < nums.length; i++)
            nums[i] = 0;

        int ai[] = nums;
        int j = ai.length;
        for (int k = 0; k < j; k++) {
            int i = ai[k];
            System.out.println(i);
        }

    }

    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;
        for (; i >= 0 || j >= 0; j--) {
            do {
                if (i < 0)
                    break;
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                    continue;
                }
                if (skipS <= 0)
                    break;
                skipS--;
                i--;
            } while (true);
            do {
                if (j < 0)
                    break;
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                    continue;
                }
                if (skipT <= 0)
                    break;
                skipT--;
                j--;
            } while (true);
            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j))
                    return false;
            } else if (i >= 0 || j >= 0)
                return false;
            i--;
        }

        return true;
    }

    public int[] sortedSquares(int nums[]) {
        int res[] = new int[nums.length];
        int pos = nums.length - 1;
        int i = 0;
        for (int j = nums.length - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                res[pos] = nums[i] * nums[i];
                i++;
            } else {
                res[pos] = nums[j] * nums[j];
                j--;
            }
            pos--;
        }

        return res;
    }
}
