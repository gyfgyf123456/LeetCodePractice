// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Iteration.java

package org.xs.BinaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Iteration {

    public Iteration() {
    }

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 0;
        Deque deque = new LinkedList();
        deque.add(root);
        while (!deque.isEmpty()) {
            depth++;
            int size = deque.size();
            int i = 0;
            while (i < size) {
                TreeNode node = (TreeNode) deque.poll();
                if (node.left == null && node.right == null)
                    return depth;
                if (node.left != null)
                    deque.add(node.left);
                if (node.right != null)
                    deque.add(node.right);
                i++;
            }
        }
        return depth;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 0;
        Deque deque = new LinkedList();
        deque.add(root);
        while (!deque.isEmpty()) {
            depth++;
            int size = deque.size();
            int i = 0;
            while (i < size) {
                TreeNode node = (TreeNode) deque.poll();
                if (node.left != null)
                    deque.add(node.left);
                if (node.right != null)
                    deque.add(node.right);
                i++;
            }
        }
        return depth;
    }

    public Node connect(Node root) {
        if (root == null)
            return null;
        Deque deque = new LinkedList();
        deque.add(root);
        while (!deque.isEmpty()) {
            List levelNode = new ArrayList();
            int size = deque.size();
            int max = 0x80000000;
            int i;
            for (i = 0; i < size; i++) {
                Node node = (Node) deque.poll();
                levelNode.add(node);
                if (node.val > max)
                    max = node.val;
                if (node.left != null)
                    deque.add(node.left);
                if (node.right != null)
                    deque.add(node.right);
            }

            i = 0;
            while (i < size) {
                if (i == size - 1)
                    ((Node) levelNode.get(i)).next = null;
                else
                    ((Node) levelNode.get(i)).next = (Node) levelNode.get(i + 1);
                i++;
            }
        }
        return root;
    }

    public List<Integer> largestValues(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int max;
        for (; !deque.isEmpty(); result.add(max)) {
            int size = deque.size();
            max = 0x80000000;
            for (int i = 0; i < size; i++) {
                TreeNode node = (TreeNode) deque.poll();
                if (node.val > max)
                    max = node.val;
                if (node.left != null)
                    deque.add(node.left);
                if (node.right != null)
                    deque.add(node.right);
            }

        }

        return result;
    }

    public List rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList();
        List res = new ArrayList();
        Deque deque = new LinkedList();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            int i = 0;
            while (i < size) {
                TreeNode element = (TreeNode) deque.poll();
                if (i == size - 1)
                    res.add(Integer.valueOf(element.val));
                if (element.left != null)
                    deque.add(element.left);
                if (element.right != null)
                    deque.add(element.right);
                i++;
            }
        }
        return res;
    }

    public List averageOfLevels(TreeNode root) {
        if (root == null)
            return new ArrayList();
        List res = new ArrayList();
        Deque deque = new LinkedList();
        deque.add(root);
        int size;
        double temp;
        for (; !deque.isEmpty(); res.add(Double.valueOf(temp / (double) size))) {
            size = deque.size();
            temp = 0.0D;
            for (int i = 0; i < size; i++) {
                TreeNode element = (TreeNode) deque.poll();
                temp += element.val;
                if (element.left != null)
                    deque.add(element.left);
                if (element.right != null)
                    deque.add(element.right);
            }

        }

        return res;
    }

    public List levelOrderBottom(TreeNode root) {
        if (root == null)
            return new ArrayList();
        List res = new ArrayList();
        Deque deque = new LinkedList();
        deque.add(root);
        List levelNodeList;
        for (; !deque.isEmpty(); res.add(levelNodeList)) {
            levelNodeList = new ArrayList();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode element = (TreeNode) deque.poll();
                levelNodeList.add(Integer.valueOf(element.val));
                if (element.left != null)
                    deque.add(element.left);
                if (element.right != null)
                    deque.add(element.right);
            }

        }

        List result = new ArrayList();
        for (int i = res.size() - 1; i >= 0; i--)
            result.add(res.get(i));

        return result;
    }

    public List levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList();
        List res = new ArrayList();
        Deque deque = new LinkedList();
        deque.add(root);
        List levelNodeList;
        for (; !deque.isEmpty(); res.add(levelNodeList)) {
            levelNodeList = new ArrayList();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode element = (TreeNode) deque.poll();
                levelNodeList.add(Integer.valueOf(element.val));
                if (element.left != null)
                    deque.add(element.left);
                if (element.right != null)
                    deque.add(element.right);
            }

        }

        return res;
    }

    public List Traversal(TreeNode root) {
        List result = new ArrayList();
        inOrder(root, result);
        return result;
    }

    private void inOrder(TreeNode cur, List result) {
        if (cur == null) {
            return;
        } else {
            inOrder(cur.left, result);
            result.add(Integer.valueOf(cur.val));
            inOrder(cur.right, result);
            return;
        }
    }

    private void preOrder(TreeNode cur, List result) {
        if (cur == null) {
            return;
        } else {
            result.add(Integer.valueOf(cur.val));
            preOrder(cur.left, result);
            preOrder(cur.right, result);
            return;
        }
    }

    private void postOrder(TreeNode cur, List result) {
        if (cur == null) {
            return;
        } else {
            postOrder(cur.left, result);
            postOrder(cur.right, result);
            result.add(Integer.valueOf(cur.val));
            return;
        }
    }

    public void iteratorTest() {
        TreeNode root = new TreeNode(0x7fffffff);
        root.left = new TreeNode(0x7fffffff);
        root.right = new TreeNode(0x7fffffff);
        System.out.println(averageOfLevels(root));
    }
}
