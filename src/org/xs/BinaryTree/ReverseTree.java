// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReverseTree.java

package org.xs.BinaryTree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

// Referenced classes of package LeetCode.binaryTree:
//            TreeNode

public class ReverseTree {

    public ReverseTree() {
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        Deque levelNode = new LinkedList();
        levelNode.add(root);
        while (!levelNode.isEmpty()) {
            int size = levelNode.size();
            int i = 0;
            while (i < size) {
                TreeNode node = (TreeNode) levelNode.poll();
                if (node.left != null)
                    levelNode.add(node.left);
                if (node.right != null)
                    levelNode.add(node.right);
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                i++;
            }
        }
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        invertTree_recur(root);
        return root;
    }

    private void invertTree_recur(TreeNode cur) {
        if (cur == null) {
            return;
        } else {
            swap(cur);
            invertTree_recur(cur.left);
            invertTree_recur(cur.right);
            return;
        }
    }

    private void swap(TreeNode cur) {
        TreeNode temp = cur.left;
        cur.left = cur.right;
        cur.right = temp;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        invertTree2(root);
    }
}
