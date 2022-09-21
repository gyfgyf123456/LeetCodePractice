// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StackAndQueue.java

package org.xs.StackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

class monotQueue {

    Deque deque;

    monotQueue() {
        deque = new LinkedList();
    }

    void poll(int val) {
        if (!deque.isEmpty() && val == ((Integer) deque.peek()).intValue())
            deque.poll();
    }

    void add(int val) {
        for (; !deque.isEmpty() && val > ((Integer) deque.getLast()).intValue(); deque.removeLast()) ;
        deque.add(Integer.valueOf(val));
    }

    int peek() {
        return ((Integer) deque.peek()).intValue();
    }
}
