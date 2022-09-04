package lccup;

import java.util.Arrays;

import org.junit.Test;

public class week309 {

    // Q1
    public boolean checkDistances(String s, int[] distance) {
        int[] index = new int[26];
        Arrays.fill(index, -1);
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (index[c] != -1) {
                if (i - index[c] - 1 != distance[c])
                    return false;
            } else
                index[c] = i;
        }
        return true;
    }

    // Q2
    public int numberOfWays(int startPos, int endPos, int k) {
        int n = Math.abs(endPos - startPos), MOD = 1000000007;
        if (k < n)
            return 0;
        int[][] dp = new int[k + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0)
                    dp[j][i] = (2 * dp[j + 1][i - 1]) % MOD;
                else if (j == k)
                    dp[j][i] = dp[j - 1][i - 1] % MOD;
                else
                    dp[j][i] = (dp[j - 1][i - 1] + dp[j + 1][i - 1]) % MOD;
            }
        }
        return dp[n][k];
    }

    @Test
    public void test() {
        longestNiceSubarray(new int[] { 1, 3, 8, 48, 10 });
    }

    public static void main(String[] args) {
        week309 x = new week309();
        x.longestNiceSubarray(new int[] { 1, 3, 8, 48, 10 });
    }

    // Q3
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length, res = 1;
        int[][] bits = new int[n + 1][32];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 32; j++) {
                bits[i][j] = bits[i - 1][j] + ((nums[i - 1] >> j) & 1);
            }
        }
        for (int i = 1, start = 0; i <= n; i++) {
            while (start < i) {
                boolean flag = true;
                for (int j = 0; j < 32; j++) {
                    if (bits[i][j] - bits[start][j] > 1) {
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    start++;
                else
                    break;
            }
            res = Math.max(res, i - start);
        }
        return res;
    }

    // Q4
    public int mostBooked(int n, int[][] meetings) {
        long[] cur = new long[n];
        int[] count = new int[n];
        Arrays.sort(meetings, (o1, o2) -> o1[0] - o2[0]);
        for (int[] meeting : meetings) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (cur[i] <= meeting[0]) {
                    count[i]++;
                    cur[i] = meeting[1];
                    flag = true;
                    break;
                }
            }
            if (flag)
                continue;
            int k = 0;
            for (int i = 1; i < n; i++) {
                if (cur[i] < cur[k])
                    k = i;
            }
            count[k]++;
            cur[k] += meeting[1] - meeting[0];
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[res])
                res = i;
        }
        return res;
    }

}
