package org.xs.BinaryTree;

public class BalancedBinaryTree {

    public BalancedBinaryTree() {
    }

    public int getNodeHeight(TreeNode node) {
        if (node == null)
            return 0;
        int leftNodeHeight = getNodeHeight(node.left);
        int rightNodeHeight = getNodeHeight(node.right);
        if (leftNodeHeight == -1)
            return -1;
        if (rightNodeHeight == -1)
            return -1;
        if (Math.abs(leftNodeHeight - rightNodeHeight) > 1)
            return -1;
        else
            return 1 + Math.max(leftNodeHeight, rightNodeHeight);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        else
            return getNodeHeight(root) != -1;
    }

    public void isBalancedTest() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        System.out.println(isBalanced(root));
    }
}
