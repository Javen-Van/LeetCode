package swordToOffer;

import bean.TreeNode;

import java.util.Arrays;

/**
 * @author Javen
 * @create 2022-01-07
 * @Description 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *              假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class offer7 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        while (inorder[index] != preorder[0])
            index++;
        root.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length),
                Arrays.copyOfRange(inorder, index + 1, inorder.length));
        return root;
    }
}
