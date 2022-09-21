// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompleteBinaryTree.java

package org.xs.BinaryTree;

public class CompleteBinaryTree {

    public CompleteBinaryTree() {
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftNum = countNodes(root.left);
            int rightNum = countNodes(root.right);
            return leftNum + rightNum + 1;
        }
    }

    public void test() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        System.out.println(countNodes(root));
    }
}
