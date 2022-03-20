package exam.meituan;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Javen
 * @create 2022-03-12
 * @Description
 */
public class Main5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] color = new int[n];
            for (int i = 0; i < n; i++) {
                color[i] = sc.nextInt();
            }
            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = sc.nextInt();
            }
            Tree root;
            Map<Integer, Tree> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(i + 1, new Tree(color[i]));
            }
            for (int i = 0; i < n; i++) {
                if (parent[i] == 0) {
                    root = map.get(i + 1);
                }
                Tree p = map.get(parent[i]), child = map.get(i + 1);
                if (p.left == null) {
                    p.left = child;
                } else {
                    p.right = child;
                }
            }
            int countW = 0, countB = 0;
            for (int i = 0; i < n; i++) {
                Tree tree = map.get(i + 1);
                int b = dfs(tree, 1) - 1;
                if (tree.color == 0) {
                    if ((tree.left == null && tree.right == null) || b > 0) countW++;
                } else {
                    if ((tree.left == null && tree.right == null) || b == 0) countB++;
                }
            }
            System.out.println(countW + " " + countB);
        }
    }

    public static int dfs(Tree root, int color) {
        if (root == null) return 0;
        return (root.color == color ? 1 : 0) + dfs(root.left, color) + dfs(root.right, color);
    }

    public static class Tree {
        int color;
        Tree left;
        Tree right;

        public Tree() {
        }

        public Tree(int color, Tree left, Tree right) {
            this.color = color;
            this.left = left;
            this.right = right;
        }

        public Tree(int color) {
            this.color = color;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
}
