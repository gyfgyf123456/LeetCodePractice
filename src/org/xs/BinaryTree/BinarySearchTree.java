package org.xs.BinaryTree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BinarySearchTree {


    /*700. 二叉搜索树中的搜索
    给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
    你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。*/

    /**
     * @description 一定要牢记二叉搜索树的中序遍历就是从小到大的顺序，这个特性在二叉搜索树的题目中要首先考虑到
     * @author xs
     * @param root
     * @return boolean
     */
    TreeNode pre_isValidBST = null;
    /**
     * @description 有序数组中两数之间的最小差值一定是相邻数之间的最小差值，中序遍历中计算相邻节点的差值不断更新差值最小值即结果
     * @author xs
     * @param root
     * @return int
     */
    int result;

    /*98.验证二叉搜索树
    给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    假设一个二叉搜索树具有如下特征：
    节点的左子树只包含小于当前节点的数。
    节点的右子树只包含大于当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。*/
    TreeNode pre_getMinimumDifference = null;
    /**
     * @description 遍历有序数组的元素出现频率，从头遍历，那么一定是相邻两个元素作比较，然后就把出现频率最高的元素输出就可以了。
     * 关键是在有序数组上的话，好搞，在树上怎么搞呢？
     * 这就考察对树的操作了。
     * 在二叉树：搜索树的最小绝对差中我们就使用了pre指针和cur指针的技巧，这次又用上了。
     * 弄一个指针指向前一个节点，这样每次cur（当前节点）才能和pre（前一个节点）作比较。
     * 而且初始化的时候pre = NULL，这样当pre为NULL时候，我们就知道这是比较的第一个元素。
     * <p>
     * 频率count 大于 maxCount的时候，不仅要更新maxCount，而且要清空结果集（以下代码为result数组），因为结果集之前的元素都失效了。
     * @author xs
     * @param root
     * @return int[]
     */
    ArrayList<Integer> resList;
    int maxCount;

    /*530.二叉搜索树的最小绝对差
    给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。*/
    int count;
    TreeNode pre;
    /*538.把二叉搜索树转换为累加树
    给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
    提醒一下，二叉搜索树满足下列约束条件：
    节点的左子树仅包含键 小于 节点键的节点。 节点的右子树仅包含键 大于 节点键的节点。 左右子树也必须是二叉搜索树。*/
    /*换一个角度来看，这就是一个有序数组[2, 5, 13]，求从后到前的累加数组，也就是[20, 18, 13]。
    数组从后向前，挨个累加就完事了，换成二叉搜索树，从树中可以看出累加的顺序是右中左，所以我们需要反中序遍历这个二叉树，然后顺序累加就可以了*/
    int preNodeValue;

    /**
     * @param root
     * @param val
     * @return org.xs.BinaryTree.TreeNode
     * @description 在递归遍历的时候，什么时候直接return 递归函数的返回值，什么时候不用加这个 return呢。
     * 我们在二叉树：递归函数究竟什么时候需要返回值，什么时候不要返回值？
     * 如果要搜索一条边，递归函数就要加返回值，这里也是一样的道理。
     * 因为搜索到目标节点了，就要立即return了，这样才是找到节点就返回（搜索某一条边），如果不加return，就是遍历整棵树了。
     * @author xs
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val)
            return root;
        if (root.val < val)
            return searchBST(root.right, val);
        if (root.val > val)
            return searchBST(root.left, val);
        else
            return null;
    }

    @Test
    public void searchBSTTest() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        searchBST(root, 2);
    }

    /*501.二叉搜索树中的众数
    给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
    假定 BST 有如下定义：
    结点左子树中所含结点的值小于等于当前结点的值
    结点右子树中所含结点的值大于等于当前结点的值
    左子树和右子树都是二叉搜索树*/

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        boolean left = isValidBST(root.left);
        if (pre_isValidBST != null && pre_isValidBST.val >= root.val) {
            return false;
        } else {
            pre_isValidBST = root;
            boolean right = isValidBST(root.right);
            return left && right;
        }
    }

    @Test
    public void isValidBSTTest() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        isValidBST(root);
    }

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return result;
    }

    public void inOrder(TreeNode cur) {
        if (cur == null)
            return;
        inOrder(cur.left);
        if (pre_getMinimumDifference != null && Math.abs(pre_getMinimumDifference.val - cur.val) < result)
            result = Math.abs(pre_getMinimumDifference.val - cur.val);
        pre_getMinimumDifference = cur;
        inOrder(cur.right);
    }

    @Test
    public void getMinimumDifferenceTest() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.right = new TreeNode(8);
        System.out.println(getMinimumDifference(root));
    }

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        findMode1(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    /*236. 二叉树的最近公共祖先
    给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
    （一个节点也可以是它自己的祖先）。”*/

    public void findMode1(TreeNode root) {
        if (root == null) {
            return;
        }
        findMode1(root.left);

        int rootValue = root.val;
        // 计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }
        // 更新结果以及maxCount
        if (count > maxCount) {
            resList.clear();
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }
        pre = root;

        findMode1(root.right);
    }

    /*235. 二叉搜索树的最近公共祖先
    给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
    满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”*/

    /**
     * @param root
     * @param p
     * @param q
     * @return org.xs.BinaryTree.TreeNode
     * @description 求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从低向上的遍历方式。
     * 在回溯的过程中，必然要遍历整棵二叉树，即使已经找到结果了，依然要把其他节点遍历完，因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
     * 要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。
     * @author xs
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        if (left != null)
            return left;
        else
            return right;
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return org.xs.BinaryTree.TreeNode
     * @description 本题是二叉搜索树，二叉搜索树是有序的，那得好好利用一下这个特点。
     * 在有序树里，如果判断一个节点的左子树里有p，右子树里有q呢？
     * 其实只要从上到下遍历的时候，cur节点是数值在[p, q]区间中则说明该节点cur就是最近公共祖先了。
     * @author xs
     */
    public TreeNode lowestCommonAncestor_BST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (Math.min(p.val, q.val) < root.val && root.val < Math.max(p.val, q.val)) return root;
        if (root.val > p.val && root.val > q.val) {
            TreeNode left = lowestCommonAncestor_BST(root.left, p, q);
            if (left != null) return left;
        }
        if (root.val < p.val && root.val < q.val) {
            TreeNode right = lowestCommonAncestor_BST(root.right, p, q);
            if (right != null) return right;
        }
        return root;
    }

    /*450.删除二叉搜索树中的节点
    给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
    并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
    一般来说，删除节点可分为两个步骤：
    首先找到需要删除的节点； 如果找到了，删除它。 说明： 要求算法时间复杂度为 $O(h)$，h 为树的高度。*/

    /*701.二叉搜索树中的插入操作
    给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
    返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
    注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。*/
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        return root;
    }

    /*669. 修剪二叉搜索树
    给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
    你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。*/

    /**
     * @param root
     * @param key
     * @return org.xs.BinaryTree.TreeNode
     * @description 确定单层递归的逻辑
     * 这里就把二叉搜索树中删除节点遇到的情况都搞清楚。
     * 有以下五种情况：
     * 第一种情况：没找到删除的节点，遍历到空节点直接返回了
     * 找到删除的节点时
     * 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
     * 第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
     * 第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
     * 第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。
     * @author xs
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {
            if (root.left == null && root.right == null)
                root = null;
            else if (root.left != null && root.right == null)
                root = root.left;
            else if (root.left == null) {
                root = root.right;
            } else {
                TreeNode cur;
                for (cur = root.right; cur.left != null; cur = cur.left) ;
                cur.left = root.left;
                root = root.right;
            }
            return root;
        } else {
            root.left = deleteNode(root.left, key);
            root.right = deleteNode(root.right, key);
            return root;
        }
    }

    /**
     * @param root
     * @param low
     * @param high
     * @return org.xs.BinaryTree.TreeNode
     * @description 遇到要删除的节点时，不要直接返回null，这样会让该节点之后符合要求的子树全部被删除，只要将其符合要求的
     * 子树作为结果返回给上一层节点就可以完成删除，注意可以这样做的原因是这是一个二叉搜索树（目标节点之后只会有一边的子树符合要求）
     * @author xs
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null)
            return null;
        if (root.val < low) {
            TreeNode right = trimBST(root.right, low, high);
            return right;
        }
        if (root.val > high) {
            TreeNode left = trimBST(root.left, low, high);
            return left;
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    /*108.将有序数组转换为二叉搜索树
    将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
    本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。*/
    public TreeNode sortedArrayToBST(int nums[]) {
        if (nums.length == 0)
            return null;
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        } else {
            int cutPoint = nums.length / 2;
            TreeNode node = new TreeNode(nums[cutPoint]);
            node.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, cutPoint));
            node.right = sortedArrayToBST(Arrays.copyOfRange(nums, cutPoint + 1, nums.length));
            return node;
        }
    }

    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        inverseInOrder(root);
        return root;
    }

    private void inverseInOrder(TreeNode node) {
        if (node == null) return;
        inverseInOrder(node.right);
        // 注意要先更新node.val，再记录node的val值，第一个节点不需要累加。
        node.val = node.val + preNodeValue;
        preNodeValue = node.val;
        inverseInOrder(node.left);
    }

    /*77. 组合
    给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
    你可以按 任何顺序 返回答案。*/
    public List<List<Integer>> combine(int n, int k) {
        int startIndex = 1;
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> allCombine = new ArrayList<>();
        backTracing(n, k, path, allCombine, startIndex);
        return allCombine;
    }

    private void backTracing(int n, int k, List<Integer> path, List<List<Integer>> allCombine, int startIndex) {
        if (path.size() == k) {
            // 注意不能直接add（path），这样做allCombine只记录了一个path的地址
            allCombine.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backTracing(n, k, path, allCombine, i + 1);
            path.remove(path.size() - 1);
        }
    }

    @Test
    public void combineTest(){
        System.out.println(combine(2, 2));
    }
}
