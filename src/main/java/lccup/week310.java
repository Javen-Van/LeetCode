package lccup;

import javafx.util.Pair;

import java.util.*;

public class week310 {

    public int mostFrequentEven(int[] nums) {
        Map<Integer, Pair> map = new HashMap<>();
        for (int num : nums) {
            Pair pair = map.getOrDefault(num, new Pair(num, 0));
            pair.value += 1;
            map.put(num, pair);
        }
        List<Pair> list = new ArrayList<>(map.values());
        list.sort((o1, o2) -> {
            if (o1.value == o2.value) return o1.key - o2.key;
            return o2.value - o1.value;
        });
        for (Pair pair : list) {
            if (pair.key % 2 == 0) return pair.key;
        }
        return -1;
    }

    static class Pair {
        int key, value;

        public Pair() {
        }

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public int partitionString(String s) {
        int res = 1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                res++;
                set.clear();
            }
            set.add(c);
        }
        return res;
    }

    public int minGroups(int[][] intervals) {
        for (int[] interval : intervals) {
            update(root, 0, N, interval[0], interval[1], 1);
        }
        return root.val;
    }


    private final int N = (int) 1e6;
    private Node root = new Node();

    public void update(Node node, int l, int r, int start, int end, int val) {
        if (start <= l && r <= end) {
            node.val += val;
            node.add += val;
            return;
        }
        pushDown(node);
        int mid = (l + r) / 2;
        if (start <= mid) update(node.left, l, mid, start, end, val);
        if (end > mid) update(node.right, mid + 1, r, start, end, val);
        pushUp(node);
    }

    public int query(Node node, int l, int r, int start, int end) {
        if (start <= l && r <= end) return node.val;
        pushDown(node);
        int res = 0, mid = (l + r) / 2;
        if (start <= mid) res = Math.max(res, query(node.left, l, mid, start, end));
        if (end > mid) res = Math.max(res, query(node.right, mid + 1, r, start, end));
        return res;
    }

    public void pushDown(Node node) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;
        node.left.val += node.add; // 按题意做更改
        node.right.val += node.add;
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }

    public void pushUp(Node node) {
        node.val = Math.max(node.left.val, node.right.val); // 按题意修改
    }

    class Node {
        Node left, right;
        int val, add;
    }

}
