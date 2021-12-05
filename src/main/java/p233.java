import org.junit.Test;

/**
 * @author Javen
 * @create 2021-08-13
 * @Description
 */
public class p233 {

    @Test
    public void test() {
        System.out.println(countDigitOne(43));
    }

    public int countDigitOne(int n) {
        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 10 == 1) dp[i] = dp[i / 10] + 1;
            else dp[i] = dp[i / 10];
            res += dp[i];
        }
        return res;
    }

    public int count(int n) {
        int res = 0;
        while (n > 0) {
            if (n % 10 == 1) res++;
            n /= 10;
        }
        return res;
    }
}
