package dailyCode;

import org.junit.Test;

public class p2700 {

    /**
     * p2786 访问数组中的位置使分数最大
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

    @Test
    public void test() {
        int[] nums = {85, 12};
        System.out.println(maxScore(nums, 79));
    }

}
