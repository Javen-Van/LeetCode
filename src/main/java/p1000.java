import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-12-03
 * @Description
 */
public class p1000 {
    // p1005 K次取反后最大化的数组和, -100 <= A[i] <= 100
    public int largestSumAfterKNegations(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(A).forEach(value -> map.put(value, map.getOrDefault(value, 0) + 1));
        int sum = Arrays.stream(A).sum();
        for (int i = -100; i < 0; i++) {
            if (map.containsKey(i)) {
                int minus = Math.min(K, map.get(i));
                sum += (-i) * minus * 2;
                map.put(-i, map.getOrDefault(-i, 0) + minus);
                K -= minus;
                if (K == 0) break;
            }
        }
        if (K % 2 == 1 && !map.containsKey(0)) {
            for (int i = 1; i < 101; i++) {
                if (map.containsKey(i)) {
                    sum -= 2 * i;
                    break;
                }
            }
        }
        return sum;
    }

    // p1034 边界着色「BFS」
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int origin = grid[row][col];
        boolean[][] isVisit = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int curX = poll[0];
            int curY = poll[1];
            boolean isBorder = false;
            for (int i = 0; i < 4; i++) {
                int x = curX + dx[i];
                int y = curY + dy[i];
                if (!(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == origin)) {
                    isBorder = true;
                } else if (!isVisit[x][y]) {
                    isVisit[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
            if (isBorder) {
                borders.add(new int[]{curX, curY});
            }
        }
        for (int[] border : borders) {
            grid[border[0]][border[1]] = color;
        }
        return grid;
    }

    // p1036 逃离大迷宫「有限次数的BFS」
    public static class Solution {
        public final long BOUNDARY = 1000000L;
        public final int VALID = 0;
        public final int FOUND = 1;
        public final int STUCK = -1;

        public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
            if (blocked.length < 2) return true;
            Set<Long> set = new HashSet<>(); // int[]型的set没有重写hash，不能去重
            for (int[] block : blocked) {
                set.add(block[0] * BOUNDARY + block[1]);
            }
            int res = bfs(blocked, source, target, set);
            if (res == FOUND) return true;
            else if (res == STUCK) return false; // source被围住
            else return bfs(blocked, target, source, set) != STUCK; // target是否被围住
        }

        public int bfs(int[][] blocked, int[] source, int[] target, Set<Long> unreachable) {
            Queue<int[]> queue = new LinkedList<>();
            Set<Long> set = new HashSet<>(unreachable);
            queue.offer(source);
            set.add(source[0] * BOUNDARY + source[1]);
            int count = blocked.length * (blocked.length - 1) / 2; // block所能围出的最大区域
            int[] diff = {0, 1, 0, -1, 0};
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + diff[i], ny = cur[1] + diff[i + 1];
                    int[] next = {nx, ny};
                    if (!set.contains(nx * BOUNDARY + ny) && nx >= 0 && nx < BOUNDARY && ny >= 0 && ny < BOUNDARY) {
                        if (nx == target[0] && ny == target[1]) return FOUND; // 到达目的地，直接返回
                        queue.offer(next);
                        set.add(nx * BOUNDARY + ny);
                        count--;
                    }
                    if (count == 0) return VALID; // 跳出区域，围不住
                }
            }
            return STUCK;
        }
    }

    // p1044 最长重复子串


    // p1078 Bigram分词
    public String[] findOcurrences(String text, String first, String second) {
        List<String> res = new ArrayList<>();
        String[] s = text.split(" ");
        for (int i = 2; i < s.length; i++) {
            if (first.equals(s[i - 2]) && second.equals(s[i - 1])) res.add(s[i]);
        }
        return res.toArray(new String[0]);
    }
}
