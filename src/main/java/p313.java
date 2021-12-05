import org.junit.Test;

import java.util.Arrays;

/**
 * @author Javen
 * @create 2021-08-09
 * @Description
 */

public class p313 {

    @Test
    public void test() {
        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
    }

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
