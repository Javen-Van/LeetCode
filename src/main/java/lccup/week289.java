package lccup;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class week289 {

    // Q1
    public String digitSum(String s, int k) {
        int n = s.length(), index = 0;
        if (n <= k) return s;
        StringBuilder sb = new StringBuilder();
        while (index < n) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += s.charAt(index++) - '0';
                if (index == n) break;
            }
            sb.append(sum);
        }
        return digitSum(sb.toString(), k);
    }

    // Q2
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int task : tasks) {
            int num = count.getOrDefault(task, 0);
            count.put(task, num + 1);
        }
        int res = 0;
        for (int v : count.values()) {
            if (v == 1) return -1;
            int n = v / 3;
            if (v % 3 == 0) {
                res += n;
            } else {
                res += n + 1;
            }
        }
        return res;
    }

    // Q3
    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length, max = 0;
        int[][][] sumX = new int[m][n + 1][2]; // 0: 2, 1: 5
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                sumX[i][j][0] = j == 0 ? 0 : sumX[i][j - 1][0] + countZero(grid[i][j - 1], 2);
                sumX[i][j][1] = j == 0 ? 0 : sumX[i][j - 1][1] + countZero(grid[i][j - 1], 5);
            }
        }
        int[][][] sumY = new int[m + 1][n][2];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                sumY[i][j][0] = i == 0 ? 0 : sumY[i - 1][j][0] + countZero(grid[i - 1][j], 2);
                sumY[i][j][1] = i == 0 ? 0 : sumY[i - 1][j][1] + countZero(grid[i - 1][j], 5);
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int y21 = sumY[i - 1][j - 1][0], y22 = sumY[m][j - 1][0] - sumY[i][j - 1][0];
                int y51 = sumY[i - 1][j - 1][1], y52 = sumY[m][j - 1][1] - sumY[i][j - 1][1];
                int x21 = sumX[i - 1][j][0], x22 = sumX[i - 1][n][0] - sumX[i - 1][j - 1][0];
                int x51 = sumX[i - 1][j][1], x52 = sumX[i - 1][n][1] - sumX[i - 1][j - 1][1];
                int temp1 = Math.max(Math.min(y21 + x21, y51 + x51), Math.min(y21 + x22, y51 + x52));
                int temp2 = Math.max(Math.min(y22 + x21, y52 + x51), Math.min(y22 + x22, y52 + x52));
                max = Math.max(Math.max(temp1, temp2), max);
            }

        }
        return max;
    }

    public int countZero(long num, int mod) {
        int res = 0;
        while (num != 0 && num % mod == 0) {
            num /= mod;
            res++;
        }
        return res;
    }

    @Test
    public void test() {
        int[][] grid = new int[][]{{534, 575, 625, 84, 20, 999, 35}, {208, 318, 96, 380, 819, 102, 669}};
        System.out.println(maxTrailingZeros(grid));
    }
}
