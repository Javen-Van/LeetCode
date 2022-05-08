package lccup;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class week77 {

    // Q1
    public int countPrefixes(String[] words, String s) {
        int res = 0;
        for (String word : words) {
            if (isPrefix(word, s)) res++;
        }
        return res;
    }

    public boolean isPrefix(String word, String s) {
        int m = word.length(), n = s.length();
        if (m > n) return false;
        for (int i = 0; i < m; i++) {
            if (word.charAt(i) != s.charAt(i)) return false;
        }
        return true;
    }

    // Q2
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length, index = 0;
        long min = Integer.MAX_VALUE, sum = nums[0];
        long[] pre = new long[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
            sum += nums[i];
        }
        for (int i = 0; i < n; i++) {
            long dif = Math.abs(pre[i] / (i + 1) - (i == n - 1 ? 0 : (sum - pre[i]) / (n - i - 1)));
            if (dif < min) {
                min = dif;
                index = i;
            }
        }
        return index;
    }

    // Q3
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // 0: 空闲，1：guard，2：wall
        long total = (long) m * n;
        int lenG = guards.length, lenW = walls.length, count = 0;
        total -= lenW - lenG;
        int[][] grid = new int[m][n];
        int[] dif = new int[]{1, 0, -1, 0, 1};
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = 2;
        }
        for (int[] guard : guards) {
            int row = guard[0], col = guard[1];
            grid[row][col] = 1;
        }
        for (int[] guard : guards) {
            int row = guard[0], col = guard[1];
            for (int i = 0; i < 4; i++) {
                int x = row + dif[i], y = col + dif[i + 1];
                while (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                    count++;
                    x += dif[i];
                    y += dif[i + 1];
                }
            }
        }
        return (int) (total - count);
    }

    // Q4
    int m, n, total;
    int[] dif = new int[]{1, 0, -1, 0, 1};
    int[][][] status;

    public int maximumMinutes(int[][] grid) {
        // 0 floor 1 fire 2 wall
        this.m = grid.length;
        this.n = grid[0].length;
        status = new int[this.m * this.n][this.m][this.n];
        status[0] = grid;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) queue.offer(new int[]{i, j});
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            time++;
            copy(status[time - 1], status[time]);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = cur[0] + dif[j], y = cur[1] + dif[j + 1];
                    if (boundary(x, y) && status[time][x][y] == 0) {
                        status[time][x][y] = 1;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        this.total = time - 1;
        int l = 0, r = time - 1, median;
        while (l <= r) {
            median = (l + r) / 2;
            boolean[][] isVis = new boolean[m][n];
            isVis[0][0] = true;
            if (canEscape(0, 0, median, isVis)) l = median + 1;
            else r = median - 1;
        }
        if (r == -1) return -1;
        if (l == time) return 1000000000;
        return l - 1;
    }


    public boolean canEscape(int x, int y, int time, boolean[][] isVis) {
        if (x == m - 1 && y == n - 1) return true;
        boolean res = false;
        if (status[time][x][y] != 0) return false;
        for (int i = 0; i < 4; i++) {
            int row = x + dif[i], col = y + dif[i + 1];
//            if (row == m - 1 && col == n - 1) return true;
            if (boundary(row, col) && !isVis[row][col] && status[Math.min(this.total, time)][row][col] == 0) {
                isVis[row][col] = true;
                res |= canEscape(row, col, time + 1, isVis);
                isVis[row][col] = false;
            }
        }
        return res;
    }

    public boolean boundary(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public void copy(int[][] origin, int[][] target) {
        for (int i = 0; i < origin.length; i++) {
            System.arraycopy(origin[i], 0, target[i], 0, origin[0].length);
        }
    }

    @Test
    public void test() {
        int[][] grid = new int[][]{{0, 2, 0, 0, 1}, {0, 2, 0, 2, 2}, {0, 2, 0, 0, 0}, {0, 0, 2, 2, 0}, {0, 0, 0, 0, 0}};
        maximumMinutes(grid);
    }

}
