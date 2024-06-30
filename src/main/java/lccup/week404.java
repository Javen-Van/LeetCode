package lccup;

import org.junit.Test;

public class week404 {

    public int maxHeightOfTriangle(int red, int blue) {
        // red first
        return (int) Math.max(Math.min(2 * Math.floor(Math.sqrt(red)) - 1, 2 * Math.floor((Math.sqrt(1 + 4 * blue) - 1) / 2)), Math.min(2 * Math.floor(Math.sqrt(blue)) - 1, 2 * Math.floor((Math.sqrt(1 + 4 * red) - 1) / 2))) + 1;
    }

    public int maximumLength(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][2][2];
        for (int i = 1; i <= n; i++) {
            int mod = nums[i - 1] % 2;
            dp[i][0][1 - mod] = dp[i - 1][0][1 - mod];
            dp[i][1][1 - mod] = dp[i - 1][1][1 - mod];
            dp[i][0][mod] = dp[i - 1][0][mod] + 1;
            dp[i][1][mod] = dp[i - 1][1][1 - mod] + 1;
        }
        int res = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                res = Math.max(res, dp[n][i][j]);
            }
        }
        return res;
    }

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][][] dp = new int[n + 1][k][k];
        for (int i = 1; i <= n; i++) {
            int mod = nums[i - 1] % k;
            for (int j = 0; j < k; j++) {
                for (int l = 0; l < k; l++) {
                    if (l == mod) {
                        dp[i][j][l] = dp[i - 1][j][(j - l + k) % k] + 1;
                    } else {
                        dp[i][j][l] = dp[i - 1][j][l];
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                res = Math.max(res, dp[n][i][j]);
            }
        }
        return res;
    }

    public int maximumLength2(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[k][k];
        for (int i = 1; i <= n; i++) {
            int mod = nums[i - 1] % k;
            for (int j = 0; j < k; j++) {
                dp[j][mod] = dp[j][(j - mod + k) % k] + 1;
            }
        }
        int res = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {58130, 25433, 62730, 85110, 27398, 56043, 37963, 89066, 3910, 92445, 88436, 43402, 88708, 95939, 84686, 31105, 52944, 52425, 5832, 99579, 56247, 33253, 27076, 94111, 574, 51751, 3278, 93517, 18591, 41661, 9915, 23613, 95511, 44218, 1503, 37390, 85807, 21403, 99303, 11878, 49656, 36966, 74717, 9504, 40661, 77028, 86834, 86534, 60952, 42649, 20372, 59847, 86018, 49740, 22743, 64980, 24977, 53109, 20271, 3366, 17442, 27400, 63286, 45438, 52241, 79946, 54825, 36855, 22883, 17059, 9087, 72217, 88766, 32841, 2003, 41592, 55943, 99893, 5697, 98746, 34210, 60470, 57854, 6937, 97070, 94892, 74641, 40321, 12729, 73595, 64134, 45857, 38778, 153, 89437, 56203, 74049, 13728, 96497, 21117};
        System.out.println(maximumLength2(nums, 835));
        System.out.println(maxHeightOfTriangle(10, 10));
    }

}
