package demo.BinaryTree;

import demo.LinkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
//        String strs = "13##2";
//        TreeNode root = TreeNode.createTree(strs);
//        recoverTree(root);
//        System.out.println(TreeNode.levelOrderToString(root));
//        System.out.println(TreeNode.levelOrderToList(root));
//        System.out.println(TreeNode.serializeToString(root));
//        System.out.println(Arrays.toString(TreeNode.serialize(root)));

//        int[] arr = {-10, -3, 0, 5, 9};
//        ListNode head = ListNode.createListByArray(arr);
//        TreeNode root = sortListToBST(head);
//        System.out.println(TreeNode.levelOrderToList(root));

//        String[] strs = {"3","9","20","#","#","15","7"};
//        TreeNode root = TreeNode.createTree(strs);
//        System.out.println(minDepth(root));
//
//        String str = "2#3###4";
//        root = TreeNode.createTree(str);
//        System.out.println(minDepth(root));

//        String[] strs = {"5", "4", "8", "11", "#", "13", "4", "7", "2", "#", "#", "#", "#", "5", "1"};
//        TreeNode root = TreeNode.createTree(strs);
//        System.out.println(pathSum(root, 22));

        String str = "12534#6";
        TreeNode root = TreeNode.createTree(str);
        flatten(root);
        System.out.println(TreeNode.levelOrderToList(root));
    }


    private static TreeNode t1, t2, pre;//记录两个需要交换的节点 和 遍历时前一个节点

    /**
     * 99. 恢复二叉搜索树
     * 给定一个二叉搜索树的根节点 root ，该树中的恰好两个节点的值被错误地交换。
     * 请在不改变其结构的情况下，恢复这棵树 。
     *
     * @param root
     */
    public static void recoverBST(TreeNode root) {
        inorderHelp(root);
        int tmp = t1.val;
        t1.val = t2.val;
        t2.val = tmp;
    }

    /**
     * 在中序遍历时记录下两个错误的节点
     *
     * @param root
     */
    private static void inorderHelp(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderHelp(root.left);
        if (pre != null && pre.val > root.val) {
            if (t1 == null) {
                t1 = pre;
            }
            t2 = root;
        }
        pre = root;
        inorderHelp(root.right);
    }

    private static ListNode rootHead;

    /**
     * 109. 有序链表转换二叉搜索树
     * 给定一个单链表的头节点 head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
     * 一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
     *
     * @param head
     * @return
     */
    public static TreeNode sortListToBST(ListNode head) {
        rootHead = head;
        return buildBST(0, ListNode.getLength(head) - 1);
    }

    /**
     * 分治 + 中序遍历，先建立左子树，再建立右子树
     *
     * @param left
     * @param right
     * @return
     */
    private static TreeNode buildBST(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (right + left + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildBST(left, mid - 1);
        root.val = rootHead.val;
        rootHead = rootHead.next;
        root.right = buildBST(mid + 1, right);
        return root;
    }

    /**
     * 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    static List<List<Integer>> res;

    /**
     * 113. 路径总和 II
     * 给定二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        dfs(root, targetSum, 0, new ArrayList<>());
        return res;
    }

    /**
     * 回溯找到所有符合的路径
     *
     * @param root      根节点
     * @param targetSum 目标路径和
     * @param sum       当前累计路径和
     * @param list      记录路径上的值
     */
    private static void dfs(TreeNode root, int targetSum, int sum, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null && sum + root.val == targetSum) {
            list.add(root.val);
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        dfs(root.left, targetSum, sum + root.val, list);
        dfs(root.right, targetSum, sum + root.val, list);
        list.remove(list.size() - 1);
    }

    private static TreeNode last = null;

    /**
     * 114. 二叉树展开为链表 (逆后序遍历)
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * <p>
     * 展开后的单链表应该同样使用 TreeNode ，
     * 其中right子指针指向链表中下一个结点，而左子指针始终为null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = last;
        root.left = null;
        last = root;
    }
}
