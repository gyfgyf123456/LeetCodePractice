package org.xs.HashTable;

import java.util.HashMap;
import java.util.Map;

public class numsSum {

    public numsSum() {
    }

    public static void main(String args[]) {
        (new numsSum()).fourSumCount(new int[]{
                1, 2
        }, new int[]{
                -2, -1
        }, new int[]{
                -1, 2
        }, new int[]{
                0, 2
        });
    }

    public int fourSumCount(int nums1[], int nums2[], int nums3[], int nums4[]) {
        Map map = new HashMap();
        int result = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int temp = nums1[i] + nums2[j];
                if (map.containsKey(Integer.valueOf(temp)))
                    map.put(Integer.valueOf(temp), Integer.valueOf(((Integer) map.get(Integer.valueOf(temp))).intValue() + 1));
                else
                    map.put(Integer.valueOf(temp), Integer.valueOf(1));
            }

        }

        for (int j = 0; j < nums3.length; j++) {
            for (int k = 0; k < nums4.length; k++) {
                int temp = -(nums3[j] + nums4[k]);
                if (map.containsKey(Integer.valueOf(temp)))
                    result += ((Integer) map.get(Integer.valueOf(temp))).intValue();
            }

        }

        return result;
    }
}
