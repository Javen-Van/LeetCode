package swordToOffer;

import bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Javen
 * @create 2022-01-06
 * @Description 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 */
public class offer37 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> code = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) code.add("null");
            else {
                queue.offer(cur.left);
                queue.offer(cur.right);
                code.add(cur.val + "");
            }
        }
        return String.join(",", code);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] code = data.split(",");
        if ("null".equals(code[0])) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(code[0]));
        queue.offer(root);
        for (int i = 1; i < code.length; i += 2) {
            TreeNode parent = queue.poll();
            if (!"null".equals(code[i])) {
                TreeNode left = new TreeNode(Integer.parseInt(code[i]));
                parent.left = left;
                queue.offer(left);
            }
            if (!"null".equals(code[i + 1])) {
                TreeNode right = new TreeNode(Integer.parseInt(code[i + 1]));
                parent.right = right;
                queue.offer(right);
            }
        }
        return root;
    }
}
