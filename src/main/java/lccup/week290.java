package lccup;

import org.junit.Test;

import java.util.*;

public class week290 {

    // Q1
    public List<Integer> intersection(int[][] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i : nums[0]) {
            res.add(i);
        }
        for (int[] num : nums) {
            List<Integer> temp = new ArrayList<>();
            for (int i : num) {
                temp.add(i);
            }
            res.retainAll(temp);
        }
        Collections.sort(res);
        return res;
    }

    // Q2
    public int countLatticePoints(int[][] circles) {
        Set<Integer> set = new HashSet<>();
        for (int[] circle : circles) {
            int r = circle[2], x = circle[0] - r, y = circle[1] - r;
            for (int i = 0; i <= r * 2; i++) {
                for (int j = 0; j <= r * 2; j++) {
                    if (isInCircle(x + i, y + j, circle)) set.add(10000 * (x + i) + y + j);
                }
            }
        }
        return set.size();
    }

    public boolean isInCircle(int x, int y, int[] circle) {
        return Math.pow(x - circle[0], 2) + Math.pow(y - circle[1], 2) <= Math.pow(circle[2], 2);
    }

    @Test
    public void test() {
        System.out.println(countLatticePoints(new int[][]{{2, 2, 1}}));
    }

    // Q3
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
        int n = rectangles.length, m = points.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int x = points[i][0], y = points[i][1];
            int idx1 = binarySearch(x, rectangles, 0, 0, n - 1);
            int idx2 = binarySearch(y, rectangles, 1, idx1, n - 1);
            res[i] = n - idx2;
        }
        return res;
    }

    public int binarySearch(int target, int[][] rectangles, int idx, int l, int r) {
        int median;
        while (l <= r) {
            median = (l + r) / 2;
            if (target <= rectangles[median][idx]) r = median - 1;
            else l = median + 1;
        }
        return l;
    }
}
