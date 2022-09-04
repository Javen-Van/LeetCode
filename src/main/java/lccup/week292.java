package lccup;

import bean.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class week292 {

    // Q1
    public String largestGoodInteger(String num) {
        String res = "0";
        for (int i = 0; i < num.length() - 3; i++) {
            String s = num.substring(i, i + 3);
            boolean flag = true;
            for (int j = 1; j < 3; j++) {
                if (s.charAt(j) != s.charAt(0)) {
                    flag = false;
                    break;
                }
            }
            if (flag && s.compareTo(res) > 0)
                res = s;
        }
        return res.equals("0") ? "" : res;
    }

    // Q2
    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int[] dfs(TreeNode cur) {
        if (cur == null)
            return new int[] { 0, 0 };
        int[] left = dfs(cur.left);
        int[] right = dfs(cur.right);
        int sum = cur.val + left[0] + right[0], count = left[1] + right[1] + 1;
        if (sum / count == cur.val)
            ans++;
        return new int[] { sum, count };
    }

    // Q3
    public int countTexts(String pressedKeys) {
        int[] keyBoard = { 0, 0, 3, 3, 3, 3, 3, 4, 3, 4 };
        int MOD = 1000000007, n = pressedKeys.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = pressedKeys.charAt(i - 1) - '0';
            dp[i] = (dp[i] + dp[i - 1]) % MOD;
            int index = i - 1;
            while (index > Math.max(0, i - keyBoard[num]) && pressedKeys.charAt(index) == pressedKeys.charAt(index - 1))
                dp[i] = (dp[i] + dp[--index]) % MOD;
        }
        return dp[n];
    }

    // Q4
    int m, n;
    char[][] grid;
    Deque<Character> stack = new LinkedList<>();

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        if ((m + n) % 2 == 0)
            return false;
        return dfs(0, 0);
    }

    public boolean dfs(int x, int y) {
        if (m + n - x - y - 2 < stack.size())
            return false;
        boolean res = false, flag = false;
        if (!stack.isEmpty() && grid[x][y] == ')' && stack.peekLast() == '(') {
            flag = true;
            stack.pollLast();
        } else {
            stack.offer(grid[x][y]);
        }
        if (x == m - 1 && y == n - 1 && stack.isEmpty())
            return true;
        if (boundary(x + 1, y))
            res = dfs(x + 1, y);
        if (boundary(x, y + 1))
            res |= dfs(x, y + 1);
        if (flag)
            stack.offerLast('(');
        else
            stack.pollLast();
        return res;
    }

    public boolean boundary(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
