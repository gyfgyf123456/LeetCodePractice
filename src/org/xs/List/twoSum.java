// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   twoSum.java

package org.xs.List;

import java.io.PrintStream;

// Referenced classes of package LeetCode.list:
//            ListNode

public class twoSum
{

    public twoSum()
    {
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        int num1 = 0;
        int num2 = 0;
        for(; l1 != null; l1 = l1.next)
        {
            num1 *= 10;
            num1 += l1.val;
        }

        for(; l2 != null; l2 = l2.next)
        {
            num2 *= 10;
            num2 += l2.val;
        }

        System.out.println((new StringBuilder()).append(l1).append("...").append(l2).toString());
        return null;
    }
}
