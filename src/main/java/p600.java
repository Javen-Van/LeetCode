/**
 * @author Javen
 * @create 2021-12-08
 * @Description
 */
public class p600 {

    // p689
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n - k + 1];
        int[] res = new int[3];
        for (int i = 0; i < k; i++) {
            pre[0] += nums[i];
        }
        for (int i = 1; i <= n - k; i++) {
            pre[i] = pre[i - 1] - nums[i - 1] + nums[i + k - 1];
        }
        int[] left = new int[n - 3 * k + 1];
        int[] right = new int[n - 3 * k + 1];
        int maxL = 0, maxR = 0;
        for (int i = k; i <= n - 2 * k; i++) {
            left[i - k] = Math.max(maxL, pre[i - k]);
        }
        for (int i = n - 2 * k; i >= k; i--) {
            right[i - k] = Math.max(maxR, pre[i + k]);
        }
        int max = 0;
        for (int i = k; i <= n-2*k; i++) {
            if (left[i - k] + right[i - k] + pre[i] > max) {

            }
        }
    }
}
