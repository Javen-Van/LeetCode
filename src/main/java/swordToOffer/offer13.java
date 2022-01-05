package swordToOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Javen
 * @create 2021-12-25
 * @Description 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、
 * 右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，
 * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class offer13 {

    // BFS
    public int movingCount(int m, int n, int k) {
        int res = 0;
        int[][] board = new int[m][n];
        for (int i = 0; i < m; i++) {
            int x = calculate(i);
            for (int j = 0; j < n; j++) {
                board[i][j] = x + calculate(j);
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int[] diff = {0, 1, 0, -1, 0};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                board[cur[0]][cur[1]] = -1;
                res++;
                for (int j = 0; j < 4; j++) {
                    int newX = cur[0] + diff[j], newY = cur[1] + diff[j + 1];
                    if (isInBoard(newX, newY, m, n) && board[newX][newY] <= k && board[newX][newY] != -1) {
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
        return res;
    }

    public int calculate(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    public boolean isInBoard(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
