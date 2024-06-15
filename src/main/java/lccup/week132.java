package lccup;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class week132 {

    public String clearDigits(String s) {
        Deque<Character> characters = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                characters.pop();
            } else {
                characters.push(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!characters.isEmpty()) {
            sb.append(characters.pollFirst());
        }
        return sb.toString();
    }

    public int findWinningPlayer(int[] skills, int k) {
        int idx = 0, max = skills[0], count = 0;
        for (int i = 1; i < skills.length; i++) {
            if (skills[i] > max) {
                count = 1;
                max = skills[i];
                idx = i;
            } else {
                count++;
            }
            if (count == k) {
                return idx;
            }
        }
        return idx;
    }

    public int maximumLength(int[] nums, int k) {
        int n = nums.length, res = 0;
        // dp[i][k]表示以nums[i]为结尾的子序列的不超过k个好序列的数量
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < j; l++) {
                    if (nums[j] == nums[l]) {
                        dp[j][i] = Math.max(dp[j][i], dp[l][i] + 1);
                    } else if (i > 0) {
                        dp[j][i] = Math.max(dp[j][i], dp[l][i - 1] + 1);
                    }
                }
                dp[j][i] = Math.max(dp[j][i], 1);
                res = Math.max(res, dp[j][i]);
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 1, 1, 3};
        System.out.println(maximumLength(nums, 2));
    }
}
