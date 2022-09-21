package org.xs.List;

public class listStructure {

    public listStructure() {
    }

    public static void main(String args[]) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(4);
        linkedList.addAtHead(6);
        linkedList.addAtTail(2);
        linkedList.addAtIndex(1, 5);
        linkedList.printList();
    }
}
