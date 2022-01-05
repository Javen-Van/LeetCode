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
