package lccup;

import java.util.Arrays;

public class week403 {

    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE, l = 0, r = nums.length - 1;
        while (l < r) {
            min = Math.min(nums[l++] + nums[r--], min);
        }
        return min / 2.0;
    }

    public int minimumArea(int[][] grid) {
        int l = Integer.MAX_VALUE, r = -1, u = Integer.MAX_VALUE, b = -1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    l = Math.min(l, j);
                    r = Math.max(r, j);
                    u = Math.min(u, i);
                    b = Math.max(b, i);
                }
            }
        }
        return (r - l + 1) * (b - u + 1);
    }

    public long maximumTotalCost(int[] nums) {
        int n = nums.length;
        long[] dp = new long[n + 1], preFix = new long[n + 1];
        long flag = 1L;
        for (int i = 0; i < n; i++) {
            preFix[i + 1] = preFix[i] + flag * nums[i];
            flag *= -1;
        }
        for (int i = 1; i <= n; i++) {
            long max = Long.MIN_VALUE;
            for (int j = 1; j <= Math.min(2, i); j++) {
                max = Math.max(max, dp[i - j] + cost(preFix, i - j, i));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    private long cost(long[] preFix, int i, int j) {
        return (preFix[j] - preFix[i]) * (i % 2 == 0 ? 1 : -1);
    }
}
