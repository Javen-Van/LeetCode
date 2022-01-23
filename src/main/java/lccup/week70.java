package lccup;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2022-01-22
 * @Description
 */
public class week70 {

    // Q1
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int n = cost.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (i % 3 == 2) continue;
            res += cost[n - 1 - i];
        }
        return res;
    }

    // Q2
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long min = 0, max = 0, pre = 0;
        for (int difference : differences) {
            pre += difference;
            min = Math.min(min, pre);
            max = Math.max(max, pre);
        }
        long dif1 = lower - min, dif2 = upper - max;
        if (dif1 >= dif2) return 0;
        return (int) (dif2 - dif1 + 1);
    }

    // Q3
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length, n = grid[0].length;
        boolean[] visit = new boolean[m * n];
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            else {
                if (o1[1] != o2[1]) return o1[1] - o2[1];
                return o1[2] - o2[2];
            }
        });
        List<List<Integer>> res = new ArrayList<>();
        int[] dif = new int[]{0, 1, 0, -1, 0};
        queue.offer(new int[]{grid[start[0]][start[1]], start[0], start[1]});
        visit[start[0] * n + start[1]] = true;
        outer:
        while (!queue.isEmpty()) {
            Queue<int[]> temp = new PriorityQueue<>(queue);
            queue.clear();
            int size = temp.size();
            for (int i = 0; i < size; i++) {
                int[] cur = temp.poll();
                int price = cur[0], x = cur[1], y = cur[2];
                if (price >= pricing[0] && price <= pricing[1]) {
                    res.add(new ArrayList<>(Arrays.asList(x, y)));
                    if (--k == 0) break outer;
                }
                for (int j = 0; j < 4; j++) {
                    int nextX = x + dif[j], nextY = y + dif[j + 1];
                    if (check(grid, nextX, nextY) && !visit[nextX * n + nextY]) {
                        queue.offer(new int[]{grid[nextX][nextY], nextX, nextY});
                        visit[nextX * n + nextY] = true;
                    }
                }
            }
        }
        return res;
    }

    public boolean check(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        return x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 0;
    }

    // Q4
    public int numberOfWays(String corridor) {
        int n = corridor.length(), s = 0, p = 0, MOD = 1000000007;
        long res = 1;
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i) == 'S') {
                s++;
            }
            if (s == 2) {
                flag = true;
                s = 0;
            }
            if (flag) {
                while (++i < n && corridor.charAt(i) == 'P') p++;
                i--;
                flag = false;
                res = (res * (p + 1)) % MOD;
                p = 0;
            }
        }
        return s == 1 ? 0 : (int) res;
    }

    @Test
    public void test() {
        System.out.println(minimumCost(new int[]{1, 2, 3}));
        System.out.println(numberOfArrays(new int[]{-40}, -46, 53));
        System.out.println(highestRankedKItems(new int[][]{{1, 2, 0, 1}, {1, 3, 3, 1}, {0, 2, 5, 1}}, new int[]{2, 3}, new int[]{2, 3}, 2));
        System.out.println(numberOfWays("SSPPSPS"));
    }
}
