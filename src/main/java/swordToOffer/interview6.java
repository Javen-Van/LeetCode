package swordToOffer;

import bean.TreeNode;

/**
 * @author Javen
 * @create 2022-05-16
 * @Description
 */
public class interview6 {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        if (p.right != null) {
            successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }
        while (root != null) {
            if (root.val > p.val) {
                successor = root;
                root = root.left;
            } else root = root.right;
        }
        return successor;
    }
}
