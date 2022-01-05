package swordToOffer;

import bean.TreeNode;

import java.util.*;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * @author Javen
 * @create 2022-01-05
 * @Description 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 */
public class offer32 {

    public int[] levelOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) continue;
            queue.offer(cur.left);
            queue.offer(cur.right);
            res.add(cur.val);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

}
