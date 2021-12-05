import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Javen
 * @create 2021-08-15
 * @Description 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元
 * 格内（可以穿过网格边界到达网格之外）。你最多可以移动 maxMove 次球。
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7
 * 取余 后的结果。
 */
public class p576 {

    @Test
    public void test() {
        System.out.println(findPaths(1, 3, 3, 0, 1));
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int res = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startColumn});
        while (maxMove >= 0 && !queue.isEmpty()) {
            int size = queue.size();
            maxMove--;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int x = poll[0], y = poll[1];
                if (boundary(x, y, m, n)) {
                    res++;
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    queue.offer(new int[]{x + dx[j], y + dy[j]});
                }
            }
        }
        return res;
    }

    boolean boundary(int x, int y, int m, int n) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    //p594
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num) + map.get(num + 1));
            }
        }
        return res;
    }
}
