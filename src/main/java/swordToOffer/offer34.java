package swordToOffer;

import bean.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Javen
 * @create 2022-01-06
 * @Description 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 */
public class offer34 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        Stack<Integer> stack = new Stack<>();
        dfs(root, target, stack);
        return res;
    }

    public void dfs(TreeNode root, int target, Stack<Integer> stack) {
        if (root == null) return;
        target -= root.val;
        stack.push(root.val);
        if (root.left == null && root.right == null && target == 0) res.add(new ArrayList<>(stack));
        dfs(root.left, target, stack);
        dfs(root.right, target, stack);
        stack.pop();
    }

}
