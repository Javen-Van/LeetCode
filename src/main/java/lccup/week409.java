package lccup;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class week409 {

    class neighborSum {

        private int[][] grid;
        private int[][] diff = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private int[][] diagonalDiff = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        private int n;
        private Map<Integer, int[]> map = new HashMap<>();

        public neighborSum(int[][] grid) {
            this.grid = grid;
            this.n = grid.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map.put(grid[i][j], new int[]{i, j});
                }
            }
        }

        public int adjacentSum(int value) {
            return calSum(value, diff);
        }

        private int calSum(int val, int[][] direction) {
            int sum = 0;
            int[] location = map.get(val);
            for (int[] d : direction) {
                int x = location[0] + d[0], y = location[1] + d[1];
                if (inBoundary(x, y)) sum += grid[x][y];
            }
            return sum;
        }

        private boolean inBoundary(int x, int y) {
            return x >= 0 && x < n && y >= 0 && y < n;
        }

        public int diagonalSum(int value) {
            return calSum(value, diagonalDiff);
        }
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] res = new int[queries.length];
        boolean[][] table = new boolean[n][n];
        for (int i = 0; i < n - 1; i++) {
            table[i][i + 1] = true;
        }
        for (int i = 0; i < queries.length; i++) {
            table[queries[i][0]][queries[i][1]] = true;
            res[i] = calDis(table);
        }
        return res;
    }

    private int calDis(boolean[][] table) {
        int n = table.length;
        int[] dp = new int[n];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (table[j][i]) dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[n - 1];
    }

    public int[] shortestDistanceAfterQueries2(int n, int[][] queries) {
        int[] res = new int[queries.length];
        boolean[][] table = new boolean[n][n];
        int[] dp = new int[n];
        for (int i = 0; i < n - 1; i++) {
            table[i][i + 1] = true;
            dp[i + 1] = i + 1;
        }
        for (int i = 0; i < queries.length; i++) {
            res[i] = process(table, queries[i][0], queries[i][1], dp, n);
        }
        return res;
    }

    private int process(boolean[][] table, int from, int to, int[] dp, int n) {
        table[from][to] = true;
        if (dp[to] <= dp[from] + 1) return dp[n - 1];
        int diff = dp[to] - dp[from] - 1;
        for (int i = to; i < n; i++) {
            dp[i] -= diff;
        }
        return dp[n - 1];
    }

    @Test
    public void test() {
        int[][] query = {{2, 4}, {0, 2}, {0, 4}};
        shortestDistanceAfterQueries2(5, query);
    }
}
