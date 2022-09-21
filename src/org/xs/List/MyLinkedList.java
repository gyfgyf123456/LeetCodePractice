package org.xs.List;

class MyLinkedList {

    int size;
    ListNode dummyhead;

    public MyLinkedList() {
        size = 0;
        dummyhead = new ListNode(0);
    }

    public int get(int index) {
        if (index >= size || index < 0)
            return -1;
        ListNode cur;
        for (cur = dummyhead.next; index-- > 0; cur = cur.next) ;
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode newHead = new ListNode(val);
        newHead.next = dummyhead.next;
        dummyhead.next = newHead;
        size++;
    }

    public void addAtTail(int val) {
        ListNode cur = dummyhead;
        ListNode tailNode = new ListNode(val);
        for (; cur.next != null; cur = cur.next) ;
        cur.next = tailNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size)
            return;
        if (index < 0)
            index = 0;
        size++;
        ListNode addNode = new ListNode(val);
        ListNode pre = dummyhead;
        for (int i = 0; i < index; i++)
            pre = pre.next;

        addNode.next = pre.next;
        pre.next = addNode;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            return;
        size--;
        ListNode pred = dummyhead;
        for (int i = 0; i < index; i++)
            pred = pred.next;

        pred.next = pred.next.next;
    }

    public void printList() {
        for (ListNode cur = dummyhead; cur.next != null; cur = cur.next)
            System.out.println(cur.next.val);

    }
}
