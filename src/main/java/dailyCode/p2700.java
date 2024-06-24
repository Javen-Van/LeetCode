package dailyCode;

import org.junit.Test;

import java.util.*;

public class p2700 {

    /**
     * p2713 矩阵中严格递增的单元格数
     *
     * @param mat
     * @return
     */
    public int maxIncreasingCells(int[][] mat) {
        Map<Integer, List<int[]>> treeMap = new TreeMap<>();
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                treeMap.putIfAbsent(mat[i][j], new ArrayList<>());
                treeMap.get(mat[i][j]).add(new int[]{i, j});
            }
        }
        int[] row = new int[m], column = new int[n];
        int res = 0;
        for (List<int[]> value : treeMap.values()) {
            int[] record = new int[value.size()]; // 临时数组，用于记录每个位置当前的dp值
            for (int k = 0; k < value.size(); k++) {
                int i = value.get(k)[0], j = value.get(k)[1];
                record[k] = Math.max(row[i], column[j]) + 1;
                res = Math.max(res, record[k]);
            }
            for (int k = 0; k < value.size(); k++) {
                int i = value.get(k)[0], j = value.get(k)[1];
                row[i] = Math.max(row[i], record[k]); // 更新i行的最大dp值
                column[j] = Math.max(column[j], record[k]); // 更新j行的最大dp值
            }
        }
        return res;
    }

    /**
     * @param nums
     * @return
     */
    public int countBeautifulPairs(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int b = nums[i] % 10;
            for (int j = 0; j < i; j++) {
                int a = nums[j];
                while (a >= 10) {
                    a /= 10;
                }
                if (gcd(a, b) == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * p2786 访问数组中的位置使分数最大-动态规划
     *
     * @param nums
     * @param x
     * @return
     */
    public long maxScore(int[] nums, int x) {
        long[][] dp = new long[nums.length][2];
        long res = nums[0];
        dp[0][nums[0] % 2] = nums[0];
        dp[0][1 - nums[0] % 2] = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int mod = nums[i] % 2;
            long cur = Math.max(dp[i - 1][mod] + nums[i], dp[i - 1][1 - mod] + nums[i] - x);
            res = Math.max(cur, res);
            dp[i][mod] = Math.max(dp[i - 1][mod], cur);
            dp[i][1 - mod] = dp[i - 1][1 - mod];
        }
        return res;
    }

    /**
     * 数组的最大美丽值，解法1「排序」
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, j = 0, res = 0;
        for (; i < nums.length; i++) {
            while (j < nums.length && nums[j] - nums[i] <= 2 * k) {
                j++;
            }
            res = Math.max(res, j - i);
            if (j == nums.length) break;
        }
        return res;
    }

    /**
     * 数组的最大美丽值，解法2「差分数组」
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximumBeauty2(int[] nums, int k) {
        int max = 0, res = 0, count = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] diff = new int[max + 2 * k + 2];
        for (int num : nums) {
            diff[num]++;
            diff[num + 2 * k + 1]--;
        }
        for (int d : diff) {
            count += d;
            res = Math.max(res, count);
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {85, 12};
        System.out.println(maxScore(nums, 79));
    }

}
