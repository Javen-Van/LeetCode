package swordToOffer;

import bean.TreeNode;

/**
 * @author Javen
 * @create 2022-01-05
 * @Description 27 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * 28 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class offer27_28 {

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode res = new TreeNode(root.val);
        res.left = mirrorTree(root.right);
        res.right = mirrorTree(root.left);
        return res;
    }

    public boolean isSymmetric(TreeNode root) {
        TreeNode node = mirrorTree(root);
        return isEqual(node, root);
    }

    public boolean isEqual(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && isEqual(a.left, b.left) && isEqual(a.right, b.right);
    }
}
