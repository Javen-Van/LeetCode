import bean.TreeNode;

import java.util.*;

/**
 * @author Javen
 * @create 2021-08-24
 * @Description 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市
 * fromi 开始，以价格 toi 抵达 pricei。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的
 * 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 */
public class p787 {
    // bfs超出时间限制
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] map = new int[n][n];
        for (int[] flight : flights) {
            map[flight[0]][flight[1]] = flight[2];
        }
        int res = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});
        while (k >= 0 && !queue.isEmpty()) {
            k--;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == dst) {
                    res = Math.min(res, cur[1]);
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (map[cur[0]][j] > 0) {
                        queue.offer(new int[]{j, cur[1] + map[cur[0]][j]});
                    }
                }
            }
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    // dp
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k + 2][n];
        int INF = 1010000;
        for (int i = 0; i <= k + 1; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][src] = 0;
        for (int i = 1; i <= k + 1; i++) {
            for (int[] flight : flights) {
                dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
            }
        }
        int res = INF;
        for (int i = 0; i <= k + 1; i++) {
            res = Math.min(res, dp[i][dst]);
        }
        return (res == INF) ? -1 : res;
    }

    // p735
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int x : asteroids) {
            while (!stack.isEmpty() && stack.peek() * x < 0) {
                if (Math.abs(stack.peek()) < Math.abs(x)) stack.pop();
                else if (Math.abs(stack.peek()) == Math.abs(x)) {
                    stack.pop();
                    break;
                } else {
                    x = stack.pop();
                }
            }
            stack.push(x);
        }
        Integer[] integers = stack.toArray(new Integer[0]);
        int[] res = new int[integers.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = integers[i];
        }
        return res;
    }

    public boolean isSameDirection(int num1, int num2) {
        return num1 * num2 > 0;
    }

    // p700 二叉搜索树的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (root.val < val) return searchBST(root.right, val);
        else return searchBST(root.left, val);
    }
}
