package org.xs.BinaryTree;

public class CompleteBinaryTree {
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
