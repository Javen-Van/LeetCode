package dailyCode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2021-12-08
 * @Description
 */
public class p600 {

    // p630 课程表「贪心 + 优先队列」
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1); // Java中默认是小根堆
        int sum = 0;
        for (int[] course : courses) {
            if (sum + course[0] <= course[1]) {
                queue.offer(course[0]);
                sum += course[0];
            } else if (!queue.isEmpty() && queue.peek() > course[0]) {
                sum -= queue.poll() - course[0];
                queue.offer(course[0]);
            }
        }
        return queue.size();
    }

    // p686 重复叠加字符串匹配「KMP算法」
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        if (n == 0) return 0;
        int res = 0;
        for (int i = 0; i < m; i++) {
            if (a.charAt(i) == b.charAt(0)) {
                int temp = 1, l = i, j = 0;
                while (j < n && a.charAt(l) == b.charAt(j)) {
                    j++;
                    l++;
                    if (l == m && j != n) {
                        l = 0;
                        temp++;
                    }
                }
                if (j == n) {
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }

    // p689 三个无重叠子数组的最大和「滑动窗口」，也可以用DP？
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int sum1 = 0, idx1 = 0, maxSum1 = 0, n = nums.length;
        int sum2 = 0, idx2 = 0, maxSum2 = 0;
        int sum3 = 0, idx1Sum2 = 0, maxSum3 = 0;
        int[] res = new int[3];
        for (int i = 2 * k; i < n; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= 3 * k - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    idx1 = i - 3 * k + 1;
                }
                if (sum2 + maxSum1 > maxSum2) {
                    maxSum2 = sum2 + maxSum1;
                    idx1Sum2 = idx1; // 记录2个无重叠子数组的最大和的答案
                    idx2 = i - 2 * k + 1;
                }
                if (sum3 + maxSum2 > maxSum3) {
                    maxSum3 = sum3 + maxSum2;
                    res[0] = idx1Sum2;
                    res[1] = idx2;
                    res[2] = i - k + 1;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return res;
    }

    // p691 贴纸拼词「状态压缩dp+记忆化搜索」
    public int minStickers(String[] stickers, String target) {
        int n = target.length(), mask = 1 << n;
        int[] dp = new int[mask];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int res = dfs(stickers, target, dp, mask - 1);
        return res <= n ? res : -1;
    }

    public int dfs(String[] stickers, String target, int[] dp, int mask) {
        int n = target.length();
        if (dp[mask] < 0) {
            int res = n + 1;
            for (String sticker : stickers) {
                int left = mask;
                int[] count = new int[26];
                for (int i = 0; i < sticker.length(); i++) {
                    count[sticker.charAt(i) - 'a']++;
                }
                for (int i = 0; i < n; i++) {
                    int index = target.charAt(i) - 'a';
                    if (((mask >> i) & 1) == 1 && count[index] > 0) {
                        count[index]--;
                        left ^= 1 << i;
                    }
                }
                if (left < mask) res = Math.min(res, dfs(stickers, target, dp, left) + 1);
            }
            dp[mask] = res;
        }
        return dp[mask];
    }
}
