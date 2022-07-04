package demo.BinaryTree;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        String strs = "13##2";
        TreeNode root = TreeNode.createTree(strs);
        recoverTree(root);
        System.out.println(TreeNode.levelOrderToString(root));
        System.out.println(TreeNode.levelOrderToList(root));
        System.out.println(TreeNode.serializeToString(root));
        System.out.println(Arrays.toString(TreeNode.serialize(root)));
    }


    private static TreeNode t1, t2, pre;//记录两个需要交换的节点 和 遍历时前一个节点

    /**
     * 99. 恢复二叉搜索树
     * 给定一个二叉搜索树的根节点 root ，该树中的恰好两个节点的值被错误地交换。
     * 请在不改变其结构的情况下，恢复这棵树 。
     *
     * @param root
     */
    public static void recoverTree(TreeNode root) {
        inorderHelp(root);
        int tmp = t1.val;
        t1.val = t2.val;
        t2.val = tmp;
    }

    /**
     * 在中序遍历时记录下两个错误的节点
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
}
