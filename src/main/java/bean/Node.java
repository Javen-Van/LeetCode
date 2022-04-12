package bean;

import java.util.List;

/**
 * @author Javen
 * @create 2022-01-06
 * @Description
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
