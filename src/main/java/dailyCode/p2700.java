package dailyCode;

import org.junit.Test;

import java.util.Arrays;

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
