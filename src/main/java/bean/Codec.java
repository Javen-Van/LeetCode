package bean;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Javen
 * @create 2022-05-11
 * @Description p449 序列化和反序列化二叉搜索树
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString().isEmpty() ? "" : sb.substring(0, sb.length() - 1);
    }

    private void postOrder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        postOrder(root.left, sb);
        postOrder(root.right, sb);
        sb.append(root.val).append('-');
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] list = data.split("-");
        Deque<Integer> deque = new LinkedList<>();
        for (String s : list) {
            deque.push(Integer.parseInt(s));
        }
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, deque);
    }

    public TreeNode construct(int low, int up, Deque<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < low || stack.peek() > up) return null;
        int val = stack.pop();
        TreeNode node = new TreeNode(val);
        node.right = construct(val, up, stack);
        node.left = construct(low, val, stack);
        return node;
    }
}
