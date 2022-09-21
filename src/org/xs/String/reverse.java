// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   reverse.java

package org.xs.String;

public class reverse {

    public reverse() {
    }

    public static void main(String args[]) {
        System.out.println((new reverse()).reverseWords(" hello    world  "));
    }

    public void reverseString(char s[]) {
        int left = 0;
        for (int right = s.length - 1; left < right; right--) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
        }

    }

    public void reverse(char s[], int i, int j) {
        int left = i;
        for (int right = j; left < right; right--) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
        }

    }

    public String reverseStr(String s, int k) {
        char chars[] = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < chars.length; i += 2 * k)
            if (i + k <= length)
                reverse(chars, i, (i + k) - 1);
            else
                reverse(chars, i, length - 1);

        return String.valueOf(chars);
    }

    public String reverseWords(String s) {
        String result = new String();
        s = s.trim();
        String s1[] = s.split(" ");
        for (int i = s1.length - 1; i > 0; i--)
            if (!s1[i].equals(""))
                result = (new StringBuilder()).append(result).append(s1[i]).append(" ").toString();

        result = (new StringBuilder()).append(result).append(s1[0]).toString();
        return result;
    }
}
