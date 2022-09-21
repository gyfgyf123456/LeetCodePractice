// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SymmetricBinaryTree.java

package org.xs.BinaryTree;


// Referenced classes of package LeetCode.binaryTree:
//            TreeNode

public class SymmetricBinaryTree {

    public SymmetricBinaryTree() {
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        else
            return compareSym(root.left, root.right);
    }

    private boolean compareSym(TreeNode left, TreeNode right) {
        if (left == null && right != null)
            return false;
        if (left != null && right == null)
            return false;
        if (left == null && right == null)
            return true;
        if (left.val != right.val) {
            return false;
        } else {
            boolean outsideIsSym = compareSym(left.left, right.right);
            boolean insideIsSym = compareSym(left.right, right.left);
            boolean isSym = outsideIsSym && insideIsSym;
            return isSym;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return compareSame(p, q);
    }

    private boolean compareSame(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p != null && q == null)
            return false;
        if (p == null && q != null)
            return false;
        if (p.val != q.val) {
            return false;
        } else {
            boolean leftIsSame = compareSame(p.left, q.left);
            boolean rightIsSame = compareSame(p.right, q.right);
            boolean isSame = leftIsSame && rightIsSame;
            return isSame;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }

    private boolean dfs(TreeNode node, TreeNode subRoot) {
        if (node == null)
            return false;
        else
            return isSameTree(node, subRoot) || dfs(node.left, subRoot) || dfs(node.right, subRoot);
    }
}
