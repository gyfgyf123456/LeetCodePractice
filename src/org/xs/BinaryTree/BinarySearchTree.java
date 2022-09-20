// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BinarySearchTree.java

package org.xs.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package LeetCode.binaryTree:
//            TreeNode

public class BinarySearchTree
{

    public BinarySearchTree()
    {
        pre = null;
        pre2 = null;
        result = 0x7fffffff;
    }

    public TreeNode searchBST(TreeNode root, int val)
    {
        if(root == null || root.val == val)
            return root;
        if(root.val < val)
            return searchBST(root.right, val);
        if(root.val > val)
            return searchBST(root.left, val);
        else
            return null;
    }

    public void searchBSTTest()
    {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        searchBST(root, 2);
    }

    public boolean isValidBST(TreeNode root)
    {
        if(root == null)
            return true;
        boolean left = isValidBST(root.left);
        if(pre != null && pre.val >= root.val)
        {
            return false;
        } else
        {
            pre = root;
            boolean right = isValidBST(root.right);
            return left && right;
        }
    }

    public void isValidBSTTest()
    {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        isValidBST(root);
    }

    public void inOrder(TreeNode cur)
    {
        if(cur == null)
            return;
        inOrder(cur.left);
        if(pre2 != null && Math.abs(pre2.val - cur.val) < result)
            result = Math.abs(pre2.val - cur.val);
        pre = cur;
        inOrder(cur.right);
    }

    public int getMinimumDifference(TreeNode root)
    {
        inOrder(root);
        return result;
    }

    public void getMinimumDifferenceTest()
    {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(8);
        System.out.println(getMinimumDifference(root));
    }

    public int[] findMode(TreeNode root)
    {
        resList = new ArrayList();
        maxCount = 0;
        count = 0;
        pre_findMode = null;
        findMode1(root);
        int res[] = new int[resList.size()];
        for(int i = 0; i < resList.size(); i++)
            res[i] = ((Integer)resList.get(i)).intValue();

        return res;
    }

    public void findMode1(TreeNode root)
    {
        if(root == null)
            return;
        findMode1(root.left);
        int rootValue = root.val;
        if(pre_findMode == null || rootValue != pre_findMode.val)
            count = 1;
        else
            count++;
        if(count > maxCount)
        {
            resList.clear();
            resList.add(Integer.valueOf(rootValue));
            maxCount = count;
        } else
        if(count == maxCount)
            resList.add(Integer.valueOf(rootValue));
        pre_findMode = root;
        findMode1(root.right);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)
            return root;
        if(left != null)
            return left;
        else
            return right;
    }

    public TreeNode insertIntoBST(TreeNode root, int val)
    {
        if(root == null)
            return new TreeNode(val);
        if(root.val > val)
            root.left = insertIntoBST(root.left, val);
        if(root.val < val)
            root.right = insertIntoBST(root.right, val);
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key)
    {
        if(root == null)
            return null;
        if(root.val == key)
        {
            if(root.left == null && root.right == null)
                root = null;
            else
            if(root.left != null && root.right == null)
                root = root.left;
            else
            if(root.left == null)
            {
                root = root.right;
            } else
            {
                TreeNode cur;
                for(cur = root.right; cur.left != null; cur = cur.left);
                cur.left = root.left;
                root = root.right;
            }
            return root;
        } else
        {
            root.left = deleteNode(root.left, key);
            root.right = deleteNode(root.right, key);
            return root;
        }
    }

    public TreeNode trimBST(TreeNode root, int low, int high)
    {
        if(root == null)
            return null;
        if(root.val < low)
        {
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if(root.val > high)
        {
            TreeNode left = trimBST(root.left, low, high);
            return left;
        } else
        {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    public TreeNode sortedArrayToBST(int nums[])
    {
        if(nums.length == 0)
            return null;
        if(nums.length == 1)
        {
            return new TreeNode(nums[0]);
        } else
        {
            int cutPoint = nums.length / 2;
            TreeNode node = new TreeNode(nums[cutPoint]);
            node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, cutPoint));
            node.right = sortedArrayToBST(Arrays.copyOfRange(nums, cutPoint + 1, nums.length));
            return node;
        }
    }

    TreeNode pre;
    TreeNode pre2;
    int result;
    ArrayList resList;
    int maxCount;
    int count;
    TreeNode pre_findMode;
}
