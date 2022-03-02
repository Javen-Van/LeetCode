import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-12-24
 * @Description
 */
public class p1700 {

    @Test
    public void test() {
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        System.out.println(eatenApples(apples, days));
    }

    // p1705 吃苹果的最大数目「贪心 + 优先队列」
    public int eatenApples(int[] apples, int[] days) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int n = apples.length, res = 0;
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && queue.peek()[0] <= i) {
                queue.poll();
            }
            if (apples[i] > 0) queue.offer(new int[]{i + days[i], apples[i]});
            if (!queue.isEmpty()) {
                int[] peek = queue.peek();
                peek[1]--;
                if (peek[1] == 0) queue.poll();
                res++;
            }
        }
        Map<Character, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            while (!queue.isEmpty() && queue.peek()[0] <= n) {
                queue.poll();
            }
            if (queue.isEmpty()) break;
            int[] poll = queue.poll();
            int cur = Math.min(poll[0] - n, poll[1]);
            res += cur;
            n += cur;
        }
        return res;
    }

    // p1706 球会落在何处「简单模拟」
    public int[] findBall(int[][] grid) {
        // 1导向右侧，-1导向左侧
        int m = grid.length, n = grid[0].length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int j = 0, col = i, dir = grid[j][col];
            while (true) {
                if ((col == n - 1 && dir == 1) || (col == 0) && dir == -1) break;
                if (dir + grid[j][col + dir] == 0) break;
                j++;
                col += dir;
                if (j == m) break;
                dir = grid[j][col];
            }
            res[i] = j == m ? col : -1;
        }
        return res;
    }

    // p1765 地图中的最高点
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] height = new int[m][n]; // -1表示水域，正数表示陆地，0表示还未初始化
        int[] dif = new int[]{0, 1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = -1;
                    for (int k = 0; k < 4; k++) {
                        int x = i + dif[k], y = j + dif[k + 1];
                        if (boundary(x, y, m, n) && isWater[x][y] == 0 && height[x][y] == 0) { // 当前位置不是水域且没有访问过
                            height[x][y] = 1;
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();

            }
        }
        return height;
    }

    public boolean boundary(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
