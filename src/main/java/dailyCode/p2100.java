package dailyCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class p2100 {

    /**
     * p2101 引爆最多的炸弹
     * @param bombs
     * @return
     */
    public int maximumDetonation(int[][] bombs) {
        List<List<Integer>> table = new ArrayList<>();
        int n = bombs.length;
        for (int i = 0; i < n; i++) {
            table.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int x = bombs[i][0], y = bombs[i][1],r=bombs[i][2];
            for (int j = 0; j < n; j++) {
                int x0 = bombs[j][0], y0 = bombs[j][1];
                if (inArea(x, y, r, x0, y0)) {
                    table.get(i).add(j);
                }
            }
        }
        int[] parent = new int[n], count = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (Integer idx : table.get(i)) {
                union(parent, i, idx);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            count[findParent(parent, i)]++;
            res = Math.max(res, count[parent[i]]);
        }
        return res;
    }

    private int findParent(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent, parent[i]);
        }
        return parent[i];
    }

    private void union(int[] parent, int i, int j) {
        parent[findParent(parent, i)] = findParent(parent, j);
    }

    private boolean inArea(int x, int y, int r, int x0, int y0) {
        return (long) (x - x0) * (x - x0) + (long) (y - y0) * (y - y0) <= (long) r * r;
    }

    @Test
    public void test() {
        int[][] bombs = {{1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}};
        System.out.println(maximumDetonation(bombs));
    }
}
