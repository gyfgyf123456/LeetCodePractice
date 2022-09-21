package org.xs.HashTable;

import java.util.*;

public class intersection {

    public intersection() {
    }

    public static void main(String args[]) {
        (new intersection()).intersect(new int[]{
                1, 2, 2, 1
        }, new int[]{
                2, 2
        });
    }

    public int[] intersection(int nums1[], int nums2[]) {
        if (nums1.length == 0 || nums2.length == 0)
            return null;
        Set set_nms1 = new HashSet();
        Set resultSet = new HashSet();
        for (int i = 0; i < nums1.length; i++)
            set_nms1.add(Integer.valueOf(nums1[i]));

        for (int i = 0; i < nums2.length; i++)
            if (set_nms1.contains(Integer.valueOf(nums2[i])))
                resultSet.add(Integer.valueOf(nums2[i]));

        int result[] = new int[resultSet.size()];
        int index = 0;
        for (Iterator iterator = resultSet.iterator(); iterator.hasNext(); ) {
            int num = ((Integer) iterator.next()).intValue();
            result[index++] = num;
        }

        return result;
    }

    public int[] intersect(int nums1[], int nums2[]) {
        Map map = new HashMap();
        int intersection[] = nums1;
        int index = intersection.length;
        for (int i = 0; i < index; i++) {
            int num = intersection[i];
            int count = ((Integer) map.getOrDefault(Integer.valueOf(num), Integer.valueOf(0))).intValue() + 1;
            map.put(Integer.valueOf(num), Integer.valueOf(count));
        }

        intersection = new int[nums1.length];
        index = 0;
        int ai[] = nums2;
        int j = ai.length;
        for (int k = 0; k < j; k++) {
            int num = ai[k];
            int count = ((Integer) map.getOrDefault(Integer.valueOf(num), Integer.valueOf(0))).intValue();
            if (count <= 0)
                continue;
            intersection[index++] = num;
            if (--count > 0)
                map.put(Integer.valueOf(num), Integer.valueOf(count));
            else
                map.remove(Integer.valueOf(num));
        }

        return Arrays.copyOfRange(intersection, 0, index);
    }
}
