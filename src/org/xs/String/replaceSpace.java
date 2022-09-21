// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   replaceSpace.java

package org.xs.String;


public class replaceSpace {

    public replaceSpace() {
    }

    public String replaceSpace(String s) {
        char chars[] = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        char ac[] = chars;
        int i = ac.length;
        for (int j = 0; j < i; j++) {
            char c = ac[j];
            if (" ".equals(String.valueOf(c)))
                sb.append("%20");
            else
                sb.append(c);
        }

        return new String(sb);
    }
}
