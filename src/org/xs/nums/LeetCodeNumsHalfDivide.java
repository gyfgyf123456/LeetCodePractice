package org.xs.nums;

public class LeetCodeNumsHalfDivide {

    public static void main(String args1[]) {
    }

    public String reverseVowels(String s) {
        char chars[] = s.toCharArray();
        int n = chars.length;
        int i = 0;
        int j = chars.length - 1;
        String y = "aeiouAEIOU";
        do {
            if (i >= j)
                break;
            for (; i < n && !y.contains(String.valueOf(chars[i])); i++) ;
            for (; j > 0 && !y.contains(String.valueOf(chars[j])); j--) ;
            if (i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        } while (true);
        return String.valueOf(chars);
    }

    public int singleNumber(int nums[]) {
        int output = 0;
        int ai[] = nums;
        int i = ai.length;
        for (int j = 0; j < i; j++) {
            int num = ai[j];
            output ^= num;
        }

        return output;
    }

    public int searchInsert(int nums[], int target) {
        int left = 0;
        for (int right = nums.length - 1; left <= right; ) {
            int middle = left + (right - left >> 1);
            if (nums[middle] < target)
                left = middle + 1;
            else if (nums[middle] > target)
                right = middle - 1;
            else
                return middle;
        }

        return left;
    }

    public int[] searchRange(int nums[], int target) {
        int leftborder = -2;
        int rightborder = -2;
        int l = 0;
        for (int r = nums.length - 1; l <= r; ) {
            int middle = l + (r - l >> 1);
            if (nums[middle] >= target) {
                r = middle - 1;
                leftborder = r;
            } else {
                l = middle + 1;
            }
        }

        l = 0;
        for (int r = nums.length - 1; l <= r; ) {
            int middle = l + (r - l >> 1);
            if (nums[middle] <= target) {
                l = middle + 1;
                rightborder = l;
            } else {
                r = middle - 1;
            }
        }

        if (leftborder == -2 || rightborder == -2)
            return (new int[]{
                    -1, -1
            });
        if (rightborder - leftborder > 1)
            return (new int[]{
                    leftborder + 1, rightborder - 1
            });
        else
            return (new int[]{
                    -1, -1
            });
    }

    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left >> 1);
            if ((long) middle * (long) middle > (long) x)
                right = middle - 1;
            else if ((long) middle * (long) middle < (long) x)
                left = middle + 1;
            else
                return middle;
        }
        return right;
    }

    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left >> 1);
            if ((long) middle * (long) middle > (long) num)
                right = middle - 1;
            else if ((long) middle * (long) middle < (long) num)
                left = middle + 1;
            else
                return true;
        }
        return false;
    }
}
