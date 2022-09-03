package swordToOffer;

import bean.TreeNode;

import java.util.*;

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

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                temp.add(cur.val);
            }
            res.add(temp);
        }
        return res;
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur;
                if (level % 2 == 0) {
                    cur = queue.pollFirst();
                    if (cur.left != null) queue.offerLast(cur.left);
                    if (cur.right != null) queue.offerLast(cur.right);
                } else {
                    cur = queue.pollLast();
                    if (cur.right != null) queue.offerFirst(cur.right);
                    if (cur.left != null) queue.offerFirst(cur.left);
                }
                temp.add(cur.val);
            }
            res.add(temp);
            level++;
        }
        return res;
    }

}
