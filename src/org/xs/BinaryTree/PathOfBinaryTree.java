package org.xs.BinaryTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PathOfBinaryTree {

    public PathOfBinaryTree() {
    }

    public void traversal(TreeNode node, List path, List res) {
        path.add(Integer.valueOf(node.val));
        if (node.left == null && node.right == null) {
            StringBuffer pathOfNode = new StringBuffer();
            for (int i = 0; i < path.size() - 1; i++)
                pathOfNode.append(path.get(i)).append("->");

            pathOfNode.append(node.val);
            res.add(pathOfNode.toString());
            return;
        }
        if (node.left != null) {
            traversal(node.left, path, res);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            traversal(node.right, path, res);
            path.remove(path.size() - 1);
        }
    }

    public List binaryTreePaths(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            List res = new ArrayList();
            List path = new ArrayList();
            traversal(root, path, res);
            return res;
        }
    }

    @Test
    public void binaryTreePathsTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = null;
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        System.out.println(binaryTreePaths(root));
    }

    public int sumOfLeftLeaves_WideFirst(TreeNode node) {
        int result = 0;
        Deque nodesOfLayer = new LinkedList();
        nodesOfLayer.offer(node);
        while (!nodesOfLayer.isEmpty()) {
            int size = nodesOfLayer.size();
            int i = 0;
            while (i < size) {
                TreeNode nodeOfLayer = (TreeNode) nodesOfLayer.pop();
                if (nodeOfLayer.left != null && nodeOfLayer.left.left == null && nodeOfLayer.left.right == null)
                    result += nodeOfLayer.left.val;
                if (nodeOfLayer.left != null)
                    nodesOfLayer.offer(nodeOfLayer.left);
                if (nodeOfLayer.right != null)
                    nodesOfLayer.offer(nodeOfLayer.right);
                i++;
            }
        }
        return result;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        else
            return sumOfLeftLeaves_WideFirst(root);
    }

    @Test
    public void sumOfLeftLeavesTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(sumOfLeftLeaves(root));
    }

    public int findBottomLeftValue(TreeNode root) {
        if (root == null)
            return 0;
        Deque valQueue = new LinkedList();
        Deque treeLayer = new LinkedList();
        treeLayer.offer(root);
        while (!treeLayer.isEmpty()) {
            int size = treeLayer.size();
            int i = 0;
            while (i < size) {
                TreeNode pop = (TreeNode) treeLayer.pop();
                valQueue.push(Integer.valueOf(pop.val));
                if (pop.right != null)
                    treeLayer.offer(pop.right);
                if (pop.left != null)
                    treeLayer.offer(pop.left);
                i++;
            }
        }
        System.out.println(valQueue);
        return ((Integer) valQueue.getFirst()).intValue();
    }

    @Test
    public void findBottomLeftValueTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(5);
        System.out.println(findBottomLeftValue(root));
    }

    public boolean hasPath(TreeNode node, int count) {
        if (node.left == null && node.right == null && count == 0)
            return true;
        if (node.left == null && node.right == null)
            return false;
        if (node.left != null && hasPath(node.left, count - node.left.val))
            return true;
        return node.right != null && hasPath(node.right, count - node.right.val);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        else
            return hasPath(root, targetSum - root.val);
    }

    @Test
    public void hasPathSumTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        System.out.println(hasPathSum(root, 5));
    }

    public void hasPath(TreeNode node, int count, List path, List result) {
        path.add(Integer.valueOf(node.val));
        if (node.left == null && node.right == null) {
            if (count - node.val == 0)
                result.add(new ArrayList(path));
            return;
        }
        if (node.left != null) {
            hasPath(node.left, count - node.val, path, result);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            hasPath(node.right, count - node.val, path, result);
            path.remove(path.size() - 1);
        }
    }

    public List pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList();
        } else {
            List path = new ArrayList();
            List res = new ArrayList();
            hasPath(root, targetSum, path, res);
            return res;
        }
    }

    @Test
    public void pathSumTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.right = new TreeNode(4);
        System.out.println(pathSum(root, 5));
    }
}
