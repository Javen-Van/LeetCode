package swordToOffer;

/**
 * @author Javen
 * @create 2022-01-13
 * @Description 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为
 *              k[0],k[1]...k[m - 1] 。
 *              请问 k[0]*k[1]*...*k[m - 1]
 *              可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *              答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
public class offer14 {
    public int cuttingRope(int n) {
        int MOD = 1000000007;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j) % MOD);
            }
        }
        return dp[n];
    }

}
