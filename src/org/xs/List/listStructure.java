// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   listStructure.java

package org.xs.List;


// Referenced classes of package LeetCode.list:
//            MyLinkedList

public class listStructure
{

    public listStructure()
    {
    }

    public static void main(String args[])
    {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(4);
        linkedList.addAtHead(6);
        linkedList.addAtTail(2);
        linkedList.addAtIndex(1, 5);
        linkedList.printList();
    }
}
