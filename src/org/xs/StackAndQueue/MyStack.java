// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StackAndQueue.java

package org.xs.StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

class MyStack
{

    public MyStack()
    {
        queue1 = new LinkedList();
        queue2 = new LinkedList();
    }

    public void push(int x)
    {
        queue2.offer(Integer.valueOf(x));
        for(; !queue1.isEmpty(); queue2.offer(queue1.poll()));
        Queue temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop()
    {
        return ((Integer)queue1.poll()).intValue();
    }

    public int top()
    {
        return ((Integer)queue1.peek()).intValue();
    }

    public boolean empty()
    {
        return queue1.isEmpty();
    }

    Queue queue1;
    Queue queue2;
}
