package swordToOffer;

import bean.Node;

/**
 * @author Javen
 * @create 2022-01-06
 * @Description 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向
 */
public class offer36 {
    Node pre, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);
        if (pre == null) head = root;
        else pre.right = root;
        root.left = pre;
        pre = root;
        dfs(root.right);
    }
}
