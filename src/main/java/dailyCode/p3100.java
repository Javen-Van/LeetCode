package dailyCode;

import java.util.TreeMap;

public class p3100 {

    /**
     * p3102 最小化曼哈顿距离
     *
     * @param points
     * @return
     */
    public int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> sx = new TreeMap<>(), sy = new TreeMap<>();
        for (int[] point : points) {
            int x = point[0], y = point[1];
            point[0] = x - y;
            point[1] = x + y;
            sx.put(point[0], sx.getOrDefault(point[0], 0) + 1);
            sy.put(point[1], sy.getOrDefault(point[1], 0) + 1);
        }
        int res = Integer.MAX_VALUE;
        // 遍历所有点，尝试将其移除，更新最小距离
        for (int[] point : points) {
            int x = point[0], y = point[1];
            // 如果只出现1次，则移除后不参与计算
            if (sx.get(x) == 1) {
                sx.remove(x);
            }
            if (sy.get(y) == 1) {
                sy.remove(y);
            }
            res = Math.min(res, Math.max(sx.lastKey() - sx.firstKey(), sy.lastKey() - sy.firstKey()));
            // 使用完再加回集合中
            if (!sx.containsKey(x)) sx.put(x, 1);
            if (!sy.containsKey(y)) sy.put(y, 1);
        }
        return res;
    }

    /**
     * p3115 质数的最大距离
     *
     * @param nums
     * @return
     */
    public int maximumPrimeDifference(int[] nums) {
        int start = -1, end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i])) {
                if (start == -1) {
                    start = i;
                }
                end = i;
            }
        }
        return end - start;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
