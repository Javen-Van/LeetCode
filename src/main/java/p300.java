import java.util.Arrays;

public class p300 {

    int MOD = 1337;

    // p372 超级次方
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    public int dfs(int a, int[] b, int i) {
        if (i == -1) return 1;
        return pow(dfs(a, b, i - 1), 10) * pow(a, b[i]) % MOD;
    }

    public int pow(int a, int n) {
        int res = 1;
        a %= MOD;
        while (n > 0) {
            res = (res * a) % MOD;
            n--;
        }
        return res;
    }

    // p313
    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] dp = new int[n + 1];
        int[] point = new int[len];
        Arrays.fill(point, 1);
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE, index = 0;
            for (int j = 0; j < len; j++) {
                min = Math.min(min, dp[point[j]] * primes[j]);
            }
            for (int j = 0; j < len; j++) {
                if (dp[point[j]] * primes[j] == min) point[j]++;
            }
            dp[i] = min;
        }
        return dp[n];
    }

}
