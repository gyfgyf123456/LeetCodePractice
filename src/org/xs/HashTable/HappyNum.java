// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   happyNum.java

package org.xs.HashTable;

import java.util.HashSet;
import java.util.Set;

public class HappyNum
{

    public HappyNum()
    {
    }

    public boolean isHappy(int n)
    {
        int next = n;
        Set set = new HashSet();
        do
        {
            next = getNextNumber(next);
            if(next == 1)
                return true;
            if(set.contains(Integer.valueOf(next)))
                return false;
            set.add(Integer.valueOf(next));
        } while(true);
    }

    public int getNextNumber(int n)
    {
        int nextNumber = 0;
        for(; n > 0; n /= 10)
            nextNumber += (n % 10) * (n % 10);

        return nextNumber;
    }
}
