// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ConstructBinaryTree.java

package org.xs.BinaryTree;

import java.util.Arrays;

public class ConstructBinaryTree {

    public ConstructBinaryTree() {
    }

    public TreeNode buildTree(int inorder[], int postorder[]) {
        if (inorder.length == 0)
            return null;
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        if (postorder.length == 1)
            return root;
        int cutPorint = 0;
        int i = 0;
        do {
            if (i >= inorder.length)
                break;
            if (inorder[i] == root.val) {
                cutPorint = i;
                break;
            }
            i++;
        } while (true);
        int leftInOrder[] = Arrays.copyOfRange(inorder, 0, cutPorint);
        int rightInOrder[] = Arrays.copyOfRange(inorder, cutPorint + 1, inorder.length);
        int leftPostOrder[] = Arrays.copyOfRange(postorder, 0, leftInOrder.length);
        int rightPostOrder[] = Arrays.copyOfRange(postorder, leftInOrder.length, postorder.length - 1);
        root.left = buildTree(leftInOrder, leftPostOrder);
        root.right = buildTree(rightInOrder, rightPostOrder);
        return root;
    }

    public void buildTreeTest() {
        buildTree(new int[]{
                9, 3, 15, 20, 7
        }, new int[]{
                9, 15, 7, 20, 3
        });
    }

    public TreeNode constructMaximumBinaryTree(int nums[]) {
        if (nums.length == 0) {
            return null;
        } else {
            int cutPoint = indexOfMaxValue(nums);
            TreeNode root = new TreeNode(nums[cutPoint]);
            int leftTree[] = Arrays.copyOfRange(nums, 0, cutPoint);
            int rightTree[] = Arrays.copyOfRange(nums, cutPoint + 1, nums.length);
            root.left = constructMaximumBinaryTree(leftTree);
            root.right = constructMaximumBinaryTree(rightTree);
            return root;
        }
    }

    private int indexOfMaxValue(int nums[]) {
        int maxValue = 0x80000000;
        int index = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                index = i;
            }

        return index;
    }

    public void constructMaximumBinaryTreeTest() {
        constructMaximumBinaryTree(new int[]{
                3, 2, 1, 6, 0, 5
        });
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null)
            return root2;
        if (root2 == null && root1 != null)
            return root1;
        if (root1 == null && root2 == null) {
            return null;
        } else {
            TreeNode root = new TreeNode(root1.val + root2.val);
            root.left = mergeTrees(root1.left, root2.left);
            root.right = mergeTrees(root1.right, root2.right);
            return root;
        }
    }
}
