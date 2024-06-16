import bean.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    /**
     * * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     * *
     * * For example: Given the below binary tree and sum = 22,
     * *        5
     * *        / \
     * *     4     8
     * *    /      / \
     * *   11     13   4
     * *  / \         / \
     * * 7  2        5   1
     * * return
     * *
     * * [ [5,4,11,2], [5,8,4,5] ]
     */

    public List<List<Integer>> res = new ArrayList<>();
    public List<Integer> path = new LinkedList<>();
    public int target;

    public List<List<Integer>> test(Node root, int target) {
        if (root == null) {
            return res;
        }
        this.target = target;
        path.add(root.val);
        dfs(root, root.val);
        return res;
    }

    public void dfs(Node cur, int curSum) {
        if (cur.left == null && cur.right == null) {
            if (curSum == target) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if (cur.left != null) {
            path.add(cur.left.val);
            dfs(cur.left, curSum + cur.left.val);
            path.remove(path.size() - 1);
        }
        if (cur.right != null) {
            path.add(cur.right.val);
            dfs(cur.right, curSum + cur.right.val);
            path.remove(path.size() - 1);
        }
    }


}
