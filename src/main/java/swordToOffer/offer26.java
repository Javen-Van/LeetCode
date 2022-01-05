package swordToOffer;

import bean.TreeNode;

/**
 * @author Javen
 * @create 2022-01-05
 * @Description 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */
public class offer26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return A != null && B != null && (isEqual(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public boolean isEqual(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        return A.val == B.val && isEqual(A.left, B.left) && isEqual(A.right, B.right);
    }

}
