// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   numsSlideWindow.java

package org.xs.nums;

import java.util.HashMap;

public class numsSlideWindow
{

    public numsSlideWindow()
    {
    }

    public int totalFruit(int fruits[])
    {
        int i = 0;
        int result = 0;
        HashMap map = new HashMap();
        for(int j = 0; j < fruits.length; j++)
        {
            if(!map.containsKey(Integer.valueOf(fruits[j])))
                map.put(Integer.valueOf(fruits[j]), Integer.valueOf(1));
            else
                map.put(Integer.valueOf(fruits[j]), Integer.valueOf(((Integer)map.get(Integer.valueOf(fruits[j]))).intValue() + 1));
            while(map.size() >= 3) 
            {
                map.put(Integer.valueOf(fruits[i]), Integer.valueOf(((Integer)map.get(Integer.valueOf(fruits[i]))).intValue() - 1));
                if(((Integer)map.get(Integer.valueOf(fruits[i]))).intValue() == 0)
                    map.remove(Integer.valueOf(fruits[i]));
                i++;
            }
            result = Math.max(result, (j - i) + 1);
        }

        return result;
    }
}
