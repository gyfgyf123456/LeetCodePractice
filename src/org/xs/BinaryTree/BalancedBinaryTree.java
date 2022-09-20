// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BalancedBinaryTree.java

package org.xs.BinaryTree;

// Referenced classes of package LeetCode.binaryTree:
//            TreeNode

public class BalancedBinaryTree
{

    public BalancedBinaryTree()
    {
    }

    public int getNodeHeight(TreeNode node)
    {
        if(node == null)
            return 0;
        int leftNodeHeight = getNodeHeight(node.left);
        int rightNodeHeight = getNodeHeight(node.right);
        if(leftNodeHeight == -1)
            return -1;
        if(rightNodeHeight == -1)
            return -1;
        if(Math.abs(leftNodeHeight - rightNodeHeight) > 1)
            return -1;
        else
            return 1 + Math.max(leftNodeHeight, rightNodeHeight);
    }

    public boolean isBalanced(TreeNode root)
    {
        if(root == null)
            return true;
        else
            return getNodeHeight(root) != -1;
    }

    public void isBalancedTest()
    {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        System.out.println(isBalanced(root));
    }
}
