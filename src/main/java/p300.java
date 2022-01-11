import java.util.Arrays;
import java.util.Random;

public class p300 {

    // p306 累加数
    public boolean isAdditiveNumber(String num) {
        return false;
    }

    // p313 超级丑数「动态规划」
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

    // p397 整数替换
    public int integerReplacement(int n) {
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                res++;
                n /= 2;
            } else if (n % 4 == 1) {
                res += 2;
                n /= 2;
            } else {
                if (n == 3) {
                    res = 2;
                    n = 1;
                } else {
                    res += 2;
                    n = n / 2 + 1;
                }
            }
        }
        return res;
    }

}
