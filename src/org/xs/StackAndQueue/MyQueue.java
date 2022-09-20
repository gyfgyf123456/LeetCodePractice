// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StackAndQueue.java

package org.xs.StackAndQueue;

import java.util.Stack;

class MyQueue
{

    public MyQueue()
    {
        inStack = new Stack();
        outStack = new Stack();
    }

    public void push(int x)
    {
        inStack.push(Integer.valueOf(x));
    }

    public int pop()
    {
        dumpStack();
        return ((Integer)outStack.pop()).intValue();
    }

    public int peek()
    {
        dumpStack();
        return ((Integer)outStack.peek()).intValue();
    }

    public boolean empty()
    {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public void dumpStack()
    {
        if(outStack.isEmpty())
            for(; !inStack.isEmpty(); outStack.push(inStack.pop()));
    }

    Stack inStack;
    Stack outStack;
}
