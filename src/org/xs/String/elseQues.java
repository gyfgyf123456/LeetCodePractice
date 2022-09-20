// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   elseQues.java

package org.xs.String;

import java.io.PrintStream;

public class elseQues
{

    public elseQues()
    {
    }

    public String reverseLeftWords(String s, int n)
    {
        if(n > s.length())
        {
            return null;
        } else
        {
            String leftString = s.substring(0, n);
            String rightString = s.substring(n, s.length());
            return (new StringBuilder()).append(rightString).append(leftString).toString();
        }
    }

    public int strStr(String haystack, String needle)
    {
        if(needle.length() == 0)
            return 0;
        char hays[] = haystack.toCharArray();
        char needles[] = needle.toCharArray();
        for(int i = 0; i < hays.length; i++)
        {
            if(hays[i] != needles[0])
                continue;
            int j = 1;
            if(i + needles.length > hays.length)
                return -1;
            for(; j < needles.length && needles[j] == hays[i + j]; j++);
            if(j == needles.length)
                return i;
        }

        return -1;
    }

    public static void main(String args[])
    {
        System.out.println((new elseQues()).strStr("mississippi", "issip"));
    }
}
